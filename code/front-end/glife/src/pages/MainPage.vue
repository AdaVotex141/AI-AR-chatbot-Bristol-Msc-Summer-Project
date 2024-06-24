<template>
    <div id="app">
      <h2 class="title">Glife</h2>
      <div class="navigate">
        <RouterLink :to="{name:'chatwindow'}">Chat</RouterLink>
        <RouterLink :to="{name:'dayroutine'}">DayRoutine</RouterLink>
        <RouterLink :to="{name:'artree'}">ArTree</RouterLink>
      </div>
      <div class="main-content">
        <RouterView></RouterView>
      </div>
    </div>
  </template>
  
  <script setup lang="ts" name="App">
    import { RouterView } from 'vue-router';
    
  
  </script>