<template>
  <div class="topicinfo">
    <little-title content="帖子详细信息" />
    <div>
      <el-row :gutter="0">
        <el-col :span="17">
          <div class="maincontent">
            <h1 class="maincontent-title">{{topicInfo.title}}</h1>
            <p style="color: #888">{{formatTime(topicInfo.updateTime)}} 更新 | {{topicInfo.thumbCount}} 人赞了该帖子</p>
            <div v-html="topicInfo.content"></div>
            <p style="text-align: right; color: #888">
              发布于: {{formatTime(topicInfo.createTime)}} 
            </p>
            <p style="text-align: right; color: #888">
              <el-button round >赞 {{topicInfo.thumbCount}}</el-button>
              <el-button round >评论 {{topicInfo.commentCount}}</el-button>
            </p>
            <el-divider />
          </div>
          <div class="commentlist">
            <h4>{{topicInfo.commentCount}}条回复</h4>
            
          </div>
          <el-divider />
          <h3>相关内容</h3>
          <el-timeline>
            <el-timeline-item v-for="(similarTopic,key) in similarTopicList" :timestamp="formatTime(similarTopic.createTime)" placement="top" v-bind:key="key">
              <el-card  shadow="never">
                <el-button class="button" style="color: black" type="text"  @click="onloadData(similarTopic.topicId)">
                  <h4>{{similarTopic.title}}</h4>
                </el-button>
                <p>{{formatTime(similarTopic.updateTime)}} 更新 | 
                  浏览 {{similarTopic.viewCount}} | 点赞 {{similarTopic.thumbCount}} | 评论 {{similarTopic.commentCount}}</p>
              </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2018/4/2" placement="top">
              Event end
            </el-timeline-item>
          </el-timeline>
        </el-col>
        <el-col :span="7">
          <el-card class="box-card" shadow="hover">
            <template #header>
              <div class="card-header">
                <b>所属用户信息</b>
                <el-button class="button" type="text"
                 @click="this.$router.push({name:'/useredit',params: {userId:this.userInfo.userId}})">去编辑</el-button>
              </div>
            </template>

            <div class="userinfo">
              <div class="uavatar">
                <el-avatar class="avatar" shape="square" :size="200"  :src="userInfo.avatar" />
              </div>
              <div class="uinfo">
                <span>用户名：{{userInfo.username}} | </span>
                <span>性别：{{userInfo.sex}}</span><br>
                <span>生日：{{birthdayTime}}</span>
              </div>
            </div>
            <el-divider/>
            <div>
              <h3>该用户的其他帖子</h3>
              <ul>
                <li v-for="(userTopic,key) in userTopicList" v-bind:key="key">
                  <el-button class="button" type="text"  @click="onloadData(userTopic.topicId)">
                    {{userTopic.title}}  {{userTopic.viewCount}}人浏览
                  </el-button>
                </li>
              </ul>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    
  </div>
</template>

<script>
import LittleTitle from '../../../components/littletitle/LittleTitle.vue'
import {getUserInfo} from '../../../network/user'
import {getTopicInfo,getTopicList,getTopicInfoByCondition} from '../../../network/topic'
import {formatTimeToStr} from '../../../assets/js/date.js'
// import { ElMessageBox } from 'element-plus'
export default {
  mounted(){
    const topicId = this.$route.params.topicId
    this.onloadData(topicId)
  },
  data(){
    return {
      topicInfo:{},
      userInfo:{},
      similarTopicList:{},
      userTopicList:{}
    }
  },
  methods: {
    onloadData(topicId){
      getTopicInfo(topicId).then(res=>{
        if(res.data.success===true){
          this.topicInfo = res.data.datas.topicInfo
          //获取用户信息
          getUserInfo(this.topicInfo.userId).then(res=>{
            if(res.data.success===true){
              this.userInfo = res.data.datas.userInfo
            }else{
              alert(res.data.message)
            }
          }),
          getTopicInfoByCondition({userId: this.topicInfo.userId},1,3).then(res=>{
            if(res.data.success===true){
              this.userTopicList = res.data.datas.topicListWithPage.list
            }else{
              alert(res.data.message)
            }
          })
        }else{
          alert(res.data.message)
        }
      }),
      getTopicList(2, 3).then(res=>{
        if(res.data.success===true){
          this.similarTopicList = res.data.datas.topicListWithPage.list
        }else{
          alert(res.data.message)
        }
      })
    }
  },
//     //删除方法
//     handleDelete(){
//       ElMessageBox.confirm(
//         '确认要删除用户'+this.userInfo.username+'吗？',
//         {
//           confirmButtonText: '确认',
//           cancelButtonText: '取消',
//           type: 'warning',
//         }
//       ).then(() => {
//           delUser(this.userInfo.userId).then(res=>{
//             if(res.data.success===true){
//               // 删除userList里对应的数据
//               this.$router.replace('/userlist') // 只能用 name
//             }else{
//               alert(res.data.message)
//             }
//           })
//         })
//     }
//   },
  components:{
    LittleTitle,
  },
  computed: {
    birthdayTime(){
      const time = this.userInfo.birthday
      return (time!=null && time!="")?formatTimeToStr(time,"yyyy-MM-dd"):"";
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
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 380px;
  margin: 20px;
}

.uavatar{
  text-align: center;
  padding: 15px;
}
.uinfo{
  padding: 15px;
}
.maincontent{
  padding-left: 10%;
  padding-right: 10%;
}
.commentlist{
  padding-left: 10%;
  padding-right: 10%;
}
</style>