<template>
  <div>
    网站数据
    <div>
      <el-upload
        class="avatar-uploader"
        action="#"
        :http-request="uploadAction"
        :show-file-list="false"
        :before-upload="beforeAvatarUpload"
        >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </div>
  </div>
</template>

<script>
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import {uploadFile} from '../../network/file'
export default {
  data(){
    return {
      imageUrl:null
    }
  },
  methods:{
    uploadAction(param){
      var form = new FormData();
      form.append("file", param.file);
      uploadFile(form).then(res=>{
        console.log(res);
        if(res.data.success===true){
          alert(res.data.message)
          this.imageUrl = "http://localhost:8088"+res.data.datas.filePath
        }else{
          alert(res.data.message)
        }
      })
    },
    // // 上传成功的函数
    // handleAvatarSuccess(res, file) {
    //   // 上传成功 回显图片
    //   this.imageUrl = URL.createObjectURL(file.raw);
    // },
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
    Plus
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}
.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>