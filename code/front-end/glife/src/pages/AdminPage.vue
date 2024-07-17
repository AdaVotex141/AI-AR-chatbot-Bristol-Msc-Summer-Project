<template>
    <div class="common-layout">
      <el-container>
        <!-- Navigate bar -->
        <el-header>
        <el-menu
            v-if="isDesktop"
            :default-active="activeIndex"
            class="el-menu-desktop"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
            background-color="#738352"
            text-color="white"
            active-text-color="black"
        >
          <el-menu-item index="/admin">
            <div class="title" @click="navigateTo('admin')">Glife Admin</div>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="/admin/task" @click="navigateTo('task')">Tasks</el-menu-item>
          <el-menu-item index="/admin/adminlist" @click="navigateTo('adminlist')">Management</el-menu-item>
          <el-menu-item index="/login" @click='logout'>Log out</el-menu-item>
        </el-menu>
          <MobileMenu v-else />
        </el-header>
        <!-- Maincontent -->
        <el-main class="main-content">
          <RouterView></RouterView>
        </el-main>
      </el-container>
    </div>

  </template>
  
<script setup lang="ts" name="App">
  import { onMounted, onBeforeUnmount } from 'vue';
  import { ElMenu, ElMenuItem, ElButton, ElDrawer } from 'element-plus';
  import {RouterView, useRoute} from 'vue-router';
  import {ref, watch} from 'vue'
  import axios from 'axios';
  import router from '@/router';
  const drawer = ref(false);
  const isDesktop = ref(window.innerWidth > 600);
  import MobileMenu from '../components/MobileMenu.vue';
  import { useUserInfoStore } from '@/stores/userInfo';

  const userInfoStore = useUserInfoStore()

  // Watch the screen width to change the style
  const checkScreenSize = () => {
    isDesktop.value = window.innerWidth > 600;
  };

  onMounted(() => {
    window.addEventListener('resize', checkScreenSize);
  });

  onBeforeUnmount(() => {
    window.removeEventListener('resize', checkScreenSize);
  });


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
          // Message given to the user
          alert('logout successful')
          // Use router to go to the login page
          router.replace({name:'logout'})
          // Change the userInfo
          userInfoStore.logout()
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
.main-content{
  display: flex;
  max-height: calc(100vh - 4rem); 
  max-width: 100vw;
  padding-top: 4rem;
  justify-content: center;
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
  height: 4rem; 
}

.flex-grow {
  flex-grow: 1;
}
@media (max-width: 600px) {
  .el-menu-desktop {
    width: 100%;
    display: flex;
    justify-content: center;
  }
  .mobile-menu-button {
    display: inline-block;
  }
}
/*@media (min-width:601px) {
  .main-content{
    display: flex;
    max-height: 100vh;
    max-width: 100vw;
    border: 1px solid #ddd;
    border-radius: 1rem;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
}*/

.el-menu-desktop .el-menu-item,
.el-menu-desktop .el-sub-menu {
  font-family: 'Cooper Black',sans-serif;
}

</style>