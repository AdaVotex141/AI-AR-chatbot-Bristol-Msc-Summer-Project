<template>
    <el-form
        ref="ruleFormRef"
        label-position="top"
        label-width="auto"
        :model="formLabelAlign"
        :rules="rules"
        style="max-width: 600px"
    >
        <el-form-item label="Task name" prop="taskname">
            <el-input v-model="formLabelAlign.taskname" />
        </el-form-item>
        <el-form-item label="Description" prop="description">
            <el-input v-model="formLabelAlign.description" type="textarea" />
        </el-form-item>
        <el-form-item label="Periods" prop="period">
            <el-radio-group v-model="formLabelAlign.period">
                <el-radio border value="daily">Daily</el-radio>
                <el-radio border value="weekly">Weekly</el-radio>
                <el-radio border value="monthly">Monthly</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" class="center text-white inline-flex w-full justify-center bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" 
            @click="addNewTask(ruleFormRef)">Add</el-button>
        </el-form-item>
    </el-form>
  </template>
  
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import axios from 'axios'
import { ElMessage, type FormInstance } from 'element-plus';
import router from "@/router";

const ruleFormRef = ref<FormInstance>()

const props = defineProps({
    toggleModal: {
        type: Function,
        default: ()=>{}
    }
});

const formLabelAlign = reactive({
    taskname: '',
    description: '',
    period:'daily',
})

const rules = reactive({
    taskname:[
    {
        required: true, 
        message: 'Please input the Task name', 
        trigger: 'blur'
    }
    ],
    description:[
    {
        required: true,
        message: 'Please input the description',
        trigger: 'blur'
    }
    ],
    period: [
    {
      required: true,
      message: 'Please select the period',
      trigger: 'change',
    },
  ],
})

async function addNewTask(ruleFormRef: FormInstance | undefined){
    if (!ruleFormRef) return
    await ruleFormRef.validate(async (valid) => {
        if(!valid){
            ElMessage({
            message: "Invalid information",
            type: 'warning'
            })
        } else {
            // // Send api request to the back end, TODO -----
            // try{
            //     const response = await axios.post('/api/admin/add',{
            //         taskname: formLabelAlign.taskname,
            //         description: formLabelAlign.description,
            //     })
            //     // Check if the register is successful
            //     if(String(response.data.code) === '1'){
            //         ElMessage({
            //             message: 'Congrats, adding new task successfully',
            //             type: 'success'
            //         })
            //         formLabelAlign.taskname = '';
            //         formLabelAlign.description = '';
            //         // Turn off the modal and call get function
            //         adminStore.getAdmins()
            //         props.toggleModal()
            //     } else {
            //         ElMessage({
            //             message: response.data.msg,
            //             type: 'error'
            //         })
            //     }
            // } catch (error){
            //     console.error('Error sending data:', error)
            //     alert('Error sending data')
            //     router.push({
            //     name:'notfound'
            //     })
            // }
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