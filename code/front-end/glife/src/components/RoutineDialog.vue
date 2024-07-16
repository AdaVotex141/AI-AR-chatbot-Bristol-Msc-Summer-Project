<template>
    <div>
      <!-- Modal toggle -->
      <button @click="toggleModal" class="text-gray-900 bg-gradient-to-r from-lime-200 via-lime-400 to-lime-500 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-lime-300 dark:focus:ring-lime-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
        Add this to my routine
      </button>
  
      <!-- Main modal -->
      <div v-if="isModalOpen" tabindex="-1" aria-hidden="true" class="fixed top-0 right-0 left-0 z-50 flex justify-center items-center w-full h-screen bg-gray-800 bg-opacity-75">
        <div class="relative p-4 w-full max-w-md max-h-full">
          <!-- Modal content -->
          <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
              <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                Open positions
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
              <p class="text-gray-500 dark:text-gray-400 mb-4">Select your desired position:</p>
              <ul class="space-y-4 mb-4">
                <li v-for="(job, index) in jobs" :key="job.value">
                  <input :id="job.value" name="job" type="radio" :value="job.value" class="hidden peer" v-model="selectedJob" />
                  <label :for="job.value" class="inline-flex items-center justify-between w-full p-5 text-gray-900 bg-white border border-gray-200 rounded-lg cursor-pointer dark:hover:text-gray-300 dark:border-gray-500 dark:peer-checked:text-blue-500 peer-checked:border-blue-600 peer-checked:text-blue-600 hover:text-gray-900 hover:bg-gray-100 dark:text-white dark:bg-gray-600 dark:hover:bg-gray-500">
                    <div class="block">
                      <div class="w-full text-lg font-semibold">{{ job.title }}</div>
                      <div class="w-full text-gray-500 dark:text-gray-400">{{ job.company }}</div>
                    </div>
                    <svg class="w-4 h-4 ms-3 rtl:rotate-180 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                      <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                    </svg>
                  </label>
                </li>
              </ul>
              <button @click="nextStep" class="text-white inline-flex w-full justify-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Next step
              </button>
            </div>
          </div>
        </div>
      </div> 
    </div>
  </template>
  
<script setup lang='ts'>
import { reactive, ref } from 'vue';

  
let isModalOpen = ref(false)
let selectedJob = ref('')
let jobs = reactive([
    { value: 'job-1', title: 'UI/UX Engineer', company: 'Flowbite' },
    { value: 'job-2', title: 'React Developer', company: 'Alphabet' },
    { value: 'job-3', title: 'Full Stack Engineer', company: 'Apple' },
])

function toggleModal() {
    isModalOpen.value = !isModalOpen.value;
}
function nextStep() {
console.log(`Selected job: ${this.selectedJob}`);
// Add your logic for the next step here
}

</script>

<style scoped>
/* Add your styles here if needed */
</style>
  