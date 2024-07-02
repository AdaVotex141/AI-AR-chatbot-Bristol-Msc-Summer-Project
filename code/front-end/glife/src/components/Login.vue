<template>
  <div class="container">
    <div class="title-container">
      <h2 class="title">LOG IN</h2>
    </div>
    <div style="margin: 10px" />
    <el-form
      ref="ruleFormRef"
      label-position="top"
      label-width="auto"
      :model="formLabelAlign"
      :rules="rules"
    >
        <el-form-item label="USERNAME" prop="username">
            <el-input v-model="formLabelAlign.username" />
        </el-form-item>
        <el-form-item label="PASSWORD" prop="password">
            <el-input v-model="formLabelAlign.password" type="password" show-password/>
        </el-form-item>
        <el-form-item class="tip-message">
            Don't have an account? 
            <el-link type="primary" :underline="false" @click="$emit('toggle-page')" target="_blank">
                Click here to register
            </el-link>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" class="center" @click="login(ruleFormRef)">Login</el-button>
        </el-form-item>
    </el-form>
  </div>
  <el-button @click="toChat">To the chat</el-button>

</template>

<script lang="ts" setup>
import axios from 'axios';
import { ElMessage, type FormInstance } from 'element-plus';
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router';

const ruleFormRef = ref<FormInstance>()

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
const router = useRouter()
const emits = defineEmits(['toggle-page'])

async function login(ruleFormRef: FormInstance | undefined){
  if (!ruleFormRef) return
  await ruleFormRef.validate(async (valid) => {
    // If form is invalid, giving a tips to the user
    if(!valid){
      ElMessage({
        message: "Please input valid information",
        type: 'warning'
      })
    } else {
      try{
        const response = await axios.post('/api/login',{
            username: formLabelAlign.username,
            password: formLabelAlign.password
        })
        /*
        Check if the login request pass the authentication
        If passed, push to the mainpage; If not, give an alert
        */
        if(String(response.data.code) === '1'){
          ElMessage({
            message: 'Login successfully',
            type: 'success'
          })
          router.push({
            name:'mainpage'
          })
        } else {
          ElMessage({
            message: response.data.msg,
            type: 'error'
          })
          formLabelAlign.password = ''
        }
      } catch (error){
          console.error('Error sending data:', error)
          alert('Error sending data')
      }
    }
  })
    
}
// for the main page UI test
function toChat(){
  router.push({
    name:'mainpage'
  })
}

</script>

<style scoped>
h2{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
}
body{
  background-color: #e8e8e8;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
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
  align-items: center;
  max-height: 60vh;
  padding: 1.5rem;
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
}
</style>