<template>
  <div class="chat-container">
    <div class="messages">
      <MessageList />
    </div>
    <div class="input">
      <MessageInput ref="ref1" />
    </div>
    <el-tour v-model="userInfoStore.tutorialStatement['chatwindow']">
      <el-tour-step title="Chat Window" description="You can talk to the chatbot here!" />
      <el-tour-step :target="ref1?.$el" title="Chat Window">
        <div>You can input the content here.</div>
      </el-tour-step>
      <el-tour-step
        :target="ref1?.$el"
        title="Chat Window"
        placement="right"
        description="And you can click here or press 'Enter' to send a message"
      />
      <el-tour-step
        title="Other Actions"
        description="You can also choose one answer you like in the list!"
      />
    </el-tour>
  </div>
</template>

<script setup lang="ts">
import MessageList from '@/components/MessageList.vue';
import MessageInput from '@/components/MessageInput.vue';
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { MoreFilled } from '@element-plus/icons-vue'
import type { ButtonInstance } from 'element-plus'
import { useUserInfoStore } from '@/stores/userInfo';

const userInfoStore = useUserInfoStore()
const ref1 = ref<ButtonInstance>()
// hidden overflow in chat window page
onMounted(() => {
  document.body.style.overflow = 'hidden';
});
onBeforeUnmount(() => {
  document.body.style.overflow = '';
});

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
