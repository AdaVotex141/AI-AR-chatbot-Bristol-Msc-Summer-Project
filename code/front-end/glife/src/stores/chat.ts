import {defineStore} from 'pinia'
import { computed, reactive } from 'vue'

export const useChatStore = defineStore('chat',()=>{

    const messages = reactive<chatMessage[]>([])

    interface chatMessage{
        text:string
        type:'text' | 'options'
        sender:'user' | 'bot'
        options:string[]
    }

    function addMessage(message:chatMessage){
        messages.push(message)
    }

    return { messages, addMessage }
})