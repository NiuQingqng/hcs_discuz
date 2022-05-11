<template>
  <div class="login">
    <div class="main-content">
      <el-row>
        <el-col :xs="1" :sm="1" :md="2" :lg="4" :xl="5">
          <div style="background-color: #eee"></div>
        </el-col>
        <el-col :xs="0" :sm="11" :md="10" :lg="8" :xl="7">
          <div class="login-img">
            <div style="padding-top: 150px; font-size: 28px">
              <span style="color: #4286F3;">HAUE </span>
              <span style="color: #FAC230;">Center </span>
              <span style="color: #55AF7B;">Square </span>
              <div style="padding-top: 20px; font-size: 18px">
                <span>用户名：niuqing</span><br>
                <span>密码：123456</span><br>
                <span>管理员：niuniu</span><br>
                <span>密码：123456</span><br>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :xs="22" :sm="11" :md="10" :lg="8" :xl="7">
          <div class="login-form">
            <h2>登 录</h2>
            <el-form>
              <el-form-item prop="username">
                <el-input placeholder="请输入用户名" 
                  style="font-size: 15px" 
                  v-model="username" 
                  size="large" >
                  <template #prefix>
                    <el-icon>
                      <User/>
                    </el-icon>
                  </template>
                </el-input>
                <router-link to="/register">没有账号？去注册</router-link>
              </el-form-item>
              <el-form-item prop="password">
                <el-input placeholder="请输入密码" 
                  style="font-size: 15px" 
                  type="password" show-password 
                  v-model="password" 
                  size="large" >
                  <template #prefix>
                    <el-icon>
                      <Lock/>
                    </el-icon>
                  </template>
                </el-input>
                <router-link to="/resetpassword">忘记密码？</router-link>
              </el-form-item>
              <el-form-item prop="code">
                <el-input placeholder="请输入验证码" 
                  style="font-size: 15px; width: 55%;" 
                  v-model="code" 
                  size="large" />
                <div style="width: 45%; padding-top: 10px" >
                  <el-image :src="codeimgurl"  @click="getImgCode"/>
                </div>
              </el-form-item>
              <el-form-item style="padding-left: 20px; padding-right: 20px">
                <el-button type="primary" round
                  size="large"
                  @click="handleLogin"
                  style="width: 100%; font-size: 15px">
                  登 录
                </el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
        <el-col :xs="1" :sm="1" :md="2" :lg="4" :xl="5">
          <div style="background-color: #eee"></div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { User,Lock } from '@element-plus/icons-vue'
import {userFind, userLogin} from "../../network/auth"
import Cookies from 'js-cookie'
import config from '../../assets/js/config'

export default {
  components: {
    User,
    Lock
  },
  data(){
    return {
      username: null,
      password: null,
      code:null,
      imgcodeid:null,
      codeimgurl:null,
      rules: null
    }
  },
  mounted(){
    // 判断是否已经登录
    this.initUserInfo()
    this.getImgCode()
  },
  methods:{
    handleLogin(){
      //判空
      if(this.username === null || this.password === null){
        alert("用户名或密码不能为空！")
        return
      }
      if(this.code === null){
        alert("验证码不能为空！")
        return
      }
      // 去空格
      this.username = this.username.replace(/\s*/g,"");
      this.password = this.password.replace(/\s*/g,"");
      this.code = this.code.replace(/\s*/g,"");
      // 判断长度
      if(this.username.length < 4){
        alert("用户名长度不能小于4！")
        return
      }
      if(this.password.length < 4){
        alert("密码长度不能小于4！")
        return
      }
      if(this.code.length != 4){
        alert("请输入四位验证码！")
        return
      }
      userLogin(this.username,this.password,this.imgcodeid,this.code).then(res=>{
        if(res.data.success===false){
          alert(res.data.message)
          // 刷新验证码
          this.getImgCode()
        }else{
          Cookies.set("token",res.data.token);
          switch (res.data.datas.role){
            case 1:
            case 2: window.location.href = config.userPage;break;
            case 3: window.location.href = config.adminPage;break;
            default: alert("账号身份有误！")
          }
        }
      })
    },
    getImgCode(){
      this.imgcodeid = Math.round(Math.random()*899999+100000)
      this.codeimgurl = config.host+"/code/findimg?imgcodeid=" + this.imgcodeid;
    },
    initUserInfo(){
      if(Cookies.get("token")===null){
        return
      }
      userFind().then(res=>{
          if(res.data.success===true){
            //判断身份
            switch(res.data.datas.user.role){
              case 1:
              case 2: 
                window.location.href = config.userPage;
                break;
              case 3: 
                //去管理员端
                window.location.href = config.adminPage;
                break;
              default: 
                break;
            }
          }else{
            return
          }
      })
    },
  },
}
</script>

<style scoped>
.main-content {
  margin-top: 80px;
}
.login-form {
  background-color: rgba(255, 255, 255, 0.2);
  padding: 20px;
  border: 1px solid #eee;
  border-radius: 0px;
  box-shadow: 1px 1px 3px #eee;
}
.login-img{
  background-color: rgba(255, 255, 255, 0.2);
  border: 1px solid #eee;
  height: 99.5%;
  box-shadow: 0px 1px 3px #eee;
}
a{
  text-decoration:none;
  color: #888
}
a:hover {
  color: #555
}
</style>