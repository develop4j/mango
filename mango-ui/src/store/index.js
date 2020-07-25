import Vue from 'vue'
import vuex from 'vuex'
import menu from './modules/menu'
import user from './modules/user'

Vue.use(vuex)

// 引入子模块
import app from './modules/app'

const store = new vuex.Store({
    modules:{
        app:app,
        menu:menu,
        user:user
    }
})

export default store