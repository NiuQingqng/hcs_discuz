<template>
  <div class="user-info-detail">
    <el-form :model="userInfo" label-width="100px">
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
        <el-input v-model="userInfo.username" disabled />
      </el-form-item>
      <el-form-item label="学号">
        <el-input v-model="userInfo.stuNo" />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="userInfo.sex" placeholder="请选择...">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker
          v-model="userInfo.birthday"
          type="date"
          placeholder="请选择..."
        />
      </el-form-item>
      <el-form-item label="城市">
        <el-input v-model="userInfo.city" />
      </el-form-item>
      <el-form-item label="真实姓名">
        <el-input v-model="userInfo.realname" />
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="userInfo.phone" />
      </el-form-item>
      <el-form-item label="电子邮件">
        <el-input v-model="userInfo.email" disabled />
      </el-form-item>
      <el-form-item label="简介">
        <el-input 
          autosize
          type="textarea"
          v-model="userInfo.describe" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {updateUser} from '../../../network/user'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import {uploadFile} from '../../../network/file'
export default {
  props:{
    userInfoProps:{
      default(){
        return null
      }
    }
  },
  data(){
    return {
      userInfo: this.userInfoProps
    }
  },
  methods:{
    onSubmit(){
      updateUser(this.userInfo).then(res=>{
        if(res.data.success===true){
          ElMessage.success('修改成功！')
          this.$router.replace("/profile?userId="+this.userInfo.userId)
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
        ElMessage.error('头像只能是JPG或PNG格式!')
      }
      if (!isLt2M) {
        ElMessage.error('头像不能超过2MB!')
      }
      return (isJPG || isPNG) && isLt2M
    }
  },
  components:{
    Plus
  }
}
</script>

<style>

</style>