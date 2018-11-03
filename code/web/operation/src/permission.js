import router from './router'
import {getToken} from "./utils/auth";

router.beforeEach((to, from, next) => {
  if (getToken()) {
    next()
  }
  else {
    if (to.path === '/Login') {
      next()
    }
    else {
      next('/Login')
    }
  }
})
