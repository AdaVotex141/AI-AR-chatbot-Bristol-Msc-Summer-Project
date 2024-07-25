<template>
    <div>
        {{ message }}
    </div>
</template>
<script setup lang='ts'>
import { ref } from 'vue';

const message = ref('You have already planted 100 trees.')
</script>
<style scoped>
    
</style>