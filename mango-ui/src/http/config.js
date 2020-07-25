import {baseUrl} from '@/utils/global'

export default {
    method: 'get',
    // 基础url前缀
    baseUrl: baseUrl,
    // 请求头信息
    headers: {
        'Content-Type':'application/json;chatset=UTF-8'
    },
    // 参数
    data: {},
    // 超时时间
    timeout: 10000,
    // 携带凭证
    withCredentials: true,
    // 返回数据类型
    responseType: 'json'
}