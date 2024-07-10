<template>
    <ul>
      <li v-for="(todo) in dayroutineStore.todos" :key="todo.id" :class="{ completed: todo.completed }">
        <input type="checkbox" v-model="todo.completed" @click="dayroutineStore.changeCompletedStatus(todo.id)"/>
        <el-input
          v-if="isEditing && currentTodoId === todo.id"
          v-model="editText"
          @blur="saveTodo(todo.id)"
          @keyup.enter="saveTodo(todo.id)"
          autofocus />
        <span v-else @click="startEditing(todo.id, todo.text)">{{ todo.text }}</span>
        <el-button @click="dayroutineStore.removeTodo(todo.id)">Remove</el-button>
      </li>
    </ul>
</template>

<script setup lang="ts">
import {onMounted} from 'vue'
import { useDayroutineStore } from '@/stores/dayroutine';
import {ref} from 'vue'
import axios from 'axios';
const dayroutineStore = useDayroutineStore()
onMounted(()=> dayroutineStore.getTodos())

const isEditing = ref(false)
const currentTodoId = ref()
const editText = ref('')

function startEditing(id:number, text:string){
  isEditing.value = true
  currentTodoId.value = id
  editText.value = text
}

async function saveTodo(id:number){
  // Edit content in the frontend
  const todo = dayroutineStore.todos.find(todo => todo.id === id)
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
      console.error('Error happened during update data in backend')
    }
  } catch (error){
    console.error(error)
  }

}

</script>

<style scoped>
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