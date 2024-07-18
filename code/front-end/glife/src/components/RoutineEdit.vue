<template>
    <div>
      <!-- Modal toggle -->
      <el-button class="edit-button" type="primary" @click="toggleModal" >
        Edit
      </el-button>
      <!-- Main modal -->
      <div v-if="isModalOpen" tabindex="-1" aria-hidden="true" class="fixed top-0 right-0 left-0 z-50 flex justify-center items-center w-full h-screen bg-gray-800 bg-opacity-75">
        <div class="relative p-4 w-full max-w-md max-h-full">
          <!-- Modal content -->
          <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                Edit Your Routine
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
              <!-- User input -->
              <p class="text-gray-500 dark:text-gray-400 mb-4">Your customized routine:</p>
              <div class="user-input">
                <el-input v-model="editContent" placeholder="Add a new routine!" clearable />
              </div>
              <p class="text-gray-500 dark:text-gray-400 mb-4">Select your desired routine's period:</p>
              <ul class="space-y-4 mb-4">
                <li v-for="period in periods" :key="period.value">
                  <input :id="period.value" name="period" type="radio" :value="period.value" class="hidden peer" v-model="editPeriod" />
                  <label :for="period.value" class="inline-flex items-center justify-between w-full p-5 text-gray-900 bg-white border border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-500 dark:peer-checked:text-blue-500 peer-checked:border-blue-600 peer-checked:text-blue-600 hover:text-gray-900 hover:bg-gray-100 dark:text-white dark:bg-gray-600 dark:hover:bg-gray-500">
                    <div class="block">
                      <div class="w-full text-lg font-semibold">{{ period.title }}</div>
                    </div>
                    <svg class="w-4 h-4 ms-3 rtl:rotate-180 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                      <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                    </svg>
                  </label>
                </li>
              </ul>
              <button @click="save" class="edit-button text-white inline-flex w-full justify-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                <!-- tick icon -->
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="size-5">
                    <path fill-rule="evenodd" d="M16.704 4.153a.75.75 0 0 1 .143 1.052l-8 10.5a.75.75 0 0 1-1.127.075l-4.5-4.5a.75.75 0 0 1 1.06-1.06l3.894 3.893 7.48-9.817a.75.75 0 0 1 1.05-.143Z" clip-rule="evenodd" />
                </svg>
                Save
              </button>
            </div>
          </div>
        </div>
      </div> 
    </div>
  </template>
  
<script setup lang='ts'>
import { useDayroutineStore } from '@/stores/dayroutine';
import { reactive, ref } from 'vue';

const dayroutineStore = useDayroutineStore()
let isModalOpen = ref(false)
const periods = reactive([
    { value: '0', title: 'Daily routine', describe: 'aaa' },
    { value: '1', title: 'Weekly routine', describe: 'bbb' },
    { value: '2', title: 'Monthly routine', describe: 'ccc' },
])
const props = defineProps(['todo'])
let editContent = ref(props.todo.text)
let editPeriod = ref(String(props.todo.period))

function toggleModal() {
    isModalOpen.value = !isModalOpen.value;
}

function save(){
  // Update the content
  dayroutineStore.updateTodo(props.todo.id, editContent.value)
  // Update the period
  dayroutineStore.updateScheduleOfTodo(props.todo.id, Number(editPeriod.value))
  // Turn off the modal
  toggleModal()
}

</script>

<style scoped>
.edit-button {
  padding: 10px;
  margin: 5px;
  background-color: #9cb470;
  border-color: transparent;
}

.edit-button:hover {
  background-color:darkseagreen;
  color: #fff;
  border-color: transparent;
}

.user-input{ 
  margin-bottom: 10px;
}

</style>
  