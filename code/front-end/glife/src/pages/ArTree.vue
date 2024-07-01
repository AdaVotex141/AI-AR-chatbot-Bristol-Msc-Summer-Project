<template>
  <div class="ar-container">
    <div class="background-image-container"></div>
    <div style="margin: 0.8rem"></div>
    <div class="main-content">
      <el-button @click="toPlant"><h2>Plant</h2></el-button>
    </div>
  </div>



</template>

<script lang='ts' setup>
import Tree from "@/assets/PlantTree.jpg";
import router from "@/router";
function toPlant(){
  router.push({
    name:'mainpage'
  })
}
</script>

<style scoped>
.ar-container{
  width: 300px;
  height: 400px;
}
.background-image-container {
  background-image: url('@/assets/PlantTree.jpg');
  display:flex;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  width: 300px;
  height: 350px;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 2rem;
}
  h2{
    font-weight: bold;
    font-family: 'Cooper Black',sans-serif;
    color: white;
    font-size: 1.4rem;
  }
  .main-content {
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    align-items: center;
  }
  .el-button {
    margin-bottom: 0.5rem;
    display: block;
    width: 50%;
    text-align: center;
    background-color:darkolivegreen;
  }
  .el-button:hover {
    background-color:darkseagreen;
    color: #fff;
  }

</style>