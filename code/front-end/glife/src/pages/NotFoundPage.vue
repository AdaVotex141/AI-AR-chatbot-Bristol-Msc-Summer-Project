
<template>
    <main class="grid min-h-full place-items-center px-6 py-24 sm:py-32 lg:px-8">
      <div class="text-center">
        <p class="text-base font-semibold ">404</p>
        <h1 class="mt-4 text-3xl font-bold tracking-tight text-gray-900 sm:text-5xl">Page not found</h1>
        <p class="mt-6 text-base leading-7 text-gray-600">Sorry, we couldn’t find the page you’re looking for.</p>
        <div class="mt-10 flex items-center justify-center gap-x-6">
          <button class="rounded-md bg-green-200 px-3.5 py-2.5 text-sm font-semibold
          text-black shadow-sm hover:bg-green-300 focus-visible:outline focus-visible:outline-2
          focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          @click="navigateTo('logout')">
            Go back login</button>
<!--          TODO: Add some function for contact support-->
          <button class="text-sm font-semibold text-gray-900" @click="navigateTo('notfound')">
            Contact support<span aria-hidden="true">&rarr;</span>
          </button>
        </div>
      </div>
    </main>

</template>

<script setup lang="ts">
import router from "@/router";

function navigateTo(routeName: string) {
  router.push({
    name: routeName
  });
}
</script>

<style scoped>
.text-base{
  font-size: 2rem;
}
.text-center{
  width: 90vw;
  height: 60vh;
  margin-left: auto;
  margin-right: auto;
}
h1{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
}
</style>