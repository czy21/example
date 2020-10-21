import util from '@/util'
import {MessageBox} from "element-ui";

let _ = require('lodash')


export interface EUI {
    inform(text: string, callback?: Function): void
}

const inform = function (text: string, callback: Function) {
    MessageBox.alert(text, '提示', {
        type: 'info',
        dangerouslyUseHTMLString: true,
        showClose: false
    }).finally(() => {
        util.basic.callIfExists(callback)
    })
}

const warn = function (text: string, callback: Function) {
    MessageBox.alert(text, '警告', {
        type: 'warning',
        dangerouslyUseHTMLString: true,
        showClose: false
    }).finally(() => {
        util.basic.callIfExists(callback)
    })
}

const confirm = function (text: string, successCallback: Function, cancelCallback: Function) {
    MessageBox.confirm(text, '提示', {
        dangerouslyUseHTMLString: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(() => {
        util.basic.callIfExists(successCallback)
    }).catch(() => {
        util.basic.callIfExists(cancelCallback)
    })
}

// const validateForm = function (target) {
//     let res = false
//     this.$refs[target].validate(valid => {
//         res = valid
//     })
//     return res
// }
//
// const actWithValidation = function (targets, callback) {
//     if (!stub.ref.lodash.isArray(targets)) {
//         targets = [targets]
//     }
//     let valid = stub.ref.lodash.every(targets, v => {
//         return validateForm.apply(this, [v])
//     })
//     valid ? util.basic.callIfExists(callback) : warn.apply(this, ['请检查输入的参数再执行操作'])
// }

export default {inform, warn, confirm}

