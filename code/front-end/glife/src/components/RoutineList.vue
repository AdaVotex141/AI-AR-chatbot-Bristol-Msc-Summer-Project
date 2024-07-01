<template>
    <ul>
        <li v-for="(todo) in todos" :key="todo.id" :class="{ completed: todo.completed }">
          <input type="checkbox" v-model="todo.completed" @click="changeCompletedStatus(todo.id)"/>
          <span>{{ todo.text }}</span>
          <el-button @click="removeTodo(todo.id)">Remove</el-button>
        </li>
    </ul>
</template>

<script setup lang="ts">
import useDayroutine from '@/hooks/useDayroutine';
import {onMounted} from 'vue'
const {todos, removeTodo, changeCompletedStatus, getTodos} = useDayroutine()
onMounted(getTodos)
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