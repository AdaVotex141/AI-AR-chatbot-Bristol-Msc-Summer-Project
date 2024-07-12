import axios from "axios";
import {ref} from "vue";
import { defineStore } from 'pinia';
import { ElMessage } from 'element-plus';
import router from "@/router";


export interface TreePoints{
    treePoints: number; // 1-8 can change the state
    plantTree: boolean; // For enable the plant tree button
}


export const fetchTreePoints = async () => {
    const treeImageSrc = ref<string>('');
    const treeImageAlt = ref<string>('Tree Image');
    const treePoints = ref<number | 1>(1);
    try {
        const response = await axios.get<{ code: string; data: number}>('/api/ARTree/init');
        console.log(response.data)
        if (String(response.data.code) === '1') {
            const data = response.data.data;
            treePoints.value = data; // 直接赋值给 treePoints.value
            updateTreeImage(data, treeImageSrc);
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
    return canPlantTree;
};

const updateTreeImage = (
    treePoints: number,
    treeImageSrc: { value: string },
) => {
    switch (treePoints) {
        case 1:
            treeImageSrc.value = 'src/assets/treeImages/1.png';
            break;
        case 2:
            treeImageSrc.value = 'src/assets/treeImages/2.png';
            break;
        case 3:
            treeImageSrc.value = 'src/assets/treeImages/3.png';
            break;
        case 4:
            treeImageSrc.value = 'src/assets/treeImages/4.png';
            break;
        case 5:
            treeImageSrc.value = 'src/assets/treeImages/5.png';
            break;
        case 6:
            treeImageSrc.value = 'src/assets/treeImages/6.png';
            break;
        case 7:
            treeImageSrc.value = 'src/assets/treeImages/7.png';
            break;
        case 8:
            treeImageSrc.value = 'src/assets/treeImages/8.png';
            break;
        default:
            treeImageSrc.value = 'src/assets/PlantTree.png';
    }
};