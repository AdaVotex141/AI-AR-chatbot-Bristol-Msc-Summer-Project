<template>
  <div class="chat-container">
    <div class="messages">
      <MessageList />
    </div>
    <div class="input">
      <MessageInput />
    </div>
  </div>
</template>

<script setup>
import MessageList from '@/components/MessageList.vue';
import MessageInput from '@/components/MessageInput.vue';

</script>

<style scoped>
@media(max-width: 600px) {
  .chat-container {
    margin: 0 auto;
    width: 100vw;
    height: 90vh;
  }
}

@media(min-width: 601px) {
  .chat-container {
    margin-top: 2.5rem;
    width: 60vw;
    height: 90vh;
    margin-left: auto;
    margin-right: auto;
  }
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
