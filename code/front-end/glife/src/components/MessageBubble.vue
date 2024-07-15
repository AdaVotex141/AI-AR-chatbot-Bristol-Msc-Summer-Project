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
                <MessageContent :message="message" />
            </div>
        </div>
      </div>
    </div>
  </template>

<script setup lang='ts'>
import {onMounted, ref} from 'vue'
import { useChatStore } from '@/stores/chat';
import LoadingEffect from '@/components/LoadingEffect.vue';
import MessageContent from '@/components/MessageContent.vue';

let name = ref()
const chatStore = useChatStore()
const props = defineProps(['message'])
let isLoading = ref(false)  

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


</script>

<style scoped>
    
</style>