<template>
  <div v-if="userInfo" style="padding: 20px; background-color: #fff">
    <el-row>
      <el-col :span="8">
        <div style="text-align: center">
          <span style="font-size: 32px;color: cornflowerblue">{{userInfo.topicCount}}</span><br/>
          <span style="font-size: 14px;">帖子数量</span>
        </div>
      </el-col>
      <el-col :span="8">
        <div style="text-align: center">
          <span style="font-size: 32px;color: cornflowerblue">{{userInfo.commentCount}}</span><br/>
          <span style="font-size: 14px;">回复次数</span>
        </div>
      </el-col>
      <el-col :span="8">
        <div style="text-align: center">
          <span style="font-size: 32px;color: cornflowerblue">{{userInfo.thumbCount}}</span><br/>
          <span style="font-size: 14px;">收到的赞</span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  props:{
    userInfo:{
      type:Object,
      default(){
        return null
      }
    }
  },
}
</script>

<style>

</style>