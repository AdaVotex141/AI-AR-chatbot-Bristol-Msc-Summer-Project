import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useARCameraStore = defineStore('arcamera',()=>{
    let isCameraActivated = ref(false)

    function setCameraActivated(flag:boolean){
        isCameraActivated.value = flag
    }

    return {isCameraActivated, setCameraActivated}

})