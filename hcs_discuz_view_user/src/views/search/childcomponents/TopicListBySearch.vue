<template>
  <div class="topic-list-by-search">
    <p style="color: #666">共找到 <span style="color: red">{{totalCount}}</span> 条关于 "<span style="color: red">{{topicTitle}}</span>" 帖子</p>
    <el-divider/>
     <!-- v-infinite-scroll="loadData" -->
    <topic-list-item v-infinite-scroll="loadData" 
      :infinite-scroll-disabled="busy"
      v-for="(value,key) in topicList"
      @handleThumbClick="handleThumbClick(value.topicId,key)"
      :topicInfo="value" :key="key"/>
  </div>
</template>

<script>
import TopicListItem from '../../../components/common/TopicListItem.vue'
import {getTopicInfoByCondition} from '../../../network/topic'
import {addThumb} from '../../../network/thumb'
export default {
  mounted() {
    this.toGetTopicList()
  },
  props:{
    topicTitle:{
        default(){
          return null
        }
    }
  },
  data(){
    return {
      topicList: [],
      currentPage: 1,
      PageNum: 1,
      busy: false,
      totalCount: 0
    }
  },
  methods: {
    toGetTopicList(){
      getTopicInfoByCondition({title: this.topicTitle},this.currentPage, 6).then(res=>{
        if(res.data.success===true){
          this.busy = false
          this.pageNum = res.data.datas.topicListWithPage.pages
          this.totalCount = res.data.datas.topicListWithPage.total
          const topicList = res.data.datas.topicListWithPage.list
          if (topicList.length > 0)
            //拼接
            this.topicList = this.topicList.concat(topicList)
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
        this.toGetTopicList()
      }
    },
    //点赞，子组件传递事件
    handleThumbClick(topicId,key){
      const thumb = {
        topicId : topicId
      }
      addThumb(thumb).then(res=>{
        if(res.data.success===true){
          this.topicList[key].thumbCount++
        }else{
          alert(res.data.message)
        }
      })
    }
  },
  components: {
    TopicListItem
  }
}
</script>

<style scoped>
.topic-list{
  height: 100%;
}
</style>