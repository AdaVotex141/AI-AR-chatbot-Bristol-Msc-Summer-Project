<template>
    <div>
      <!-- Modal toggle -->
      <el-button class="addButton" type="primary" @click="toggleModal" >
        <!-- plus icon -->
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="size-5">
          <path d="M10.75 4.75a.75.75 0 0 0-1.5 0v4.5h-4.5a.75.75 0 0 0 0 1.5h4.5v4.5a.75.75 0 0 0 1.5 0v-4.5h4.5a.75.75 0 0 0 0-1.5h-4.5v-4.5Z" />
        </svg>
        Add a new admin
      </el-button>
      <!-- Main modal -->
      <div v-if="isModalOpen" tabindex="-1" aria-hidden="true" class="fixed top-0 right-0 left-0 z-50 flex justify-center items-center w-full h-screen bg-gray-800 bg-opacity-75">
        <div class="relative p-4 w-full max-w-md max-h-full">
          <!-- Modal content -->
          <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                New Admin
              </h3>
              <button @click="toggleModal" type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm h-8 w-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white">
                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                  <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                </svg>
                <span class="sr-only">Close modal</span>
              </button>
            </div>
            <!-- Modal body -->
            <div class="p-4 md:p-5">
                <!-- Register Form -->
                <AdminRegisterForm :toggleModal="toggleModal" />
            </div>
          </div>
        </div>
      </div> 
    </div>
  </template>
  
<script setup lang='ts'>
import AdminRegisterForm from '@/components/AdminTemplate/AdminList/AdminRegisterForm.vue'
import { useAdminStore } from '@/stores/admin';
import { ref } from 'vue';

const adminStore = useAdminStore()  
let isModalOpen = ref(false)

function toggleModal() {
    isModalOpen.value = !isModalOpen.value;
}

</script>

<style scoped>
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

.user-input{ 
  margin-bottom: 10px;
}
</style>
  