<template>
  <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <!-- Add new admin button -->
    <div class="right-button">
      <AddAdminButton v-if="userInfoStore.permission === 2" />
      <el-button v-else class="addButton" type="success" disabled >
        <!-- plus icon -->
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="size-5">
          <path d="M10.75 4.75a.75.75 0 0 0-1.5 0v4.5h-4.5a.75.75 0 0 0 0 1.5h4.5v4.5a.75.75 0 0 0 1.5 0v-4.5h4.5a.75.75 0 0 0 0-1.5h-4.5v-4.5Z" />
        </svg>
        Add a new admin
      </el-button>
    </div>
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
        <tr v-for="admin in adminStore.admins" :key="admin.id" class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
          <th scope="row" class="px-6 py-4 text-gray-900 whitespace-nowrap dark:text-white">
            <div class="text-base font-semibold">{{ admin.username }}</div>
            <!-- <div class="font-normal text-gray-500">{{ admin.email }}</div> -->
          </th>
          <td class="px-6 py-4">{{ getPermissionString(admin.permission) }}</td>
          <td class="px-6 py-4">
            <button @click="adminStore.removeAdmin(admin.username)" 
            v-show='admin.permission !== 2 && userInfoStore.permission === 2' 
            type="button" 
            class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-full text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
            Remove
          </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import AddAdminButton from '@/components/AddAdminButton.vue'
import { useAdminStore } from '@/stores/admin';
import { useUserInfoStore } from '@/stores/userInfo';

const adminStore = useAdminStore()
const userInfoStore = useUserInfoStore()

onMounted(()=>{
  adminStore.getAdmins()
})

const permissionMap: { [key: number]: string } = {
  0: 'user',
  1: 'admin',
  2: 'root admin'
};

const getPermissionString = (permission: number): string => {
  return permissionMap[permission] || 'user';
};
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
