<template>
  <el-container>
    <el-header class="title">REGISTER</el-header>
    <div style="margin: 10px" />
    <el-main>
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
          <el-form-item label="Confirm Password" prop="confirmPassword">
              <el-input v-model="formLabelAlign.confirmPassword" type="password" show-password/>
          </el-form-item>
          <el-form-item label="EMAIL" prop="email">
              <el-input v-model="formLabelAlign.email" />
              <el-button type="primary" class="center" 
              @click="getVerificationCode"
              :disabled="countdown > 0"
              >
              {{buttontext}}
              </el-button>
          </el-form-item>
          <el-form-item label="VERIFICATION-CODE" prop="verificationCode">
            <el-input v-model="formLabelAlign.verificationCode" />
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
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import { computed, onMounted, reactive, ref } from 'vue'
import axios from 'axios'
import { ElMessage, type FormInstance } from 'element-plus';
import router from "@/router";
import VueCookies from 'vue-cookies';

const ruleFormRef = ref<FormInstance>()

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the password again'))
  } else if (value !== formLabelAlign.password) {
    callback(new Error("Two inputs don't match!"))
  } else {
    callback()
  }
}

const formLabelAlign = reactive({
    username: '',
    password: '',
    confirmPassword:'',
    email: '',
    verificationCode:''
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
    confirmPassword:[
      {
        required: true,
        validator: validatePass,
        trigger: 'blur'
      }
    ],
    verificationCode:[
      {
        required: true,
        message: 'Please enter the verification code',
        trigger: 'blur'
      }
    ],
    email:[{
        required: true,
        message: 'Please enter your email',
        trigger: 'blur'
      },
      {
        type: 'email',
        message: 'Please input correct email address',
        trigger: 'blur'
      }
    ]
})

const emits = defineEmits(['toggle-page'])
const countdown = ref(0)
let timer: number | undefined
const buttontext = computed(()=>{
  return countdown.value > 0 ? `wait ${countdown.value}s` : 'Get Verification Code' 
})
const Cookies:any = VueCookies

onMounted(()=>{
  updateCountdown()
})

function updateCountdown() {
  const savedData = Cookies.get('countdownForCode');
  if (savedData) {
    const expireTime = parseInt(savedData);
    const now = Date.now();
    
    if (now < expireTime) {
      countdown.value = Math.floor((expireTime - now) / 1000);
      startCountdown(); 
    } else {
      Cookies.remove('countdownForCode'); 
      countdown.value = 0; 
    }
  } else {
    countdown.value = 0
  }
}


function startCountdown(){
  if(timer) clearInterval(timer)
  // Get a expired time to set cookie
  const expireTime = Date.now() + countdown.value * 1000
  Cookies.set('countdownForCode', expireTime.toString(), "1MIN")
  // Decrease the value of countdown and update the cookie
  timer = setInterval(()=>{
    // Adjust countdown.value according to the remaining time
    const now = Date.now()
    const remainingTime = Math.max(Math.floor((expireTime - now) / 1000), 0)
    countdown.value = remainingTime
    // The countdown is over
    if(remainingTime <= 0){
      clearInterval(timer)
      Cookies.remove('countdownForCode')
    }
  }, 1000)
}

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
                    email: formLabelAlign.email,
                },{
                  params: {
                    code: formLabelAlign.verificationCode
                  },
                  headers: {
                    'Content-Type': 'application/json'
                  }
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
                router.push({
                  name:'notfound'
                })
            }
        }
        
    })
}

async function getVerificationCode(){
  try{
    // Validate the email field
    await ruleFormRef.value?.validateField('email')

    const response = await axios.post('/api/sendCode', formLabelAlign.email,{
      headers:{
        'Content-Type': 'application/json'
      }
    })

    if(String(response.data.code) === '1'){
      ElMessage({
        message: 'Verification Code has been sent, please check your email.',
        type: 'success'
      })
      // Start the countdown
      countdown.value = 60
      startCountdown()
    } else {
      ElMessage({
        message: 'Something bad happened, please try again',
        type: 'error'
      })
    }
  } catch (error) {
    ElMessage({
        message: 'Your email information is wrong, please check it',
        type: 'error'
    })
  }
}

</script>

<style scoped>

.title {
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
  text-align: center;
  font-size: 4rem;
  color: darkolivegreen;
}
.el-container{
  max-width:1024px;
  align-items: center;
  height: 100vh;
  max-height: 90vh;
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