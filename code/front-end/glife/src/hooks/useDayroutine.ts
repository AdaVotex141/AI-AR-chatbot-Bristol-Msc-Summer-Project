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
            todos.value.push({ id: 111, text: newTodo.value.trim(), completed: false });
            newTodo.value = '';
            try {
                const response = await axios.post('/api/routine/add', {
                    content: newTodo
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
        todos.value.splice(index, 1);
    };

    return {newTodo, todos, addTodo, removeTodo, initTodo}
}


