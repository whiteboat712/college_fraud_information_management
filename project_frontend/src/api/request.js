import Service from "@/api/config";
import config  from 'process'

// 封装get
const get = (config) => {
    return Service({
        ...config,
        method: 'get',
        params: config.data
    })
}

// 封装post
const post = (config) => {
    return Service({
        ...config,
        method: 'post',

        data: config.data,
    })
}
export default {
    get,
    post,
}