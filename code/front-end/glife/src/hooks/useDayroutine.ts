import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';

export default function () {
    interface Todo{
        id: number;
        text: string;
        completed: boolean;
    }
    const newTodo = ref('');
    const todos = ref<Todo[]>([]);

    async function initTodo(){
        try{
            const response = await axios.get('/api/routine/init')
            if(String(response.data.code) === '1'){
                //Get the data from response
                const data: {id: number; text: string; tick: number}[] = response.data.data
                //Map the data to todos and Sort them with id ascendingly
                console.log(data[0].text)
                todos.value = data.map(item => ({
                    id: item.id,
                    text: item.text,
                    completed: Boolean(item.tick)
                }))
                .sort((a, b) => a.id - b.id)
                console.log("3ok")
            } else {
                alert('Backend give a code 0?') // TODO: need to be deleted after ensuring the fault
            }
        } catch (error) {
            console.error("Error happened during initializing the dayroutine")
            alert("Error happened during initializing the dayroutine")
        }
    }

    async function addTodo() {
        if (newTodo.value.trim() !== '') {
            // Get maxId in current todos and set maxId+1 as todos
            const maxId = todos.value.length > 0 ? Math.max(...todos.value.map(todo => todo.id)) : 0
            // Show the result when adding a new todo
            todos.value.push({ id: maxId + 1, text: newTodo.value.trim(), completed: false });
            const content = newTodo.value
            newTodo.value = '';
            // Send api request to backend
            try {
                const response = await axios.post('/api/routine/add', {
                    content: content
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
            }
        }
    };

    function removeTodo(index:number){
        // Show the effect when remove a todo
        todos.value.splice(index, 1);
        // Sending api request to the backend

    };

    return {newTodo, todos, addTodo, removeTodo, initTodo}
}


