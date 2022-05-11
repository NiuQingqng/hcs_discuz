<template>
  <div class="userlist">
    <little-title content="新增用户" />
    
    <el-form :model="userInfo" label-width="120px">
      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader"
          action="#"
          :http-request="uploadAction"
          :show-file-list="false"
          :before-upload="beforeAvatarUpload"
          >
          <el-avatar  v-if="userInfo.avatar" class="avatar"
           shape="square" :size="100" :fit="fill" :src="userInfo.avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="userInfo.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="userInfo.password" />
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="userInfo.phone" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">新增</el-button>
        <el-button @click="this.$router.go(-1)">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import LittleTitle from '../../components/littletitle/LittleTitle.vue'
import {addUser} from '../../network/user'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import {uploadFile} from '../../network/file'
export default {
  data(){
    return {
      userInfo:{
        avatar: null,
        username: null,
        password: null,
        phone: null
      }
    }
  },
  methods:{
    onSubmit(){
      addUser(this.userInfo).then(res=>{
        if(res.data.success===true){
          alert(res.data.message)
          this.$router.go(-1)
        }else{
          alert(res.data.message)
        }
      })
    },
    uploadAction(param){
      var form = new FormData();
      form.append("file", param.file);
      uploadFile(form).then(res=>{
        console.log(res);
        if(res.data.success===true){
          alert(res.data.message)
          this.userInfo.avatar = res.data.datas.filePath
        }else{
          alert(res.data.message)
        }
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isPNG = file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG && !isPNG) {
        ElMessage.error('Avatar picture must be JPG or PNG format!')
      }
      if (!isLt2M) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
      }
      return (isJPG || isPNG) && isLt2M
    }
  },
  components:{
    LittleTitle,
    Plus
  },
}
</script>

<style>

</style>