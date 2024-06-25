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
  }

  #app {
    flex: 1;
    display: flex;
    flex-direction: column;
  }

</style>
