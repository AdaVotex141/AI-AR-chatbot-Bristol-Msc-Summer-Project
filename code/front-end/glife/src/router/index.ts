import {createRouter, createWebHistory} from 'vue-router'

//import components
import DayRoutine from '@/pages/DayRoutine.vue'
import ChatWindow from '@/components/ChatWindow.vue'
import ArTree from '@/pages/ArTree.vue'
import LoginRegister from '@/pages/LoginRegister.vue'
import MainPage from '@/pages/MainPage.vue'

//create router
const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/login',
            component:LoginRegister
        },
        {
            name:'mainpage',
            path:'/mainpage',
            component:MainPage,
            children:[
                {
                    name:'chatwindow',
                    path:'chatwindow',
                    component:ChatWindow
                },
                {
                    name:'artree',
                    path:'artree',
                    component:ArTree
                },
                {
                    name:'dayroutine',
                    path:'dayroutine',
                    component:DayRoutine
                }
            ]
        },
        {
            path:'/',
            redirect:'/login'
        }
    ]
})

//export router
export default router
