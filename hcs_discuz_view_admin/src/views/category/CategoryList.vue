<template>
  <div id="category-list">
    <little-title content="分类管理" />
    <el-button @click="handleAdd()">添加分类信息</el-button>
    <el-divider/>
    <el-table
      :data="categoryList"
      stripe
      style="width: 100%" 
      >
      <el-table-column prop="categoryId" label="Id" />
      <el-table-column prop="categoryName" label="分类名" />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="order" label="排序" />
      <el-table-column prop="topicCount" label="帖子数量" />
      <el-table-column prop="status" label="状态" />
      <el-table-column label="操作" width="140">
        <template #default="scope">
          <el-button
            size="small"
            @click.stop="handleEdit(scope.$index)"
            >编辑</el-button>
          <el-button
            size="small"
            type="danger"
            @click.stop="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-dialog v-model="dialogFormVisible" title="添加/修改信息">
    <el-form>
      <el-form-item label="分类名" :label-width="formLabelWidth">
        <el-input v-model="categoryInfo.categoryName" />
      </el-form-item>
      <el-form-item label="描述" :label-width="formLabelWidth">
        <el-input v-model="categoryInfo.description" />
      </el-form-item>
      <el-form-item label="排序" :label-width="formLabelWidth">
        <el-input-number v-model="categoryInfo.order" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleSubmit">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import LittleTitle from '../../components/littletitle/LittleTitle.vue'
import {getCategoryList, delCategory, addCategory, updateCategory} from '../../network/category'
import { ElMessageBox, ElMessage } from 'element-plus'
export default {
  mounted() {
    this.toGetCategoryList();
  },
  components: { LittleTitle },
  data(){
    return {
      categoryList: [],
      dialogFormVisible: false,
      categoryInfo: {
        categoryId:null,
        categoryName: null,
        description: null,
        order: null
      },
      optionId: null
    }
  },
  methods: {
    toGetCategoryList(){
      getCategoryList().then(res=>{
        if(res.data.success===true){
          this.categoryList = res.data.datas.categoryList
        }else{
          alert(res.data.message)
        }
      })
    },
    //编辑方法
    handleAdd(){
      this.optionId = null
      this.dialogFormVisible = true
      this.categoryInfo = {}
    },
    //编辑方法
    handleEdit(index){
      this.optionId = this.categoryList[index].categoryId
      this.dialogFormVisible = true
      this.categoryInfo = this.categoryList[index]
    },
    handleSubmit(){
      if(this.categoryInfo.categoryName === null){
        ElMessageBox.alert("分类名不能为空！")
        return
      }
      if(this.categoryInfo.order === null){
        ElMessageBox.alert("排序不能为空！")
        return
      }
      if(this.categoryInfo.categoryName.length < 2){
        ElMessageBox.alert("分类名最少两个字符！")
        return
      }
      if(this.optionId){
        //修改方法
        updateCategory(this.categoryInfo).then(res => {
          if(res.data.success===true){
            ElMessage.success(res.data.message)
            //异步网络请求，如果写在外面，可能在修改之前就请求数据了。
            this.toGetCategoryList();
          }else{
            ElMessage.error(res.data.message)
          }
        })
      }else{
        //添加
        addCategory(this.categoryInfo).then(res => {
          if(res.data.success===true){
            ElMessage.success(res.data.message)
            this.toGetCategoryList();
          }else{
            ElMessage.error(res.data.message)
          }
        })
      }
      this.dialogFormVisible = false
    },
    handleCancel(){
      this.toGetCategoryList();
      this.dialogFormVisible = false
    },
    //删除方法
    handleDelete(index, category){
      ElMessageBox.confirm(
        '确认要删除此分类吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
          delCategory(category.categoryId).then(res=>{
            this.dialogVisible = true
            if(res.data.success===true){
              // 删除List里对应的数据
              this.categoryList.splice(index,1)
            }else{
              alert(res.data.message)
            }
          })
        })
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
</style>