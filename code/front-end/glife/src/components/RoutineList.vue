<template>
    <ul>
      <li v-for="(todo) in props.store.filteredTodos" :key="todo.id" :class="{ completed: todo.completed }">
        <input type="checkbox" v-model="todo.completed" @click="props.store.changeCompletedStatus(todo.id)"/>
        <span>{{ todo.text }}</span>
        <div class="right-button">
          <RoutineEdit :todo="todo" />
          <el-button @click="props.store.removeTodo(todo.id)">Remove</el-button>
        </div>
      </li>
    </ul>
</template>

<script setup lang="ts">
import RoutineEdit from './RoutineEdit.vue'
import {onMounted} from 'vue'

const props = defineProps(['store', 'isSystemroutine'])

onMounted(()=> props.store.getTodos())

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