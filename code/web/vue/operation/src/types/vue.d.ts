import {API} from '@/api'
import {Helper} from '@/helper'

declare module 'vue/types/vue' {
    interface Vue {
        $api: API,
        $helper: Helper
    }
}