<script setup lang="ts">
import { reactive } from 'vue';

const settings = reactive({
  username: '',
  email: '',
  theme: 'light'
});

const saveSettings = () => {
  console.log('Settings saved:', settings);
};
</script>

<template>
  <div class="settings">
    <p><b>Your username is: amy(can not be change)</b></p>
    <form @submit.prevent="saveSettings">
      <div class="form-group">
        <label for="username">Change your nickname:</label>
        <input type="text" id="username" v-model="settings.username" />
      </div>
      <div class="form-group">
        <label for="email">Change your email:</label>
        <input type="email" id="email" v-model="settings.email" />
      </div>
      <div class="form-group">
        <label for="theme">Change the theme color:</label>
        <select id="theme" v-model="settings.theme">
          <option value="light">light</option>
          <option value="dark">dark</option>
        </select>
      </div>
      <button type="submit">Save</button>
    </form>
  </div>
</template>

<style scoped>
@media(max-width: 600px) {
  .settings {
    border-radius: 4px;
    margin-top: 2.5rem;
    width: 100vw;
    height: 90vh;
  }
}
@media(min-width: 601px) {
  .settings {
    border-radius: 4px;
    margin-top: 2.5rem;
    width: 60vw;
    height: 90vh;
    margin-left: auto;
    margin-right: auto;
  }
}

.form-group {
  margin-bottom: 1em;
}

.form-group label {
  display: block;
  margin-bottom: 0.5em;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 0.5em;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 0.5em 1em;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>