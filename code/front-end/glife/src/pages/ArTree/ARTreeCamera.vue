<template>
  <div>
    <a-scene vr-mode-ui="enabled: false" arjs="sourceType: webcam; videoTexture: true; debugUIEnabled: false" renderer="antialias: true; alpha: true">
      <a-camera gps-new-camera="gpsMinDistance: 3"></a-camera>
    </a-scene>
  </div>
</template>

<script>
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