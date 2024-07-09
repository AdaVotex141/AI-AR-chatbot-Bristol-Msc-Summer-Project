import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserInfoStore = defineStore('userInfo',()=>{
    let isAuthenticated = ref(false)
    let user = ref('')

    function login(userName:string){
        isAuthenticated.value = true
        user.value = userName
    }

    function logout(){
        isAuthenticated.value = false
        user.value = ''
    }

    return {isAuthenticated, user, login, logout}
})