import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useUserTaskStore } from './usertask'

export const useWebSocketStore = defineStore('websocket',()=>{
    const socket = ref<WebSocket | null>(null)
    const userTaskStore = useUserTaskStore()

    function connect(url:string){
        // Connect websocket
        socket.value = new WebSocket(url)
        socket.value.onmessage = (event) => {
            // Event info
            console.log("WebSocket receive message: ", event)
            userTaskStore.setTaskContent(event.data)
        }
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