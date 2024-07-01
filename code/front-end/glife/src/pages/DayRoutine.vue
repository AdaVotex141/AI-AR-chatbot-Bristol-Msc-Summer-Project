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
          <span>{{ todo.text }}</span>
          <el-button @click="removeTodo(todo.id)">Remove</el-button>
        </li>
      </ul>
    </el-main>
  </el-container>
</template>

<script setup>
  import useDayroutine from '@/hooks/useDayroutine';
  import { onMounted } from 'vue';

  const {newTodo, todos, addTodo, removeTodo, getTodos, changeCompletedStatus} = useDayroutine()
  onMounted(getTodos)

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