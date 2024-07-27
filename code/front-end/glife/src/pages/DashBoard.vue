<template>
    <div class="dashboard">
        <div class="dashboard-container">
            <RoutineStatistic ref="ref2" class="routine-part" />
            <div class="bottom-container">
                <UserRandomTask ref="ref1" class="randomtask" />
                <TreeStatistic ref="ref3" class="treestatistic" />
            </div>
        </div>

        <el-tour v-model="userInfoStore.tutorialStatement['dashboard']">
            <el-tour-step :target="ref1?.$el" title="Random Tasks">
            <div>Here, you can add random tasks to your routine!</div>
            </el-tour-step>
            <el-tour-step
            :target="ref2?.$el"
            title="Routine Information"
            description="Here you can review the progress and completion status of your routine."
            />
            <el-tour-step
            :target="ref3?.$el"
            title="Tree Information"
            description="Here you can review the status of your trees."
            />
        </el-tour>
    </div>
</template>
<script setup lang="ts">
import RoutineStatistic from '@/components/RoutineStatistic.vue';
import UserRandomTask from '@/components/UserRandomTask.vue';
import TreeStatistic from '@/components/TreeStatistic.vue';
import { useUserInfoStore } from '@/stores/userInfo';
import { MoreFilled } from '@element-plus/icons-vue'
import type { ButtonInstance } from 'element-plus'
import { onMounted, ref } from 'vue';

const userInfoStore = useUserInfoStore()
const ref1 = ref<ButtonInstance>()
const ref2 = ref<ButtonInstance>()
const ref3 = ref<ButtonInstance>()

</script>
<style scoped>
.dashboard{
    margin:0 auto;
    margin-top: 2.0rem;
    width: 80vw;
    height: 100vh;
}

.dashboard-container {
    margin-top: 4rem;
    flex-direction: column;    
}

.routine-part {
    margin-top: 2rem;
    flex: 1; 
    background-color: lightblue;
}

.bottom-container {
    margin-top: 2rem;
    display: grid;
    flex: 1; 
    grid-template-columns: 1fr 1fr;
    gap: 1rem;
}

.randomtask {
    background-color: #a8e6cf;
    min-height: 34vh;
}

.treestatistic {
    background-color: #dcedc1;
    margin-left: 2rem;
    text-align: center;
}
</style>