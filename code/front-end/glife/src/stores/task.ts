import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useTaskStore = defineStore('task',()=>{
    const tabs = [
        { id: 'published', label: 'Published' },
        { id: 'unpublished', label: 'Unpublished' },
    ];
    const activeTab = ref('published');
    
    function setActiveTab(tabId: string) {
        activeTab.value = tabId;
    }

    return {tabs, activeTab, setActiveTab}
})