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
  width: 80vw;
  height: 50vh;
}

.messages {
  min-height: 65vh;
  display: flex;
  flex: 1;
  padding: 20px;

}

.input {
  padding: 10px;
  background-color: #fff;
  border-top: 1px solid #ddd;
  border-radius: 1rem;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  position: sticky;
  bottom: 0;

}
</style>
