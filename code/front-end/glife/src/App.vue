<template>
  <div id="app">
    <RouterView></RouterView>
  </div>
</template>

<script setup lang="ts" name="App">
  import { RouterView } from 'vue-router';
</script>

<style>
html, body {
  display: flex;
  margin: 0;
  padding: 0;
  flex-direction: column;
  background-color: #e8e8e8;
  justify-content: center;
  align-items: center;
  overflow-y: hidden;
}

#app {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>
