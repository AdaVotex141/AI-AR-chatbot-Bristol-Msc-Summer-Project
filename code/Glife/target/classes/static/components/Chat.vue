<template>
  <div class="chat">
    <div class="chat-container" ref="chatContainer">
      <div v-for="(message, index) in messages" :key="index" class="message">
        <div v-if="message.from === 'user'" class="message-bubble user">{{ message.text }}</div>
        <div v-else class="message-bubble assistant">{{ message.text }}</div>
      </div>
    </div>
    <div class="input-container">
      <input v-model="inputMessage" @keydown.enter="sendMessage" type="text" placeholder="Type your message...">
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Chat',
  data() {
    return {
      inputMessage: '',
      messages: []
    };
  },
  methods: {
    async sendMessage() {
      if (!this.inputMessage.trim()) return;

      this.messages.push({ text: this.inputMessage, from: 'user' });
      this.scrollToBottom();

      try {
        const response = await axios.post('/api/chat/message', this.inputMessage);
        const assistantResponse = response.data;
        this.messages.push({ text: assistantResponse.result.output.generic[0].text, from: 'assistant' });
        this.inputMessage = '';
        this.scrollToBottom();
      } catch (error) {
        console.error('Error sending message:', error);
        // Handle error
      }
    },
    scrollToBottom() {
      this.$nextTick(() => {
        const chatContainer = this.$refs.chatContainer;
        chatContainer.scrollTop = chatContainer.scrollHeight;
      });
    }
  }
};
</script>

<style scoped>
.chat {
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: space-between;
}

.chat-container {
  overflow-y: auto;
  flex: 1;
}

.message {
  display: flex;
  justify-content: flex-end;
  margin: 8px;
}

.message-bubble {
  padding: 10px;
  border-radius: 8px;
  max-width: 70%;
  word-wrap: break-word;
  margin-bottom: 8px;
}

.user {
  background-color: #007bff;
  color: white;
  align-self: flex-end;
}

.assistant {
  background-color: #f0f0f0;
  color: black;
  align-self: flex-start;
}

.input-container {
  display: flex;
  margin-top: 16px;
}

.input-container input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px 0 0 4px;
  font-size: 16px;
}

.input-container button {
  padding: 10px 16px;
  background-color: #007bff;
  color: white;
  border: 1px solid #007bff;
  border-radius: 0 4px 4px 0;
  cursor: pointer;
  font-size: 16px;
}

.input-container button:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}
</style>
