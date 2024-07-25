<template>
    <div class="container">
        <el-container>
            <el-header>Random Task</el-header>
            <el-main>
                <div class="task-content-container">
                    <span class="task-content"> {{ userTaskStore.stringOfTask }} </span>
                    <span class="task-count" v-show="userTaskStore.numberOfTasks > 0">
                        {{ userTaskStore.numberOfTasks }}
                    </span>
                </div>
                <div class="button-container" v-show="userTaskStore.numberOfTasks > 0">
                    <el-button @click="addTask">Add it</el-button>
                    <el-button @click="changeTask">Don't like it</el-button>
                </div>
            </el-main>
        </el-container>
    </div>
</template>
<script setup lang="ts">
import { useUserTaskStore } from '@/stores/usertask';
import { onMounted } from 'vue';

const userTaskStore = useUserTaskStore()

onMounted(()=>{
    // Get user's random task
    userTaskStore.getNumberOfTask()
    userTaskStore.getRandomTask()
})

async function addTask(){
    userTaskStore.addTaskToRoutine()
    //  give a new task
    userTaskStore.getRandomTask()
    // update number, so that it will update this component when number of task become 0
    userTaskStore.getNumberOfTask()
}

async function changeTask(){
    userTaskStore.throwCurrentTask()
    userTaskStore.getRandomTask()
    // update number, so that it will update this component when number of task become 0
    userTaskStore.getNumberOfTask()
}

</script>

<style scoped>
.container{
    background-color: bisque;
}

.task-container {
    border: 1px solid #ccc;
    padding: 16px;
    border-radius: 8px;
}

.task-content-container {
    position: relative;
    max-width: 100%;
    word-wrap: break-word;
}

.task-content {
    display: block;
    max-width: 100%;
    white-space: pre-wrap;
}

.task-count {
    position: absolute;
    top: 0;
    right: 0;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    width: 1.5rem;
    height: 1.5rem;
    font-size: 0.75rem;
    font-weight: 600;
    color: #1e3a8a;
    background-color: #bfdbfe;
    border-radius: 9999px;
}

.button-container {
    margin-top: 16px;
    display: flex;
    gap: 8px;
}
</style>