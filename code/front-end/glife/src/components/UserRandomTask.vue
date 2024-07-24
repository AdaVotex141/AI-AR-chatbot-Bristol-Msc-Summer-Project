<template>
    <div>
        <el-container>
            <el-header>Random Task</el-header>
            <el-main>
                <span> {{ userTaskStore.taskContent }} </span>
                <span class="inline-flex items-center justify-center w-4 h-4 ms-2 text-xs font-semibold text-blue-800 bg-blue-200 rounded-full">
                    {{ userTaskStore.numberOfTasks }}
                </span>
                <el-button>Add it</el-button>
                <el-button>Choose another one</el-button>
            </el-main>
        </el-container>
    </div>
</template>
<script setup lang="ts">
import { useUserTaskStore } from '@/stores/usertask';
import { onMounted } from 'vue';

const userTaskStore = useUserTaskStore()

onMounted(()=>{
    userTaskStore.getNumberOfTask()
    userTaskStore.getRandomTask()
})
</script>

<style scoped>
    
</style>