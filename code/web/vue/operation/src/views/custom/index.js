import SvgIcon from './svg/SvgIcon'


export default {
  components: [SvgIcon],
  install: Vue => {
    [SvgIcon].map(m => {
      Vue.component(m.name, m)
    })
  },
}
