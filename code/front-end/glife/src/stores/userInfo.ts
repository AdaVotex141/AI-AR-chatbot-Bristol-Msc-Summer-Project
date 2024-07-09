import { defineStore } from 'pinia'

export const useUserInfoStore = defineStore('userInfo',()=>{
    let isAuthenticated = false
    let user = null

    function login(userName:string){
        isAuthenticated = true
        user = userName
    }

    function logout(){
        isAuthenticated = false
        user = null
    }

    return {isAuthenticated, user, login, logout}
})