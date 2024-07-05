<script setup lang="ts">
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
      <div class="bg1"></div><div class="bg2"></div><div class="bg3"></div>
      <div class="bg4"></div><div class="bg5"></div><div class="bg6"></div>
      <div class="bg7"></div><div class="bg8"></div><div class="bg9"></div>
      <div class="bg10"></div><div class="bg11"></div><div class="bg12"></div>
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
    margin-top: 2.5rem;
    width: 100vw;
    height: 90vh;
    margin-left: auto;
    margin-right: auto;
  }
}

.badge-show {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 10px;
  background-color: transparent;
}
.bg1{background-image: url('@/assets/badgeImages/1.png');}
.bg2{background-image: url('@/assets/badgeImages/2.png');}
.bg3{ background-image: url('@/assets/badgeImages/3.png');}
.bg4{ background-image: url('@/assets/badgeImages/4.png');}
.bg5{ background-image: url('@/assets/badgeImages/5.png');}
.bg6{ background-image: url('@/assets/badgeImages/6.png');}
.bg7{ background-image: url('@/assets/badgeImages/7.png');}
.bg8{ background-image: url('@/assets/badgeImages/8.png');}
.bg9{ background-image: url('@/assets/badgeImages/9.png');}
.bg10{ background-image: url('@/assets/badgeImages/10.png');}
.bg11{ background-image: url('@/assets/badgeImages/11.png');}
.bg12{ background-image: url('@/assets/badgeImages/12.png');}
.bg1, .bg2, .bg3, .bg4, .bg5, .bg6, .bg7, .bg8, .bg9, .bg10, .bg11, .bg12{
  width: 100%;
  height: 30vh;
  background-size: cover;
  background-position: top;
}
</style>