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
          <el-menu-item index="1"><RouterLink :to="{name:'chatwindow'}" class="navigateRouter">Chat</RouterLink></el-menu-item>
          <el-menu-item index="2"><RouterLink :to="{name:'dayroutine'}" class="navigateRouter">Routine</RouterLink></el-menu-item>
          <el-sub-menu index="3">
            <template #title>ARTree</template>
            <el-menu-item index="3-1"><RouterLink :to="{name:'artree'}" class="navigateRouter">Plant VR Tree</RouterLink></el-menu-item>
            <el-menu-item index="3-2">My Badge</el-menu-item>
            <el-menu-item index="3-3">Share VR Tree</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="10">
            <template #title>Profile</template>
            <el-menu-item index="4-1"><RouterLink :to="{name:'notfound'}" class="navigateRouter">setting</RouterLink></el-menu-item>
            <el-menu-item index="4-2"><RouterLink :to="{name:'logout'}" class="navigateRouter">Log out</RouterLink></el-menu-item>
          </el-sub-menu>
        </el-menu>
        </el-header>
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
.common-layout{
  height: 100vh;
  width: 100vw;
}
.title{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
  text-align: center;
  height:4rem;
  font-size: 3rem;
  color: whitesmoke;
}
.el-header{
  padding: 0px 0px 0px 0px;
  width: 100%;
  position: fixed;
  top:0;
  z-index: 101;
}

.main-content{
  display: flex;
  max-height: 100vh;
  max-width: 100vw;
  border: 1px solid #ddd;
  border-radius: 1rem;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.flex-grow {
  flex-grow: 1;
}
.el-menu-demo {
  width: 100%;
  display: flex;
  justify-content: center;
}
.el-menu-demo .el-menu-item,
.el-menu-demo .el-sub-menu {
  font-family: 'Cooper Black',sans-serif;
}
.navigateRouter{
  color: inherit; /* 保持文本颜色 */
  text-decoration: none; /* 移除默认下划线 */
  display: inline-block;
  width: 100%;
  height: 100%;
  line-height: inherit;
}

.navigateRouter:hover {
  color: transparent;; /* 保持悬停时的颜色透明 */
}
</style>