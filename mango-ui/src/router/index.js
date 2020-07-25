import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Login from '@/views/Login'
import NotFound from '@/views/404'
import store from '../store'
import api from '@/http/api'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },{
      path: '/404',
      name: 'NotFound',
      component: NotFound
    }
  ]
})

router.beforeEach((to,from,next) => {
  //登陆成功后把登陆信息存储到会话中
  //存在时间为会话生命周期，页面关闭即失效
  let userName = sessionStorage.getItem('user')
  if(to.path === '/login'){
      // 判断用户信息是否存在，如果存在直接跳转到首页
      if(userName){
          next({path:'/'})
      }else{
        next()
      }
  }else{
    // 如果访问非登录页面且用户信息已过期重新登陆
    if(!userName){
      next({path:'/login'})
    }else{
      // 动态加载菜单和路由
      addDynamicMenuAndRoutes(userName,to,from)
      next()
    }
  }
})

// 动态加载菜单和路由

function addDynamicMenuAndRoutes(userName,to,from){
  if(store.state.app.menuRouteLoaded){
    console.log('动态菜单和路由已存在')
    return;
  }
  api.menu.find
}