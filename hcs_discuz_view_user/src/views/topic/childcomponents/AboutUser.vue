<template>
  <div class="about-user">
    <!-- <div>
      <h3>
        关于作者
      </h3>
    </div> -->
    <div>
      <el-row>
        <el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="18">
          <div class="user-info">
            <div>
              <el-avatar shape="circle" :size="42" :src="userInfo.avatar" />
            </div>
            <div style="padding-left: 10px;">
              <b style="font-size: 18px;">{{userInfo.username}}</b><br>
              <span style="font-size: 14px; color: #666">计算机科学与技术</span>
            </div>
          </div>
        </el-col>
        <el-col :xs="0" :sm="0" :md="6" :lg="6" :xl="6">
          <div style="text-align: right;">
            <el-button type="text" style="font-size: 16px" @click="handleProfileClick">他的主页</el-button>
          </div> 
        </el-col>
      </el-row>
    </div>
    
    <div>
      <p style="color: #666">
        {{userInfo.describe}}
      </p>
    </div>
    <div>
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
    <div style="padding-top: 20px">
      <el-button style="width: 100%" size="large" type="primary" plain round>给他点赞</el-button>
    </div>
  </div>
</template>

<script>
export default {
  props:{
    userInfo:{
        type:Object,
        default(){
          return {}
        }
    }
  },
  methods:{
    handleProfileClick(){
      this.$router.push({path:'/profile', query: {userId: this.userInfo.userId}})
    },
  }
}
</script>

<style>
.user-info div{
  display: inline-block;
  vertical-align:top
}
</style>