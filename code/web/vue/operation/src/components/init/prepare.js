import {prepare} from 'fr'
import store from '@c/store'
import mixins from '@c/mixins'
import ui from '@c/ui'

const decorators = {
  store,
}
const extraComponents = [
  ui,
  mixins
]
prepare({decorators, extraComponents})
