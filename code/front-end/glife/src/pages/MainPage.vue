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
.title, .navigate{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
}
.title{
  position: sticky;
  top:0;
  z-index: 101;
  text-align: center;
  height:4rem;
  font-size: 3rem;
  color: darkolivegreen;
  background-color: #edeeef;

}
.navigate{
  position: sticky;
  top:7%;
  z-index: 100;
  text-align: center;
  background-color:  #edeeef;
}
.main-content{
  height: 79vh;
  border: 1px solid #ddd;
  border-radius: 1rem;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
</style>