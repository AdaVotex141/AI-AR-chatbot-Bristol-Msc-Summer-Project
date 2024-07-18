import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserInfoStore = defineStore('userInfo',()=>{
    let isAuthenticated = ref(false)
    let user = ref('')
    let loginDays = ref(0)
    let userid = ref(0)
    let email = ref('')

    function login(data:any){
        isAuthenticated.value = true
        user.value = data.username
        loginDays.value = data.loginDays
        userid.value = data.id
        email.value = data.email
    }

    function logout(){
        isAuthenticated.value = false
        user.value = ''
        loginDays.value = 0
        userid.value = 0
        email.value = ''
    }

    return {isAuthenticated, user, loginDays, userid, email, login, logout}
})