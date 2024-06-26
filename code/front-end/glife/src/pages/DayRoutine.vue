<template>
    <el-container class="dayroutine-app">
      <el-header>DayRoutine</el-header>
      <el-main>
        <div class="user-input">
          <el-input 
          v-model="newTodo" 
          @keyup.enter="addTodo" 
          placeholder="Add a new routine!" 
          clearable/>
          <el-button type="primary" @click="addTodo">Add</el-button>
        </div>
        <el-divider content-position="left">Your routines:</el-divider>
        <ul>
          <li v-for="(todo, index) in todos" :key="index" :class="{ completed: todo.completed }">
            <input type="checkbox" v-model="todo.completed" />
            <span>{{ todo.text }}</span>
            <el-button @click="removeTodo(index)">Remove</el-button>
          </li>
        </ul>
      </el-main>
    </el-container>
  </template>
  
  <script setup>
  import axios from 'axios';
  import { ref } from 'vue';
    const newTodo = ref('');
    const todos = ref([
      { text: 'Learn Vue 3', completed: false },
      { text: 'Build a to-do list', completed: false }
    ]);

    async function addTodo(){
      if (newTodo.value.trim() !== '') {
        todos.value.push({ text: newTodo.value.trim(), completed: false });
        newTodo.value = '';
        try{
          const response = await axios.post('/routine/add', {
            content: newTodo    
          })
          if(String(response.data.code) === '1'){
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
        } catch (error){
          console.error('Error add to do:', error)
          alert('Error happened when adding dayroutine')
        }
      }
    };

    const removeTodo = (index) => {
      todos.value.splice(index, 1);
    };
  
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