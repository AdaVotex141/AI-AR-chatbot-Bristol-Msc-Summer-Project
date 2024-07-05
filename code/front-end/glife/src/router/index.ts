import {createRouter, createWebHistory} from 'vue-router'

//import components
import DayRoutine from '@/pages/DayRoutine.vue'
import ChatWindow from '@/pages/ChatWindow.vue'
import ArTree from '@/pages/ArTree/ArTree.vue'
import LoginRegister from '@/pages/LoginRegister.vue'
import MainPage from '@/pages/MainPage.vue'
import NotFoundPage from "@/pages/NotFoundPage.vue";
import InitPage from "@/pages/InitPage.vue";
import Setting from "@/pages/Setting.vue";
import BadgeSystem from "@/pages/ArTree/BadgeSystem.vue";

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
                },
                {
                    name:'setting',
                    path:'setting',
                    component:Setting
                },
                {
                    name:'mybadge',
                    path:'mybadge',
                    component: BadgeSystem
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
        {
            name:'startpage',
            path:'/welcome',
            component: InitPage
        }
    ]
})

//export router
export default router
