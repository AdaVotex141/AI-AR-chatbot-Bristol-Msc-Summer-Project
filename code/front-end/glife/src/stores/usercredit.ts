import axios from "axios";
import {ref} from "vue";
import { defineStore } from 'pinia';
import { ElMessage } from 'element-plus';
import router from "@/router";


export interface UserPointsResponse{
    userPoints: number;
    plantTree: boolean;
}

export const useUserPointsStore = defineStore('userPoints',() => {
    const userPoints = ref<number | null>(null);
    const canPlantTree = ref<boolean | null>(null);

    async function fetchUserPoints(){
        try{
            const response = await axios.get('/api/user/points')
            if(String(response.data.code) === '1'){
                const data: UserPointsResponse = response.data.data;
                userPoints.value = data.userPoints;
                canPlantTree.value = data.plantTree;
            }else {
                ElMessage({
                    message: 'Failed to fetch user points',
                    type: 'error'
                });
                router.push({ name: 'notfound' });
            }
        }catch (error) {
                console.error('Error fetching user points:', error);
                ElMessage({
                    message: 'Error fetching user points',
                    type: 'error'
                });
                router.push({ name: 'notfound' });
            }
    }
    return { userPoints, canPlantTree, fetchUserPoints };
});