<template>
  <div>
    <little-title content="用户留言板" />
    <el-timeline>
      <el-timeline-item v-for="(item , key) in messageList" :timestamp="formatTime(item.createTime)" :key="key" placement="top">
        <el-card>
          <h4>{{item.content}}</h4>
          <p>{{item.user.username}} 发布于 {{formatTime(item.createTime)}}</p>
          <el-button type="text" @click="handleDelete(item.adviseId)" style="color: #999">删除</el-button>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/3" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/3 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/2" placement="top">
        <el-card>
          <h4>Update Github template</h4>
          <p>Tom committed 2018/4/2 20:46</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import LittleTitle from '../../components/littletitle/LittleTitle.vue'
import { getMessageList, addMessage, deleteMessage} from '../../network/advise'
import {commonFormatTimeToStr} from '../../assets/js/date.js'
import { ElMessageBox } from 'element-plus'
export default {
  components:{
    LittleTitle
  },
  mounted(){
    this.toGetMessageList()
  },
  data(){
    return {
      messageList: [],
      messageContent: null,
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