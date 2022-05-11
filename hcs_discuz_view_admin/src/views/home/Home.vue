<template>
  <div class="home">
    <div>
      <el-carousel indicator-position="outside">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3>{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="font-size: 24px;margin-top: 20px;text-align: center;">
      欢迎管理员{{username}}来到
      <span style="color: #4286F3;">HAUE </span>
      <span style="color: #FAC230;">Center </span>
      <span style="color: #55AF7B;">Square </span>
      后台管理系统<br/>
    </div>
    
  </div>
</template>

<script>
import {UserFind} from '../../network/auth'
export default {
  name: 'HelloWorld',
  data(){
    return {
      username:null
    }
  },
  mounted(){
    UserFind().then(res=>{
      if(res.data.success===false){
        alert("未登录")
      }else{
        this.username = res.data.datas.user.username
      }
    })
  },
  methods:{
    
  }
}
</script>
<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>