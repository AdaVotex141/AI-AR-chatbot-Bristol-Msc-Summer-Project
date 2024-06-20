<template>
    <div id="app">
      <h2 class="title">Glife</h2>
      <div class="navigate">
        <RouterLink to="/chatwindow">Chat</RouterLink>
        <RouterLink to="/dayroutine">DayRoutine</RouterLink>
        <RouterLink to="/artree">ArTree</RouterLink>
      </div>
      <div class="main-content">
        <RouterView></RouterView>
      </div>
    </div>
  </template>
  
  <script setup lang="ts" name="App">
    import { RouterView } from 'vue-router';
    
  
  </script>