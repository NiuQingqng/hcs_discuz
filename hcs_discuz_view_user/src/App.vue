<template>
  <div id="app">
    <div v-loading="!isLogin" v-if="isLogin">
      <div id="header">
        <el-affix :offset="0">
          <nav-bar :userInfo="userInfo" />
        </el-affix>
      </div>
      <div id="content">
        <router-view/>
      </div>
    </div>
  </div>
  <el-backtop style="height: 50px; width: 50px" :right="20" :bottom="20" />
</template>

<script>
import NavBar from './components/navbar/NavBar.vue'
import {UserFind} from './network/auth'
import Cookies from 'js-cookie'
import config from './assets/js/config'

export default {
  name: 'App',
  mounted(){
    this.initUserInfo()
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
                this.userInfo = res.data.datas.user;
                //登录的User信息存入到store
                this.$store.commit('initLoginUserInfo', res.data.datas.user)
                this.isLogin = true;//数据加载完成并判断登录，去生成子组件
                break;
              case 3: 
                //去管理员端
                window.location.href = config.adminPage;
                break;
              default: 
                alert("权限有误");
                //回登录页
                window.location.href = config.authPage;
                break;
            }
          }else{
            alert("未登录!")
          }
      })
    },
  },
  components: {
    NavBar
   
  }
}
</script>

<style lang="scss">
#app{
  background-color: #eee;
}
</style>
