<template>
  <div class="message-list">
    <div class="message-list-item" v-for="(notificationInfo,key) in notificationList" :key="key">
      <div class="is-new"  v-if="notificationInfo.status===1"></div>
      <span style="color: #999">{{formatTime(notificationInfo.createTime)}}</span><br/>
      <el-button type="text" @click="handleUserClick(notificationInfo.user.userId)">{{notificationInfo.user.username}}</el-button>
      <span @click="handleMessageClick(notificationInfo.topic.topicId)">  
        <span style="margin: 5px">{{notificationInfo.subject}}</span>
        <span type="text">{{notificationInfo.topic.title}}</span>
      </span>
    </div>
    
  </div>
</template>

<script>
import {commonFormatTimeToStr} from '../../../assets/js/date.js'
import {getNotificationInfoByCondition} from '../../../network/notification'
export default {
  props:{
    userId:{
      default(){
        return 1
      }
    }
  },
  mounted(){
    this.toGetNotificationList()
  },
  data(){
    return {
      notificationList: [],
      currentPage: 1,
      PageNum: 1,
      busy: false,
    }
  },
  methods: {
    toGetNotificationList(){
      getNotificationInfoByCondition({toUserId: this.userId},this.currentPage, 6).then(res=>{
        if(res.data.success===true){
          this.busy = false
          this.pageNum = res.data.datas.notificationListWithPage.pages
          const notificationList = res.data.datas.notificationListWithPage.list
          if (notificationList.length > 0)
            //拼接
            this.notificationList = this.notificationList.concat(notificationList)
        }else{
          alert(res.data.message)
        }
      })
    },
    loadData(){
      this.busy = true
      console.log("加载数据");
      if(this.currentPage < this.pageNum){
        this.currentPage++
        this.toGetNotificationList()
      }
    },
    handleMessageClick(topicId){
      this.$router.push({name:'/topicdetail',query:{topicId: topicId}})
    }
    //点赞，子组件传递事件
    // handleThumbClick(topicId,key){
    //   const thumb = {
    //     topicId : topicId
    //   }
    //   addThumb(thumb).then(res=>{
    //     if(res.data.success===true){
    //       this.topicList[key].thumbCount++
    //     }else{
    //       alert(res.data.message)
    //     }
    //   })
    // }
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
.message-list-item{
  padding: 10px;
  border-radius: 3px;
  margin-bottom: 5px;
}
.message-list-item :hover{
  color: #999
}
.is-new{
  height: 6px;
  width: 6px;
  border-radius: 3px;
  background-color: #f00;
}
</style>