<script setup lang="ts">

</script>

<template>
  <div id="app">
    <ChatWindow />
    <DayRoutine />
  </div>
</template>

<script>
import ChatWindow from './components/ChatWindow.vue';
import DayRoutine from './components/DayRoutine.vue';

export default {
  name: 'App',
  components: {
    ChatWindow,
    DayRoutine
  }
};
</script>

<style>
html, body, #app {
  height: 100%;
  margin: 0;
  display: flex;
  flex-direction: column;
}

#app {
  flex: 1;
  display: flex;
  flex-direction: column;
}
</style>
