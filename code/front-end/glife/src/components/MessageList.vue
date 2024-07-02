<template>
  <div class="message-list" ref="messageList">
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
  },
  mounted() {
    this.scrollToBottom();
  },
  updated() {
    this.scrollToBottom();
  },
  methods: {
    scrollToBottom() {
      this.$nextTick(() => {
        const messageList = this.$refs.messageList;
        if (messageList) {
          const shouldScroll = messageList.scrollTop + messageList.clientHeight !== messageList.scrollHeight;
          if (shouldScroll) {
            messageList.scrollTop = messageList.scrollHeight;
          }
        }
      });
    }
  },
  watch: {
    messages() {
      this.scrollToBottom();
    }
  }
};
</script>

<style scoped>
.message-list {
  max-height: 60vh;
  flex: 1;
  overflow-y: auto;
  overflow-x: hidden;
  padding: 1.5rem;
  background-color: #f5f5f5;
  display: flex;
  flex-direction: column;
  scroll-behavior: smooth;
}

.message {
  margin: 10px 0;
  padding: 10px;
  border-radius: 4px;
  word-wrap: break-word;
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
