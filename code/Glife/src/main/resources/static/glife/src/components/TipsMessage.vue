<template>
    <el-form-item>
        <div class="small-text">
            {{msg}} <el-link @click="switchToRegisterForm">Click here to register</el-link>
        </div>
    </el-form-item>
</template>

<script lang='ts' setup>
import { defineProps } from 'vue';
const props = defineProps({
    msg: String
})
function switchToRegisterForm(){
    console.log("hello")
}

</script>

<style scoped>
</style>