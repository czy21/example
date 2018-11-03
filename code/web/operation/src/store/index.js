import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import getters from './getters'
import pocket from './modules/pocket'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    pocket
  },
  getters
})

export default store
