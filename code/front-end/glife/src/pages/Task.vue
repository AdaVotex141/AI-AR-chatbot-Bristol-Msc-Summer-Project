<template>
    <div class="routine-container">
    <el-container class="dayroutine-app">
      <el-header>{{ getCurrentDate() }} Routine: </el-header>
      <el-main>

        <el-divider content-position="left">Your {{dayroutineStore.activeTab}} routines:</el-divider>
        <!-- RoutineList -->

      </el-main>
    </el-container>
    </div>
</template>

<script setup lang='ts'>
import RoutineTags from '@/components/RoutineTags.vue'
import RoutineDialog from '@/components/RoutineDialog.vue'
import RoutineList from '@/components/RoutineList.vue';
import { useDayroutineStore } from '@/stores/dayroutine';
import { useSystemroutineStore } from '@/stores/systemroutine';

const dayroutineStore = useDayroutineStore()
const systemroutineStore = useSystemroutineStore()

// Get the date information
function getCurrentDate() {
    return new Date().toLocaleDateString('en-GB');
}

</script>

<style scoped>
@media(max-width: 600px) {
.routine-container {
    margin: 0 auto;
    width: 100vw;
    height: 90vh;
}
.el-header{
    background-color: #9cb470;
    padding: 1.5rem;
    margin-bottom: 0.5rem;
    font-size: 1.5rem;
    color: whitesmoke;
    font-weight: bold;
    font-family: 'Cooper Black',sans-serif;
    border-bottom: 1px solid #e0e0e0;
    border-radius: 0.8rem;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
}
}

@media(min-width: 601px) {
.routine-container  {
    margin-top: 2.5rem;
    width: 60vw;
    height: 90vh;
    margin-left: auto;
    margin-right: auto;
}
.el-header{
    background-color: #9cb470;
    padding: 1.5rem;
    margin-bottom: 0.5rem;
    font-size: 1.5rem;
    color: whitesmoke;
    font-weight: bold;
    font-family: 'Cooper Black',sans-serif;
    border-bottom: 1px solid #e0e0e0;
    border-radius: 0.8rem;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    top: 5%;
}
}
.add-button{
    display: flex;
    justify-content: center;
    align-items: center;
}

.el-main{
overflow-y: hidden;
}
.user-input {
display: flex;
align-items: center;
position: sticky;
top: 0;
z-index: 99;
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
background-color: #9cb470;
border-color: transparent;
}

button:hover {
background-color:darkseagreen;
color: #fff;
border-color: transparent;
}

.systemroutine {
background-color: gold;
}

</style>