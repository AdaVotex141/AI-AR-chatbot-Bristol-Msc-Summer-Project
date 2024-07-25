<template xmlns="http://www.w3.org/1999/html">
    <div class="ar-container">
        <el-container>
          <el-main>
            <div class="background-image-container" :style="{ backgroundImage: 'url(' + treepointsStore.treeImageSrc + ')' }" />
          </el-main>
          <!-- <el-footer><el-button :disabled="isDisabled" @click="handleClick"><h2>Plant</h2></el-button></el-footer> -->
          <el-footer>
            <el-button @click="handlePlant" :disabled="isDisabled"><h2>Plant</h2></el-button>
            <el-button @click="handleCamera"><h2>Camera</h2></el-button>
          </el-footer>
        </el-container>
    </div>
</template>

<script lang='ts' setup>
import { ref, computed, onMounted, watch } from 'vue';
import { useTreepointsStore } from "@/stores/treepoints";
import router from '@/router';
import { ElMessage } from 'element-plus';

const treepointsStore = useTreepointsStore()

// fetch the data
onMounted(() => {
  treepointsStore.fetchCanPlantTree();
  treepointsStore.fetchTreePoints()
});

// Is the plant tree button disabled
const isDisabled = computed(() => {
  if(treepointsStore.treePoints == 8){
    return false
  } else {
    return true
  }
});

// handle plant
const handlePlant = () => {
    router.push({
      name:'artree-camera'
    })
};

// const handlePlant = () => {
//     router.push({
//       name:'artree-camera'
//     })
// };

function handleCamera(){
  router.push({
    name:'ar-camera'
  })
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
@media (min-width: 601px) {
  .background-image-container{
    display: flex;
    align-items: center;
    background-color: white;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    width: 30vw;
    height: 70vh;
    font-size: 2rem;
    border-radius: 1rem;
  }
}
@media (max-width: 600px) {
  .background-image-container{
    display: flex;
    background-image: 'url($(treeImageSrc.value))';
    background-size: 50% 50%;
    background-color: white;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    min-width: 60vw;
    min-height: 70vh;
    font-size: 2rem;
    border-radius: 1rem;
  }
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
.el-button:disabled {
  background-color: #90EE90;    /* 禁用状态背景颜色 */
  color: black;                /* 禁用状态文字颜色 */
  cursor: not-allowed;        /* 禁用状态鼠标样式 */
  box-shadow: none;           /* 禁用状态移除阴影 */
  opacity: 0.6;               /* 禁用状态降低不透明度 */
}
.button:disabled:hover {
  background-color: #cccccc; /* 仍然保持灰色 */
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