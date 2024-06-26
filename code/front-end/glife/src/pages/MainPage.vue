<template>
    <div class="common-layout">
      <el-container>
        <el-header class="title">Glife</el-header>
        <el-main class="navigate">
          <RouterLink :to="{name:'chatwindow'}">Chat</RouterLink>
          <RouterLink :to="{name:'dayroutine'}">DayRoutine</RouterLink>
          <RouterLink :to="{name:'artree'}">ArTree</RouterLink>
        </el-main>
        <el-main class="main-content">
          <RouterView></RouterView>
        </el-main>
      </el-container>
    </div>
  </template>
  
  <script setup lang="ts" name="App">
    import { RouterView } from 'vue-router';
    
  
  </script>

<style scoped>
.navigate{
  font-family: 'Cooper Black',sans-serif;
}
.main-content{
  background: white;
  border: 1px solid #ddd;
  border-radius: 16px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
</style>