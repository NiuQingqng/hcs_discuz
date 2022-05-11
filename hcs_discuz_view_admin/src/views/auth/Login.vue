<template>
  <div>
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>登录</span>
          <el-button class="button" type="text">去注册</el-button>
        </div>
      </template>
      <el-form method="post">
        
        <el-form-item>
          <el-input type="text" v-model="username" name="username"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="text" v-model="password" name="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-image :src="codeimgurl"  @click="getImgCode"/>
          <el-input type="text" v-model="imgcode" name="imgcode"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onLogin">登录</el-button>
        </el-form-item>
        
      </el-form>
    </el-card>
  </div>
</template>

<script>
import {UserLogin} from '../../network/auth'
import Cookies from 'js-cookie'
export default {
  name: "Login",
  data(){
    return {
      username:'niuniu',
      password:'123456',
      imgcodeid:null,
      codeimgurl:null,
    }
  },
  mounted(){
    this.getImgCode()
  },
  methods:{
    onLogin(){
      if(this.username!=null&&this.password!=null){
        UserLogin(this.username,this.password).then(res=>{
            if(res.data.success===false){
              alert("账号或密码错误！")
            }else{
              //sessionStorage.setItem("token",res.data.token);
              Cookies.set("token",res.data.token);
              this.$router.replace('/')
            }
        })
      }else{
        alert("账号或密码不能为空！")
      }
    },
    getImgCode(){
      this.imgcodeid = Math.round(Math.random()*899999+100000)
      this.codeimgurl = "http://127.0.0.1:8088/code/findimg?imgcodeid=" + this.imgcodeid;
      // e.target.src= "http://127.0.0.1:8081/findImg?imgcodeid=" + this.imgcodeid;
    }
  },
}
</script>

<style>

</style>