<template>
  <div class="py-24 sm:py-32">
    <div class="mx-auto max-w-7xl px-6 lg:px-8">
      <div class="mx-auto max-w-4xl lg:text-center">
        <h2 class="mt-2 text-4xl font-bold tracking-tight sm:text-4xl lg:text-7xl">Glife</h2>
        <h2 class="mt-6 text-lg leading-8 lg:text-3xl">Help you build a greener, greater life!</h2>
        <p class="mt-6 text-lg leading-8 text-gray-600">Glife is one website which is aim to helping people live more sustainably and environmentally friendly.</p>
      </div>
      <div class="mx-auto mt-16 max-w-2xl sm:mt-20 lg:mt-24 lg:max-w-4xl">
        <dl class="grid max-w-xl grid-cols-1 gap-x-8 gap-y-10 lg:max-w-none lg:grid-cols-2 lg:gap-y-16">
          <div v-for="feature in features" :key="feature.name" class="relative pl-16 rounded-lg hover:bg-gray-300">
            <dt class="text-base font-semibold leading-7 text-gray-900">
              <div class="absolute left-0 top-5 flex h-12 w-12 items-center justify-center rounded-lg bg-green-400">
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
import { ArrowLongDownIcon, ChatBubbleBottomCenterTextIcon , QuestionMarkCircleIcon, ClipboardDocumentListIcon } from '@heroicons/vue/24/outline'

const features = [
  {
    name: 'Chat Window',
    description:
        'You can chat with a IBM robot which can suggest you some advices.',
    icon: ChatBubbleBottomCenterTextIcon ,
  },
  {
    name: 'Day Routine',
    description:
        'You can get some random day routines here or add some by yourself.',
    icon: ClipboardDocumentListIcon,
  },
  {
    name: 'Plant tree',
    description:
        'You can plant some AR tree in here',
    icon: ArrowLongDownIcon,
  },
  {
    name: 'FAQ',
    description:
        'Still has some questions? Look at our FAQ to get help.',
    icon: QuestionMarkCircleIcon,
  },
]
</script>

<style>
h2{
  font-weight: bold;
  font-family: 'Cooper Black',sans-serif;
  color: #9cb470;
}
</style>