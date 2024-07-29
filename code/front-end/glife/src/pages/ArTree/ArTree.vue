<template xmlns="http://www.w3.org/1999/html">
    <div class="ar-container">
        <el-container>
          <el-main ref="ref1">
            <div class="background-image-container" :style="{ backgroundImage: 'url(' + treepointsStore.treeImageSrc + ')' }" />
          </el-main>
          <el-footer>
            <el-button ref ="ref2" @click="handlePlant" :disabled="isDisabled"><h2>Plant</h2></el-button>
            <el-button ref="ref3"  @click="handleCamera"><h2>Camera</h2></el-button>
          </el-footer>
        </el-container>
      <el-tour v-model="userInfoStore.tutorialStatement['artree']">
        <el-tour-step
            :target="ref1?.$el"
            title="Growing Tree">
          <div>Here, you can make the tree grow by earning points.</div>
        </el-tour-step>
        <el-tour-step
            :target="ref1?.$el"
            title="Earn Points Way"
            description="You can earn points by completing random tasks assigned by the system."
        />
        <el-tour-step
            :target="ref1?.$el"
            title="Earn Points Way"
            description="You can also earn points by completing scheduled tasks added by the robot."
        />
        <el-tour-step
            :target="ref2?.$el"
            title="Plant the Tree"
            description="Here, by pressing this button, you can open the camera and plant a virtual tree."
        />
        <el-tour-step
            :target="ref2?.$el"
            title="Plant the Tree"
            description="The button will only be enabled when the tree has grown to its final stage."
        />
        <el-tour-step
            :target="ref3?.$el"
            title="Share or View the Tree"
            description="Here, you can view the status of your trees and others' trees on the map."
        />
      </el-tour>
    </div>
</template>

<script lang='ts' setup>
import { ref, computed, onMounted, watch } from 'vue';
import { useTreepointsStore } from "@/stores/treepoints";
import { useUserInfoStore } from '@/stores/userInfo';
import router from '@/router';
import {type ButtonInstance, ElMessage} from 'element-plus';

const userInfoStore = useUserInfoStore()
const ref1 = ref<ButtonInstance>()
const ref2 = ref<ButtonInstance>()
const ref3 = ref<ButtonInstance>()

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
  background-color: #90EE90;
  color: black;
  cursor: not-allowed;
  box-shadow: none;
  opacity: 0.6;
}
.el-button:disabled:hover {
  background-color: #cccccc;
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