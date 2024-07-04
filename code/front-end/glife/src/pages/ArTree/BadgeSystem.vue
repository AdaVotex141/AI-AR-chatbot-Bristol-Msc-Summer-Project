<script setup lang="ts">

</script>

<template>
  <div class="main-container"><div class = "title">Your Badges</div>
    <div style="height: 7rem;
            flex-direction: column;
            background-color: transparent;
            text-align: center;
            align-items: center;" > You don't have any badges yet...Complete tasks to earn badges...
    </div>
    <div class="badge-show" style="text-align: center; align-items: center;">
      Add all badge pictures in here...
    </div>
  </div>
</template>


<style scoped>
.el-button {
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  display: block;
  width: 50%;
  text-align: center;
  background-color:darkolivegreen;
  border-color: transparent;
  flex-direction: column;
  align-items: center;
}
.el-button:hover {
  background-color:darkseagreen;
  color: #fff;
  border-color: transparent;
}


.button-list{
  display: flex;
  align-items: flex-end;
  justify-content: center;
  flex-direction: column;
}
.new-button{
  margin-top: 2rem;
  margin-bottom: 0.5rem;
  width: 100%;
  justify-content: center;

}
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
</style>