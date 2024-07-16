import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
import router from "@/router";
import {defineStore} from 'pinia'

interface Member{
    id:number
    username:string
}

export const useMemberStore = defineStore('member',()=> {
    const newTodo = ref('');
    const members = ref<Member[]>([]);
    

    async function getMembers(){
        try{
            const response = await axios.get('/api/admin/init')
            if(String(response.data.code) === '1'){
                //Get the data from response
                const data: {id: number; username: string;}[] = response.data.data
                //Map the data to todos and Sort them with id ascendingly
                members.value = data.map(item => ({
                    id: item.id,
                    username: item.username,
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

    //TODO: refactor this method
    async function addMember() {
        if (newTodo.value.trim() !== '') {
            // Get newTodo's value and set it to empty string on the frontend
            const content = newTodo.value
            newTodo.value = '';
            // Send api request and user input to backend
            try {
                const response = await axios.post('/api/admin/add', {
                    content: content
                })
                if (String(response.data.code) === '1') {
                    ElMessage({
                        message: 'Add routine successfully',
                        type: 'success'
                    })
                } else {
                    ElMessage({
                        message: 'Bad things happened',
                        type: 'error'
                    })
                }
            } catch (error) {
                console.error('Error add to do:', error)
                alert('Error happened when adding dayroutine')
                router.push({name:'notfound'});
            }
            // Get latest data from backend
            getMembers()
        }
    };

    async function removeMember(username:string){
        // Sending api request to the backend
        try{
            const response = await axios.post('/api/admin/remove', {
                name: username
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
        getMembers()
    };

    return {newTodo, members, addMember, removeMember, getMembers}
}) 
    
