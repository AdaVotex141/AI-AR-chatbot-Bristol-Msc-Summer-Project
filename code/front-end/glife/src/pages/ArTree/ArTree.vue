<template xmlns="http://www.w3.org/1999/html">
    <div class="ar-container">
      <el-container>
        <el-container>
          <el-main><div class="background-image-container">Growing tree</div></el-main>
          <el-footer><el-button @click="handleClick"><h2>Plant</h2></el-button></el-footer>
        </el-container>
      </el-container>
    </div>

</template>

<script lang='ts' setup>
import { ref, computed } from 'vue';
import { ElMessage } from 'element-plus'

const plant_con = ref(false);
const clickCount = ref(0);
const isDisabled = computed(() => !plant_con.value);
// Add the function to let the plant button has condition
const handleClick = () => {
  clickCount.value += 1;
  if (clickCount.value > 3) {
    plant_con.value = true;
  }
  if(isDisabled.value){
    // Condition not met
    ElMessage({
      message: 'You have to finish the day routine to get the chance',
      type: "warning",
    });
  }else{
    ElMessage({
      message: 'Congratulations! Now you can plant your tree!',
      type: 'success',
    });
    setTimeout(() => {
      window.location.href = 'https://jiebristol.github.io/89';
      clickCount.value = 0;
      plant_con.value = false;
    },1500);
  }
}
</script>

<style scoped>
@media(max-width: 600px) {
  .ar-container {
    margin-top: 2.5rem;
    width: 100vw;
    height: 90vh;
  }
}
@media(min-width: 601px) {
  .ar-container{
    margin-top: 2.5rem;
    width: 60vw;
    height: 90vh;
    margin-left: auto;
    margin-right: auto;
  }
}

.background-image-container{
  display: flex;
  background-color: #738352;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  min-width: 300px;
  min-height: 500px;
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
  border-color: transparent;
  flex-direction: column;
  align-items: center;
}
.el-button:hover {
  background-color:darkseagreen;
  color: #fff;
  border-color: transparent;
}
.el-footer{
  display: flex;
  justify-content: center;
}

.el-main{
  display: flex;
  justify-content: center;
}
.button-list{
  display: flex;
  align-items: flex-end;
  justify-content: center;
  flex-direction: column;
}
</style>