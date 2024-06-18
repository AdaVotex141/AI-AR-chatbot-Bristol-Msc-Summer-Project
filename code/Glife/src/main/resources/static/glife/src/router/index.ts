import {createRouter, createWebHistory} from 'vue-router'

//import components
import DayRoutine from '@/components/DayRoutine.vue'
import ChatWindow from '@/components/ChatWindow.vue'

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
        }
    ]
})

//export router
export default router
