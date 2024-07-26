<template>
    <div class="container">
        <div class="title">Random Task</div>
        <div class="main-content">
            <div class="task-content-container">
                <span class="task-content"> {{ userTaskStore.stringOfTask }} </span>
            </div>
            <div class="button-container" v-show="userTaskStore.numberOfTasks > 0">
                <el-button type="primary" @click="addTask">Add it</el-button>
                <el-button type="primary" @click="changeTask">Don't like it</el-button>
            </div>
            <span class="task-count" v-show="userTaskStore.numberOfTasks > 0">
                {{ userTaskStore.numberOfTasks }}
            </span>
        </div>
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
.container {
    background-color: bisque;
    position: relative;
}

.task-content-container {
    margin-top: 1rem;
    max-width: 100%;
    word-wrap: break-word;
    display: flex;
    justify-content: center;
    align-items: center;
}

.task-content {
    display: block;
    max-width: 100%;
    white-space: pre-wrap;
    margin: 0 auto;
    word-break: break-word;
}

.task-count {
    position: absolute;
    top: 16px;
    right: 16px;
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
    gap: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.title {
    background-color: #9cb470;
    margin-bottom: 0.5rem;
    padding: 0.8rem;
    font-size: 1.5rem;
    color: whitesmoke;
    font-weight: bold;
    font-family: 'Cooper Black', sans-serif;
    border-bottom: 1px solid #e0e0e0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
}

.main-content{
    margin: 0 auto;
}
</style>