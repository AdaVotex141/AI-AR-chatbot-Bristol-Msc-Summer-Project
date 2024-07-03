<template>
  <div class="container">
    <div class="title-container">
      <h2 class="title">REGISTER</h2>
    </div>
    <div style="margin: 10px" />
    <el-form
      ref="ruleFormRef"
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
            <el-input v-model="formLabelAlign.password" type="password" show-password/>
        </el-form-item>
        <el-form-item label="EMAIL" prop="email">
            <el-input v-model="formLabelAlign.email" />
            <el-button type="primary" class="center" @click="getVerificationCode">Get Verification Code</el-button>
        </el-form-item>
        <el-form-item class="tip-message">
            Already have an account? 
            <el-link type="primary" :underline="false" @click="$emit('toggle-page')" target="_blank">
                Click here to login
            </el-link>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" class="center" @click="register(ruleFormRef)">Register</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import axios from 'axios'
import { ElMessage, type FormInstance } from 'element-plus';

const ruleFormRef = ref<FormInstance>()

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
async function register(ruleFormRef: FormInstance | undefined){
    if (!ruleFormRef) return
    await ruleFormRef.validate(async (valid) => {
        if(!valid){
            ElMessage({
                    message: "Invalid information",
                    type: 'warning'
            })
        } else {
            try{
                const response = await axios.post('/api/register',{
                    username: formLabelAlign.username,
                    password: formLabelAlign.password,
                    email: formLabelAlign.email
                })
                // Check if the register is successful
                if(String(response.data.code) === '1'){
                    ElMessage({
                        message: 'Congrats, registration successful',
                        type: 'success'
                    })
                    formLabelAlign.username = '';
                    formLabelAlign.password = '';
                    formLabelAlign.email = '';
                    emits('toggle-page')
                } else {
                    ElMessage({
                        message: response.data.msg,
                        type: 'error'
                    })
                }
            } catch (error){
                console.error('Error sending data:', error)
                alert('Error sending data')
            }
        }
        
    })
}

async function getVerificationCode(){

}

</script>

<style scoped>
h2{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
}
.title-container {
  margin-top: 20px;
  width: 100%;
  height: 100px;
  text-align: center;
  max-width: 1024px;
  display: grid;
  place-items: center;
}

.title {
  text-align: center;
  font-size: 4rem;
  color: darkolivegreen;
}
.container{
  max-width:1024px;
  align-items: center;
  height: 100vh;
  max-height: 500px;
  padding: 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 1rem;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.tip-message {
  text-align: center;
}

.tip-message .el-link {
  font-weight: bold;
}

.el-button.center {
  display: block;
  margin: 0 auto;
  width: 100%;
  text-align: center;
  background-color:darkolivegreen;
  border-color: transparent;
}

.el-button.center:hover {
  background-color:darkseagreen;
  color: #fff;
  border-color: transparent;
}
</style>