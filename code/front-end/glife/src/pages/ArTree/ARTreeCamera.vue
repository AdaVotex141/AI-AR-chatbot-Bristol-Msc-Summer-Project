<template>
  <div>
    <a-scene vr-mode-ui="enabled: false" arjs="sourceType: webcam; videoTexture: true; debugUIEnabled: false" renderer="antialias: true; alpha: true">
      <a-camera gps-new-camera="gpsMinDistance: 3"></a-camera>
    </a-scene>
    <input type="button" id="myButton" @click="planTree" :value="`Plan Tree remaining times: ${remainingClicks}`" :style="{ backgroundColor: buttonColor }">
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import VueWs from 'vue-websocket';

export default {
  mixins:[VueWs],
  created() {
    this.connect("ws://localhost:8040");
  },
  data(){
    return{
      message:""
    }
  },
  methods:{
    handleDate(data){
      console.log(data);
    },
    sendMessage(){
      this.send("hello,word");
    },
    closeConnection(){
      this.close()
    }

  },
  async mounted() {
    try {
      console.log('A-Frame and AR.js scripts loading...');
      await import('aframe');
      await import('@ar-js-org/ar.js/aframe/build/aframe-ar.js');
      console.log('A-Frame and AR.js scripts loaded.');
    } catch (error) {
      console.error('Error during mounted hook:', error);
    }
  },
  setup() {
    let latitude = ref(null);
    let longitude = ref(null);
    let avClicks = ref(5);
    let buttonColor = ref('green');

    function getLocation(){
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
      } else {
        console.error("Geolocation is not supported by this browser.");
      }
    }


    function showPosition(position){
      latitude.value = position.coords.latitude;
      longitude.value = position.coords.longitude;
    }

    function planTree() {
      getLocation();
      if (avClicks.value !== 0) {
        avClicks.value--;
        const scene = document.querySelector('a-scene');
        const newEntity = document.createElement('a-entity');
        newEntity.setAttribute('gltf-model', 'tree.glb');
        newEntity.setAttribute('gps-new-entity-place', `latitude: ${latitude.value + 0.00001}; longitude: ${longitude.value + 0.00001}`);
        const scaleValue = 0.004;
        newEntity.setAttribute('scale', `${scaleValue} ${scaleValue} ${scaleValue}`);
        scene.appendChild(newEntity);
        document.getElementById("myButton").value = `Plan Tree remaining times: ${avClicks.value}`;
      }
      if (avClicks.value === 0) {
        buttonColor.value = 'grey';
      }
    }
    onMounted(() => {
      getLocation();
      document.getElementById("myButton").value = `Plan Tree remaining times: ${avClicks.value}`;
    });

    return {
      planTree,
      remainingClicks: avClicks,
      buttonColor
    };
  }
};
</script>

<style scoped>
#myButton {
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  padding: 15px 30px;
  font-size: 18px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  z-index: 1;
  color: white;
}
</style>