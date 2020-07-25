import axios from 'axios'
import config from './config'
import Cookies from 'js-cookie'
import router from '@/router'
/**
 * axios拦截器，可以进行请求拦截拦截和响应拦截
 */

export default function $axios(options){
    return new Promise((resolve,reject) => {
        const instance = axios.create({
            baseURL: config.baseUrl,
            headers:config.headers,
            timeout:config.timeout,
            withCredentials:config.withCredentials
        })
        // request 请求拦截器
        instance.interceptors.request.use(
            config => {
                let token = Cookies.get('token')
                if(token){
                    config.headers.token = token
                }else{
                    // 重定向到登录页面
                    router.push('/login')
                }
                return config
            },
            error => {
                console.log('request error',error)
                if(error.code === 'ECONNABORTED' && error.message.indexOf('timeout') != -1){
                    console.log("请求超时")
                }
                // 重定向到错误页面
                const errorInfo = error.response
                console.log(errorInfo)
                if(errorInfo){
                    error = errorInfo.data
                    const errorStatus = errorInfo.status;
                    router.push({
                        path:`/error/${errorStatus}`
                    })
                }
                return Promise.reject(error)
            }
        )
        // response 响应拦截器
        instance.interceptors.response.use(
            response => {
                return response.data
            },
            err => {
                if (err && err.response) {
                    switch (err.response.status) {
                      case 400:
                        err.message = '请求错误'
                        break
                      case 401:
                        err.message = '未授权，请登录'
                        break
                      case 403:
                        err.message = '拒绝访问'
                        break
                      case 404:
                        err.message = `请求地址出错: ${err.response.config.url}`
                        break
                      case 408:
                        err.message = '请求超时'
                        break
                      case 500:
                        err.message = '服务器内部错误'
                        break
                      case 501:
                        err.message = '服务未实现'
                        break
                      case 502:
                        err.message = '网关错误'
                        break
                      case 503:
                        err.message = '服务不可用'
                        break
                      case 504:
                        err.message = '网关超时'
                        break
                      case 505:
                        err.message = 'HTTP版本不受支持'
                        break
                      default:
                    }
                  }
                  console.error(err)
                return Promise.reject(error)
            }
        )
        // 请求处理
        instance(options).then(res => {
            resolve(res)
            return false
        }).catch(error => {
            reject(error)
        })
    })
}
