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
    let isLoading = ref(true)
    let isInitialWindow = ref(true)

    function addMessage(message:chatMessage){
        messages.push(message)
    }

    function setIsInitialWindowToFalse(){
        isInitialWindow.value = false
    }

    function setIsLoading(flag:boolean){
        isLoading.value = flag
    }

    return { messages, addMessage, isInitialWindow, setIsInitialWindowToFalse, isLoading, setIsLoading}
})