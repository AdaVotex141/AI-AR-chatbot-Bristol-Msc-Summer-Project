<template>
  <div class="message-input">
    <input
      v-model="message"
      @keydown.enter="sendMessage"
      placeholder="Type a message"
    />
    <button @click="sendMessage">Send</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useChatStore } from '@/stores/chat';
const chatStore = useChatStore()

const message = ref('');

const sendMessage = () => {
  if (message.value.trim()) {
    chatStore.handleSendMessage(message.value)
    message.value = '';  
  }
};

</script>

<style scoped>
.message-input {
  display: flex;
  padding: 10px;
  background-color: #fff;
}

.message-input input {
  flex: 1;
  padding: 10px;
  margin-right: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.message-input button {
  padding: 10px 20px;
  background-color: #9cb470;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.message-input button:hover {
  background-color: #738352;
}
</style>
