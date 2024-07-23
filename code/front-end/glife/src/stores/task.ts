import router from '@/router';
import axios from 'axios';
import { defineStore } from 'pinia'
import { reactive, ref } from 'vue'

interface Task{
    id:number
    title:string
    description:string
    creator:string
    create_time:string
    schedule:number
}

export const useTaskStore = defineStore('task',()=>{
    const tabs = [
        { id: 'published', label: 'Published' },
        { id: 'unpublished', label: 'Unpublished' },
    ];
    const activeTab = ref('published');

    function setActiveTab(tabId: string) {
        activeTab.value = tabId;
    }

    const tasks = ref<Task[]>([])

    async function getTasks(){
        try{
            const response = await axios.post('/api/admin/randomTask/init')
            console.log(response.data.data)
            if(String(response.data.code) === '1'){
                //Get the data from response
                const data: {
                    id:number;
                    title:string;
                    description:string;
                    creator:string;
                    create_time:string;
                    schedule:number}[] = response.data.data
                //Save data in the tasks
                tasks.value = data
            } else {
                alert('Backend give a code 0?') // TODO: need to be deleted after ensuring the fault
                router.push({name:'notfound'});
            }
        } catch (error) {
            console.error("Error happened during initializing the tasks")
            alert("Error happened during initializing the tasks")
            router.push({name:'notfound'});
        }
    }

    return {tabs, activeTab, tasks, setActiveTab, getTasks}
})