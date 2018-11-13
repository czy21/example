import {prepare} from 'fr'
import store from '@c/store'
import routes from '@c/routes'
import mixins from '@c/mixins'
import ui from '@c/ui'

const decorators = {
  router: {
    mode: 'history',
    routes: routes
  },
  store,
}
const extraComponents = [
  ui,
  mixins
]
prepare({decorators, extraComponents})
