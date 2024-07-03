<template xmlns="http://www.w3.org/1999/html">
    <div class="ar-container">
      <el-container>
        <el-aside width="15vw"><div class = "aside-title">Your Badges</div>
          <div style="height: 7rem;
            background-color: #caeea4;
            border-radius: 1rem;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            display: flex;
            text-align: center;
            align-items: center;" >Status: You don't have any badges yet...
          </div>
        <div class = "button-list">
          <el-button type="primary" round class="new-button" @click = "profileClick">Profile</el-button>
          <el-button type="primary" round class="new-button" @click = "badgesClick">Badge</el-button>
          <el-button type="primary" round class="new-button" @click = "shareClick">Share</el-button>
        </div>
        </el-aside>
        <el-container>
          <el-main><div class="background-image-container"></div></el-main>
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
const profileClick = () => {
  ElMessage({
    message: 'You can look the personal profile by clicking this button',
    type: "info",
  })
}

const badgesClick = () => {
  ElMessage({
    message: 'You can look all the badges you can get by clicking this button',
    type: "info",
  })
}

const shareClick = () => {
  ElMessage({
    message: 'You can share the badges you get by click this button',
    type: "info",
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
.new-button{
  margin-top: 2rem;
  margin-bottom: 0.5rem;
  width: 100%;
  justify-content: center;

}
.aside-title{
  font-family: 'Cooper Black',sans-serif;
  font-size: 1.5rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

</style>