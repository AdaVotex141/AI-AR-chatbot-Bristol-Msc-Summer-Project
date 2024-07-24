<template>
    <div class="container">
        <UserRandomTask />
        <RoutineStatistic class="routine-part" />
    </div>
</template>
<script setup lang="ts">
import RoutineStatistic from '@/components/RoutineStatistic.vue';
import UserRandomTask from '@/components/UserRandomTask.vue';


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