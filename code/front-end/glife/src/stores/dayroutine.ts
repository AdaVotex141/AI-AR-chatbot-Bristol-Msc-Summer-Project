import axios from 'axios';
import { ElMessage } from 'element-plus';
import { computed, ref } from 'vue';
import router from "@/router";
import { defineStore } from 'pinia'

interface Todo {
    id: number;
    text: string;
    period: number;
    completed: boolean;
}

export const useDayroutineStore = defineStore('dayroutine', () => {
    const newTodo = ref('');
    const periodOfNewToDo = ref('')
    const periodValue = computed(() => {
        switch (periodOfNewToDo.value) {
            case 'daily':
                return 0;
            case 'weekly':
                return 1;
            case 'monthly':
                return 2;
            default:
                return 0;
        }
    })
    const todos = ref<Todo[]>([]);
    const tabs = [
        { id: 'daily', label: 'Daily' },
        { id: 'weekly', label: 'Weekly' },
        { id: 'monthly', label: 'Monthly' },
    ];
    const activeTab = ref('daily');
    const activeTabValue = computed(() => {
        switch (activeTab.value) {
            case 'daily':
                return 0;
            case 'weekly':
                return 1;
            case 'monthly':
                return 2;
            default:
                return 0;
        }
    })

    const filteredTodos = computed(() => {
        return todos.value
            .filter(todo => todo.period === activeTabValue.value)
            .sort((a, b) => b.id - a.id)
    })

    async function getTodos() {
        try {
            const response = await axios.get('/api/routine/init')
            if (String(response.data.code) === '1') {
                //Get the data from response
                const data: { id: number; content: string; schedule: number; tick: number }[] = response.data.data
                //Map the data to todos and Sort them with id ascendingly
                todos.value = data.map(item => ({
                    id: item.id,
                    text: item.content,
                    period: item.schedule,
                    completed: Boolean(item.tick)
                }))
                    .sort((a, b) => b.id - a.id)
            } else {
                alert('Backend give a code 0?') // TODO: need to be deleted after ensuring the fault
                router.push({ name: 'notfound' });
            }
        } catch (error) {
            console.error("Error happened during initializing the dayroutine")
            alert("Error happened during initializing the dayroutine")
            router.push({ name: 'notfound' });
        }
    }

    async function addTodo() {
        if (newTodo.value.trim() !== '') {
            // Get newTodo's value and set it to empty string on the frontend
            const content = newTodo.value
            const period = periodValue.value
            newTodo.value = ''
            periodOfNewToDo.value = ''
            // Send api request and user input to backend
            try {
                const response = await axios.post('/api/routine/add', {
                    content: content,
                    schedule: period
                })
                if (String(response.data.code) === '1') {
                    ElMessage({
                        message: 'Add routine successfully',
                        type: 'success'
                    })
                } else {
                    ElMessage({
                        message: 'Bad things happened',
                        type: 'error'
                    })
                }
            } catch (error) {
                console.error('Error add to do:', error)
                alert('Error happened when adding dayroutine')
                router.push({ name: 'notfound' });
            }
            // Get latest data from backend
            getTodos()
        }
    };

    async function removeTodo(idOfRecord: number) {
        // Sending api request to the backend
        try {
            const response = await axios.post('/api/routine/delete', idOfRecord, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            if (String(response.data.code) !== '1') {
                console.error('Delete api request fail')
                router.push({ name: 'notfound' });
            }
        } catch (error) {
            console.error(error)
            router.push({ name: 'notfound' });
        }
        // Set the latest todos
        getTodos()
    };

    async function changeCompletedStatus(id: number) {
        // Sending api request to the backend
        try {
            const response = await axios.post('/api/routine/tick', id, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            if (String(response.data.code) !== '1') {
                console.error('Backend send code 0')
                router.push({ name: 'notfound' });
            }
        } catch (error) {
            console.error(error)
            router.push({ name: 'notfound' });
        }
    }

    function setActiveTab(tabId: string) {
        activeTab.value = tabId;
    }

    async function updateTodo(id:number, content:string) {
        // Edit content in the frontend
        const todo = todos.value.find(todo => todo.id === id)
        if (todo && todo.text !== content) {
            todo.text = content
        } else {
            return
        }
        // Sending request to the backend
        try {
            const response = await axios.post('/api/routine/update', {
                id: id,
                content: content
            })
            if (String(response.data.code) !== '1') {
                console.error('Error happened during update data in backend')
            }
        } catch (error) {
            console.error(error)
        }
    }

    async function updateScheduleOfTodo(id:number, period:number) {
        // Edit content in the frontend
        const todo = todos.value.find(todo => todo.id === id)
        if (todo && todo.period !== period) {
            todo.period = period
        } else {
            return
        }
        // Sending request to the backend
        try {
            const response = await axios.post('/api/routine/update-schedule', {
                id: id,
                schedule: period
            })
            if (String(response.data.code) !== '1') {
                console.error('Error happened during update data in backend')
            }
        } catch (error) {
            console.error(error)
        }
    }

    return { newTodo, todos, filteredTodos, periodOfNewToDo, tabs, activeTab, activeTabValue, 
        updateScheduleOfTodo, updateTodo, setActiveTab, addTodo, removeTodo, getTodos, changeCompletedStatus }
})

