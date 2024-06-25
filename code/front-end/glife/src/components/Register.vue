<template>
  <div class="container">
    <div class="title-container">
      <h2 class="title">REGISTER</h2>
    </div>
    <div style="margin: 10px" />
    <el-form
      label-position="top"
      label-width="auto"
      :model="formLabelAlign"
      :rules="rules"
      style="max-width: 600px"
    >
        <el-form-item label="USERNAME" prop="username">
            <el-input v-model="formLabelAlign.username" />
        </el-form-item>
        <el-form-item label="PASSWORD" prop="password">
            <el-input v-model="formLabelAlign.password" type="password" />
        </el-form-item>
        <el-form-item label="EMAIL" prop="email">
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
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import axios from 'axios'

const formLabelAlign = reactive({
    username: '',
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
            username: formLabelAlign.username,
            password: formLabelAlign.password,
            email: formLabelAlign.email
        })
        // Check if the register is successful
        console.log('Response:', response.data);
        
        formLabelAlign.username = '';
        formLabelAlign.password = '';
        formLabelAlign.email = '';
    } catch (error){
        console.error('Error sending data:', error)
        alert('Error sending data')
    }
}

</script>
