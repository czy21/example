import helper, {Helper} from "@/helper";
import api, {API} from "@/api";

export interface Stub {
    api: API,
    helper: Helper
}

export default {
    api, helper
}