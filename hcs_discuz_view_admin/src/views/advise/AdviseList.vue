<template>
  <div class="advise-list">
    <little-title content="举报信息管理" />
    <div>
      <el-select class="advise-category-select" 
        v-model="adviseCategory" 
        @change="adviseSelect"
        placeholder="请选择分类">
        <el-option label="全部" value="0" />
        <el-option label="未处理" value="1" />
        <el-option label="已处理" value="2" />
      </el-select>
      <el-divider/>
    </div>
    <div>
      <el-table
        :data="adviseList"
        stripe
        style="width: 100%" 
        >
        <el-table-column prop="adviseId" label="Id" />
        <el-table-column prop="describe" label="描述" />
        <el-table-column prop="topicId" label="被举报帖子" />
        <el-table-column prop="adviseUserId" label="发起用户" />
        <el-table-column
          prop="result"
          label="处理结果"
          >
          <template #default="scope">
            <el-tag
              :type="scope.row.result === 2 ? 'success' : 'fail'"
              disable-transitions>
              {{ scope.row.result === 2 ? '已处理' : '未处理' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="adminId"
          label="处理人"
          >
          <template #default="scope">
            {{ scope.row.adminId != null ? scope.row.adminId : '暂无处理人' }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="140">
          <template #default="scope">
            <el-button
              size="small"
              @click.stop="handleView(scope.row)"
              >详情</el-button>
            <el-button
              size="small"
              type="danger"
              @click.stop="handleDelete(scope.row)"
              >处理</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import LittleTitle from '../../components/littletitle/LittleTitle.vue'
import {getAdviseList, handleAdvise, getAdviseListByCondition} from '../../network/advise'
import { ElMessageBox, ElMessage } from 'element-plus'
import {commonFormatTimeToStr} from '../../assets/js/date.js'
export default {
  mounted() {
    this.toGetAdviseList();
  },
  components:{LittleTitle},
  data(){
    return {
      adviseList: [],
      adviseCategory: null,
    }
  },
  methods:{
    toGetAdviseList(){
      getAdviseList().then(res=>{
        if(res.data.success===true){
          this.adviseList = res.data.datas.adviseList
        }else{
          alert(res.data.message)
        }
      })
    },
    //查看详细信息
    handleView(adviseInfo){
      ElMessageBox.alert(
        'Id：'+adviseInfo.adviseId
        +'<br/>发起时间：'+ adviseInfo.createTime
        +'<br/>处理时间：'+ (adviseInfo.handleTime === null ? '暂未处理' : adviseInfo.handleTime)
        ,
        '举报信息详情',
        {
          dangerouslyUseHTMLString: true,
        }
      )
    },
    //删除方法
    handleDelete(advise){
      ElMessageBox.confirm(
        '确认已经处理这条举报信息了吗？',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
        handleAdvise(advise.adviseId).then(res=>{
          this.dialogVisible = true //？
          if(res.data.success===true){
            // 删除userList里对应的数据
            this.toGetAdviseList()
            ElMessage.success(res.data.message)
          }else{
            alert(res.data.message)
          }
        })
      })
    },
    adviseSelect(){
      if (this.adviseCategory === '0'){
          this.toGetAdviseList()
          return
      }
      getAdviseListByCondition(this.adviseCategory).then(res=>{
        if(res.data.success===true){
          this.adviseList = res.data.datas.adviseList
        }else{
          alert(res.data.message)
        }
      })
    }
  },
  computed:{
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
  .demo-pagination-block {
    height: 66px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>