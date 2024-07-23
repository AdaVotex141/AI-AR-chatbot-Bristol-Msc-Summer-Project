<template>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
      <!-- Add new admin button -->
      <div class="right-button">
        <AddTaskButton />
      </div>
      <!-- Table -->
      <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th v-for="listHead in listHeads" scope="col" class="px-6 py-3">{{listHead}}</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="task in taskStore.tasks" :key="task.id" class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
            <th scope="row" class="px-6 py-4 text-gray-900 whitespace-nowrap dark:text-white">
              <div class="text-base font-semibold">Creator</div>
              <!-- <div class="font-normal text-gray-500">{{ admin.email }}</div> -->
            </th>
            <td class="px-6 py-4">taskname</td>
            <td class="px-6 py-4">description of task</td>
            <td class="px-6 py-4">Create time</td>
            <!-- <td class="px-6 py-4">{{ getPermissionString(admin.permission) }}</td>
            <td class="px-6 py-4">
              <button @click="adminStore.removeAdmin(admin.username)" 
              v-show='admin.permission !== 2 && userInfoStore.permission === 2' 
              type="button" 
              class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
              Remove
            </button>
            </td> -->
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup lang="ts">
  import { onMounted } from 'vue';
  import AddTaskButton from '@/components/AddTaskButton.vue'
  import { useUserInfoStore } from '@/stores/userInfo';
  import { useTaskStore } from '@/stores/task';
  
  const userInfoStore = useUserInfoStore()
  const taskStore = useTaskStore()
  const listHeads = ['Task name', 'Description', 'Schedule', 'Creator', 'Create time']

  onMounted(()=>{
    taskStore.getTasks()
  })

  </script>
  
  <style scoped>
  .right-button{
    display: flex;
    justify-content: flex-end;
  }
  
  .addButton {
    padding: 10px;
    margin: 5px;
    background-color: #9cb470;
    border-color: transparent;
  }
  
  .addButton:hover {
    background-color:darkseagreen;
    color: #fff;
    border-color: transparent;
  }
  </style>
  