<template>
  <div class="message-list" ref="messageList">
    <InitialChatWindow v-show="chatStore.isInitialWindow" @buttonClicked="handleOptionClick"/>
    <div v-for="(message, index) in messages" :key="index" :class="['message', message.sender]">
      <MessageBubble :message="message" /> 
    </div>
  </div>
</template>

<script setup>
import InitialChatWindow from './InitialChatWindow.vue';
import { ref, onMounted, onUpdated, watch, nextTick } from 'vue';
import { useChatStore } from '@/stores/chat';
import MessageBubble from '@/components/MessageBubble.vue';

const chatStore = useChatStore()

// Define the props
const props = defineProps({
  messages: {
    type: Array,
    required: true
  }
});

const messageList = ref(null);

const scrollToBottom = () => {
  nextTick(() => {
    if (messageList.value) {
      const shouldScroll = messageList.value.scrollTop + messageList.value.clientHeight !== messageList.value.scrollHeight;
      if (shouldScroll) {
        messageList.value.scrollTop = messageList.value.scrollHeight;
      }
    }
  });
};

onMounted(scrollToBottom);
onUpdated(scrollToBottom);

watch(() => props.messages, scrollToBottom);

const emits = defineEmits(['optionClicked'])

function handleOptionClick(option){
  emits('optionClicked', option)
}

</script>

<style scoped>
.message-list {
  height: 75vh;
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

@media(min-width: 600px) {
  .message.user {
    max-width: 60%;
    align-self: flex-end;
  }
  .message.bot {
    max-width: 60%;
    align-self: flex-start;
  }

}
@media(max-width: 600px) {
  .message.user {
    max-width: 100%;
    align-self: flex-end;
  }
  .message.bot {
    max-width: 100%;
    align-self: flex-start;
  }
}


</style>
