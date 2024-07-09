import axios from "axios";
import {ref} from "vue";
import { defineStore } from 'pinia';
import { ElMessage } from 'element-plus';
import router from "@/router";


export interface UserPointsResponse{
    userPoints: number; // For user badge
    treePoints: number; // 1-8 can change the state
    plantTree: boolean; // For enable the plant tree button
}

export const useUserPointsStore = defineStore('userPoints',() => {
    const userPoints = ref<number | null>(null);
    const treePoints =ref<number | null>(null);
    const canPlantTree = ref<boolean | null>(null);

    async function fetchUserPoints(){
        try{
            const response = await axios.get('/api/ARtree/init')
            if(String(response.data.code) === '1'){
                const data: UserPointsResponse = response.data.data;
                userPoints.value = data.userPoints;
                treePoints.value = data.treePoints;
                canPlantTree.value = data.plantTree;
            }else {
                ElMessage({
                    message: 'Failed to fetch user points',
                    type: 'error'
                });
                // router.push({ name: 'notfound' });
            }
        }catch (error) {
                console.error('Error fetching user points:', error);
                ElMessage({
                    message: 'Error fetching user points',
                    type: 'error'
                });
                // router.push({ name: 'notfound' });
            }
    }
    return { userPoints, canPlantTree, fetchUserPoints };
});