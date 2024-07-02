<template>
  <div class="message-list">
    <div v-for="(message, index) in messages" :key="index" :class="['message', message.sender]">
      {{ message.text }}
    </div>
  </div>
</template>

<script>
export default {
  props: {
    messages: {
      type: Array,
      required: true
    }
  }
};
</script>

<style scoped>
.message-list {
  max-height: 60vh;
  flex: 1;
  overflow-y: auto;
  padding: 1.5rem;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.message {
  margin: 10px 0;
  padding: 10px;
  border-radius: 4px;
}

.message.user {
  background-color: #dcf8c6;
  width:65%;
  align-self: flex-end;
}

.message.bot {
  background-color: #f1f0f0;
  width:65%;
  align-self: flex-start;
}
</style>
