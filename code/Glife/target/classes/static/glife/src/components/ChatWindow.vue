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

<script>
import { ref } from 'vue';
import MessageList from './MessageList.vue';
import MessageInput from './MessageInput.vue';

export default {
  components: {
    MessageList,
    MessageInput
  },
  setup() {
    const messages = ref([]);

    const handleSendMessage = (message) => {
      messages.value.push({ text: message, sender: 'user' });

      setTimeout(() => {
        messages.value.push({ text: 'This is a bot response', sender: 'bot' });
      }, 1000);
    };

    return {
      messages,
      handleSendMessage
    };
  }
};
</script>

<style>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 60vh;
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
