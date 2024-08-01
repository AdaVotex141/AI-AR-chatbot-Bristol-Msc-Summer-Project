<template>
  <div class="routine-container">
  <el-container class="dayroutine-app">
    <el-header>{{ getCurrentDate() }} Routine: </el-header>
    <el-main>
      <RoutineDialog class="add-button" ref="ref1" />
      <RoutineTags ref="ref2" />
      <el-divider content-position="left">Your {{dayroutineStore.activeTab}} routines:</el-divider>
      <!-- RoutineList -->
      <div class="randomtask">
        <RoutineList :store="systemroutineStore" :todos="systemroutineStore.filteredRandomTaskTodos" :isSystemroutine="true"/>
      </div>
      <div class="systemroutine">
        <RoutineList :store="systemroutineStore" :todos="systemroutineStore.filteredSystemTodos" :isSystemroutine="true"/>
      </div>
      <RoutineList :store="dayroutineStore" :todos="dayroutineStore.filteredTodos" :isSystemroutine="false"/>
    </el-main>
  </el-container>
  </div>
  <!-- tutorial -->
  <div>
    <el-tour v-model="userInfoStore.tutorialStatement['routine']">
      <el-tour-step title="Routine" description="Here is your routine!" />
      <el-tour-step :target="ref1?.$el" title="Routine">
        <div>You can click here to add a customized routine.</div>
      </el-tour-step>
      <el-tour-step
        :target="ref2?.$el"
        title="Routine"
        description="You can navigate through these tabs to access and review your various routines."
      />
    </el-tour>
  </div>
</template>

<script setup lang='ts'>
  import RoutineTags from '@/components/Routine/RoutineTags.vue'
  import RoutineDialog from '@/components/Routine/Buttons/RoutineDialog.vue'
  import RoutineList from '@/components/Routine/RoutineList.vue';
  import { useDayroutineStore } from '@/stores/dayroutine';
  import { useSystemroutineStore } from '@/stores/systemroutine';
  import { onUpdated, ref } from 'vue';
  import { MoreFilled } from '@element-plus/icons-vue'
  import type { ButtonInstance } from 'element-plus'
  import { useUserInfoStore } from '@/stores/userInfo';

  const dayroutineStore = useDayroutineStore()
  const systemroutineStore = useSystemroutineStore()
  const userInfoStore = useUserInfoStore()
  const ref1 = ref<ButtonInstance>()
  const ref2 = ref<ButtonInstance>()

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
  background-color: #a8e6cf;
}

.randomtask{
  background-color: #dcedc1;
}

</style>