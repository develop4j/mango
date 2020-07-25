<template>
  <div class="container">
    <!-- 导航栏 -->
    <nav-bar></nav-bar>
    <!-- 头部区域 -->
    <header-bar></header-bar>
    <!-- 主内容区域 -->
    <main-content></main-content>
  </div>
</template>

<script>
// import axios from 'axios'
// import mock from '@/mock/index.js'
import HeaderBar from './HeaderBar'
import NavBar from './NavBar'
import MainContent from './MainContent'
export default {
  components:{
    HeaderBar,
    NavBar,
    MainContent
  }
}
</script>

