<template>
  <el-container class="dayroutine-app">
    <el-header>DayRoutine</el-header>
    <el-main>
      <div class="user-input">
        <el-input v-model="newTodo" @keyup.enter="addTodo" placeholder="Add a new routine!" clearable />
        <el-button type="primary" @click="addTodo">Add</el-button>
      </div>
      <el-divider content-position="left">Your routines:</el-divider>
      <ul>
        <li v-for="(todo) in todos" :key="todo.id" :class="{ completed: todo.completed }">
          <input type="checkbox" v-model="todo.completed" @click="changeCompletedStatus(todo.id)"/>
          <el-input
            v-if="isEditing && currentTodoId === todo.id"
            v-model="editText"
            @blur="saveTodo(todo.id)"
            @keyup.enter="saveTodo(todo.id)"
            autofocus />
          <span v-else @click="startEditing(todo.id, todo.text)">{{ todo.text }}</span>
          <el-button @click="removeTodo(todo.id)">Remove</el-button>
        </li>
      </ul>
    </el-main>
  </el-container>
</template>

<script setup>
  import useDayroutine from '@/hooks/useDayroutine';
  import { onMounted, ref } from 'vue';
  import axios from 'axios';

  const {newTodo, todos, addTodo, removeTodo, getTodos, changeCompletedStatus} = useDayroutine()
  onMounted(getTodos)

  // TODO: Content used for todolist, use component to replace it in the future
  const isEditing = ref(false)
  const currentTodoId = ref(null)
  const editText = ref('')
  const editInput = ref(null)

  function startEditing(id, text){
    isEditing.value = true
    currentTodoId.value = id
    editText.value = text
  }

  async function saveTodo(id){
    // Edit content in the frontend
    const todo = todos.value.find(todo => todo.id === id)
    if(todo){
      todo.text = editText.value
    }
    isEditing.value = false
    currentTodoId.value = null
    // Sending request to the backend
    try{
      const response = await axios.post('/api/routine/update', {
        id: id,
        content: editText.value
      })
      if(String(response.data.code) !== '1'){
        console.error('Error happend during update data in backend')
      }
    } catch (error){
      console.error(error)
    }

  }

</script>

<style scoped>
.user-input {
  display: flex;
  align-items: center;
}

.todo-app {
  max-width: 500px;
  margin: 0 auto;
  text-align: center;
}

input[type="text"] {
  width: 80%;
  padding: 10px;
  margin: 10px 0;
}

button {
  padding: 10px;
  margin: 5px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  padding: 10px;
  border-bottom: 1px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

li.completed span {
  text-decoration: line-through;
}

li.completed input[type="checkbox"] {
  checked: true;
}
</style>