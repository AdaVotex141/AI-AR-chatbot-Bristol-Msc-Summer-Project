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
          <p class="text-sm font-normal text-gray-900 dark:text-white">{{ message.text }}</p>
        </div>
      </div>
    </div>
  </template>

<script setup lang='ts'>
import {onMounted, ref} from 'vue'
let name = ref()

const props = defineProps(['message'])  

onMounted(()=>{
    if(props.message.sender === 'user'){
        name.value = 'You'
    } else {
        name.value = 'Bot'
    }
})


</script>

<style scoped>
    
</style>