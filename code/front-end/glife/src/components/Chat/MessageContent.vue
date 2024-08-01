<template>
    <p v-if="message.type === 'text'"class="text-sm font-normal text-gray-900 dark:text-white">{{ message.text }}</p>
    <div v-else="message.type === 'options'">
        <div class="options" v-for="option in message.options" >
            <button 
            :class="{
                'text-gray-900 bg-gradient-to-r from-lime-200 via-lime-400 to-lime-500 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-lime-300 dark:focus:ring-lime-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2': !optionClicked,
                'font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2 text-gray-900 bg-lime-300 cursor-not-allowed': optionClicked
            }"
            @click="handleOptionClick(option)" 
            :key="option"
            :disabled="optionClicked"
            plain 
            >
            {{ option }}
            </button>
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