import axios from 'axios'
import {defineStore} from 'pinia'
import { reactive, ref } from 'vue'

interface ChatMessage{
    text?:string
    type:'text' | 'options'
    sender:'user' | 'bot'
    options?:string[]
}
interface Item{
    responseType: 'text' | 'option',
    text: string,
    labels: string[]
}

export const useChatStore = defineStore('chat',()=>{

    const messages = reactive<ChatMessage[]>([])
    let isInitialWindow = ref(true)
    const timePhrases = ['daily', 'weekly', 'monthly']

    function addMessage(message:ChatMessage){
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

        try{
          // 'timePhrases' message
          if(timePhrases.includes(message)){
            const resultOfAdding = addSystemRoutine(message)
            if(!resultOfAdding) throw new Error("Cannot add it to the system routine");
          }
          // Send api request to the backend
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

    function handleResponseData(data:{responseSectionList:Item[]}){
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
    
    async function addSystemRoutine(message:string){
      // Get period code according to the message
      let period = 0
      if(message === 'daily'){
        period = 0
      } else if (message === 'weekly') {
        period = 1
      } else if (message === 'monthly') {
        period = 2
      }
      // Sending the api request
      try{
        const response = await axios.post('/api/system_routine/add-assistant', period, {
          headers:{
              'Content-Type': 'application/json'
          }
        })
        if(String(response.data.code) !== '1'){
          return false
        } else {
          return true
        }
      } catch (error){
        return false
      }
    }

    return { messages, addMessage, isInitialWindow, setIsInitialWindowToFalse, handleSendMessage}
})