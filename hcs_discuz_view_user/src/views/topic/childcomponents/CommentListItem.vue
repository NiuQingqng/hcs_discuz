<template>
  <div class="comment-list-item">
    <div class="user-info">
      <div>
        <el-avatar shape="square" :size="42" :src="commentInfo.user.avatar" @click="handleProfileClick" />
      </div>
      <div  style="padding-left: 10px;">
        <b style="font-size: 18px;">{{commentInfo.user.username}}</b><br>
        <span style="font-size: 12px; color: #666">计算机科学与技术</span>
      </div>
    </div>
    <div class="comment-content">
        <p v-html="commentInfo.content">
        </p>
    </div>
    <div class="comment-other" style="margin-top: 10px">
      <p style="color: #999">{{formatTime(commentInfo.createTime)}}</p>
      <el-row>
        <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
          <span><el-button color="#626aef" plain round @click="this.$emit('handleThumbClick')">点赞 {{commentInfo.thumbCount}}</el-button></span>
          <el-button type="text" @click="handleCommentClick">
            <span class="comment-other-comment" ref="commentText">{{commentInfo.commentCount}} 条评论</span>
          </el-button>
          <span style="margin-left: 15px" v-if="commentInfo.user.userId===loginUserId">
            <el-button type="text" @click="handleDeleteComment">删除</el-button>
          </span>
        </el-col>
        <el-col :xs="0" :sm="12" :md="12" :lg="12" :xl="12">
          <div style="text-align: right">
            <span style="color: #999"> {{formatTime(commentInfo.createTime)}} </span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="grid-content" v-show="isShowCommentEdit">
      <little-comment-list 
        :rootId="commentInfo.commentId" 
        @handleLittleCommentClick="handleLittleCommentClick"
        @afterDeleteComment="afterDeleteLittleComment"
        :key="littleCommentListKey"/>
    </div>
    <div style="margin-top: 10px;" v-show="isShowCommentEdit">
      <el-input
        ref="littlecommentinput"
        v-model="content"
        autosize
        type="textarea"
        placeholder="Please input"
      />
      <div style="margin-top: 10px; text-align: right">
        <el-button type="primary" @click="handleSubmit">发送</el-button>
      </div>
    </div>
    <el-divider />
  </div>
</template>

<script>
import {commonFormatTimeToStr} from '../../../assets/js/date.js'
import LittleCommentList from './commentListItemChilds/LittleCommentList.vue'
import {addComment} from '../../../network/comment'
import { ElMessage } from 'element-plus'
export default {
  props:{
    commentInfo:{
      type:Object,
      default(){
        return {}
      }
    }
  },
  components: {
    LittleCommentList
  },
  data(){
    return {
      isShowCommentEdit:false,
      content: null,
      littleCommentListKey: 1,
      loginUserId: this.$store.state.loginUserInfo.userId
    }
  },
  methods: {
    //回复评论点击,展开小评论
    handleCommentClick(){
      if(this.isShowCommentEdit){
        this.$refs.commentText.innerHTML = this.commentInfo.commentCount+" 条评论";
      }
      else {
        this.$refs.commentText.innerHTML = "收起 ^";
      }
      this.isShowCommentEdit = !this.isShowCommentEdit
    },
    //小评论回复按键点击，输入框填入@用户名
    handleLittleCommentClick(username){
      this.content = "回复@" + username + "：";
      this.$refs.littlecommentinput.focus();
    },
    //评论提交（小评论）
    handleSubmit(){
      const comment = {
        rootId: this.commentInfo.commentId,
        content: this.content,
      }
      addComment(comment).then(res=>{
        if(res.data.success===true){
          ElMessage({
            message: '发送成功！',
            type: 'success',
          })
          this.content=null
          this.littleCommentListKey = this.littleCommentListKey+1 //刷新评论列表
          this.$emit('afterAddLittleComment') //提示父组件更新评论数量
        }else{
          alert(res.data.message)
        }
      })
    },
    handleProfileClick(){
      this.$router.push({path:'/profile', query: {userId: this.commentInfo.userId}})
    },
    //删除方法，删除本条回复(回复！！！)
    handleDeleteComment(){
      this.$emit('handleDeleteComment') //提示父组件
    },
    //接收子组件事件，评论删除之后(评论！！！)
    afterDeleteLittleComment(){
      this.$emit('afterDeleteLittleComment') //提示父组件
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
.user-info div{
  display: inline-block;
  vertical-align:top
}
.comment-other-comment{
  color: #888;
  margin-left: 15px;
}
.comment-other-comment:hover{
  color: #666;
}
.grid-content{
  margin-top: 10px;
  box-shadow: 0px 0px 3px #ccc;
  border-radius: 2px;
  background-color: #fff;
  padding: 20px;
}
</style>