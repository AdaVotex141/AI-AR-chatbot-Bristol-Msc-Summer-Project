<template>
  <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <!-- Add new admin button -->

    <!-- Table -->
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
      <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
          <th scope="col" class="px-6 py-3">Name</th>
          <th scope="col" class="px-6 py-3">Permission</th>
          <th scope="col" class="px-6 py-3">Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="admin in filteredUsers" :key="admin.id" class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
          <th scope="row" class="flex items-center px-6 py-4 text-gray-900 whitespace-nowrap dark:text-white">
            <div>
              <div class="text-base font-semibold">{{ admin.name }}</div>
              <div class="font-normal text-gray-500">{{ admin.email }}</div>
            </div>  
          </th>
          <td class="px-6 py-4">{{ admin.permission }}</td>
          <td class="px-6 py-4">
            <button v-show='admin.permission !== "root"' type="button" class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">Remove</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';


const filteredUsers = reactive([
  { 
    id: 1,
    name: 'hxy',
    email: 'hxy@outlook.com',
    permission: 'Bristol'
  },
  { 
    id: 2,
    name: 'rdh',
    email: 'rdh@gmail.com',
    permission: 'root'
  },
])
</script>

<style scoped>

</style>
