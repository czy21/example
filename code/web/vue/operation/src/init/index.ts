import ref, {Ref} from './reference'
import api, {API} from './request'
import helper, {Helper} from "@/helper";

export interface Stub {
    api: API,
    helper: Helper,
    ref: Ref
}

export default {
    api, helper, ref
}