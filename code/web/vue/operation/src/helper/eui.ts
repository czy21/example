import util from '@/util'

export interface EUI {
    inform(text: string, callback?: Function): void
}

const inform = function (text: string, callback: Function) {
    console.log(text)
    util.basic.callIfExists(callback)
    // this.$alert(text, '提示', {
    //     type: 'info',
    //     dangerouslyUseHTMLString: true,
    //     showClose: false
    // }).finally(() => {
    //
    //     stub.ref.jsUtil.basic.callIfExists(callback)
    // }, true)
}

export default {inform}

