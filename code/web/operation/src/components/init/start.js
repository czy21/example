import {start} from 'fr'
import routes from '@c/routes'
import app from '@/app.vue'

const decorators = {
  router: {
    mode: 'history',
    routes: routes
  },
  launch: {
    rootVueComponent: {
      el: '#app',
      components: {app}
    }
  }
}
start({decorators})
