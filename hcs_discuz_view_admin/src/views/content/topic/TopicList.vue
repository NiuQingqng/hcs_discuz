<template>
  <div>
    <little-title content="帖子管理" />
    <div>
      <el-input
        v-model="searchContent"
        placeholder="Please input"
        class="input-with-select"
      >
        <template #prepend>
          <el-select v-model="searchSelect" placeholder="选择条件" style="width: 110px">
            <el-option label="Id" value="1" />
            <el-option label="所属用户" value="2" />
            <el-option label="标题" value="3" />
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
      <el-button
      size="big"
      @click="handleEdit(null)"
      >写一篇帖子</el-button>
    </div>
    <el-divider />
    <el-table
      :data="topicList"
      stripe
      style="width: 100%" 
      @row-click="handleClick"
      >
      <el-table-column width="180" prop="topicId" label="Id" />
      <el-table-column width="180" prop="userId" label="所属用户Id" />
      <el-table-column width="180" prop="categoryId" label="所属分类Id" />
      <el-table-column prop="title" label="标题" />
      <el-table-column width="180" prop="status" label="状态" >
        <template #default="scope">
          <el-tag
            :type="scope.row.status === 1 ? 'success' : 'fail'"
            disable-transitions>
            {{ scope.row.status === 1 ? '正常' : '已删除' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="140">
        <template #default="scope">
          <el-button
            size="small"
            @click.stop="handleEdit(scope.row.topicId)"
            >编辑</el-button>
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
import {getTopicList, delTopic, getTopicInfoByCondition} from '../../../network/topic'
import { ElMessageBox } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
export default {
  mounted() {
    this.toGetTopicList()
  },
  components: { LittleTitle, Search },
  data(){
    return {
      topicList: [],
      searchSelect: null,
      searchContent: null,
      isSearching: false,
      currentPage:1,
      pageSize: 10,
      total: 0
    }
  },
  methods: {
    //查看详细信息
    handleClick(topic){
      this.$router.push({name:'/topicinfo',params: {topicId:topic.topicId}}) // 只能用 name
    },
    //编辑方法
    handleEdit(topicId){
      this.$router.push({name:'/writetopic',params: {topicId:topicId}}) // 只能用 name
    },
    //删除方法
    handleDelete(index, topic){
      ElMessageBox.confirm(
        '确认要删除这条帖子吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
          delTopic(topic.topicId).then(res=>{
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
    handleResetSearch(){
      this.isSearching = false
      this.currentPage = 1
      this.searchSelect = null
      this.searchContent = null
      this.toGetTopicList()
    },
    handleSearch(){
      this.currentPage = 1
      this.toSearch()
    },
    toSearch(){
      let topicInfo = {
        topicId: null,
        userId: null,
        title:null,
      }
      if(this.searchSelect==="1") topicInfo.topicId=this.searchContent;
      if(this.searchSelect==="2") topicInfo.userId=this.searchContent;
      if(this.searchSelect==="3") topicInfo.title=this.searchContent;
      getTopicInfoByCondition(topicInfo,this.currentPage, this.pageSize).then(res=>{
        if(res.data.success===true){
          this.topicList = res.data.datas.topicListWithPage.list
          this.total =  res.data.datas.topicListWithPage.total
          this.isSearching = true
        }else{
          alert(res.data.message)
        }
      })
    },
    toGetTopicList(){
      getTopicList(this.currentPage, this.pageSize).then(res=>{
        if(res.data.success===true){
          this.topicList = res.data.datas.topicListWithPage.list
          this.total = res.data.datas.topicListWithPage.total
          this.isSearching = false
        }else{
          alert(res.data.message)
        }
      })
    },
    handleCurrentChange(){
      this.isSearching ? this.toSearch() : this.toGetTopicList()
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
</style>