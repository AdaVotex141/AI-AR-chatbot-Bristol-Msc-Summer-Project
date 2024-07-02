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
    const response = await axios.post('/api/assistant/input', {
      inputMessage: message
    })
    const data = response.data
    console.log(data.data)
    if(String(data.code) === '1'){
      handleResponseData(data.data)  
    } else {
      messages.value.push({text: 'Server did not give response, please try again.', sender: 'bot'})
    }
  } catch (error){
    messages.value.push({text: 'Bad things happened, please try again.', sender: 'bot'})
  }
};

function handleResponseData(data){
  if(data && Array.isArray(data.responseSectionList)){
    data.responseSectionList.forEach((item) => {
      if(item.responseType === 'text'){
        messages.value.push({text: item.text, sender: 'bot'})
      } else if (item.responseType === 'option'){
        let result = ''
        item.labels.forEach((label) => {
          result += label
          result += ' | '
        })
        messages.value.push({text: result, sender: 'bot'})
      }
    })
  } else {
    messages.value.push({text: 'wtf idk', sender: 'bot'})
  }
}

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
