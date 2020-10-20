export interface EUI {
    inform(text: string, callback: Function): void
}

const inform = function (text: string, callback?: Function): void {
    console.log(text)
}
export default {
    inform
}