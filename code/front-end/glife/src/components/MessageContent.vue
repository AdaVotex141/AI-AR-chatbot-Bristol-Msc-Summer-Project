<template>
    <p v-if="message.type === 'text'"class="text-sm font-normal text-gray-900 dark:text-white">{{ message.text }}</p>
        <div v-else="message.type === 'options'">
            <div class="options">
                <el-button v-for="option in message.options" 
                @click="handleOptionClick(option)" 
                :key="option"
                :disabled="optionClicked"
                type="success" plain >
                {{ option }}
                </el-button>
            </div>
        </div>
</template>

<script setup lang='ts'>
import { useChatStore } from '@/stores/chat';
import { ref } from 'vue';

let optionClicked = ref(false)
const chatStore = useChatStore()

defineProps(['message'])

function handleOptionClick(option:string){
  // Set button to clicked
  optionClicked.value = true
  // Handle option clicked and send message to the chatbot
  chatStore.handleSendMessage(option)
}

</script>
<style scoped>
    
</style>