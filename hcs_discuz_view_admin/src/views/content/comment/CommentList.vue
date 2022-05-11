<template>
  <div>
    <little-title content="评论管理" />
    <div>
      <el-select class="comment-category-select" 
        v-model="commentCategory" 
        placeholder="请选择分类">
        <el-option label="回复" value="1" />
        <el-option label="评论" value="2" />
      </el-select>
      <el-input
        v-model="searchContent"
        placeholder="Please input"
        class="input-with-select"
      >
        <template #prepend>
          <el-select v-model="searchSelect" placeholder="选择条件" style="width: 110px">
            <el-option label="Id" value="1" />
            <el-option label="所属用户" value="2" />
            <el-option label="所属帖子" value="3" />
            <el-option label="所属回复" value="4" />
            <el-option label="内容" value="5" />
          </el-select>
        </template>
        <template #append>
          <el-button @click="handleSearch"><el-icon><Search/></el-icon></el-button>
        </template>
      </el-input>
      <el-button
      size="big"
      @click="handleResetSearch"
      >重置搜索条件</el-button>
    </div>
    <el-divider />
    <el-alert title="回复是指对帖子的回复，评论是指对回复的评论。" style="margin-bottom: 20px" type="success" />
    <el-table
      :data="commentList"
      stripe
      style="width: 100%" 
      @row-click="handleClick"
      >
      <el-table-column prop="commentId" label="Id" />
      <el-table-column prop="user.username" label="所属用户" />
      <el-table-column prop="topicId" label="所属帖子Id" />
      <el-table-column prop="rootId" label="所属回复Id" />
      <el-table-column prop="content" width="680" label="内容" />
      <el-table-column
        prop="status"
        label="分类"
        >
        <template #default="scope">
          <el-tag
            :type="scope.row.rootId === null ? 'success' : 'fail'"
            disable-transitions>
            {{ scope.row.rootId === null ? '回复' : '评论' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="140">
        <template #default="scope">
          <el-button
            size="small"
            @click.stop="handleView(scope.row)"
            >查看</el-button>
          <el-button
            size="small"
            type="danger"
            @click.stop="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="demo-pagination-block">
      <el-pagination
        v-model:currentPage="currentPage"
        v-model:pageSize="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="total"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import LittleTitle from '../../../components/littletitle/LittleTitle.vue'
import {getCommentList, delComment, getCommentListByCondition} from '../../../network/comment'
import { ElMessageBox } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
export default {
  mounted() {
    this.toGetCommentList()
    
  },
  components: { LittleTitle, Search },
  data(){
    return {
      commentList: [],
      searchSelect: null,
      searchContent: null,
      isSearching: false,
      currentPage:1,
      pageSize: 10,
      total: 0,
      //评论的类别
      commentCategory: null
    }
  },
  methods: {
    //查看详细信息
    // handleClick(comment){
    //   this.$router.push({name:'/commentinfo',params: {commentId:comment.commentId}}) // 只能用 name
    // },
    //查看详细信息
    handleView(commentInfo){
      ElMessageBox.alert(
        '<span style="font-size: 20px">'+commentInfo.user.username+'：</span>'+"<br/><br/>"+commentInfo.content,
        '评论详情',
        {
          dangerouslyUseHTMLString: true,
        }
      )
    },
    //删除方法
    handleDelete(index, comment){
      ElMessageBox.confirm(
        '确认要删除这条评论吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
        delComment(comment.commentId).then(res=>{
          this.dialogVisible = true //？
          if(res.data.success===true){
            // 删除userList里对应的数据
            this.commentList.splice(index,1)
          }else{
            alert(res.data.message)
          }
        })
      })
    },
    toGetCommentList(){
      getCommentList(this.currentPage,this.pageSize).then(res=>{
        if(res.data.success===true){
          this.commentList = res.data.datas.commentListWithPage.list
          this.total = res.data.datas.commentListWithPage.total
        }else{
          alert(res.data.message)
        }
      })
    },
    handleResetSearch(){
      this.isSearching = false
      this.currentPage = 1
      this.searchSelect = null
      this.searchContent = null
      //分类筛选
      this.commentCategory = null
      this.toGetCommentList()
    },
    handleSearch(){
      this.currentPage = 1
      this.toSearch()
    },
    toSearch(){
      let commentInfo = {
        commentId: null,
        userId: null,
        topicId:null,
        rootId:null,
        content:null
      }
      if(this.searchSelect==="1") commentInfo.commentId=this.searchContent;
      if(this.searchSelect==="2") commentInfo.userId=this.searchContent;
      if(this.searchSelect==="3") commentInfo.topicId=this.searchContent;
      if(this.searchSelect==="4") commentInfo.rootId=this.searchContent;
      if(this.searchSelect==="5") commentInfo.content=this.searchContent;
      getCommentListByCondition(commentInfo,this.currentPage, this.pageSize).then(res=>{
        if(res.data.success===true){
          this.commentList = res.data.datas.commentListWithPage.list
          this.total =  res.data.datas.commentListWithPage.total
          this.isSearching = true
        }else{
          alert(res.data.message)
        }
      })
    },
    handleCurrentChange(){
      this.isSearching ? this.toSearch() : this.toGetCommentList()
    }
  },
  computed:{
    status(){
      return this.commentList
    }
  }
  
}
</script>

<style scoped>
  .demo-pagination-block {
    height: 66px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .input-with-select{
    width: 500px;
    padding-right: 30px;
  }
  .comment-category-select{
    width: 150px;
    padding-right: 15px;
  }
</style>