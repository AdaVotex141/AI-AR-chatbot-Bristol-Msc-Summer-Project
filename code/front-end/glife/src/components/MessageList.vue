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
  position: sticky;
  bottom: 0;
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background-color: #f5f5f5;
}

.message {
  margin: 10px 0;
  padding: 10px;
  border-radius: 4px;
}

.message.user {
  left: 0;
  background-color: #dcf8c6;
  width:100%;
  align-self: flex-end;
}

.message.bot {
  right: 0;
  align-self: flex-start;
  background-color: #f1f0f0;
  width:100%;
}
</style>
