<template>
    <div >
        <el-space class="container">
            <UserRandomTask ref="ref1" />
            <RoutineStatistic ref="ref2" class="routine-part" />
        </el-space>

        <el-tour v-model="open">
            <el-tour-step :target="ref1?.$el" title="Random Tasks">
            <div>Here, you can add random tasks to your routine!</div>
            </el-tour-step>
            <el-tour-step
            :target="ref2?.$el"
            title="Routine Information"
            description="Here you can review the progress and completion status of your routine."
            />
        </el-tour>
    </div>
</template>
<script setup lang="ts">
import RoutineStatistic from '@/components/RoutineStatistic.vue';
import UserRandomTask from '@/components/UserRandomTask.vue';
import { MoreFilled } from '@element-plus/icons-vue'
import type { ButtonInstance } from 'element-plus'
import { onMounted, ref } from 'vue';

const ref1 = ref<ButtonInstance>()
const ref2 = ref<ButtonInstance>()
const open = ref(false)

onMounted(()=>{
    open.value = true
})

</script>
<style scoped>
.container {
    display: flex;
    flex-direction: column; 
    width: 100%;
    height: 100%;
}

.routine-part {
    display: flex;
    height: 40vh; 
    width: 80vw; 
    border: 1px solid #000; 
}
</style>