<template>
  <div id="app">
    <el-container v-loading="!isLogin" v-if="isLogin">
      <el-header id="header">
        <nav-header :userInfo="userInfo" />
      </el-header>
      <el-container id="content">
        <el-aside>
          <side-menu id="side-menu" />  
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import NavHeader from './components/header/Header.vue'
import SideMenu from "./components/menu/Menu.vue"
import {UserFind} from './network/auth'
import Cookies from 'js-cookie'
import config from './assets/js/config'

export default {
  name: 'App',
  mounted(){
    this.initUserInfo()
  },
  components:{
    NavHeader,
    SideMenu,
  },
  data(){
    return {
      userInfo: {},
      isLogin: false
    }
  },
  methods: {
    initUserInfo(){
      if(Cookies.get("token")===null){
        alert("token为空，未登录")
        //this.$router.replace("/login")
        window.location.href = config.loginPage
        return
      }
      UserFind().then(res=>{
          if(res.data.success===true){
            //判断身份
            switch(res.data.datas.user.role){
              case 1:
              case 2: 
                //去用户端
                window.location.href = config.userPage;
                break;
              case 3: 
                this.userInfo = res.data.datas.user;
                //登录的User信息存入到store
                //this.$store.commit('initLoginUserInfo', res.data.datas.user)
                this.isLogin = true;//数据加载完成并判断登录，去生成子组件
                break;
              default: 
                alert("权限有误");
                //回登录页
                window.location.href = config.loginPage;
                break;
            }
          }else{
            alert("未登录!")
          }
      })
    },
  }
}
</script>

<style>
  
</style>
