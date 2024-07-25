<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useBadgeStates } from '@/stores/badgesystems';

const badgeStore = useBadgeStates();
const allBadgeIds = Array.from({ length: 12 }, (_, i) => i + 1);

const badgeImageSrc = badgeStore.badgeImageSrc;

onMounted(async () => {
  try {
    await badgeStore.fetchBadgeStatus();
    badgeStore.updateBadgeImages();
  } catch (error) {
    console.error('Error fetching badges:', error);
  }
});
</script>

<template>
  <div class="main-container"><div class = "title">Your Badges</div>
    <div style="height: 1rem;
            flex-direction: column;
            background-color: transparent;
            text-align: center;
            align-items: center;" > You don't have any badges yet...Complete tasks to earn badges... all badge pictures in here...
    </div>
      <div class="badge-show">
        <img class = "badge-show" v-for="badgeId in allBadgeIds" :key="badgeId" :src="badgeImageSrc[badgeId]" :alt="'Badge ' + badgeId" />
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
  min-height: 180px;
  min-width: 180px;
}

</style>