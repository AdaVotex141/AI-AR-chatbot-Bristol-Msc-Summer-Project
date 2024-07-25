import { defineComponent, h } from 'vue';

export default defineComponent({
    name: 'ArTreeIcon',
    render() {
        return h('svg', {
            xmlns: 'http://www.w3.org/2000/svg',
            class: 'w-5 h-5 mb-1 text-gray-500 dark:text-gray-400 group-hover:text-blue-600 dark:group-hover:text-blue-500',
            fill: 'none',
            viewBox: '0 0 24 24',
            stroke: 'currentColor',
            'stroke-width': '1.5',
            'aria-hidden': 'true',
            'data-slot': 'icon',
        }, [
            h('path', {
                'stroke-linecap': 'round',
                'stroke-linejoin': 'round',
                d: 'M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M12 13l-3 -3M12 13l3 -3M12 13v10'
            })
        ]);
    }
});
