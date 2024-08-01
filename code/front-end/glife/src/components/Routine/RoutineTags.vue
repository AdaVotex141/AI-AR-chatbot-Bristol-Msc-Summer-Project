<template>
    <div class="mb-4 border-b border-gray-200 dark:border-gray-700">
      <ul class="flex flex-wrap -mb-px text-sm font-medium text-center" role="tablist">
            <li class="me-2" v-for="tab in dayroutineStore.tabs" :key="tab.id" role="presentation">
            <button
                :class="['inline-block p-4 border-b-2 rounded-t-lg', { 'border-blue-500 text-blue-500': dayroutineStore.activeTab === tab.id }]"
                @click="dayroutineStore.setActiveTab(tab.id)"
                :id="`${tab.id}-tab`"
                :aria-controls="tab.id"
                role="tab"
                :aria-selected="dayroutineStore.activeTab === tab.id"
            >
                {{ tab.label }}
            </button>
            </li>
        </ul>
        <div v-for="tab in dayroutineStore.tabs" :key="tab.id" v-show="dayroutineStore.activeTab === tab.id" :id="tab.id" role="tabpanel">
            <slot :name="tab.id"></slot>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { useDayroutineStore } from '@/stores/dayroutine';

const dayroutineStore = useDayroutineStore()

</script>

<style scoped>

</style>
