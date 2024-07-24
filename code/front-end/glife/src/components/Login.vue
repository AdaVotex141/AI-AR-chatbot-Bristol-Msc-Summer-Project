<template>
  <el-container :class="{'admin-background': formLabelAlign.isAdmin}">
    <el-header class="title">LOG IN</el-header>
    <div style="margin: 10px" />
    <el-main>
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
              {{tipMessage}}
              <el-link v-show="!formLabelAlign.isAdmin" type="primary" :underline="false" @click="$emit('toggle-page')" target="_blank">
                  Click here to register
              </el-link>
          </el-form-item>
          <el-form-item >
              <el-button type="primary" class="center" @click="login(ruleFormRef)">Login</el-button>
          </el-form-item>
          <el-switch class="center-switch" active-text="Admin" inactive-text="User" v-model="formLabelAlign.isAdmin" />       
      </el-form>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import { useUserInfoStore } from '@/stores/userInfo';
import axios from 'axios';
import { ElMessage, type FormInstance } from 'element-plus';
import { computed, reactive, ref } from 'vue'
import { useRouter } from 'vue-router';
import JSONBIG from 'json-bigint'
import { useWebSocketStore } from '@/stores/websocket';
import { useUserTaskStore } from '@/stores/usertask';

axios.defaults.transformResponse = [
  function (data){
    const json = JSONBIG({
      storeAsString: true
    })
    const res = json.parse(data)
    return res
  }
]

const userInfoStore = useUserInfoStore()
const websocketStore = useWebSocketStore()
const userTaskStore = useUserTaskStore()

const ruleFormRef = ref<FormInstance>()

const formLabelAlign = reactive({
    username: '',
    password: '',
    isAdmin: false
})

const tipMessage = computed(()=>{
  return formLabelAlign.isAdmin ? 'Welcome to glife admin system. Please log in.' : 'Don\'t have an account?'
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
      // Choose login methods according to the switch statement
      let apiPath = ''
      let nextPageName = ''
      if(!formLabelAlign.isAdmin){
        apiPath = '/api/login'
        nextPageName = 'dashboard'
      } else {
        apiPath = '/api/admin/login' // TODO: change the api request path
        nextPageName = 'admin'
      }
      try{
        const response = await axios.post(apiPath,{
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
            name: nextPageName
          })
          // Change the user info 
          userInfoStore.login(response.data.data)
          // If it is a user
          if(!formLabelAlign.isAdmin){
            // Connect websocket
            websocketStore.connect(`ws://localhost:8040/message?userId=${userInfoStore.userid}`)
            // Get user's random task
            userTaskStore.getRandomTask()
          }
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
        router.push({
          name:'notfound'
        })
      }
    }
  })
}

</script>

<style scoped>
body{
  background-color: #e8e8e8;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
}

.title {
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
  text-align: center;
  font-size: 4rem;
  color: darkolivegreen;
}

.el-container{
  align-items: center;
  max-height: 70vh;
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

.center-switch {
  display: flex;
  justify-content: center;
  align-items: center;
}

.admin-background {
  background-color: #f5f5dc;
}
</style>