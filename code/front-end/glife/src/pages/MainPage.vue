<template>
    <div class="common-layout">
      <el-container>
        <el-header>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
            background-color="#738352"
            text-color="white"
            active-text-color="black"
        >
          <el-menu-item index="0">
            <div class="title">Glife</div>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="1">Chat</el-menu-item>
          <el-menu-item index="2">Routine</el-menu-item>
          <el-sub-menu index="3">
            <template #title>ARTree</template>
            <el-menu-item index="3-1">Plant VR Tree</el-menu-item>
            <el-menu-item index="3-2">My Badge</el-menu-item>
            <el-menu-item index="3-3">Share VR Tree</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="10">
            <template #title>Profile</template>
            <el-menu-item index="4-1">Setting</el-menu-item>
            <el-menu-item index="4-2">Log out</el-menu-item>
          </el-sub-menu>
        </el-menu>

        </el-header>
        <el-main class="navigate">
          <RouterLink :to="{name:'chatwindow'}" class="navigateElement">Chat</RouterLink>
          <RouterLink :to="{name:'dayroutine'}" class="navigateElement">DayRoutine</RouterLink>
          <RouterLink :to="{name:'artree'}" class="navigateElement">ArTree</RouterLink>
        </el-main>
        <el-main class="main-content">
          <RouterView></RouterView>
        </el-main>
      </el-container>
    </div>

  </template>
  
  <script setup lang="ts" name="App">
    import { RouterView } from 'vue-router';
    import { ref } from 'vue'

    const activeIndex = ref('1')
    const handleSelect = (key: string, keyPath: string[]) => {
      console.log(key, keyPath)
    }

  
  </script>

<style scoped>
.navigateElement{
  margin-right: 20px;
}

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
  color: whitesmoke;
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

.flex-grow {
  flex-grow: 1;
}

.el-menu-demo .el-menu-item,
.el-menu-demo .el-sub-menu {
  font-family: 'Cooper Black',sans-serif;
}

</style>