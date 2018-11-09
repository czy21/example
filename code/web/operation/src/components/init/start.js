import {start} from 'fr'
import routes from '@c/routes'
import app from '@/app.vue'

const decorators = {
  router: {routes},
  launch: {
    rootVueComponent: {
      el: '#app',
      components: {app}
    }
  }
}
start({decorators})
