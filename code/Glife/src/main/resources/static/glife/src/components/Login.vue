<template>
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
        <el-input v-model="formLabelAlign.password" type="password"/>
      </el-form-item>
      <el-form-item class="tip-message">
        Don't have an account? 
        <el-link type="primary" :underline="false" @click="switchToRegisterForm" target="_blank">
            Click here to register
        </el-link>
      </el-form-item>
    </el-form>
    
</template>

<script lang="ts" setup>
import { reactive } from 'vue'

const formLabelAlign = reactive({
    name: '',
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

function switchToRegisterForm(){
    console.log("cnm")
}

</script>
