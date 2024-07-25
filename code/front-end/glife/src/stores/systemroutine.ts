import axios from 'axios';
import { ElMessage } from 'element-plus';
import { computed, ref } from 'vue';
import router from "@/router";
import {defineStore} from 'pinia'
import { useDayroutineStore } from './dayroutine';

interface Todo{
    id: number;
    text: string;
    period: number;
    completed: boolean;
}

export const useSystemroutineStore = defineStore('systemroutine',()=> {
    const newTodo = ref('');
    const systemTodos = ref<Todo[]>([]);
    const randomTaskTodos = ref<Todo[]>([]);
    const dayroutineStore = useDayroutineStore()

    const filteredSystemTodos = computed(()=>{
        return systemTodos.value
        .filter(todo => todo.period === dayroutineStore.activeTabValue)
        .sort((a, b) => b.id - a.id)
    })

    const filteredRandomTaskTodos = computed(()=>{
        return randomTaskTodos.value
        .filter(todo => todo.period === dayroutineStore.activeTabValue)
        .sort((a, b) => b.id - a.id)
    })

    async function getTodos(){
        try{
            const response = await axios.get('/api/system_routine/init')
            if(String(response.data.code) === '1'){
                //Get the data from response
                const data: {id: number; content: string; schedule:number; tick: number; type:number}[] = response.data.data
                const randomTaskRoutineData = data.filter(item => item.type === 1);
                const systemRoutineData = data.filter(item => item.type === 0);
                //Map the data to todos and Sort them with id ascendingly
                randomTaskTodos.value = randomTaskRoutineData.map(item => ({
                    id: item.id,
                    text: item.content,
                    period: item.schedule,
                    completed: Boolean(item.tick)
                }))
                .sort((a, b) => b.id - a.id)
                systemTodos.value = systemRoutineData.map(item => ({
                    id: item.id,
                    text: item.content,
                    period: item.schedule,
                    completed: Boolean(item.tick)
                }))
                .sort((a, b) => b.id - a.id)
            } else {
                alert('Backend give a code 0?') // TODO: need to be deleted after ensuring the fault
                router.push({name:'notfound'});
            }
        } catch (error) {
            console.error("Error happened during initializing the dayroutine")
            alert("Error happened during initializing the dayroutine")
            router.push({name:'notfound'});
        }
    }

    async function removeTodo(idOfRecord:number){
        // Sending api request to the backend
        try{
            const response = await axios.post('/api/system_routine/delete', idOfRecord, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            if(String(response.data.code) !== '1'){
                console.error('Delete api request fail')
                router.push({name:'notfound'});
            }
        } catch (error){
            console.error(error)
            router.push({name:'notfound'});
        }
        // Set the latest todos
        getTodos()
    };

    async function changeCompletedStatus(id:number){
        // Sending api request to the backend
        try{
            const response = await axios.post('/api/system_routine/tick', id, {
                headers:{
                    'Content-Type': 'application/json'
                }
            })
            if(String(response.data.code) !== '1'){
                console.error('Backend send code 0')
                router.push({name:'notfound'});
            }
        } catch (error){
            console.error(error)
            router.push({name:'notfound'});
        }
    }

    return {newTodo, systemTodos, filteredSystemTodos, randomTaskTodos, filteredRandomTaskTodos, removeTodo, getTodos, changeCompletedStatus}
}) 
    
