<template>
    <div :class="`flex items-start gap-2.5 ${message.sender ==='user' ? 'flex-row-reverse' : ''}`">
      <img class="w-8 h-8 rounded-full" src="@/assets/avatarImages/robot.png" alt="Avatar image">
      <div class="flex flex-col gap-1 w-full max-w-[320px]">
        <div :class="`flex items-center space-x-2 ${message.sender ==='user' ? 'flex-row-reverse space-x-reverse' : ''}`">
          <span class="text-sm font-semibold text-gray-900 dark:text-white">{{ name }}</span>
          <!-- <span class="text-sm font-normal text-gray-500 dark:text-gray-400">{{ time }}</span> -->
        </div>
        <div :class="`flex flex-col leading-1.5 p-4 border-gray-200 dark:bg-gray-700 
        ${message.sender === 'user' ? 'bg-lime-200 rounded-b-xl rounded-tl-xl' : 'bg-gray-200 rounded-b-xl rounded-tr-xl'}`">
            <!-- Loading animation -->
            <LoadingEffect v-if="message.sender === 'bot' && isLoading" />
            <div v-else>
                <!-- Message content -->
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
            </div>
        </div>
      </div>
    </div>
  </template>

<script setup lang='ts'>
import {onMounted, ref} from 'vue'
import { useChatStore } from '@/stores/chat';
import LoadingEffect from '@/components/LoadingEffect.vue';

let name = ref()
const chatStore = useChatStore()
const props = defineProps(['message'])
let isLoading = ref(false)  
let optionClicked = ref(false)
onMounted(()=>{
    // Set the name
    name.value = props.message.sender === 'user' ? 'You' : 'Bot'

    // If the sender is bot, set one second timeout before stoping loading animation
    if(props.message.sender === 'bot'){
        isLoading.value = true
        setTimeout(()=>{
            isLoading.value = false
        }, 1000)
    }
})

function handleOptionClick(option:string){
  // Set button to clicked
  optionClicked.value = true
  // Handle option clicked and send message to the chatbot
  chatStore.handleSendMessage(option)
}


</script>

<style scoped>
    
</style>