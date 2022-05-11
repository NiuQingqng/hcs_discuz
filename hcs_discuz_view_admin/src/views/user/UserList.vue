<template>
  <div id="user-list">
    <little-title content="用户管理" />
    <div>
      <el-input
        v-model="searchContent"
        placeholder="Please input"
        class="input-with-select"
      >
        <template #prepend>
          <el-select v-model="searchSelect" placeholder="选择条件" style="width: 110px">
            <el-option label="Id" value="1" />
            <el-option label="用户名" value="2" />
            <el-option label="学号" value="3" />
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
      @click="this.$router.push({name:'/useradd'})"
      >添加用户</el-button>
    </div>
    <el-divider />
    <el-table
      :data="userList"
      stripe
      style="width: 100%" 
      @row-click="handleClick"
      >
      <el-table-column prop="userId" label="Id" />
      <el-table-column prop="username" label="用户名" />
      <el-table-column width="200" prop="stuNo" label="学号" />
      <el-table-column width="100" prop="sex" label="性别" />
      <el-table-column width="180" prop="realname" label="真实姓名" />
      <el-table-column prop="phone" label="手机号" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column label="操作" width="140">
        <template #default="scope">
          <el-button
            size="small"
            @click.stop="handleEdit(scope.row.userId)"
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
import LittleTitle from '../../components/littletitle/LittleTitle.vue'
import {getUserList, delUser, getUserInfoByCondition} from '../../network/user'
import { ElMessageBox } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
export default {
  mounted() {
    this.toGetUserList()
  },
  components: { 
    LittleTitle,
    Search
  },
  data(){
    return {
      userList: [],
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
    handleClick(user){
      this.$router.push({name:'/userinfo',params: {userId:user.userId}}) // 只能用 name
    },
    //编辑方法
    handleEdit(userId){
      this.$router.push({name:'/useredit',params: {userId:userId}}) // 只能用 name
    },
    //删除方法
    handleDelete(index, user){
      ElMessageBox.confirm(
        '确认要删除用户'+user.username+'吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
          delUser(user.userId).then(res=>{
            this.dialogVisible = true
            if(res.data.success===true){
              // 删除userList里对应的数据
              this.userList.splice(index,1)
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
      this.toGetUserList()
    },
    handleSearch(){
      this.currentPage = 1
      this.toSearch()
    },
    toSearch(){
      let userInfo = {
        userId: null,
        username: null,
        stuNo:null,
      }
      if(this.searchSelect==="1") userInfo.userId=this.searchContent;
      if(this.searchSelect==="2") userInfo.username=this.searchContent;
      if(this.searchSelect==="3") userInfo.stuNo=this.searchContent;
      getUserInfoByCondition(userInfo,this.currentPage,this.pageSize).then(res=>{
        if(res.data.success===true){
          this.userList = res.data.datas.userListWithPage.list
          this.total =  res.data.datas.userListWithPage.total
          this.isSearching = true
        }else{
          alert(res.data.message)
        }
      })
    },
    toGetUserList(){
      getUserList(this.currentPage,this.pageSize).then(res=>{
        if(res.data.success===true){
          this.userList = res.data.datas.userListWithPage.list
          this.total =  res.data.datas.userListWithPage.total
          this.isSearching = false
        }else{
          alert(res.data.message)
        }
      })
    },
    handleCurrentChange(){
      this.isSearching ? this.toSearch() : this.toGetUserList()
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