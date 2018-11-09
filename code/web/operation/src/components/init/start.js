import {start} from 'fr'
import routes from '@c/routes'
import store from '@c/store'
import app from '@/app.vue'

const decorators = {
  router: {routes},
  launch: {
    rootVueComponent: {
      el: '#app',
      components: {app}
    }
  },
  store
}
start({decorators})
