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
            path:'/chatwindow',
            component:ChatWindow
        },
        {
            path:'/dayroutine',
            component:DayRoutine
        },
        {
            path:'/artree',
            component:ArTree
        },
        {
            path:'/login',
            component:Login
        },
        {
            path:'/',
            redirect:'/login'
        }
    ]
})

//export router
export default router
