import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import echarts from 'echarts'
import store from './store';
import api from './api'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/icon/iconfont.css';
// import './permission'
import helper from './compoments/helper'

Vue.use(ElementUI, {size: 'mini'});
Vue.prototype.$api = api;
Vue.prototype.$echarts = echarts
Vue.prototype.$helper = helper

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
