<template>
  <div class="user-topic-list">
    <div v-for="(topicInfo,key) in topicList" :key="key">
      <div class="topic-title">
        <router-link :to="{path:'/topicdetail',query:{topicId: topicInfo.topicId}}">
          <span style="font-size: 18px"><b>{{topicInfo.title}}</b></span>
        </router-link>
      </div>
      <div class="topic-content">
        <router-link :to="{path:'/topicdetail',query:{topicId: topicInfo.topicId}}">
          <p v-html="topicInfo.content">
          </p>
        </router-link>
      </div>
      <div class="topic-datas">
        <span>{{formatTime(topicInfo.createTime)}} ·</span>
        <span>{{topicInfo.viewCount}} 浏览 ·</span>
        <span>{{topicInfo.thumbCount}} 点赞 ·</span>
        <span>{{topicInfo.commentCount}} 评论</span>
      </div>
      <div class="topic-option" v-if="isSelf">
        <el-button type="text">修改</el-button>
        <el-button type="text" @click="handleDelete(key, topicInfo.topicId)">删除</el-button>
      </div>
      <el-divider/>
      <!-- <div style="height: 1px; background-color:#ddd"></div> -->
    </div>
  </div>
</template>

<script>
import {getTopicInfoByCondition, delTopic} from '../../../network/topic'
import {commonFormatTimeToStr} from '../../../assets/js/date.js'
import { ElMessageBox } from 'element-plus'
export default {
  props: {
    userId:{
      default(){
        return 1
      }
    },
    isSelf: {
      default(){
        return false
      }
    }
  },
  mounted(){
    this.toGetTopicList()
  },
  data(){
    return {
      topicList: [],
      currentPage: 1,
      PageNum: 1,
    }
  },
  methods: {
    toGetTopicList(){
      getTopicInfoByCondition({userId: this.userId},this.currentPage, 6).then(res=>{
        if(res.data.success===true){
          this.pageNum = res.data.datas.topicListWithPage.pages
          const topicList = res.data.datas.topicListWithPage.list
          if (topicList.length > 0)
            //拼接
            this.topicList = this.topicList.concat(topicList)
        }else{
          alert(res.data.message)
        }
      })
    },
    //删除方法
    handleDelete(index, topicId){
      ElMessageBox.confirm(
        '确认要删除这条帖子吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
          delTopic(topicId).then(res=>{
            this.dialogVisible = true
            if(res.data.success===true){
              // 删除userList里对应的数据
              this.topicList.splice(index,1)
            }else{
              alert(res.data.message)
            }
          })
        })
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
.topic-content{
  line-height: 25px;
  overflow:hidden; 
  text-overflow:ellipsis;
  display:-webkit-box; 
  -webkit-box-orient:vertical;
  -webkit-line-clamp:2;
}
.topic-datas{
  margin-top: 20px;
  color: #666
}
.topic-datas span{
  margin-right: 10px;
}
a{
  text-decoration:none ; 
  color: #000;
}
a:hover{
  color: #666;
}
</style>