import router from '@/router'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'

interface Admin{
    id:number
    username:string
    permission:number
}

export const useAdminStore = defineStore('admin',()=>{
    const admins = ref<Admin[]>([])

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
                    permission: item.permission
                }))
                .sort((a, b) => {
                    if(a.permission !== b.permission){
                        return b.permission - a.permission
                    } else {
                        return a.username.localeCompare(b.username)
                    }
                })
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

    async function removeAdmin(id:number){
        // Sending api request to the backend
        try{
            const response = await axios.post('/api/admin/remove', {
                id: id
            })
            if(String(response.data.code) !== '1'){
                console.error('Remove api request fail')
                router.push({name:'notfound'});
            }
        } catch (error){
            console.error(error)
            router.push({name:'notfound'});
        }
        // Set the latest todos
        getAdmins()
    };

    return {admins, getAdmins, removeAdmin}
})