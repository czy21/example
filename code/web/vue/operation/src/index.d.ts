import {api} from '@/api'

declare module 'vue/types/vue' {
    interface Vue {
        $api: api
    }
}