<template xmlns="http://www.w3.org/1999/html">
    <div class="ar-container">
        <el-container>
          <el-main><div class="background-image-container"><img :src="treeImageSrc" /></div></el-main>
          <!-- <el-footer><el-button :disabled="isDisabled" @click="handleClick"><h2>Plant</h2></el-button></el-footer> -->
          <el-footer><el-button @click="handleClick"><h2>Plant</h2></el-button></el-footer>
        </el-container>
    </div>
</template>

<script lang='ts' setup>
import { ref, computed, onMounted } from 'vue';
import { ElMessage } from 'element-plus'
import { fetchTreePoints, fetchCanPlantTree, updateTreeImage } from "@/stores/treepoints";
import router from '@/router';

// define the response data
const treeImageSrc = ref<string>('');
let treePoints = ref<number | null>(null);
const canPlantTree = ref<boolean | null>(null);

// fetch the data
onMounted(async () => {
  const treeData = await fetchTreePoints();
  treeImageSrc.value = treeData.treeImageSrc.value;
  treePoints.value = treeData.treePoints.value;

  const canPlantTreeData = await fetchCanPlantTree();
  canPlantTree.value = canPlantTreeData.value;
});

// Is the plant tree button disabled
const isDisabled = computed(() => !canPlantTree.value);

// handle click
// const handleClick = () => {
//   if (!isDisabled.value) {
//     ElMessage({
//       message: 'You have to finish the day routine to get the chance',
//       type: 'warning',
//     });
//   } else {
//     ElMessage({
//       message: 'Congratulations! Now you can plant your tree!',
//       type: 'success',
//     });
//     router.push({
//       name:'artree-camera'
//     })
//   }
// };

const handleClick = () => {
  
    router.push({
      name:'artree-camera'
    })
  
};
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
    background-color: white;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    min-width: 30vw;
    min-height: 70vh;
    font-size: 2rem;
    border-radius: 1rem;
  }
}
@media (max-width: 600px) {
  .background-image-container{
    display: flex;
    background-image: 'url($(treeImageSrc.value))';
    background-size: 100% 100%;
    background-color: white;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    min-width: 80vw;
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