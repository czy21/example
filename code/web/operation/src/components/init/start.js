import {start} from 'fr'

import app from '@/app.vue'

const decorators = {
  launch: {
    rootVueComponent: {
      el: '#app',
      components: {app}
    }
  }
}
start({decorators})
