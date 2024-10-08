<template>
  <div>
    <a-scene vr-mode-ui="enabled: false" arjs="sourceType: webcam; videoTexture: true; debugUIEnabled: false"
             renderer="antialias: true; alpha: true">
      <a-camera gps-new-camera="gpsMinDistance: 3"></a-camera>
    </a-scene>
    <button id="myButton" @click="plantTree" :style="{backgroundColor:buttonColor}">Plant</button>
    <button id="myButton2" @click="returnToARTree">GO back</button>
  </div>
  <div>
    <GoogleMap id="map"
               v-if="isLocationLoaded"
        api-key="AIzaSyD7yNhMUS2eFelVVz1x6i9hsTbePnK48to"
        :center="center"
        :zoom="17"
    >
      <Marker
          v-for="(marker, index) in markers"
          :key="index"
          :options="marker"
      />
    </GoogleMap>
  </div>
</template>

<script setup lang="ts">
import {ref, onMounted, onBeforeUnmount} from 'vue';
import router from "@/router";
import {useUserInfoStore} from '@/stores/userInfo';
import { GoogleMap, Marker } from 'vue3-google-map'

const latitude = ref<number>(51.4558853);
const longitude = ref<number>(-2.6029143);
const buttonColor = ref('green');
const socket = ref<WebSocket | null>(null);
const userName = ref('');
const userID =ref('');
const intervalId = ref<number | null>(null);
const userInfoStore = useUserInfoStore()
const center = ref({ lat: 0, lng: 0 });
const markers = ref<Array<{ position: { lat: number, lng: number }, title: string ,icon: { url: string, scaledSize: { width: number; height: number } }}>>([

]);
const isLocationLoaded = ref(false);

function addModel(a: number, b: number) {
  const scene = document.querySelector('a-scene');
  if (scene) {
    const newEntity = document.createElement('a-entity');
    newEntity.setAttribute('gltf-model', '/src/assets/3DTree/tree.glb');
    newEntity.setAttribute('gps-new-entity-place', `latitude: ${a + 0.000015}; longitude: ${b}`);
    const scaleValue = 0.007;
    newEntity.setAttribute('scale', `${scaleValue} ${scaleValue} ${scaleValue}`);
    scene.appendChild(newEntity);
  } else {
    console.error('a-scene not found.');
  }
}

function plantTree() {
  getLocation();
  console.log(userID)
  if (latitude.value && longitude.value) {
    addModel(latitude.value, longitude.value);
    buttonColor.value = 'grey';
    if (socket.value && socket.value.readyState === WebSocket.OPEN) {
      const message = JSON.stringify({
        type: 'plant-location',
        latitude: latitude,
        longitude: longitude,
        userName: userName,
        userID: userID
      });
      socket.value.send(message.toString());
    } else {
      console.error('WebSocket is not open.');
    }
  } else {
    console.error('Latitude and/or Longitude not available.');
  }
}

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else {
    console.error("Geolocation is not supported by this browser.");
  }
}

function sendPeriodicMessage() {
  getLocation();
  if (latitude.value && longitude.value) {
    if (socket.value && socket.value.readyState === WebSocket.OPEN) {
      const message = JSON.stringify({
        type: 'current-location',
        latitude: latitude,
        longitude: longitude,
        userName: userName,
        userID: userID
      });
      socket.value.send(message.toString());
    }else {
      console.error('WebSocket is not open.');
    }
  }else {
    console.error('Latitude and/or Longitude not available.');
  }
}
function removeAllEntities() {
  const scene = document.querySelector('a-scene');
  if (scene) {
    const entities = scene.querySelectorAll('a-entity');
    entities.forEach(entity => entity.remove());
  }
}
function getMap(){
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      center.value.lat = position.coords.latitude;
      center.value.lng = position.coords.longitude;
      isLocationLoaded.value = true;
      console.log(center.value);
    });
  } else {
    console.error("Geolocation is not supported by this browser.");
  }
}

function showPosition(position: GeolocationPosition) {
  latitude.value = position.coords.latitude;
  longitude.value = position.coords.longitude;
}

onMounted(() => {
  getLocation();
  getMap()
  userName.value = userInfoStore.user;
  userID.value=userInfoStore.userid;
  console.log(userID);
  socket.value = new WebSocket("ws://localhost:8040/ARtree")
  intervalId.value = window.setInterval(sendPeriodicMessage, 1000);
  socket.value.onmessage = (event) => {
    //removeAllEntities();
    console.log(event.data);
    const [newLongitudeStr, newLatitudeStr,name] = event.data.split(",");
    const newLongitude=Number(newLongitudeStr)
    const newLatitude=Number(newLatitudeStr)
    addModel(newLongitude, newLatitude);
    console.log(name);
    markers.value.push({
      position: { lat: newLatitude, lng: newLongitude },
      title: `Tree: ${name.trim()}`,
      icon: {
        url: '/src/assets/marker/MarkerTree.png',
        scaledSize: { width: 30, height: 30 }
      }
    })
  }
})

onBeforeUnmount(() => {
  cleanupARScene();
  if(intervalId.value!=null){
    window.clearInterval(intervalId.value);
    intervalId.value=null;
  }
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


function returnToARTree() {
  router.replace({
    name: 'artree'
  })
}

</script>

<style scoped>
#myButton {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  padding: 15px 30px;
  font-size: 18px;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  z-index: 1;
}

#myButton2 {
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
#map {
  position: fixed;
  top: 20px;
  right: 20px;
  width: 20vw;
  height: 500px;
  z-index: 1;
}
@media (max-width: 767px) {
  #map {
    width: 40vw;
    height: 150px;
    top: 5px;
    right: 5px;
  }
}
</style>