import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import store from './store'
import api from "./api"

Vue.config.productionTip = false
Vue.use(ElementUI, {size: 'mini'});
Vue.prototype.$api = api
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
