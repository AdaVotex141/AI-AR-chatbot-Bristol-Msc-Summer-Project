<template>
  <div class="chat-container">
    <div class="messages">
      <MessageList :messages="messages" @optionClicked="handleOptionClick" />
    </div>
    <div class="input">
      <MessageInput @sendMessage="handleSendMessage" />
    </div>
  </div>
</template>

<script setup>
import MessageList from '@/components/MessageList.vue';
import MessageInput from '@/components/MessageInput.vue';
import axios from 'axios';
import { useChatStore } from '@/stores/chat';

const chatStore = useChatStore()
const messages = chatStore.messages;

async function handleSendMessage(message){
  // TODO: where should I put this function in?
  chatStore.setIsInitialWindowToFalse()
  
  console.log(chatStore.isInitialWindow)
  // Show the message on the window
  chatStore.addMessage({ text: message, sender: 'user', type: 'text'});

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
      chatStore.addMessage({text: 'Server did not give response, please try again.', sender: 'bot', type: 'text'})
    }
  } catch (error){
    chatStore.addMessage({text: 'Bad things happened, please try again.', sender: 'bot', type: 'text'})
  }
};

function handleResponseData(data){
  if(data && Array.isArray(data.responseSectionList)){
    data.responseSectionList.forEach((item) => {
      if(item.responseType === 'text'){
        chatStore.addMessage({text: item.text, sender: 'bot', type: 'text'})
      } else if (item.responseType === 'option'){
        chatStore.addMessage({options: item.labels, sender: 'bot', type: "options"})
      }
    })
  } else {
    chatStore.addMessage({text: 'wtf idk', sender: 'bot', type: 'text'})
  }
}

function handleOptionClick(option){
  handleSendMessage(option)
}

</script>

<style scoped>
.chat-container {
  margin-top: 2.5rem;
  width: 100vw;
  height: 90vh;
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
