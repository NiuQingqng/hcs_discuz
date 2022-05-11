<template>
  <div class="creative-center">
    <div>
      <el-row>
        <el-col :span="12">
          <div>
            <el-icon :size="24"><edit /></el-icon>
            <span>我的内容</span>
          </div>
        </el-col>
        <el-col :span="12">
          <div style="text-align: right">
            <router-link :to="{path:'/profile', query: {userId: 0}}">
              个人中心
            </router-link>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="creative-list-item">
      <el-row>
        <el-col :span="6" @click="handleWriteClick">
          <el-button size="large" type="primary" circle><el-icon><edit /></el-icon></el-button><br />
          <span>写帖子</span>
        </el-col>
        <el-col :span="6" @click="handleWriteClick">
          <el-button size="large" type="success" circle><el-icon><edit /></el-icon></el-button><br />
          <span>问问题</span>
        </el-col>
        <el-col :span="6">
          <el-button size="large" type="warning" circle><el-icon><edit /></el-icon></el-button><br />
          <span>留言</span>
        </el-col>
        <el-col :span="6" @click="handleProfileClick">
          <el-button size="large" type="danger" circle><el-icon><edit /></el-icon></el-button><br />
          <span>我的内容</span>
        </el-col>
      </el-row>
    </div>
    <div class="creative-card">
      <el-card  shadow="hover" body-style="background-color: #fcfcfc;color: #999">
        <span>开启你的创作之旅,结识更多的朋友吧！</span>
      </el-card>
    </div>
    <div v-if="userInfo" style="margin-bottom: 20px">
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
    <div>
      <el-button size="large" style="font-size:16px; color: #626aef;width: 100%"  @click="handleWriteClick">去写一个帖子</el-button>
    </div>
  </div>
</template>

<script>
import { Edit } from '@element-plus/icons-vue'
export default {
  components:{
    Edit
  },
  props:{
    userInfo:{
      type:Object,
      default(){
        return null
      }
    }
  },
  methods: {
    handleProfileClick(){
      this.$router.push({name:'/profile', query: {userId: 0}})
    },
    handleWriteClick(){
      this.$router.push({name:'/writetopic'})
    },
  }
}
</script>

<style scoped>
.creative-list-item{
  margin-top: 20px;
  text-align: center;
}
.creative-card{
  padding: 20px;
}
a{
  text-decoration:none ; 
  color: #000;
}
a:hover{
  color: #666;
}
</style>