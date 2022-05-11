<template>
  <div class="userinfo">
    <little-title content="用户详细信息" />
    <el-descriptions
      class="margin-top"
      title="用户详细信息"
      label-align="center"
      align="center"
      :column="3"
      border
    >
      <template #extra>
        <el-button @click="handleEdit()">编辑</el-button>
        <el-button type="warning" @click="handleAble()">{{stutusOptStr}}</el-button>
        <el-button type="danger" @click="handleDelete()">删除</el-button>
      </template>
      <el-descriptions-item label="用户Id">{{userInfo.userId}}</el-descriptions-item>
      <el-descriptions-item label="用户名">{{userInfo.username}}</el-descriptions-item>
      <el-descriptions-item label="学号">{{userInfo.stuNo}}</el-descriptions-item>
      <el-descriptions-item label="性别">{{userInfo.sex}}</el-descriptions-item>
      <el-descriptions-item label="生日">{{birthdayTime}}</el-descriptions-item>
      <el-descriptions-item label="城市">{{userInfo.city}}</el-descriptions-item>
      <el-descriptions-item label="手机号">{{userInfo.phone}}</el-descriptions-item>
      <el-descriptions-item label="电子邮箱">{{userInfo.email}}</el-descriptions-item>
      <el-descriptions-item label="注册时间">{{registerTime}}</el-descriptions-item>
      <el-descriptions-item label="上次登录">{{lastLoginTime}}</el-descriptions-item>
      <el-descriptions-item label="状态">{{stutusStr}}</el-descriptions-item>
      <el-descriptions-item label="简介">{{userInfo.describe}}</el-descriptions-item>
      <el-descriptions-item label="内容">
        发表过的帖子数量：{{userInfo.topicCount}}
        | 发表过的评论数量：{{userInfo.commentCount}}
        | 共收到到过{{userInfo.thumbCount}}个赞
      </el-descriptions-item>
    </el-descriptions>
    <el-divider />
    <h3>最近内容</h3>
    <el-timeline class="timeline">
      <el-timeline-item v-for="(userTopic,key) in userTopicList" :timestamp="formatTime(userTopic.createTime)" placement="top" v-bind:key="key">
        <el-card shadow="never">
          <el-button class="button" style="color: black" type="text"  @click="this.$router.push({name:'/topicinfo',params: {topicId:userTopic.topicId}})">
            <h4>{{userTopic.title}}</h4>
          </el-button>
          <p>{{formatTime(userTopic.updateTime)}} 更新 | 
            浏览 {{userTopic.viewCount}} | 点赞 {{userTopic.thumbCount}} | 评论 {{userTopic.commentCount}}</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2018/4/2" placement="top">
        Event end
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import LittleTitle from '../../components/littletitle/LittleTitle.vue'
import {getUserInfo, delUser, ableUser} from '../../network/user'
import {formatTimeToStr} from '../../assets/js/date.js'
import { ElMessageBox } from 'element-plus'
import {getTopicInfoByCondition} from '../../network/topic'
export default {
  beforeCreate(){
    getUserInfo(this.$route.params.userId).then(res=>{
        if(res.data.success===true){
          this.userInfo = res.data.datas.userInfo
        }else{
          alert(res.data.message)
        }
    }),
  
    getTopicInfoByCondition({userId: this.$route.params.userId},1,3).then(res=>{
      if(res.data.success===true){
        this.userTopicList = res.data.datas.topicListWithPage.list
      }else{
        alert(res.data.message)
      }
    })
  },
  data(){
    return {
      userInfo:{},
      userTopicList: null
    }
  },
  methods: {
    //编辑方法
    handleEdit(){
      this.$router.push({name:'/useredit',params: {userId:this.userInfo.userId}}) // 只能用 name
    },
    handleAble(){
      ElMessageBox.confirm(
        '确认要冻结/解冻用户'+this.userInfo.username+'吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
          ableUser(this.userInfo.userId).then(res=>{
            if(res.data.success===true){
              //修改状态
              this.userInfo.data.status=(this.userInfo.status===1)?2:1
            }else{
              alert(res.data.message)
            }
          })
        })
    },
    //删除方法
    handleDelete(){
      ElMessageBox.confirm(
        '确认要删除用户'+this.userInfo.username+'吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
          delUser(this.userInfo.userId).then(res=>{
            if(res.data.success===true){
              // 删除userList里对应的数据
              this.$router.replace('/userlist') // 只能用 name
            }else{
              alert(res.data.message)
            }
          })
        })
    }
  },
  components:{
    LittleTitle
  },
  computed: {
    stutusStr(){
      switch (this.userInfo.status){
        case 1: return "正常";
        case 2: return "冻结";
        default: return "已删除";
      }
    },
    stutusOptStr(){
      switch (this.userInfo.status){
        case 1: return "冻结";
        case 2: return "解冻";
        default: return "冻结";
      }
    },
    birthdayTime(){
      const time = this.userInfo.birthday
      return (time!=null && time!="")?formatTimeToStr(time,"yyyy-MM-dd"):"";
    },
    registerTime(){
      const time = this.userInfo.registerTime
      return (time!=null && time!="")?formatTimeToStr(time):"";
    },
    lastLoginTime(){
      const time = this.userInfo.lastLoginTime
      return (time!=null && time!="")?formatTimeToStr(time):"";
    },
    //格式化时间监听，返回格式化时间函数
    formatTime(){
      return function(oldTime){
        const time = oldTime
        return (time!=null && time!="")?formatTimeToStr(time):"";
      }
      
    }
  }
}
</script>

<style scoped>
  .timeline{
    width: 1000px;
  }
</style>