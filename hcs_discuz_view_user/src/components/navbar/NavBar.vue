<template>
  <div class="nav-bar">
    <el-row>
      <el-col :xs="0" :sm="0" :md="0" :lg="2" :xl="4"><div class="grid-content" /></el-col>
      <el-col :xs="5" :sm="3" :md="3" :lg="2" :xl="2" style="padding-top: 7px; text-align: center">
        <span style="font-size: 32px;" @click="this.$router.push({name:'/'})">
          <b>
            <span style="color: #4286F3;">H</span>
            <span style="color: #FAC230;">C</span>
            <span style="color: #55AF7B;">S</span>
          </b>
        </span>
      </el-col>
      <el-col :xs="9" :sm="8" :md="8" :lg="6" :xl="4">
        <el-menu
          :default-active="this.$route.name"
          class="el-menu-demo"
          mode="horizontal"
          router=true
        >
          <el-menu-item index="/">热门</el-menu-item>
          <el-menu-item index="/new">最新</el-menu-item>
          <el-menu-item index="/category">分类</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :xs="0" :sm="0" :md="0" :lg="1" :xl="1"><div class="grid-content" /></el-col>
      <el-col :xs="0" :sm="5" :md="5" :lg="5" :xl="4" style="padding-top: 12px; padding-left: 5px">
        <el-input
          v-model="keyWord"
          placeholder="Please input"
          class="w-50 m-2"
          >
          <template #append>
            <el-button type="primary" @click="handleSearch"><el-icon><Search/></el-icon></el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :xs="0" :sm="3" :md="3" :lg="2" :xl="2" style="padding-top: 12px; text-align: center">
        <el-button type="primary" round @click="this.$router.push('/writetopic')">写帖子</el-button>
      </el-col>
      <el-col :xs="4" :sm="0" :md="0" :lg="0" :xl="0" style="padding-top: 12px; text-align: center">
        <el-button type="primary" round @click="this.$router.push('/writetopic')">发帖</el-button>
      </el-col>
      <el-col :xs="6" :sm="5" :md="5" :lg="4" :xl="3">
        <el-row>
          <el-col :xs="12" :sm="11" :md="10" :lg="6" :xl="6">
            <div class="grid-content" style="background: #fff; padding-top: 14px; padding-right: 8px; text-align: center" >
              <el-popover placement="bottom" title="消息" :width="300" trigger="click">
                <template #reference>
                  <el-badge :is-dot="isNewMessage" class="item">
                    <el-icon :size="28" color="#aaa" @click="readMessage"><bell-filled /></el-icon>
                  </el-badge>
                </template>
                <message-list :userId="userInfo.userId" v-if="isLoaded"/>
              </el-popover>
            </div>
          </el-col>
          <el-col :xs="11" :sm="11" :md="11" :lg="6" :xl="6">
            <div class="grid-content" style="background: #fff; padding-top: 10px; padding-right: 8px; text-align: right" >
              <span @click="handleProfileClick">
                <el-avatar shape="square" :size="38" :src="userInfo.avatar" />
              </span>
            </div>
          </el-col>
          <el-col :xs="0" :sm="0" :md="0" :lg="12" :xl="12">
            <el-dropdown>
              <div class="grid-content" 
                style="padding-top: 18px; text-align: left" 
                >
                <span style="font-size: 20px; padding-right: 10px">{{userInfo.username}}</span>
                <el-icon><arrow-down-bold /></el-icon>
              </div>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="handleProfileClick">个人中心</el-dropdown-item>
                  <el-dropdown-item @click="handleResetPassword">修改密码</el-dropdown-item>
                  <el-dropdown-item @click="handleUserLogout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-col>
          <el-col :xs="1" :sm="2" :md="3" :lg="0" :xl="0"></el-col>
        </el-row>
      </el-col>
      <el-col :xs="0" :sm="0" :md="0" :lg="2" :xl="4"><div class="grid-content" /></el-col>
    </el-row>
    <div id="menu-line"></div>
  </div>
</template>

<script>
import { Search, BellFilled, ArrowDownBold } from '@element-plus/icons-vue'
import {UserLogout} from '../../network/auth'
import MessageList from './childcomponents/MessageList.vue'
import {handleNotification} from '../../network/notification'
import config from '../../assets/js/config'
import Cookies from 'js-cookie'
export default {
  props:{
    userInfo:{
      default(){
        return {}
      }
    }
  },
  data(){
    return {
      keyWord: null,
      isNewMessage: true,
      isLoaded: true
    }
  },
  methods: {
    handleProfileClick(){
      this.$router.push({path:'/profile', query: {userId: this.userInfo.userId}})
    },
    handleUserLogout(){
      // 告诉服务端退出
      UserLogout()
      //清除登录的cookie
      Cookies.remove('token')
      window.location.href = config.authPage
    },
    handleSearch(){
      this.$router.push({name:'/search',params: {topicTitle:this.keyWord}})
      //this.$router.push({path:'/search/'+this.keyWord})
    },
    readMessage(){
      this.isNewMessage = false,
      handleNotification(this.userInfo.userId);
    },
    handleResetPassword(){
      window.location.href = config.resetPasswordPage
    }
  },
  components: { 
    Search,
    BellFilled,
    ArrowDownBold,
    MessageList
  },

}
</script>

<style scoped>
.grid-content{
  height: 30px;
}
.nav-bar{
  background-color: #fff;
}
#menu-line{
  height: 1px;
  background-color: #ddd;
}
.el-menu-item{
  font-size: 16px;
}

</style>