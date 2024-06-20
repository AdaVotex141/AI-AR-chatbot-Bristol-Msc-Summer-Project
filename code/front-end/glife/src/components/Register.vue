<template>
    <h2 class="title">REGISTER</h2>
    <div style="margin: 20px" />
    <el-form
      label-position="top"
      label-width="auto"
      :model="formLabelAlign"
      :rules="rules"
      style="max-width: 600px"
    >
        <el-form-item label="Username" prop="username">
            <el-input v-model="formLabelAlign.name" />
        </el-form-item>
        <el-form-item label="Password" prop="password">
            <el-input v-model="formLabelAlign.password" type="password" />
        </el-form-item>
        <el-form-item label="Email" prop="email">
            <el-input v-model="formLabelAlign.email" />
        </el-form-item>
        <el-form-item class="tip-message">
            Already have an account? 
            <el-link type="primary" :underline="false" @click="$emit('toggle-page')" target="_blank">
                Click here to login
            </el-link>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" class="center" @click="register">Register</el-button>
        </el-form-item>
    </el-form>
    
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import axios from 'axios'

const formLabelAlign = reactive({
    name: '',
    password: '',
    email: ''
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
    ],
    email:[{
        required: true,
        message: 'Please enter your email',
        trigger: 'blur'
    }]
})

const emits = defineEmits(['toggle-page'])
async function register(){
    try{
        const response = await axios.post('/api/register',{
            username: formLabelAlign.name,
            password: formLabelAlign.password,
            email: formLabelAlign.email
        })

        
        console.log('Response:', response.data);
        
        formLabelAlign.name = '';
        formLabelAlign.password = '';
        formLabelAlign.email = '';
    } catch (error){
        console.error('Error sending data:', error)
    }
}

</script>
