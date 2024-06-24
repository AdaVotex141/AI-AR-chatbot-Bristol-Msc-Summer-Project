<template>
    <h2 class="title">LOGIN</h2>
    <div style="margin: 20px" />
    <el-form
      label-position="top"
      label-width="auto"
      :model="formLabelAlign"
      :rules="rules"
      style="max-width: 600px"
    >
        <el-form-item label="Username" prop="username">
            <el-input v-model="formLabelAlign.username" />
        </el-form-item>
        <el-form-item label="Password" prop="password">
            <el-input v-model="formLabelAlign.password" type="password"/>
        </el-form-item>
        <el-form-item class="tip-message">
            Don't have an account? 
            <el-link type="primary" :underline="false" @click="$emit('toggle-page')" target="_blank">
                Click here to register
            </el-link>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" class="center" @click="login">Login</el-button>
        </el-form-item>
    </el-form>
    
</template>

<script lang="ts" setup>
import axios from 'axios';
import { reactive } from 'vue'

const formLabelAlign = reactive({
    username: '',
    password: '',
})

const rules = reactive({
    username:[
    {
        required: true, 
        message: 'Please enter your username', 
        trigger: 'blur'
    }
    ],
    password:[
    {
        required: true,
        message: 'Please enter your password',
        trigger: 'blur'
    }
    ]
})

const emits = defineEmits(['toggle-page'])

async function login(){
    try{
        const response = await axios.post('/api/login',{
            username: formLabelAlign.username,
            password: formLabelAlign.password
        })
        
        

    } catch (error){
        console.error('Error sending data:', error)
        alert('Login failure')
    }
}

</script>
