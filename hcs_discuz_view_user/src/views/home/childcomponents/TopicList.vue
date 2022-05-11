<template>
  <div class="topic-list">
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
import {getTopicListByHot, getTopicList} from '../../../network/topic'
import {addThumb} from '../../../network/thumb'
export default {
  props:{
    isHot:{
      type:Boolean,
      default(){
        return true
      }
    },
  },
  mounted() {
    this.toGetTopicList()
  },
  data(){
    return {
      topicList: [],
      currentPage: 1,
      PageNum: 1,
      busy: false,
    }
  },
  methods: {
    toGetTopicList(){
      if (this.isHot){
        getTopicListByHot(this.currentPage, 6).then(res=>{
          if(res.data.success===true){
            this.busy = false
            this.pageNum = res.data.datas.topicListWithPage.pages
            const topicList = res.data.datas.topicListWithPage.list
            if (topicList.length > 0)
              //拼接
              this.topicList = this.topicList.concat(topicList)
          }else{
            alert(res.data.message)
          }
        })
      } else{
        getTopicList(this.currentPage, 6).then(res=>{
          if(res.data.success===true){
            this.busy = false
            this.pageNum = res.data.datas.topicListWithPage.pages
            const topicList = res.data.datas.topicListWithPage.list
            if (topicList.length > 0)
              //拼接
              this.topicList = this.topicList.concat(topicList)
          }else{
            alert(res.data.message)
          }
        })
      }
      
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