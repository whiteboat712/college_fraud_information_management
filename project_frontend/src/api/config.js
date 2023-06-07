import axios from "axios";
import {ElMessage} from "element-plus";

const Service = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json;charset=utf-8',
    },
    time: 10000,
})

// 请求拦截器
Service.interceptors.request.use((config) => {
    config.headers.common['Authorization'] = window.sessionStorage.getItem('token') === null ? null : window.sessionStorage.getItem('token');
    return config;
})
// 响应拦截器
Service.interceptors.response.use((response) => {
    // 获取接口返回结果
    const res = response.data
    if (res.status === 0) {
        return res
    } else {
        ElMessage.error(res.message || '网络异常')
        return res
    }
})

export default Service

