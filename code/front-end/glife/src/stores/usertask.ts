import router from '@/router'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserTaskStore = defineStore('usertask',()=>{

    const numberOfTasks = ref(0)
    
    async function getRandomTask(){
        // Sending api request to the backend
        try {
            const response = await axios.get('/api/randomTask/init')
            console.log(response.data)
            if (String(response.data.code) !== '1') {
                console.error('Backend send code 0')
                router.push({ name: 'notfound' });
            }
        } catch (error) {
            console.error(error)
            router.push({ name: 'notfound' });
        }
    }

    async function getNumberOfTask(){
        // Sending api request to the backend
        try {
            const response = await axios.get('/api/randomTask/length')
            console.log(response.data)
            if (String(response.data.code) !== '1') {
                console.error('Backend send code 0')
                router.push({ name: 'notfound' });
            } else {
                numberOfTasks.value = response.data.data
            }
        } catch (error) {
            console.error(error)
            router.push({ name: 'notfound' });
        }
    }

    return {numberOfTasks, getRandomTask, getNumberOfTask}
})