const inform = function (text: string, callback: Function) {
    console.log(text)
    // this.$alert(text, '提示', {
    //     type: 'info',
    //     dangerouslyUseHTMLString: true,
    //     showClose: false
    // }).finally(() => {
    //     stub.ref.jsUtil.basic.callIfExists(callback)
    // }, true)
}

export default {inform}

