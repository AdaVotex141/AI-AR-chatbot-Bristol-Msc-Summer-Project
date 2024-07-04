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
          <el-menu-item index="/welcome">
            <div class="title" @click="navigateTo('startpage')">Glife</div>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="/mainpage/chatwindow" @click="navigateTo('chatwindow')">Chat</el-menu-item>
          <el-menu-item index="/mainpage/dayroutine" @click="navigateTo('dayroutine')">Routine</el-menu-item>
          <el-sub-menu index="3">
            <template #title>ARTree</template>
            <el-menu-item index="/mainpage/artree" @click="navigateTo('artree')">Plant VR Tree</el-menu-item>
            <el-menu-item index="/mainpage/badge">My Badge</el-menu-item>
            <el-menu-item index="/mainpage/share">Share VR Tree</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="10">
            <template #title>Profile</template>
            <el-menu-item index="/mainpage/setting" @click="navigateTo('setting')">Setting</el-menu-item>
            <el-menu-item index="/login" @click='logout'>Log out</el-menu-item>
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
  import {RouterView, useRoute} from 'vue-router';
    import {ref, watch} from 'vue'
    import axios from 'axios';
    import router from '@/router';

    // Watch the current router
    const route = useRoute();
    const activeIndex = ref(route.path);
    watch(route, (newRoute) => {
      activeIndex.value = newRoute.path;
    });

    const handleSelect = (key: string, keyPath: string[]) => {
      console.log(key, keyPath)
    }
    function navigateTo(routeName: string) {
      router.push({
        name: routeName
      });
    }

    async function logout(){
      try{
        const response = await axios.post('/api/logout')
        if(String(response.data.code) !== '1'){
          alert('Logout fail, please try again')
        } else {
          alert('logout successful')
          router.replace({name:'logout'})
        }
      } catch (error) {
        router.push({name:'notfound'})
      }
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
  padding: 0;
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
  color: inherit;
  text-decoration: none;
  display: inline-block;
  width: 100%;
  height: 100%;
  line-height: inherit;
}

.navigateRouter:hover {
  color: transparent;
}
</style>