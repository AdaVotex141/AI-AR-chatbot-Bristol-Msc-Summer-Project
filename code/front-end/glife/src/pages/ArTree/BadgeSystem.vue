<script setup lang="ts">
import {ref, onMounted, computed} from 'vue';
import { useBadgeStates } from '@/stores/badgesystems';

const badgeStore = useBadgeStates();
const allBadgeIds = Array.from({ length: 12 }, (_, i) => i + 1);

const badgeImageSrc = badgeStore.badgeImageSrc;
onMounted(async () => {
  try {
    await badgeStore.fetchBadgeStatus();
    await badgeStore.fetchBadgeDescriptions();
    badgeStore.updateBadgeImages();
  } catch (error) {
    console.error('Error fetching badges:', error);
  }
});
const showMessage = ref(false);
const badgeMessage = computed(() => {
  if(badgeStore.badgeNumbers===0){
    showMessage.value = !showMessage.value;
    return "You haven't earned any badges yet, keep it up!";
  }else{
    return "Congratulations! You already get the " + badgeStore.badgeNumbers +  " badges!" ;
  }
})
const getDescriptionById = (id: number) => {
  return badgeStore.getDescriptionById(id);
}
</script>

<template>
  <div class="main-container"><div class = "title">Your Badges</div>
    <div style="height: 1rem;
            flex-direction: column;
            background-color: transparent;
            text-align: center;
            align-items: center;" >
      {{badgeMessage}}
    </div>
    <div class="badge-show">
      <div class="badge" v-for="badgeId in allBadgeIds" :key="badgeId">
        <img :src="badgeImageSrc[badgeId]" :alt="getDescriptionById(badgeId)" />
        <div class="image-description">{{ getDescriptionById(badgeId) }}</div>
      </div>
    </div>
  </div>
</template>


<style scoped>
.title{
  font-family: 'Cooper Black',sans-serif;
  font-size: 1.5rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

@media(min-width: 601px) {
  .main-container {
    margin-top: 2.5rem;
    width: 70vw;
    height: 90vh;
    margin-left: auto;
    margin-right: auto;
  }
}
@media(max-width: 600px) {
  .main-container {
    margin: 0 auto;
    width: 100vw;
    height: 90vh;
  }
}

.badge-show {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 10px;
  background-color: transparent;
  min-height: 200px;
  min-width: 200px;
}

.image-description {
  display: none;
  position: absolute;
  bottom: 5%;
  left: 50%;
  transform: translateX(-50%);
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 5px;
  border-radius: 3px;
}

.badge:hover .image-description {
  display: block;
}

</style>