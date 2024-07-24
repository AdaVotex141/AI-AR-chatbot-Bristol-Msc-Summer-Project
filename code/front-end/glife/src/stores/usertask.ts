import router from '@/router'
import axios from 'axios'
import { defineStore } from 'pinia'
import { computed, ref } from 'vue'

export const useUserTaskStore = defineStore('usertask',()=>{

    const numberOfTasks = ref(0)
    const taskContent = ref('')
    const stringOfTask = computed(()=>{
        return numberOfTasks.value > 0 ? taskContent.value : 'No new tasks available now'
    })
    
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
            const response = await axios.post('/api/randomTask/length')
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

    async function addTaskToRoutine(){
        try{
            const response = await axios.post('/api/randomTask/add', taskContent.value, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
        } catch (error){
            console.error(error)
            router.push({ name: 'notfound' });
        }
    }

    function setTaskContent(content:string){
        taskContent.value = content
    }

    return {numberOfTasks, taskContent, stringOfTask, setTaskContent, getRandomTask, getNumberOfTask, addTaskToRoutine}
})