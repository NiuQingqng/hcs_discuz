<template>
  <div class="comment-list-item">
    <div class="user-info">
      <div>
        <el-avatar shape="square" :size="34" :src="commentInfo.user.avatar" @click="handleProfileClick" />
      </div>
      <div  style="padding-left: 10px;">
        <b style="font-size: 16px;">{{commentInfo.user.username}}</b><br>
        <div class="comment-content">
            <p>
              {{commentInfo.content}}
            </p>
        </div><br/>
        <span>
          <el-button type="text" @click="this.$emit('handleThumbClick')">点赞 {{commentInfo.thumbCount}}</el-button>
          <el-button type="text" @click="this.$emit('handleCommentClick')">回复</el-button>
        </span>
        <span style="margin-left: 15px" v-if="commentInfo.user.userId===loginUserId">
          <el-button type="text" @click="handleDeleteComment">删除</el-button>
        </span>
      </div>
    </div>
    <el-divider />
  </div>
</template>

<script>
import {commonFormatTimeToStr} from '../../../../assets/js/date.js'
export default {
  props:{
    commentInfo:{
      type:Object,
      default(){
        return {}
      }
    }
  },
  data(){
    return {
      loginUserId: this.$store.state.loginUserInfo.userId
    }
  },
  methods:{
    handleProfileClick(){
      this.$router.push({path:'/profile', query: {userId: this.commentInfo.userId}})
    },
    //删除方法，让父组件来做删除本条评论
    handleDeleteComment(){
      this.$emit('handleDeleteComment') //提示父组件
    },
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
</style>