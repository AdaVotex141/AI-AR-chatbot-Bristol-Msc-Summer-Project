import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useWebSocketStore } from './websocket'

export const useUserInfoStore = defineStore('userInfo',()=>{
    let isAuthenticated = ref(false)
    let user = ref('')
    let loginDays = ref(0)
    let userid = ref(0)
    let email = ref('')
    let permission = ref(0)
    const websocketStore = useWebSocketStore()

    function login(data:any){
        isAuthenticated.value = true
        user.value = data.username
        loginDays.value = data.loginDays
        userid.value = data.id
        email.value = data.email
        permission.value = data.permission
        websocketStore.connect(`ws://localhost:8040/message?userId=${userid.value}`)
    }

    function logout(){
        isAuthenticated.value = false
        user.value = ''
        loginDays.value = 0
        userid.value = 0
        email.value = ''
        permission.value = 0
        websocketStore.close()
    }

    return {isAuthenticated, user, loginDays, userid, email, permission, login, logout}
})