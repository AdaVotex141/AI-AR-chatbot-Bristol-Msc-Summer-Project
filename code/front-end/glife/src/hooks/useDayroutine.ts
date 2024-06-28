import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';

export default function () {
    const newTodo = ref('');
    const todos = ref([
        { text: 'Learn Vue 3', completed: false },
        { text: 'Build a to-do list', completed: false }
    ]);

    async function addTodo() {
        if (newTodo.value.trim() !== '') {
            todos.value.push({ text: newTodo.value.trim(), completed: false });
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

    return {newTodo, todos, addTodo, removeTodo}
}


