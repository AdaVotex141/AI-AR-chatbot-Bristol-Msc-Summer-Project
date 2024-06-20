<template>
    <div id="app" class="container">
        <div v-if="showLoginForm">
            <h2 style="font-family: 'Nunito', sans-serif; color: #438944;">LOGIN</h2>
            <el-form :model="loginForm" :rules="loginFormRules" ref="loginForm" label-width="80px">
                <el-form-item label="Username" prop="username">
                    <el-input v-model="loginForm.username"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input type="password" v-model="loginForm.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <div class="small-text">
                        Don't have an account? <el-link @click="switchToRegisterForm">Click here to register</el-link>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" class="center" @click="login">Login</el-button>
                </el-form-item>
            </el-form>
        </div>

        <div v-else>
            <h2 style="font-family: 'Nunito', sans-serif; color: #438944;">REGISTER</h2>
            <el-form :model="registerForm" :rules="registerFormRules" ref="registerForm" label-width="80px">
                <el-form-item label="Username" prop="username">
                    <el-input v-model="registerForm.username"></el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input type="password" v-model="registerForm.password"></el-input>
                </el-form-item>
                <el-form-item label="Email" prop="email">
                    <el-input v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item>
                    <div class="small-text">
                        Already have an account? <el-link @click="switchToLoginForm">Click here to login</el-link>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" class="center" @click="register">Register</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
    
<script lang='ts' setup>
    

</script>

<style scoped>
    body {
        background-color: #f0f0f0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        font-family: 'Nunito', sans-serif;
    }
    .container {
        background-color: white;
        max-width: 400px;
        margin: 50px auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        font-family: 'Nunito', sans-serif;
    }
    .form-group {
        margin-bottom: 15px;
    }
    .form-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }
    .form-group input {
        width: calc(100% - 10px);
        padding: 5px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }
    .form-group .small-text {
        font-size: 12px;
        color: #666;
        margin-top: 5px;
        text-align: center;
    }
    .form-group .link {
        cursor: pointer;
        color: blue;
        text-decoration: underline;
    }
    h2 {
        font-family: 'Indie Flower', cursive;
        font-size: 2.5rem;
        text-align: center;
    }
    button.center {
        display: flex;
        justify-content: center;
        background-color: #438944;
        color: white;
        width: 70%;
        border: none;
        padding: 10px;
        font-size: 16px;
        cursor: pointer;
        border-radius: 3px;
    }

</style>