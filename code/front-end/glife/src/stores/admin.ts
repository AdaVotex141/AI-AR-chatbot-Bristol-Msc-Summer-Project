import router from '@/router'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'

interface Admin{
    id:number
    username:string
    permission:string
}

export const useAdminStore = defineStore('admin',()=>{
    const usernameOfNewAdmin = ref('')
    const passwordOfNewAdmin = ref('')
    const admins = ref<Admin[]>([])
    const permissionMap = new Map<number, string>([
        [0, 'user'],
        [1, 'admin'],
        [2, 'root admin']
    ])

    async function getAdmins(){
        try{
            const response = await axios.get('/api/admin/init')
            if(String(response.data.code) === '1'){
                //Get the data from response
                const data: {id: number; username: string; permission:number}[] = response.data.data
                //Map the data to todos and Sort them with id ascendingly
                admins.value = data.map(item => ({
                    id: item.id,
                    username: item.username,
                    permission: permissionMap.get(item.permission) || 'user'
                }))
                .sort((a, b) => a.id - b.id)
            } else {
                alert('Backend give a code 0?') // TODO: need to be deleted after ensuring the fault
                router.push({name:'notfound'});
            }
        } catch (error) {
            console.error("Error happened during initializing the dayroutine")
            alert("Error happened during initializing the dayroutine")
            router.push({name:'notfound'});
        }
    }

    return {usernameOfNewAdmin, passwordOfNewAdmin, admins, getAdmins}
})