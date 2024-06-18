<template>
    <div class="dayroutine-app">
      <h1>DayRoutine</h1>
      <input v-model="newTodo" @keyup.enter="addTodo" placeholder="Add a new task" />
      <button @click="addTodo">Add</button>
      <ul>
        <li v-for="(todo, index) in todos" :key="index" :class="{ completed: todo.completed }">
          <input type="checkbox" v-model="todo.completed" />
          <span>{{ todo.text }}</span>
          <button @click="removeTodo(index)">Remove</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  
  export default {
    name: 'DayRoutine',
    setup() {
      const newTodo = ref('');
      const todos = ref([
        { text: 'Learn Vue 3', completed: false },
        { text: 'Build a to-do list', completed: false }
      ]);
  
      const addTodo = () => {
        if (newTodo.value.trim() !== '') {
          todos.value.push({ text: newTodo.value.trim(), completed: false });
          newTodo.value = '';
        }
      };
  
      const removeTodo = (index) => {
        todos.value.splice(index, 1);
      };
  
      return {
        newTodo,
        todos,
        addTodo,
        removeTodo
      };
    }
  };
  </script>
  
  <style>
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