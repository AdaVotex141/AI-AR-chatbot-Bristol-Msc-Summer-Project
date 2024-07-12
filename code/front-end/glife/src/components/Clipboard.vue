<template>
  <div>
    <button @click="copyToClipboard">Copy Text</button>
  </div>
</template>

<script setup>
import ClipboardJS from 'clipboard';
import { ref } from 'vue';

const longitude = ref(0);
const latitude = ref(0); 


onMounted(async () => {
  await fetchCoordinates(); 
});

//put it onto 
const fetchCoordinates = async () => {
  await new Promise(resolve => setTimeout(resolve, 1000)); 
  longitude.value = 123.456;
  latitude.value = 78.901; 
};


const clipboard = new ClipboardJS('button', {
  text: () => `Hi! I'm using Glife and have planted an ARtree at ${longitude.value}, ${latitude.value}. Join us now and let's protect the environment together!`
});


clipboard.on('success', (e) => {
  console.log('Copied:', e.text);
  e.clearSelection();
});


clipboard.on('error', (e) => {
  console.error('Copy failed:', e.trigger);
});


onUnmounted(() => {
  clipboard.destroy();
});


const copyToClipboard = () => {
  clipboard.onClick(); 
};
</script>

<style scoped>

</style>
