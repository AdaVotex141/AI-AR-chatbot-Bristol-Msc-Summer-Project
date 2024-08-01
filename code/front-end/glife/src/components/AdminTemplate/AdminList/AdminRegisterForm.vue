<template>
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
        <el-form-item>
            <el-button type="primary" class="center text-white inline-flex w-full justify-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" 
            @click="addNewAdmin(ruleFormRef)">Add</el-button>
        </el-form-item>
    </el-form>
  </template>
  
  <script lang="ts" setup>
  import { reactive, ref } from 'vue'
  import axios from 'axios'
  import { ElMessage, type FormInstance } from 'element-plus';
  import router from "@/router";
import { useAdminStore } from '@/stores/admin';

  const ruleFormRef = ref<FormInstance>()
  
    const props = defineProps({
        toggleModal: {
            type: Function,
            default: ()=>{}
        }
    });

    const adminStore = useAdminStore()

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
  })
  
  const rules = reactive({
      username:[
      {
          required: true, 
          message: 'Please input the username', 
          trigger: 'blur'
      }
      ],
      password:[
      {
          required: true,
          message: 'Please input the password',
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
  })
  
  async function addNewAdmin(ruleFormRef: FormInstance | undefined){
      if (!ruleFormRef) return
      await ruleFormRef.validate(async (valid) => {
          if(!valid){
              ElMessage({
                message: "Invalid information",
                type: 'warning'
              })
          } else {
              try{
                  const response = await axios.post('/api/admin/add',{
                      username: formLabelAlign.username,
                      password: formLabelAlign.password,
                  })
                  // Check if the register is successful
                  if(String(response.data.code) === '1'){
                        ElMessage({
                            message: 'Congrats, adding new admin successful',
                            type: 'success'
                        })
                        formLabelAlign.username = '';
                        formLabelAlign.password = '';
                        // Turn off the modal and call get function
                        adminStore.getAdmins()
                        props.toggleModal()
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
    padding: 10px;
    margin: 5px;
    background-color: #9cb470;
    border-color: transparent;
  }
  
  .el-button.center:hover {
    background-color:darkseagreen;
    color: #fff;
    border-color: transparent;
  }
  </style>