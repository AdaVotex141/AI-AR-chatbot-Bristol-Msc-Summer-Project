<template>
    <div class="common-layout">
      <el-container>
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
          <el-menu-item index="/welcome">
            <div class="title" @click="navigateTo('startpage')">Glife</div>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="/mainpage/chatwindow" @click="navigateTo('chatwindow')">Chat</el-menu-item>
          <el-menu-item index="/mainpage/dayroutine" @click="navigateTo('dayroutine')">Routine</el-menu-item>
          <el-sub-menu index="3">
            <template #title>ARTree</template>
            <el-menu-item index="/mainpage/artree" @click="navigateTo('artree')">Plant VR Tree</el-menu-item>
            <el-menu-item index="/mainpage/badge" @click="navigateTo('mybadge')">My Badge</el-menu-item>
            <el-menu-item index="/mainpage/share" @click="navigateTo('notfound')">Share VR Tree</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="10">
            <template #title>Profile</template>
            <el-menu-item index="/mainpage/setting" @click="navigateTo('setting')">Setting</el-menu-item>
            <el-menu-item index="/login" @click='logout'>Log out</el-menu-item>
          </el-sub-menu>
        </el-menu>

          <div class="fixed top-0 z-50 w-full h-16 -translate-x-1/2 bg-white border-t border-gray-200 left-1/2 dark:bg-gray-700 dark:border-gray-600" v-else>
            <div class="grid h-full max-w-lg grid-cols-6 mx-auto">
              <!--          TODO: change the style and add the back to welcome page in here    -->
              <button data-tooltip-target="tooltip-document" type="button" class="inline-flex flex-col items-center justify-center px-5 hover:bg-gray-50 dark:hover:bg-gray-800 group">
                <svg class="w-5 h-5 mb-1 text-gray-500 dark:text-gray-400 group-hover:text-blue-600 dark:group-hover:text-blue-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M.188 5H5V.13a2.96 2.96 0 0 0-1.293.749L.879 3.707c-.358.362-.617.81-.753 1.3C.148 5.011.166 5 .188 5ZM14 8a6 6 0 1 0 0 12 6 6 0 0 0 0-12Zm2 7h-1v1a1 1 0 0 1-2 0v-1h-1a1 1 0 0 1 0-2h1v-1a1 1 0 0 1 2 0v1h1a1 1 0 0 1 0 2Z"/>
                  <path d="M6 14a7.969 7.969 0 0 1 10-7.737V2a1.97 1.97 0 0 0-1.933-2H7v5a2 2 0 0 1-2 2H.188A.909.909 0 0 1 0 6.962V18a1.969 1.969 0 0 0 1.933 2h6.793A7.976 7.976 0 0 1 6 14Z"/>
                </svg>
                <span class="sr-only">New document</span>
              </button>
              <div id="tooltip-document" role="tooltip" class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700">
                New document
                <div class="tooltip-arrow" data-popper-arrow></div>
              </div>
              <button data-tooltip-target="tooltip-bookmark" type="button" class="inline-flex flex-col items-center justify-center px-5 hover:bg-gray-50 dark:hover:bg-gray-800 group">
                <svg class="w-5 h-5 mb-1 text-gray-500 dark:text-gray-400 group-hover:text-blue-600 dark:group-hover:text-blue-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 14 20">
                  <path d="M13 20a1 1 0 0 1-.64-.231L7 15.3l-5.36 4.469A1 1 0 0 1 0 19V2a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v17a1 1 0 0 1-1 1Z"/>
                </svg>
                <span class="sr-only">Bookmark</span>
              </button>
              <div id="tooltip-bookmark" role="tooltip" class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700">
                Bookmark
                <div class="tooltip-arrow" data-popper-arrow></div>
              </div>
              <button data-tooltip-target="tooltip-settings" type="button" class="inline-flex flex-col items-center justify-center px-5 hover:bg-gray-50 dark:hover:bg-gray-800 group">
                <svg class="w-5 h-5 mb-1 text-gray-500 dark:text-gray-400 group-hover:text-blue-600 dark:group-hover:text-blue-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                  <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 12.25V1m0 11.25a2.25 2.25 0 0 0 0 4.5m0-4.5a2.25 2.25 0 0 1 0 4.5M4 19v-2.25m6-13.5V1m0 2.25a2.25 2.25 0 0 0 0 4.5m0-4.5a2.25 2.25 0 0 1 0 4.5M10 19V7.75m6 4.5V1m0 11.25a2.25 2.25 0 1 0 0 4.5 2.25 2.25 0 0 0 0-4.5ZM16 19v-2"/>
                </svg>
                <span class="sr-only">Settings</span>
              </button>
              <div id="tooltip-settings" role="tooltip" class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700">
                Settings
                <div class="tooltip-arrow" data-popper-arrow></div>
              </div>
              <button data-tooltip-target="tooltip-profile" type="button" class="inline-flex flex-col items-center justify-center px-5 hover:bg-gray-50 dark:hover:bg-gray-800 group">
                <svg class="w-5 h-5 mb-1 text-gray-500 dark:text-gray-400 group-hover:text-blue-600 dark:group-hover:text-blue-500" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M10 0a10 10 0 1 0 10 10A10.011 10.011 0 0 0 10 0Zm0 5a3 3 0 1 1 0 6 3 3 0 0 1 0-6Zm0 13a8.949 8.949 0 0 1-4.951-1.488A3.987 3.987 0 0 1 9 13h2a3.987 3.987 0 0 1 3.951 3.512A8.949 8.949 0 0 1 10 18Z"/>
                </svg>
                <span class="sr-only">Profile</span>
              </button>
              <div id="tooltip-profile" role="tooltip" class="absolute z-10 invisible inline-block px-3 py-2 text-sm font-medium text-white transition-opacity duration-300 bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700">
                Profile
                <div class="tooltip-arrow" data-popper-arrow></div>
              </div>
            </div>
          </div>

        </el-header>
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

.el-menu-desktop .el-menu-item,
.el-menu-desktop .el-sub-menu {
  font-family: 'Cooper Black',sans-serif;
}

</style>