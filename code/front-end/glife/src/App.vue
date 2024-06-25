<template>
  <div id="app">
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts" name="App">
  import { RouterView } from 'vue-router';
</script>

<style>
html, body, #app {
  height: 100%;
  margin: 0;
  display: flex;
  flex-direction: column;
  background-color: #e8e8e8;
  justify-content: center;
  align-items: center;
}

#app {
  flex: 1;
  display: flex;
  flex-direction: column;
}
</style>
