<template>
  <div class="message-list" ref="messageList">
    <InitialChatWindow v-if="chatStore.isInitialWindow" @buttonClicked="handleOptionClick"/>
    <div v-else>
      <div v-for="(message, index) in messages" :key="index" :class="['message', message.sender]">
        <div v-if="message.type === 'text'"> {{ message.text }} </div>
        <div v-if="message.type === 'options'">
          <div class="options">
            <el-button v-for="option in message.options" @click="handleOptionClick(option)" type="success" plain >
              {{ option }}
            </el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import InitialChatWindow from './InitialChatWindow.vue';
import { ref, onMounted, onUpdated, watch, nextTick } from 'vue';
import { useChatStore } from '@/stores/chat';

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

.message.user {
  background-color: #dcf8c6;
  max-width:45%;
  align-self: flex-end;
}

.message.bot {
  background-color: #f1f0f0;
  max-width: 45%;
  align-self: flex-start;
}
</style>
