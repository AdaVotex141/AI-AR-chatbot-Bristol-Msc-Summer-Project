import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useWebSocketStore = defineStore('websocket',()=>{
    const socket = ref<WebSocket | null>(null)

    function connect(url:string){
        socket.value = new WebSocket(url)
        socket.value.onmessage = (event) => {
            console.log("WebSocket receive message: ", event)
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