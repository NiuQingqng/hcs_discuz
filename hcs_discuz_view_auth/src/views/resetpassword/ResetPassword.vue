<template>
  <div class="resetpassword">
    <div class="main-content">
      <el-row>
        <el-col :xs="1" :sm="4" :md="6" :lg="7" :xl="8">
          <div style="background-color: #eee"></div>
        </el-col>
        <el-col :xs="22" :sm="16" :md="12" :lg="10" :xl="8">
          <div class="resetpassword-form">
            <h2>重置密码</h2>
            <el-form
              :rules="rules"
              >
              <el-form-item prop="email">
                <el-input placeholder="请输入邮箱" 
                  style="font-size: 15px;" 
                  v-model="email" 
                  size="large" >
                  <template #prefix>
                    <el-icon>
                      <Message/>
                    </el-icon>
                  </template>
                  
                </el-input>
              </el-form-item>
              <el-form-item prop="code">
                <el-input placeholder="请输入邮箱验证码" 
                  style="font-size: 15px;" 
                  v-model="code" 
                  size="large" >
                  <template #prefix>
                    <el-icon>
                      <Message/>
                    </el-icon>
                  </template>
                  <template #append>
                    <el-button size="large" @click="handSendCode">发送验证码</el-button>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input placeholder="设置你的新密码" 
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
              </el-form-item>
              <el-form-item>
                <div style="width: 3%;"></div>
                <el-button
                  round size="large" 
                  @click="this.$router.go(-1)"
                  style="width: 45%; font-size: 15px">
                  取 消
                </el-button>
                <div style="width: 4%;"></div>
                <el-button type="primary"
                  round size="large" 
                  @click="handleSubmit"
                  style="width: 45%; font-size: 15px">
                  提 交
                </el-button>
                <div style="width: 3%;"></div>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
        <el-col :xs="1" :sm="4" :md="6" :lg="7" :xl="8">
          <div style="background-color: #eee"></div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { Lock,Message } from '@element-plus/icons-vue'
import { ElMessageBox,ElMessage } from 'element-plus'
import config from '../../assets/js/config'
import {existSendCode, resetPwd} from '../../network/auth'
import Cookies from 'js-cookie'

export default {
  components: {
    Lock,
    Message
  },
  data(){
    return {
      password: null,
      email: null,
      code: null,
      imgcodeid:null,
      codeimgurl:null,
    }
  },
  mounted(){
    this.getImgCode()
  },
  methods:{
    // 验证邮箱函数
    validateEmail() {
      // 判空
      if(this.email === null){
        return false
      }
      // 去空格
      this.email = this.email.replace(/\s*/g,"");
      // 验证长度
      if(this.email.length < 4 || this.email.length > 40){
        return false
      }
      // 验证格式
      var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return re.test(this.email);
    },
    // 发送验证码请求
    handSendCode(){
      if(!this.validateEmail()){
        alert("邮箱格式有误！")
        return
      }
      ElMessageBox.prompt(
      '<img src='+ this.codeimgurl+'>',
      '请输入验证码',{
        dangerouslyUseHTMLString: true,
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
      }).then(() => {
          existSendCode(this.email).then(res=>{
            if(res.data.success===false){
              ElMessage.error(res.data.message)
            }else{
              ElMessage.success("验证码已发送！")
            }
          })
        }).catch(() => {
          ElMessage({
            type: 'info',
            message: '取消发送！',
          })
        })
    },
    // 提交
    handleSubmit(){
      if(!this.validateEmail()){
        alert("邮箱格式有误！")
        return
      }
      //判空
      if(this.code === null){
        alert("邮箱验证码不能为空！")
        return
      }
      if(this.password === null){
        alert("用户名或密码不能为空！")
        return
      }
      //去空格
      this.code = this.code.replace(/\s*/g,"");
      this.password = this.password.replace(/\s*/g,"");
      // 验证长度
      if(this.code.length < 4){
        alert("邮箱验证码长度不能小于4！")
        return
      }
      if(this.password.length < 4 || this.password.length > 16){
        alert("密码长度为4-16！")
        return
      }
      resetPwd(this.email, this.code, this.password).then(res=>{
        if(res.data.success===false){
          alert(res.data.message)
        }else{
          alert("重置成功！")
          // 清除登录的cookie
          Cookies.remove('token')
          this.$router.replace("/")
        }
      })
    },
    // 获取图片验证码请求
    getImgCode(){
      this.imgcodeid = Math.round(Math.random()*899999+100000)
      this.codeimgurl = config.host+"/code/findimg?imgcodeid=" + this.imgcodeid;
    }
  },
}
</script>

<style scoped>
.main-content {
  margin-top: 40px;
}
.resetpassword-form {
  background-color: rgba(255, 255, 255, 0.2);
  padding: 20px;
  border: 1px solid #eee;
  border-radius: 0px;
  box-shadow: 1px 1px 3px #eee;
}
</style>