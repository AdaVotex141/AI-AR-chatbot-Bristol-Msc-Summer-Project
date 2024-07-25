<template>
    <div class="dashboard">
        <el-container class="dashboard-container">
            <RoutineStatistic ref="ref2" class="routine-part" />
            <div class="bottom-container">
                <UserRandomTask ref="ref1" class="randomtask" />
                <TreeStatistic ref="ref3" class="treestatistic" />
            </div>
        </el-container>

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
import { ref } from 'vue';

const userInfoStore = useUserInfoStore()
const ref1 = ref<ButtonInstance>()
const ref2 = ref<ButtonInstance>()
const ref3 = ref<ButtonInstance>()

</script>
<style scoped>
.dashboard{
    margin:0 auto;
    margin-top: 2.5rem;
    width: 100vw;
    height: 100vh;
}

.dashboard-container {
    flex-direction: column;    
}

.routine-part {
    flex: 1; 
    background-color: lightblue;
}

.bottom-container {
    display: grid;
    flex: 1; 
    grid-template-columns: 1fr 1fr;
}

.randomtask {
    background-color: lightcoral;
}

.treestatistic {
    background-color: cyan;
}
</style>