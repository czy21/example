import {Stub} from '@/init'


declare module 'vue/types/vue' {
    interface Vue {
        $stub: Stub
    }
}