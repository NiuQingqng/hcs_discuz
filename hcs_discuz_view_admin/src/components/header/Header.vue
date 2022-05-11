<template>
  <div class="header">
    <el-row>
      <el-col :span="12" style="padding-top: 7px; padding-bottom: 10px;">
        <span style="font-size: 32px;" @click="this.$router.push({name:'/'})">
          <b>
            <span style="color: #4286F3;">H</span>
            <span style="color: #FAC230;">C</span>
            <span style="color: #55AF7B;">S</span>
          </b>
        </span>
      </el-col>
      <el-col :span="12">
        <div style="padding-top: 10px; text-align: right" >
          <span @click="handleProfileClick">
            <el-avatar shape="square" :size="38" :src="userInfo.avatar" />
          </span>
          <el-dropdown>
          <div style="padding-top: 8px; padding-left:10px " >
            <span style="font-size: 20px; padding-right:5px">{{userInfo.username}}</span>
            <el-icon><arrow-down-bold /></el-icon>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="handleResetPassword">修改密码</el-dropdown-item>
              <el-dropdown-item @click="handleUserLogout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        </div>
      </el-col>
    </el-row>
    <div style="height: 1px; background-color: #ddd"></div>
  </div> 
</template>

<script>
  import {ArrowDownBold } from '@element-plus/icons-vue'
  import {UserLogout} from '../../network/auth'
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
    components: {
      ArrowDownBold
    },
    methods: {
      handleUserLogout(){
        // 告诉服务端退出
        UserLogout()
        // 清除登录的cookie
        Cookies.remove('token')
        // 到登录页
        window.location.href = config.authPage
      },
      handleResetPassword(){
        window.location.href = config.resetPasswordPage
      }
    }
  }
</script>

<style scoped>

</style>