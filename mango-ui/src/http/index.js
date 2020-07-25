import api from './api' //导入有接口
// import { install } from 'element-ui'

const install = Vue => {
    if(install.installed){
        return;
    }
    install.installed = true;
    Object.defineProperties(Vue.prototype,{
        // 此处挂载在Vue原型的$api上
        $api:{
            get(){
                return api
            }
        }
    })
}

export default install