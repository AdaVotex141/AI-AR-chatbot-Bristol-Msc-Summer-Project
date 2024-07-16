import axios from 'axios'
import {defineStore} from 'pinia'
import { reactive, ref } from 'vue'

interface chatMessage{
    text?:string
    type:'text' | 'options'
    sender:'user' | 'bot'
    options?:string[]
}
interface item{
    responseType: 'text' | 'option',
    text: string,
    labels: string[]
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

    async function handleSendMessage(message:string){
        // Turn off the initial window
        setIsInitialWindowToFalse()
        
        // Show the message on the window
        addMessage({ text: message, sender: 'user', type: 'text'});

        // 'Yes' message
        if(message === 'Yes'){
          handleYesMessage()
        }
      
        // Send api request to the backend
        try{
          const response = await axios.post('/api/assistant/input', {
            inputMessage: message
          })
          const data = response.data
          if(String(data.code) === '1'){
            handleResponseData(data.data)  
          } else {
            addMessage({text: 'Server did not give response, please try again.', sender: 'bot', type: 'text'})
          }
        } catch (error){
          addMessage({text: 'Bad things happened, please try again.', sender: 'bot', type: 'text'})
        }
    };

    function handleResponseData(data:{responseSectionList:item[]}){
      data.responseSectionList.forEach((item, index) => {
        setTimeout(() => {
          if (item.responseType === 'text') {
            addMessage({ text: item.text, sender: 'bot', type: 'text' });
          } else if (item.responseType === 'option') {
            addMessage({ options: item.labels, sender: 'bot', type: 'options' });
          }
        }, index * 1000);
      })
    }
    
    async function handleYesMessage(){
      try{
        const response = axios.post('/api/system_routine/add-assistant')
        console.log('yes message')
      } catch (error){
        
      }
    }

    return { messages, addMessage, isInitialWindow, setIsInitialWindowToFalse, handleSendMessage}
})