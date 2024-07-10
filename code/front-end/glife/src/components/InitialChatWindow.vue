<template>
    <div>
        <el-space direction="vertical">
            <el-text size="large">{{ introductionMsg }}</el-text>
            <el-button v-for="message in messages" @click="chatStore.handleSendMessage(message)">{{ message }}</el-button>
        </el-space>
    </div>
</template>

<script setup lang='ts'>
import { useChatStore } from '@/stores/chat';
import { reactive, ref } from 'vue'
const introductionMsg = ref('Hi! I’m a virtual assistant. How can I help you today?')
const messages = reactive([
    'Tell me about the Tutorial.',
    'Tell me about the AR forest.',
    'Hi, bot.'
])
const chatStore = useChatStore()

</script>
<style scoped>
    
</style>