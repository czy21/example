import {AxiosResponse} from "axios";

let _ = require('lodash')

const callIfExists = function (fn?: Function, resultForNonFunction?: any, ...args: any) {
    if (_.isFunction(fn)) {
        return fn?.apply(args)
    }
    return resultForNonFunction
}

const downloadFile = function (res: AxiosResponse, fileName?: string) {
    let url = URL.createObjectURL(res.data)
    const a = document.createElement('a')
    a.style.display = 'none'
    a.download = decodeURIComponent(res.headers?.filename ?? fileName)
    a.href = url
    a.click()
    URL.revokeObjectURL(url);
}

export default {
    callIfExists,
    downloadFile
}