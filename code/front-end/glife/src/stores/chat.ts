import {defineStore} from 'pinia'
import { reactive, ref } from 'vue'

interface chatMessage{
    text?:string
    type:'text' | 'options'
    sender:'user' | 'bot'
    options?:string[]
}

export const useChatStore = defineStore('chat',()=>{

    const messages = reactive<chatMessage[]>([])

    let isInitialWindow = ref(true)

    function addMessage(message:chatMessage){
        messages.push(message)
    }

    function setIsInitialWindowToFalse(){
        isInitialWindow.value = false
    }

    return { messages, addMessage, isInitialWindow, setIsInitialWindowToFalse}
})