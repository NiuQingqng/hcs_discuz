<template>
  <div class="comment-list">
    
    <el-card shadow="never" style="margin-bottom: 10px">
      <span v-if="totalCount>0">
        共 {{totalCount}} 条回复
      </span>
      <span v-if="totalCount===0">
        暂无回复
      </span>
    </el-card>
    <comment-list-item 
      v-infinite-scroll="loadData" 
      :infinite-scroll-disabled="busy"
      v-for="(value,key) in commentList"
      @handleThumbClick="handleThumbClick(value.commentId,key)"
      @afterAddLittleComment="afterAddLittleComment(key)"
      @handleDeleteComment="handleDeleteComment(value.commentId,key)"
      @afterDeleteLittleComment="afterDeleteLittleComment(key)"
      :commentInfo="value" :key="key"/>
  </div>
</template>

<script>
import CommentListItem from './CommentListItem.vue'
import {getCommentInfoByCondition, delComment} from '../../../network/comment'
import {addThumb} from '../../../network/thumb'
import { ElMessageBox } from 'element-plus'
export default {
  mounted() {
    this.toGetCommentList()
  },
  props:{
    topicId:{
      default(){
        return 0
      }
    }
  },
  data(){
    return {
      commentList: [],
      currentPage: 1,
      PageNum: 1,
      totalCount: 0,
      busy: false,
    }
  },
  components: { CommentListItem },
   methods: {
    toGetCommentList(){
      getCommentInfoByCondition({topicId: this.topicId},this.currentPage, 6).then(res=>{
        if(res.data.success===true){
          this.busy = false
          this.pageNum = res.data.datas.commentListWithPage.pages
          this.totalCount = res.data.datas.commentListWithPage.total
          const commentList = res.data.datas.commentListWithPage.list
          if (commentList.length > 0)
            //拼接
            this.commentList = this.commentList.concat(commentList)
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
        this.toGetCommentList()
      }
      
    },
    //点赞，子组件传递事件
    handleThumbClick(commentId,key){
      const thumb = {
        commentId : commentId
      }
      addThumb(thumb).then(res=>{
        if(res.data.success===true){
          this.commentList[key].thumbCount++
        }else{
          alert(res.data.message)
        }
      })
    },
    //添加评论之后之后页面数量更新
    afterAddLittleComment(key){
      this.commentList[key].commentCount++
    },
    //删除评论之后之后页面数量更新
    afterDeleteLittleComment(key){
      this.commentList[key].commentCount--
    },
    handleDeleteComment(commentId,index){
      ElMessageBox.confirm(
        '确认要删除这条评论吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
        delComment(commentId).then(res=>{
          if(res.data.success===true){
            // 删除userList里对应的数据
            this.commentList.splice(index,1)
            // 总回复条数减一
            this.totalCount = this.totalCount - 1
            //提示父组件更新回复数量
            this.$emit('afterDeleteComment')
          }else{
            alert(res.data.message)
          }
        })
      })
    }
  },
}
</script>

<style>

</style>