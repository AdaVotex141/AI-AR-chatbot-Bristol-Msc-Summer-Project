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

export const fetchUserPoints = async () => {
    const userPoints = ref<number | null>(null);
    try {
        const response = await axios.get<{ code: string; data: UserPointsResponse }>('/api/ARtree/init');
        if (String(response.data.code) === '1') {
            const data = response.data.data;
            userPoints.value = data.userPoints;
        } else {
            ElMessage({
                message: 'Failed to fetch user points',
                type: 'error',
            });
        }
    } catch (error) {
        console.error('Error fetching user points:', error);
        ElMessage({
            message: 'Error fetching user points',
            type: 'error',
        });
    }
    return userPoints;
};

export const fetchTreePoints = async () => {
    const treeImageSrc = ref<string>('');
    const treeImageAlt = ref<string>('Tree Image');
    const treePoints = ref<number | null>(null);
    try {
        const response = await axios.get<{ code: string; data: UserPointsResponse }>('/api/ARtree/init');
        if (String(response.data.code) === '1') {
            const data = response.data.data;
            treePoints.value = data.treePoints;
            updateTreeImage(data.treePoints, treeImageSrc);
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
    return { treeImageSrc, treeImageAlt, treePoints };
};

export const fetchCanPlantTree = async () => {
    const canPlantTree = ref<boolean | null>(null);
    try {
        const response = await axios.get<{ code: string; data: UserPointsResponse }>('/api/ARtree/init');
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
    return canPlantTree;
};

const updateTreeImage = (
    treePoints: number,
    treeImageSrc: { value: string },
) => {
    switch (treePoints) {
        case 1:
            treeImageSrc.value = '@/assets/treeImages/1.png';
            break;
        case 2:
            treeImageSrc.value = '@/assets/treeImages/2.png';
            break;
        case 3:
            treeImageSrc.value = '@/assets/treeImages/3.png';
            break;
        default:
            treeImageSrc.value = '@/assets/treeImages/8.png';
    }
};