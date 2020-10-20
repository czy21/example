import eui from './eui'

export interface EUI {
    inform(text: string, callback?: Function): void
}

export default eui