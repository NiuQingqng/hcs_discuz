<template>
  <div id="main-content">
    <div class="topic-title" style="background-color: #fff">
      <span style="font-size: 24px"><b>{{topicInfo.title}}</b></span>
      <span><el-button type="text" class="topic-advise" @click="handleAdvise">举报</el-button></span>
    </div>
   
    <div class="user-info" style="padding-top: 15px">
      <div>
        <el-avatar shape="square" :size="50" :src="userInfo.avatar" @click="handleProfileClick" />
      </div>
      <div  style="padding-left: 10px;">
        <b style="font-size: 24px;">{{userInfo.username}}</b><br>
        <span style="font-size: 14px; color: #666">计算机科学与技术</span>
      </div>
    </div>
    <div class="topic-content" style="background-color: #fff; line-height: 26px">
      <p v-html="topicInfo.content">
      </p>
    </div>
    <div class="topic-other" style="margin-top: 10px">
      <p style="color: #999">编辑于：{{formatTime(topicInfo.updateTime)}}</p>
      <p v-if="userInfo.userId===loginUserId">
        <el-button type="text" @click="handleEdit">编辑</el-button>
        <el-button type="text" @click="handleDelete">删除</el-button>
      </p>
      <el-row>
        <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
          <span><el-button color="#626aef" plain @click="handleThumbClick">点赞 {{topicInfo.thumbCount}}</el-button></span>
          <span style="margin: 10px"><el-button style="color: #626aef" @click="handleCommentClick">回复</el-button></span>
          <span class="topic-other-comment">{{topicInfo.commentCount}} 条回复</span>
        </el-col>
        <el-col :xs="0" :sm="12" :md="12" :lg="12" :xl="12">
          <div style="text-align: right">
            <span style="color: #999"> {{formatTime(topicInfo.createTime)}} 发布 </span>
            <span style="color: #999"> {{topicInfo.viewCount}} 浏览</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import {commonFormatTimeToStr} from '../../../assets/js/date.js'
import {delTopic} from '../../../network/topic'
import {addAdvise} from '../../../network/advise'
import { ElMessageBox,ElMessage } from 'element-plus'
export default {
  props:{
    topicInfo:{
      type:Object,
      default(){
        return {}
      }
    },
    userInfo:{
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
  methods: {
    handleThumbClick(){
      this.$emit('handleThumbClick')
    },
    handleCommentClick(){
      this.$emit('handleCommentClick')
    },
    handleProfileClick(){
      this.$router.push({path:'/profile', query: {userId: this.userInfo.userId}})
    },
    handleEdit(){
      //todo 传数据
      this.$router.push({name:'/writetopic',params: {updateTopicId:this.topicInfo.topicId}}) // 只能用 name
    },
    //删除方法
    handleDelete(){
      ElMessageBox.confirm(
        '确认要删除这条帖子吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
          delTopic(this.topicInfo.topicId).then(res=>{
            this.dialogVisible = true
            if(res.data.success===true){
              ElMessage({
                message: '已成功删除！',
                type: 'success',
              })
              this.$router.go(-1)
            }else{
              alert(res.data.message)
            }
          })
        })
    },
    handleAdvise(){
      ElMessageBox.prompt('请输入举报原因', '举报', {
        confirmButtonText: '提交',
        cancelButtonText: '取消',
      })
        .then(({ value }) => {
          if(value === null || value === "") {
            ElMessage.error('举报原因不能为空！')
            return
          }
          let advise = {
            describe: value,
            topicId: this.topicInfo.topicId
          }
          addAdvise(advise).then(res => {
            if(res.data.success===true){
              ElMessage.success('举报信息已发送！')
            }else{
              ElMessage.error('举报失败！')
            }
          })
        })
        .catch(() => {
          ElMessage({
            type: 'info',
            message: '取消举报',
          })
        })
      
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

<style>
/* 
在此vue文件中去掉了scoped

原因在于
<img src="http://bztchina01.oss-cn-hangzhou.aliyuncs.com/public/uploads/1616642885524089.jpg"
是从后端动态获取到的内容，所以在VUE中html标签不会生成data-v-xxx属性（vue文件中css时使用scoped标记产生的，因为要保证各文件中的css不相互影响，给每个component都做了唯一的标记，所以每引入一个component就会出现一个新的'data-v-xxx'标记）
所以我们应该将<style lang="scss" scoped>中的scoped去掉
如此html标签中的data-v-xxx就不会生成了，max-width: 100%;也将图片自动缩放，起到了他的作用。
地址 https://www.likecs.com/show-306232664.html
*/
#main-content .user-info div{
  display: inline-block;
  vertical-align:top
}
#main-content .topic-content{
  vertical-align: middle;
}
#main-content .topic-advise{
  margin-left: 10px;
  color: #aaa;
}
#main-content .topic-advise:hover{
  margin-left: 10px;
  color: #888;
}
/* 
  此处不做唯一id限制，会影响到别的地方，但正合适，帖子列表不用去设置了
 */
.topic-content img{
  max-width: 100%;
  max-height: 100%;
  display: block;
  margin: auto;
}
</style>