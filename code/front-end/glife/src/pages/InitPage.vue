<template>
  <div class="py-18 sm:py-24">
    <div class="mx-auto max-w-7xl px-6 lg:px-8">
      <div class="mx-auto max-w-4xl lg:text-center">
        <h2 class="mt-2 text-4xl font-bold tracking-tight sm:text-4xl lg:text-7xl">Glife</h2>
        <h2 class="mt-6 text-lg leading-8 lg:text-3xl">Help you build a greener, greater life!</h2>
        <p class="mt-6 text-lg leading-8 text-gray-600 font-bold">{{welcomeMessage}}</p>
        <button v-show="!userInfoStore.isAuthenticated" id="loginButton" @click="gotoLogin" type="button" class="text-gray-900 bg-gradient-to-r from-teal-200 to-lime-200 hover:bg-gradient-to-l hover:from-teal-200 hover:to-lime-200 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-teal-700 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">Getting started with Glife!</button>
      </div>
      <div class="mx-auto mt-16 max-w-2xl sm:mt-12 lg:mt-16 lg:max-w-4xl">
        <dl class="grid max-w-xl grid-cols-1 gap-x-8 gap-y-10 lg:max-w-none lg:grid-cols-2 lg:gap-y-16">
          <div @click="handleFeatureClick(feature)" v-for="feature in features" :key="feature.name" class="relative pl-16 rounded-lg hover:bg-gray-300" >
            <dt class="text-base font-semibold leading-7 text-gray-900">
              <div class="absolute left-2 top-5 flex h-12 w-12 items-center justify-center rounded-lg bg-green-400">
                <component :is="feature.icon" class="h-8 w-8 text-white" aria-hidden="true" />
              </div>
              {{ feature.name }}
            </dt>
            <dd class="mt-2 text-base leading-7 text-gray-600">{{ feature.description }}</dd>
          </div>
        </dl>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ChatBubbleBottomCenterTextIcon, ClipboardDocumentListIcon } from '@heroicons/vue/24/outline'
import ArTreeIcon from "@/assets/icons/ArTreeIcon.js"
import BadgeIcon from "@/assets/icons/BadgeIcon.js";
import router from "@/router";
import { useUserInfoStore } from '@/stores/userInfo';
import { computed } from 'vue';

const userInfoStore = useUserInfoStore()
const welcomeMessage = computed(()=>{
  return userInfoStore.isAuthenticated ? 
  `Welcome to Glife, ${userInfoStore.user}! Let's start it.` 
  : 'Glife is a website aimed at helping people live more sustainably and in an environmentally friendly way.'
})
const features = [
  {
    name: 'Chat Window',
    description:
        'You can chat with a IBM robot which can suggest you some advices.',
    icon: ChatBubbleBottomCenterTextIcon ,
    router: 'chatwindow',
  },
  {
    name: 'Day Routine',
    description:
        'You can get some random day routines here or add some by yourself.',
    icon: ClipboardDocumentListIcon,
    router: 'dayroutine',
  },
  {
    name: 'Plant AR tree',
    description:
        'You can plant some AR tree and share your AR tree in here.',
    icon: ArTreeIcon,
    router: 'artree',
  },
  {
    name: 'My Badges',
    description:
        'You can find all badges you got.',
    icon: BadgeIcon,
    router: 'mybadge',
  },
]
  function handleFeatureClick(feature) {
    router.push({
      name: feature.router
    });
  }

  function gotoLogin(){
    router.push({
      name: 'login'
    })
  }

</script>

<style>
h2{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
  color: #9cb470;
}

#loginButton{
  margin-top: 20px;
}
</style>