<template>
  <div class="info">
    <h3>他的资料卡</h3>
    <span>昵称：{{userInfo.username}}</span><br/>
    <span>性别：{{userInfo.sex}}</span><br/>
    <span>生日：{{userInfo.birthday}}</span><br/>
    <span>城市：{{userInfo.city}}</span><br/>
    <span>个人简介：{{userInfo.describe}}</span>
  </div>
</template>

<script>
export default {
  props:{
    userInfo:{
      default(){
        return {}
      }
    }
  }
}
</script>

<style>
.info span{
  line-height: 25px;
  color: #666
}
</style>