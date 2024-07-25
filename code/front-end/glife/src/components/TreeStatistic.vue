<template>
    <div>
        {{ message }}
    </div>
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
    
</style>