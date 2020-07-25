<template>
    <div class="headbar" :style="{'background':themeColor}" :class="collapse?'position-collapse-left':'position-left'">
        <!--导航收缩  -->
        <span class="hamburg">
            <el-menu class="el-menu-demo" :background-color="themeColor" text-color="#fff" active-text-color="#14889A" mode="horizontal">
                <el-menu-item index="1" @click="onCollapse"><hamburger :isActive="collapse"></hamburger></el-menu-item>
            </el-menu>
        </span>
        <!-- 导航菜单 -->
        <span class="navbar">
        <el-menu :default-active="activeIndex" class="el-menu-demo" 
            :background-color="themeColor" text-color="#fff" active-text-color="#ffd04b" mode="horizontal" @select="selectNavBar()">
            <el-menu-item index="1" @click="$router.push('/')">{{$t("common.home")}}</el-menu-item>
            <el-menu-item index="2" @click="openWindow('https://gitee.com/liuge1988/kitty/wikis/Home')">{{$t("common.doc")}}</el-menu-item>
            <el-menu-item index="3" @click="openWindow('https://github.com/develop4j?tab=repositories')">{{$t("common.blog")}}</el-menu-item>
        </el-menu>
        </span>
        <!-- 工具栏 -->
        <span class="toolbar">
            <el-menu class="el-menu-demo" :background-color="themeColor" text-color="#14889A" active-text-color="#14889A" mode="horizontal">
                <!-- 主题切换 -->
                <el-menu-item index="1">
                    <theme-picker class="theme-picker" :default="themeColor" @onThemeChange="onThemeChange"></theme-picker>
                </el-menu-item>
                <!-- 语言切换 -->
                <el-menu-item index="2" v-popover:popover-lang>
                    <li style="color:#fff;" class="fa fa-language fa-lg"></li>
                    <el-popover ref="popover-lang" placement="bottom-start" trigger="click" v-model="langVisible">
                        <div class="lang-item" @click="changeLanguage('zh_cn')">简体中文</div>
                        <div class="lang-item" @click="changeLanguage('en_us')">English</div>
                    </el-popover>
                </el-menu-item>
                <!-- 我的私信 -->
                <el-menu-item index="3" v-popover:popover-message>
                    <el-badge :value="5" :max="99" class="badge" type="error">
                        <li style="color:#fff" class="fa fa-envelope-o fa-lg"></li>
                    </el-badge>
                    <el-popover ref="popover-message" placement="bottom-end" trigger="click">
                        <message-panel></message-panel>
                    </el-popover>
                </el-menu-item>
                <!-- 系统通知 -->
                <el-menu-item index="3" v-popover:popover-notice>
                    <el-badge :value="4" :max="99" class="badge" type="error">
                        <li style="color:#fff;" class="fa fa-bell-o fa-lg"></li>
                    </el-badge>
                    <el-popover ref="popover-notice" placement="bottom-end" trigger="click">
                        <notice-panel></notice-panel>
                    </el-popover>
                </el-menu-item>
                <!-- 用户信息 -->
                <el-menu-item index="5" v-popover:popover-personal>
                    <span class="user-info"><img :src="user.avatar" />{{user.name}}</span>
                    <el-popover ref="popover-personal" placement="bottom-end" trigger="click" :visible-arrow="false">
                        <personal-panel :user="user"></personal-panel>
                    </el-popover>
                </el-menu-item>
            </el-menu>
        </span>
    </div>
</template>
<script>
import { mapState } from 'vuex'
import Hamburger from "@/components/hamburger"
import ThemePicker from '@/components/themePicker'
import PersonalPanel from '@/views/core/PersonalPanel'
import NoticePanel from '@/views/core/NoticePanel'
import MessagePanel from '@/views/core/MessagePanel'
// import mock from '@/mock/index'
export default {
    components:{
        Hamburger,
        ThemePicker,
        MessagePanel,
        NoticePanel,
        PersonalPanel
    },
    data(){
        return {
            user:{
                name:'Levy',
                avatar:'',
                role:'超级管理员',
                registeInfo: "注册时间：2018-12-20 "
            },
            activeIndex:'1',
            langVisible:false
        }
    },
    methods:{
        openWindow(url) {
            window.open(url)
        },
        selectNavBar(key,keyPath){
            console.log(key,keyPath)
        },
        // 折叠导航栏
        onCollapse: function() {
            this.$store.commit('onCollapse')
        },
        // 主题切换
        onThemeChange(themeColor){
            this.$store.commit('setThemeColor',themeColor)
        },
        // 语言切换
        changeLanguage(lang) {
        lang === '' ? 'zh_cn' : lang
        this.$i18n.locale = lang
        this.langVisible = false
        }
    },
    mounted(){
        var user = sessionStorage.getItem("user")
        if (user) {
            this.user.name = user
            this.user.avatar = require("@/assets/user.png")
        }
    },
    computed:{
        ...mapState({
            themeColor: state=>state.app.themeColor,
            collapse:state => state.app.collapse
        })
    }
}
</script>

<style scoped lang="scss">
.headbar {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 1030;
  height: 60px;
  line-height: 60px;
  border-color: rgba(180, 190, 190, 0.8);
  border-left-width: 1px;
  border-left-style: solid;
}
.hamburg,.navbar {
  float: left;
}
.toolbar {
  float: right;
}
.lang-item {
  font-size: 16px;
  padding-left: 8px;
  padding-top: 8px;
  padding-bottom: 8px;
  cursor: pointer;
}
.lang-item:hover {
  font-size: 18px;
  background: #b0d6ce4d;
}
.user-info {
  font-size: 20px;
  color: #fff;
  cursor: pointer;
  img {
    width: 40px;
    height: 40px;
    border-radius: 10px;
    margin: 10px 0px 10px 10px;
    float: right;
  }
}
.badge {
  line-height: 18px;
}
.position-left {
  left: 200px;
}
.position-collapse-left {
  left: 65px;
}
</style>