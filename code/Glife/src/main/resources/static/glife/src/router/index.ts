import {createRouter, createWebHistory} from 'vue-router'

//import components
import DayRoutine from '@/pages/DayRoutine.vue'
import ChatWindow from '@/components/ChatWindow.vue'
import ArTree from '@/pages/ArTree.vue'
import Login from '@/pages/Login.vue'

//create router
const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/',
            component:ChatWindow
        },
        {
            path:'/dayroutine',
            component:DayRoutine
        },
        {
            path:'/arTree',
            component:ArTree
        },
        {
            path:'/login',
            component:Login
        }
    ]
})

//export router
export default router
