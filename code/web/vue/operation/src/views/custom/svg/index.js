const requireAll = requireContext => requireContext.keys().map(requireContext)
const req = require.context('@a/icons/svg', false, /\.svg$/)
const iconMap = requireAll(req)

export default {
  getIconList() {
    return iconMap.map(item => {
      return item.default.id
    })
  }
}
