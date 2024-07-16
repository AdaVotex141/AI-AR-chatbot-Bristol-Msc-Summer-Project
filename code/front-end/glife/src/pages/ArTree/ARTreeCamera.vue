<template>
  <div>
    <a-scene vr-mode-ui="enabled: false" arjs="sourceType: webcam; videoTexture: true; debugUIEnabled: false" renderer="antialias: true; alpha: true">
      <a-camera gps-new-camera="gpsMinDistance: 3"></a-camera>
    </a-scene>
    <button id="myButton" @click="planTree">Plan tree</button>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
const latitude = ref<number | null>(null);
const longitude = ref<number | null>(null);
let buttonColor = ref('green');
function planTree(){
  if (latitude.value && longitude.value) {
    const scene = document.querySelector('a-scene');
    if (scene) {
      const newEntity = document.createElement('a-entity');
      newEntity.setAttribute('gltf-model', '/src/assets/3DTree/tree.glb');
      newEntity.setAttribute('gps-new-entity-place', `latitude: ${latitude.value + 0.00001}; longitude: ${longitude.value + 0.00001}`);
      const scaleValue = 0.004;
      console.log(latitude.value);
      console.log(longitude.value);
      newEntity.setAttribute('scale', `${scaleValue} ${scaleValue} ${scaleValue}`);
      scene.appendChild(newEntity);
      buttonColor.value = 'grey';
    } else {
      console.error('a-scene not found.');
    }
  } else {
    console.error('Latitude and/or Longitude not available.');
  }
}
function getLocation(){
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else {
    console.error("Geolocation is not supported by this browser.");
  }
}
function showPosition(position:GeolocationPosition){
  latitude.value = position.coords.latitude;
  longitude.value = position.coords.longitude;
}
onMounted(()=>{
  getLocation();
})
</script>

<style scoped>
#myButton {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  padding: 15px 30px;
  font-size: 18px;
  background-color: green;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  z-index: 1; /* Make sure the button is on top of the AR scene */
}
</style>