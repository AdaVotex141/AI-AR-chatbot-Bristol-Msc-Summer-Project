<template>
  <div class="message-input">
    <input v-model="message" @keydown.enter="sendMessage" placeholder="Type a message" />
    <button @click="sendMessage">Send</button>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  emits: ['sendMessage'],
  setup(_, { emit }) {
    const message = ref('');

    const sendMessage = () => {
      if (message.value.trim()) {
        emit('sendMessage', message.value);
        message.value = '';
      }
    };

    return {
      message,
      sendMessage
    };
  }
};
</script>

<style>
.message-input {
  display: flex;
  padding: 10px;
  border-top: 1px solid #ddd;
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
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.message-input button:hover {
  background-color: #0056b3;
}
</style>
