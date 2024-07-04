import {createRouter, createWebHistory} from 'vue-router'

//import components
import DayRoutine from '@/pages/DayRoutine.vue'
import ChatWindow from '@/pages/ChatWindow.vue'
import ArTree from '@/pages/ArTree.vue'
import LoginRegister from '@/pages/LoginRegister.vue'
import MainPage from '@/pages/MainPage.vue'
import NotFoundPage from "@/pages/NotFoundPage.vue";

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
            ],
            redirect:'/mainpage/chatwindow'
        },
        {
            path:'/',
            redirect:'/login'
        },
        {
            name: 'notfound',
            path:'/notfound',
            component: NotFoundPage
        },
        {
            name:'logout',
            path:'/login',
            component:LoginRegister
        },
    ]
})

//export router
export default router
