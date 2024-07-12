<template>
  <div class="map-container">
    <div ref="map" class="map"></div>
  </div>
</template>

<script setup>
import L from 'leaflet';
import { ref, onMounted } from 'vue';

const map = ref(null);

onMounted(() => {
  // 创建地图实例，并设置初始的中心点和缩放级别
  const myMap = L.map(map.value).setView([51.505, -0.09], 13);

  // 添加地图瓦片图层（可以根据需要选择不同的地图提供商）
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(myMap);
});
</script>

<style scoped>
.map-container {
  height: 400px; /* 设置地图容器的高度 */
}

.map {
  height: 100%; /* 地图占满容器的高度 */
}
</style>
