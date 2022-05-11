<template>
  <div>
    <el-row>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
      <el-col :xs="24" :sm="16" :md="14" :lg="12" :xl="9">
        <div class="grid-content">
          <main-content @handleThumbClick="handleThumbClick" 
            @handleCommentClick="handleCommentClick"
            :topicInfo="topicInfo" 
            :userInfo="userInfo" 
            :key="topicInfo" />
        </div>
        <div class="grid-content" v-show="isShowComment">
          <write-comment :topicId="topicId" @afterAddComment="afterAddComment"/>
        </div>
        <div class="grid-content">
          <comment-list 
            :topicId="this.$route.query.topicId" 
            @afterDeleteComment = "afterDeleteComment"
            :key="commentListKey"/>
        </div>
      </el-col>
      <el-col :xs="0" :sm="8" :md="8" :lg="6" :xl="5">
        <!-- 关于作者 -->
        <div class="grid-content">
          <about-user :userInfo="userInfo" />
        </div>
        <!-- 轮播图 -->
        <div class="grid-content" style="padding: 0px">
          <carousel-diagram/>
        </div>
        <!-- 留言板 -->
        <div class="grid-content">
          <message-board/>
        </div>
        <!-- 关于 -->
        <div style="margin: 5px; padding: 10px;">
          <about/>
        </div>
      </el-col>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
    </el-row>
  </div>
</template>

<script>
import AboutUser from './childcomponents/AboutUser.vue'
import CommentList from './childcomponents/CommentList.vue'
import MainContent from './childcomponents/MainContent.vue'
import {getTopicInfo} from '../../network/topic'
import {getUserInfo} from '../../network/user'
import {addThumb} from '../../network/thumb'
import WriteComment from './childcomponents/WriteComment.vue'
import About from '../../components/right/About.vue'
import CarouselDiagram from '../../components/right/CarouselDiagram.vue'
import MessageBoard from '../../components/right/MessageBoard.vue'
export default {
  mounted() {
    this.topicId = this.$route.query.topicId,
    this.toGetTopicInfo()
  },
  data(){
    return {
      topicId: null,
      topicInfo: {},
      userInfo:{},
      isShowComment: false,
      commentListKey: 1
    }
  },
  components: { MainContent, CommentList, AboutUser, WriteComment,MessageBoard,CarouselDiagram,About },
  methods: {
    toGetTopicInfo(){
      //获取帖子信息
      getTopicInfo(this.topicId).then(res=>{
        if(res.data.success===true){
          this.topicInfo = res.data.datas.topicInfo
          //获取用户信息
          getUserInfo(this.topicInfo.userId).then(res=>{
            if(res.data.success===true){
              this.userInfo = res.data.datas.userInfo
            }else{
              alert(res.data.message)
            }
          })  
        }else{
          alert(res.data.message)
          this.$router.go(-1)
        }
      })
    },
    //接收子组件事件，进行点赞
    handleThumbClick(){
      const thumb = {
        topicId : this.topicInfo.topicId
      }
      addThumb(thumb).then(res=>{
        if(res.data.success===true){
          this.topicInfo.thumbCount++
        }else{
          alert(res.data.message)
        }
      })
    },
    //接收子组件事件，进行评论
    handleCommentClick(){
      this.isShowComment = !this.isShowComment
    },
    //接收子组件事件，评论添加之后
    afterAddComment(){
      this.isShowComment = false
      this.topicInfo.commentCount=this.topicInfo.commentCount+1
      this.commentListKey = this.commentListKey+1 //刷新评论列表  没生效需要修改
    },
    //接收子组件事件，评论删除之后
    afterDeleteComment(){
      this.topicInfo.commentCount=this.topicInfo.commentCount-1
    }
  }

}
</script>

<style scoped>
.grid-content{
  margin: 5px;
  box-shadow: 0px 0px 5px #ddd;
  border-radius: 2px;
  background-color: #fff;
  padding: 20px;
}
</style>