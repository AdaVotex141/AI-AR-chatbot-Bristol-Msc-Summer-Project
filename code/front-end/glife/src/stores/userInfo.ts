import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserInfoStore = defineStore('userInfo',()=>{
    let isAuthenticated = ref(false)
    let user = ref('')
    let loginDays = ref(0)
    let userid = ref('')
    let email = ref('')
    let permission = ref(0)

    function login(data:any){
        isAuthenticated.value = true
        user.value = data.username
        loginDays.value = data.loginDays
        userid.value = String(data.id)
        email.value = data.email
        permission.value = data.permission
    }

    function logout(){
        isAuthenticated.value = false
        user.value = ''
        loginDays.value = 0
        userid.value = ''
        email.value = ''
        permission.value = 0
    }

    return {isAuthenticated, user, loginDays, userid, email, permission, login, logout}
})