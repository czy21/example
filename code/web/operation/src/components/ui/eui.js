export default function (stub) {
  const inform = function (text, callback) {
    this.$alert(text, '提示', {
      type: 'info',
      dangerouslyUseHTMLString: true,
      showClose: false
    }).finally(() => {
      stub.ref.jsUtil.basic.callIfExists(callback)
    }, true)
  }
  return {inform}
}
