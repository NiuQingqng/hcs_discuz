<template>
  <div class="topic-list-item">
    <div class="topic-title">
      <router-link :to="{path:'/topicdetail',query:{topicId: topicInfo.topicId}}">
        <span style="font-size: 20px"><b>{{topicInfo.title}}</b></span>
      </router-link>
    </div>
    <div class="topic-content">
      <router-link :to="{path:'/topicdetail',query:{topicId: topicInfo.topicId}}">
        <p v-html="topicInfo.content">
        </p>
      </router-link>
    </div>
    <div class="topic-other" style="margin-top: 10px">
      <el-row>
        <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
          <span><el-button color="#626aef" plain @click="this.$emit('handleThumbClick')">点赞 {{topicInfo.thumbCount}}</el-button></span>
          <el-button type="text" @click="handleCommentClick">
            <span class="topic-other-comment">{{topicInfo.commentCount}} 条回复</span>
          </el-button>
        </el-col>
        <el-col :xs="0" :sm="12" :md="12" :lg="12" :xl="12">
          <div style="text-align: right">
            <span style="color: #999"> {{formatTime(topicInfo.updateTime)}} 更新 </span>
        <span style="color: #999"> {{topicInfo.viewCount}}浏览</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-divider />
  </div>
</template>

<script>
import {commonFormatTimeToStr} from '../../assets/js/date.js'
export default {
  props:{
    topicInfo:{
      type:Object,
      default(){
        return {}
      }
    }
  },
  methods: {
    handleCommentClick(){
      this.$router.push({path:'/topicdetail',query: {topicId:this.topicInfo.topicId}})
    }
  },
  computed: {
    //格式化时间监听，返回格式化时间函数
    formatTime(){
      return function(oldTime){
        return commonFormatTimeToStr(oldTime)
      }
    }
  }
}
</script>

<style scoped>
.topic-title:hover{
  color: cadetblue;
}
.topic-content{
  line-height: 25px;
  overflow:hidden; 
  text-overflow:ellipsis;
  display:-webkit-box; 
  -webkit-box-orient:vertical;
  -webkit-line-clamp:2;
}
a{
  text-decoration:none ; 
  color: #000;
}
a:hover{
  color: #666;
}
.topic-other-comment{
  color: #888;
  margin-left: 15px;
}
.topic-other-comment:hover{
  color: #666;
}
</style>