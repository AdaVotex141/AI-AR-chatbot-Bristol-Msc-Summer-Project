<template>
    <div class="ar-container">
      <el-container>
        <el-aside width="15vw">Look the Badge here</el-aside>
        <el-container>
          <el-main><div class="background-image-container"></div></el-main>
          <el-footer><el-button @click="toPlant"><h2>Plant</h2></el-button></el-footer>
        </el-container>
      </el-container>
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
  width: 80vw;
  height: 50vh;
}
.background-image-container{
  background-image: url('@/assets/PlantTree.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  width: 500px;
  height: 500px;
  font-size: 2rem;
  border-radius: 1rem;
}
h2{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
  color: white;
  font-size: 1.4rem;
}
.el-button {
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  display: block;
  width: 50%;
  text-align: center;
  background-color:darkolivegreen;
  flex-direction: column;
  align-items: center;
}
.el-button:hover {
  background-color:darkseagreen;
  color: #fff;
}

.el-footer{
  display: flex;
  justify-content: center;
}

.el-main{
  display: flex;
  justify-content: center;
}
</style>