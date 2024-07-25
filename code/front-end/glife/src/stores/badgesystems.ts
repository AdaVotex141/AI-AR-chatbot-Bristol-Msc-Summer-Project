import axios from "axios";
import {computed, ref} from "vue";
import { defineStore } from 'pinia';
import { ElMessage } from 'element-plus';
import { useUserInfoStore } from './userInfo';

export interface BadgeSystems{
    badgeId: number ; // 1-12 total badge id
}

export const useBadgeStates= defineStore('badgessystems', () =>{
    const gottenBadges = ref<BadgeSystems[]>([]);
    const badgeImageSrc = ref<Record<number, string>>({});
    const userInfoStore = useUserInfoStore();
    console.log(userInfoStore.userid);
    async function fetchBadgeStatus() {
        try {
            const response = await axios.get(`/api/user_badges/user/${userInfoStore.userid}`);
            console.log(response)
            if (String(response.data.code) === '1') {
                // get the gotten badges list from back-end
                gottenBadges.value = response.data.data;
                console.log('Fetched badges:', gottenBadges.value);
                // update the badge image color
                updateBadgeImages();
            }
        } catch (error) {
            console.error('Error fetching badge status:', error);
            ElMessage({
                message: 'Error fetching badge status',
                type: 'error',
            });
        }
    }
    // get all gret and colored badges
    const greyBadgeImages = import.meta.glob('/src/assets/badgeImagesGrey/*.png', { eager: true });
    const colorBadgeImages = import.meta.glob('/src/assets/badgeImages/*.png', { eager: true });

    // update single badge image
    function updateBadgeImage(badgeId: number, isColored: boolean) {
        const imagePath = isColored
            ? `/src/assets/badgeImages/${badgeId}.png`
            : `/src/assets/badgeImagesGrey/${badgeId}.png`;
        const imageModule = isColored ? colorBadgeImages[imagePath] : greyBadgeImages[imagePath];

        if (imageModule) {
            badgeImageSrc.value[badgeId] = (imageModule as { default: string }).default;
        } else {
            console.error(`Image for badge ID ${badgeId} not found at path ${imagePath}`);
        }
    }
    // update all the badges
    function updateBadgeImages(badgeId: number){
        // badgeImageSrc.value = badgeImages[`/src/assets/badgeImages/${badgeId}.png`]?.default
        // Assume badge IDs range from 1 to 12
        const allBadgeIds = Array.from({ length: 12 }, (_, i) => i + 1);

        // Set all badges to grey initially
        allBadgeIds.forEach(badgeId => updateBadgeImage(badgeId, false));

        // Set gotten badges to colored
        gottenBadges.value.forEach((id) => updateBadgeImage(id, true));
    }
    const badgeNumbers= computed(()=> {
        return gottenBadges.value.length;
    })
    return {
        gottenBadges,
        fetchBadgeStatus,
        updateBadgeImages,
        badgeImageSrc,
        badgeNumbers,
    };
});