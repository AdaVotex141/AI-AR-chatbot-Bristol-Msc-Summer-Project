<template>
    <el-container>
        <el-header>Tree Status</el-header>
        <el-main>{{ message }}</el-main>
    </el-container>
</template>
<script setup lang='ts'>
import router from '@/router';
import axios from 'axios';
import { computed, onMounted, ref } from 'vue';

const message = computed(()=>{
    return 'You have already planted ' + treeSum.value + " trees."
})
const treeSum = ref(0)

onMounted(()=> {
    getTreeInfo()
})

async function getTreeInfo(){
    try{
        const response = await axios.get('/api/ARTree/getTreeSum')
        console.log(response)
        if(String(response.data.code) !== '1'){
            console.error('cannot get treesum in the treestatistic.')
        } else {
            treeSum.value = response.data.data
        }
    } catch (error){
        console.error(error)
        router.replace({
            name: 'notfound'
        })
    }
}
</script>
<style scoped>
.el-header{
    background-color: #9cb470;
    padding: 1.5rem;
    margin-bottom: 0.5rem;
    font-size: 1.5rem;
    color: whitesmoke;
    font-weight: bold;
    font-family: 'Cooper Black',sans-serif;
    border-bottom: 1px solid #e0e0e0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    top: 5%;
}
</style>