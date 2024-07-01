<template>
  <div class="chat-container">
    <div class="messages">
      <MessageList :messages="messages" />
    </div>
    <div class="input">
      <MessageInput @sendMessage="handleSendMessage" />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import MessageList from '@/components/MessageList.vue';
import MessageInput from '@/components/MessageInput.vue';
import axios from 'axios';

const messages = ref([]);

async function handleSendMessage(message){
  // Show the message on the window
  messages.value.push({ text: message, sender: 'user' });

  // Send api request to the backend
  try{
    const response = axios.get('/api/assistant/input', {
      inputmessage: message.value
    })
    console.log(response.data)

  } catch (error){
    console.error(error)
  }
  
  messages.value.push({ text: 'This is a bot response', sender: 'bot' });
};

</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  width: 300px;
  height: 400px;
}

.messages {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background-color: #f5f5f5;
}

.input {
  padding: 10px;
  background-color: #fff;
  border-top: 1px solid #ddd;
}
</style>
