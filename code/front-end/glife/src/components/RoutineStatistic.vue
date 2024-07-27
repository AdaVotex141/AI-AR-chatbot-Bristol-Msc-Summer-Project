<template>
    <el-container>
        <el-header>Your Routine</el-header>
        <el-main>
            <div class="progress-container">
                <div class="progress-item">
                    <el-progress :text-inside="true" :stroke-width="24" :percentage='dailyStat' >
                    <span>{{dailyStat}}%</span>
                    </el-progress>
                    <el-button text>Daily</el-button>
                </div>
                <div class="progress-item">
                    <el-progress
                    :text-inside="true"
                    :stroke-width="24"
                    :percentage="weeklyStat"
                    status="success"
                    >
                    <span>{{weeklyStat}}%</span>
                    </el-progress>
                    <el-button text>Weekly</el-button>
                </div>
                <div class="progress-item">
                    <el-progress
                    :text-inside="true"
                    :stroke-width="24"
                    :percentage="monthlyStat"
                    status="warning"
                    >
                    <span>{{monthlyStat}}%</span>
                    </el-progress>
                    <el-button text>Monthly</el-button>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script setup lang="ts">
import router from '@/router';
import axios from 'axios';
import { onMounted, ref } from 'vue';

const dailyStat = ref(0)
const weeklyStat = ref(0)
const monthlyStat = ref(0)

async function getRoutineStatistic(){
    try{
        const response = await axios.get('/api/stat')
        if(String(response.data.code) === '1'){
            const data = response.data.data
            dailyStat.value = data.daily
            weeklyStat.value = data.weekly
            monthlyStat.value = data.monthly
        }
    } catch (error){
        console.error(error)
        router.replace({name: 'notfound'})
    }
}

onMounted(()=>{
    getRoutineStatistic()
})

</script>
  
<style scoped>

.el-header{
    background-color: #9cb470;
    padding: 1.5rem;
    margin-bottom: 0.5rem;
    font-size: 1.5rem;
    color: whitesmoke;
    font-weight: bold;
    font-family: 'Cooper Black',sans-serif;
    border-bottom: 1px solid #e0e0e0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    top: 5%;
}

span{
    color: black;
}

.el-container{
    background-color: #fff;
    width: auto;
}

.progress-container {
    display: flex;
    margin: 0 auto;
    flex-direction: column; 
    gap: 20px;
    width: auto;
    max-width: 80vw;
}

.progress-item {
    display: flex;
    align-items: center; 
    gap: 10px; 
    max-width: 80vw;
}

.el-progress {
    flex: 1; 
    min-width: 50px;
}

.el-button {
    white-space: nowrap;
    min-width: 8vw; 
}
</style>