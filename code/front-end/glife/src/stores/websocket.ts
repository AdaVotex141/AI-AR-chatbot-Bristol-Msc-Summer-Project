import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useUserTaskStore } from './usertask'

export const useWebSocketStore = defineStore('websocket',()=>{
    const socket = ref<WebSocket | null>(null)
    const userTaskStore = useUserTaskStore()

    function connect(url:string): Promise<void>{
        return new Promise((resolve, reject)=>{
            // Connect websocket
            socket.value = new WebSocket(url)
            // Onopen
            socket.value.onopen = () => {
                resolve()
            }
            // When websocket receive the msg
            socket.value.onmessage = (event) => {
                // Event info
                console.log("WebSocket receive message: ", event)
                userTaskStore.setTaskContent(event.data)
                userTaskStore.getNumberOfTask()
            }
        })   
    }

    function sendMessage(message:string){

    }

    function close(){
        if(socket.value){
            socket.value.close()
            socket.value = null
        }
    }

    return {socket, connect, sendMessage, close}
})