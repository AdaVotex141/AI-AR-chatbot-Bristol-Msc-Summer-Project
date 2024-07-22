<template>
  <div>
    <a-scene vr-mode-ui="enabled: false" arjs="sourceType: webcam; videoTexture: true; debugUIEnabled: false" renderer="antialias: true; alpha: true">
      <a-camera gps-new-camera="gpsMinDistance: 3"></a-camera>
    </a-scene>
    <button id="myButton" @click="returnToARTree">GO back</button>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted ,onBeforeUnmount } from 'vue';
import router from "@/router";
import { useUserInfoStore } from '@/stores/userInfo';
const latitude = ref<number | null>(null);
const longitude = ref<number | null>(null);
const socket = ref<WebSocket | null>(null);
const userName = ref('');
const intervalId = ref<number | null>(null);
const userInfoStore = useUserInfoStore()

function getLocation(){
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else {
    console.error("Geolocation is not supported by this browser.");
  }
}
function sendPeriodicMessage() {
  getLocation();
  if (socket.value && socket.value.readyState === WebSocket.OPEN) {
    const message = JSON.stringify({
      type: 'current-location',
      latitude: latitude,
      longitude: longitude,
      userName: userName
    });
    socket.value.send(message.toString());
    console.log('Sent message:', message);
  }
}
function showPosition(position:GeolocationPosition){
  latitude.value = position.coords.latitude;
  longitude.value = position.coords.longitude;
}
onMounted(()=>{
  getLocation();
  userName.value=userInfoStore.user;
  console.log(userName.value);
  socket.value=new WebSocket("ws://localhost:8040/ARtree")
  intervalId.value = window.setInterval(sendPeriodicMessage, 50000);
  socket.value.onmessage = (event) => {
    const receivedMessage = JSON.parse(event.data);
    const scene = document.querySelector('a-scene');
    const newEntity = document.createElement('a-entity');
    newEntity.setAttribute('gltf-model', '/src/assets/3DTree/tree.glb');
    newEntity.setAttribute('gps-new-entity-place', `latitude: ${receivedMessage.latitude}; longitude: ${receivedMessage.longitude}`);
    const scaleValue = 0.007;
    newEntity.setAttribute('scale', `${scaleValue} ${scaleValue} ${scaleValue}`);
    if (scene) {
      scene.appendChild(newEntity);
    }
  }
})

onBeforeUnmount(() => {
  cleanupARScene();
  if (socket.value) {
    socket.value.close();
  }
});

function cleanupARScene() {
  const scene = document.querySelector('a-scene');
  if (scene) {
    scene.parentNode?.removeChild(scene);
  }
  const video = document.querySelector('video');
  if (video && video.srcObject) {
    const stream = video.srcObject as MediaStream;
    const tracks = stream.getTracks();
    tracks.forEach(track => track.stop());
    video.srcObject = null;
    video.parentNode?.removeChild(video);
  }
}


function returnToARTree(){
  router.replace({
    name:'artree'
  })
}

</script>

<style scoped>
#myButton {
  position: absolute;
  top: 20px;
  left: 20px;
  padding: 15px 30px;
  font-size: 18px;
  color: white;
  background-color: green;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  z-index: 1;
}
</style>