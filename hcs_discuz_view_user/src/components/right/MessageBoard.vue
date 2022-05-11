<template>
  <div class="message-board">
    <div class="board-title" style="text-align: center"><h3>留言板</h3></div>
    <!-- <div class="message" style="line-height: 30px">
      <span style="color: cornflowerblue">牛牛</span>：<span>越来越好</span><br/>
      <span style="color: #aaa">2022-04-03</span>
      <el-divider/>
    </div>
    <div class="message" style="line-height: 30px">
      <span style="color: cornflowerblue">小明</span>：<span>内容质量很高，继续加油，越来越好。内容质量很高，继续加油，越来越好</span><br/>
      <span style="color: #aaa">2022-04-03</span>
      <el-divider/>
    </div> -->
    <!-- <div class="message" style="line-height: 30px">
      <span style="color: cornflowerblue">无敌小番茄</span>：<span>越来越好</span><br/>
      <span style="color: #aaa">2022-04-03</span>
      <el-divider/>
    </div> -->
    <div class="message" style="line-height: 30px" v-for="(item , key) in messageList" :key="key">
      <span style="color: cornflowerblue">{{item.user.username}}</span>：<span>{{item.content}}</span><br/>
      <span style="color: #aaa">{{formatTime(item.createTime)}}</span>
      <span v-if="item.adviseUserId===loginUserId" style="margin-left: 10px">
        <el-button type="text" @click="handleDelete(item.adviseId)" style="color: #999">删除</el-button>
      </span>
      <el-divider/>
    </div>
    <div class="board-title" style="text-align: center">
      <el-input
        v-model="messageContent"
        placeholder="给我留个言吧！"
      >
        <template #append>
          <el-button @click="handleSubmit">发表</el-button>
        </template>
      </el-input>
    </div>
  </div>
</template>

<script>
import { getMessageList, addMessage, deleteMessage} from '../../network/advise'
import {commonFormatTimeToStr} from '../../assets/js/date.js'
import { ElMessageBox } from 'element-plus'
export default {
  mounted(){
    this.toGetMessageList()
  },
  data(){
    return {
      messageList: [],
      messageContent: null,
      loginUserId: this.$store.state.loginUserInfo.userId
    }
  },
  methods:{
    toGetMessageList(){
      getMessageList().then(res=>{
        if(res.data.success===true){
          this.messageList = res.data.datas.messageList
        }else{
          alert(res.data.message)
        }
      })
    },
    handleSubmit(){
      if(this.messageContent === null){
        alert("内容不可为空");
        return
      }
      addMessage(this.messageContent).then(res=>{
        if(res.data.success===true){
          this.messageContent = null
          this.toGetMessageList()
        }else{
          alert(res.data.message)
        }
      })
    },
    //删除
    handleDelete(adviseId){
      ElMessageBox.confirm(
        '确认要删除这条评论吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
        deleteMessage(adviseId).then(res=>{
          if(res.data.success===true){
            this.toGetMessageList()
          }else{
            alert(res.data.message)
          }
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
</style>