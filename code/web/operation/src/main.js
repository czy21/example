import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import echarts from 'echarts'
import store from './store';
import api from './api'
import helper from './compoments/helper'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/icon/iconfont.css';
// import './permission'

Vue.use(ElementUI, {size: 'mini'});
Object.defineProperties(Vue.prototype, {
  '$api': {
    value: api
  },
  '$echarts': {
    value: echarts
  },
  '$helper': {
    value: helper
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
