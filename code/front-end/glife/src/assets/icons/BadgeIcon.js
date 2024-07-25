import { defineComponent, h } from 'vue';

export default defineComponent({
    name: 'BadgeIcon',
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
            h('circle', {
                cx: '12.5',
                cy: '12.5',
                r: '10',
                stroke: 'white',
                'stroke-width': '1.5',
                fill: 'none'
            })
        ]);
    }
});
