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
  const warn = function (text, callback) {
    this.$alert(text, '警告', {
      type: 'warning',
      dangerouslyUseHTMLString: true,
      showClose: false
    }).finally(() => {
      stub.ref.jsUtil.basic.callIfExists(callback)
    }, true)
  }

  const confirm = function (text, successCallback, cancelCallback) {
    this.$confirm(text, '提示', {
      dangerouslyUseHTMLString: true,
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      stub.ref.jsUtil.basic.callIfExists(successCallback)
    })
      .catch(() => {
        stub.ref.jsUtil.basic.callIfExists(cancelCallback)
      })
  }

  const validateForm = function (target) {
    let res = false
    this.$refs[target].validate(valid => {
      res = valid
    })
    return res
  }

  const actWithValidation = function (targets, callback) {
    if (!stub.ref.lodash.isArray(targets)) {
      targets = [targets]
    }
    let valid = stub.ref.lodash.every(targets, v => {
      return validateForm.apply(this, [v])
    })
    valid ? stub.ref.jsUtil.basic.callIfExists(callback) : warn.apply(this, ['请检查输入的参数再执行操作'])
  }
  return {inform, warn, confirm, actWithValidation}
}
