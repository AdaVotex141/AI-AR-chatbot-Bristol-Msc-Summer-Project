<template>
    <div class colorMan>
        <el-space class="container">
            <RoutineStatistic ref="ref2" class="routine-part" />
            <UserRandomTask ref="ref1" />
            <TreeStatistic ref="ref3" />
        </el-space>

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
.container {
    padding-top: 4rem;
    flex-direction: column; 
    width: 100%;
    height: 100%;
    margin: 0 auto;
}

.routine-part {
    display:position;
    top:10%;
    height: 40vh; 
    width: 100vw; 
}
</style>