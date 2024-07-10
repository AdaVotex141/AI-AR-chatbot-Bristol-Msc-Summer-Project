<template>
  <Login v-if="showLogin" @toggle-page="togglePage"/>
  <Register v-if="!showLogin" @toggle-page="togglePage"/>
</template>

<script lang='ts' setup>
import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'
import { ref } from 'vue';

const showLogin = ref(true)

function togglePage(){
  showLogin.value = !showLogin.value
}

</script>

<style scoped>
body{
  background-color: #e8e8e8;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
}
</style>