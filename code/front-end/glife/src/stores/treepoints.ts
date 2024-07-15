import axios from "axios";
import {ref} from "vue";
import { defineStore } from 'pinia';
import { ElMessage } from 'element-plus';


export interface TreePoints{
    treePoints: number; // 1-8 can change the state
    plantTree: boolean; // For enable the plant tree button
}

export const useTreepointsStore = defineStore('treepoints',()=>{

    const treeImageSrc = ref<string>('');
    const treeImageAlt = ref<string>('Tree Image');
    const treePoints = ref<number | 1>(1);
    const canPlantTree = ref<boolean | null>(null);

    const treeImages = import.meta.glob< {default: string}>('@/assets/treeImages/*.png', {eager: true})

    async function fetchTreePoints(){
        try {
            const response = await axios.get<{ code: string; data: number}>('/api/ARTree/init');
            if (String(response.data.code) === '1') {
                const data = response.data.data;
                treePoints.value = data; 
                updateTreeImage(data);
            } else {
                ElMessage({
                    message: 'Failed to fetch tree points',
                    type: 'error',
                });
            }
        } catch (error) {
            console.error('Error fetching tree points:', error);
            ElMessage({
                message: 'Error fetching tree points',
                type: 'error',
            });
        }
    }

    async function fetchCanPlantTree(){
        try {
            const response = await axios.get<{ code: string; data: TreePoints }>('/api/ARTree/init');
            if (String(response.data.code) === '1') {
                const data = response.data.data;
                canPlantTree.value = data.plantTree;
            } else {
                ElMessage({
                    message: 'Failed to fetch plant tree status',
                    type: 'error',
                });
            }
        } catch (error) {
            console.error('Error fetching plant tree status:', error);
            ElMessage({
                message: 'Error fetching plant tree status',
                type: 'error',
            });
        }
    }

    function updateTreeImage(treePoints: number){
        if(treePoints >= 1 && treePoints <= 8) {
            treeImageSrc.value = treeImages[`/src/assets/treeImages/${treePoints}.png`]?.default
        }
    }
    return { treeImageSrc, treeImageAlt, treePoints, canPlantTree, fetchTreePoints, fetchCanPlantTree}
})