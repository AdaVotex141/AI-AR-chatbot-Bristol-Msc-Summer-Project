<template>
  <div id="app">
    <h2>Glife</h2>
    <div class="navigate">
      <RouterLink to="/">Chat</RouterLink>
      <RouterLink to="/dayroutine">DayRoutine</RouterLink>
      <RouterLink to="/ArTree">ArTree</RouterLink>
    </div>
    <div class="main-content">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script setup lang="ts" name="App">
  import { RouterView, RouterLink } from 'vue-router';

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
