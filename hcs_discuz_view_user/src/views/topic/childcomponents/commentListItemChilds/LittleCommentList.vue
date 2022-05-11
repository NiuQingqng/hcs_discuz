<template>
  <div class="little-comment-list">
    <b v-if="totalCount>0">共 {{totalCount}} 条评论</b>
    <span style="color: #666" v-if="totalCount===0">
      暂无评论
    </span>
    <el-divider/>
    <little-comment-list-item 
      v-for="(value,key) in commentList"
      @handleThumbClick="handleThumbClick(value.commentId,key)"
      @handleCommentClick="this.$emit('handleLittleCommentClick',value.user.username)"
      @handleDeleteComment="handleDeleteComment(value.commentId,key)"
      :commentInfo="value" :key="key"/>
    <span style="font-size: 14px; color: #999">加载更多...</span>
  </div>
</template>

<script>
import LittleCommentListItem from './LittleCommentListItem.vue'
import {getCommentInfoByCondition, delComment} from '../../../../network/comment'
import {addThumb} from '../../../../network/thumb'
import { ElMessageBox } from 'element-plus'
export default {
  mounted() {
    this.toGetCommentList()
  },
  props:{
    rootId:{
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
      totalCount: 0
    }
  },
  components: { LittleCommentListItem },
   methods: {
    toGetCommentList(){
      console.log(this.rootId);
      getCommentInfoByCondition({rootId: this.rootId},this.currentPage, 6).then(res=>{
        if(res.data.success===true){
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
    //删除，子组件传递事件
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
            //提示父组件更新评论数量
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