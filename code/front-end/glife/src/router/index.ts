import {createRouter, createWebHistory} from 'vue-router'

//import components
import DayRoutine from '@/pages/User/DayRoutine.vue'
import ChatWindow from '@/pages/User/ChatWindow.vue'
import ArTree from '@/pages/ArTree/ArTree.vue'
import LoginRegister from '@/pages/User/LoginRegister.vue'
import MainPage from '@/pages/User/MainPage.vue'
import NotFoundPage from "@/pages/NotFoundPage.vue";
import InitPage from "@/pages/InitPage.vue";
import BadgeSystem from "@/pages/ArTree/BadgeSystem.vue";
import ARTreeCamera from "@/pages/ArTree/ARTreeCamera.vue"
import { useUserInfoStore } from '@/stores/userInfo'
import { protectedPaths } from './protectedPaths'
import AdminPage from '@/pages/Admin/AdminPage.vue'
import MemberList from '@/pages/Admin/MemberList.vue'
import Task from '@/pages/Admin/Task.vue'
import DashBoard from '@/pages/User/DashBoard.vue'
import ARCamera from '@/pages/ArTree/ARCamera.vue'

//create router
const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            name:'login',
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
                    component:ArTree,
                },
                {
                    name:'dayroutine',
                    path:'dayroutine',
                    component:DayRoutine
                },
                {
                    name:'mybadge',
                    path:'mybadge',
                    component: BadgeSystem
                },
                {
                    name:'dashboard',
                    path:'dashboard',
                    component: DashBoard
                }
            ],
            redirect:'/mainpage/dashboard'
        },
        {
            name:'admin',
            path:'/admin',
            component: AdminPage,
            children:[
                {
                    name:'adminlist',
                    path:'adminlist',
                    component: MemberList
                },
                {
                    name:'task',
                    path:'task',
                    component: Task
                }
            ],
            redirect:'/admin/task'
        },
        {
            name:'artree-camera',
            path:'/artree-camera',
            component: ARTreeCamera
        },
        {
            name:'ar-camera',
            path:'/ar-camera',
            component: ARCamera
        },
        {
            path:'/',
            redirect:'/welcome'
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

router.beforeEach((to, from, next)=>{
    const userInfoStore = useUserInfoStore()
    // Check if the path is protected or not, using the protectedPaths.ts file
    const isProtected = protectedPaths.some(path => to.path.startsWith(path))
    if(isProtected && !userInfoStore.isAuthenticated){
        next('/login')
    } else {
        next()
    }
})
// hidden overflow in chat window page
router.beforeEach((to, from, next) => {
    if (to.name === 'chatwindow') {
        document.body.style.overflow = 'hidden';
    } else {
        document.body.style.overflow = '';
    }
    next();
});

//export router
export default router
