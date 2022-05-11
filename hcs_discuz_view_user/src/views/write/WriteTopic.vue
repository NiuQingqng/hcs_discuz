<template>
  <div class="write">
    <el-row>
      <el-col :xs="0" :sm="1" :md="2" :lg="4" :xl="6"></el-col>
      <el-col  :xs="24" :sm="22" :md="20" :lg="16" :xl="12">
        <div style="margin:10px">
          <el-input
            v-model="topicInfo.title"
            maxlength="60"
            placeholder="请输入标题"
            class="input-with-select"
            size="large"
            style="font-size: 16px"
            >
            <template #append>
              <el-select v-model="topicInfo.categoryId" placeholder="请选择" style="width: 110px">
                <el-option v-for="item in categoryList" :label="item.categoryName" :value="item.categoryId" :key="item" />
              </el-select>
            </template>
          </el-input>
        </div>
        <div style="border: 1px solid #ccc; margin: 10px">
          <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editorRef"
            :defaultConfig="toolbarConfig"
            :mode="mode"
          />
          <Editor
            style="height: 500px; overflow-y: hidden;"
            v-model="topicInfo.content"
            :defaultConfig="editorConfig"
            :mode="mode"
            @onCreated="handleCreated"
          />
          <div style="background-color: #ccc; height: 1px"></div>
          <div style="background-color: #fff; height: 50px; line-height:50px; text-align: right; padding-right: 20px">
            <span style="color: #888; padding-right: 20px">正文字数: 0字</span>
            
            <el-button type="primary" @click="handleSubmit">提交</el-button>
          </div>
        </div>
        <div style="margin-left:10px;margin-right:10px">
          {{topicInfo.content}}
          <!-- <p>
            本平台所有用户必须遵守中华人民共和国有关法律法规及本平台的规定。严禁发表造谣、侮辱、诽谤他人、煽动颠覆国家政权的言论和泄露国家机密；严禁发表暴力、淫秽、色情、迷信、宣扬种族歧视、破坏民族团结的言论和消息；用户发表的文章仅代表其个人观点，与模具论坛无关；用户须承担一切因自己的行为而直接或间接导致的民事、行政或刑事法律责任。在任何情况下，平台均不对内容的准确性、合法性、完整性以及因内容而引起的任何损失、损害承担任何责任。用户发表的内容仅代表个人观点，与平台无关。
          </p> -->
        </div>
      </el-col>
      <el-col :xs="0" :sm="1" :md="2" :lg="4" :xl="6"></el-col>
    </el-row>
    
  </div>
  
</template>

<script>
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import config from '../../assets/js/config'
import Cookies from 'js-cookie'
import { onBeforeUnmount, ref, shallowRef } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import {getCategoryList} from '../../network/category'
import {getTopicInfo,addTopic,updateTopic} from '../../network/topic'
import { ElMessageBox,ElMessage } from 'element-plus'

export default {
  mounted(){
    this.updateTopicId  = this.$route.params.updateTopicId

    if(this.updateTopicId){
      this.toGetCategoryList();
      this.toGetTopicInfo();
    }else{
      this.toGetCategoryList();
    }
  },
  data(){
    return {
      topicInfo: {
        title: null,
        categoryId:null,
        content: null,
      },
      categoryList: [],
      updateTopicId: null
    } 
  },
  components: { Editor, Toolbar },
  methods: {
    handleSubmit(){
      if (this.topicInfo.title===null) {
        ElMessageBox.alert("请输入标题！")
        // alert("请输入标题！")
        return;
      }
      if (this.topicInfo.categoryId===null) {
        ElMessageBox.alert("请选择分类！")
        // alert("请选择分类！")
        return;
      }
      if(this.updateTopicId){
        this.toUpdateTopic();
      }else{
        this.toAddTopic();
      }
    },
    toGetCategoryList(){
      getCategoryList().then(res=>{
        if(res.data.success===true){
          this.categoryList = res.data.datas.categoryList
          console.log(this.categoryList);
        }else{
          alert(res.data.message)
        }
      })
    },
    toGetTopicInfo(){
      getTopicInfo(this.updateTopicId).then(res=>{
        if(res.data.success===true){
          if(res.data.datas.topicInfo.userId!=this.$store.state.loginUserInfo.userId){
            alert("权限有误！")
            return ;
          }
          this.topicInfo = res.data.datas.topicInfo
        }else{
          alert(res.data.message)
        }
      })
    },
    toAddTopic(){
      addTopic(this.topicInfo).then(res=>{
        if(res.data.success===true){
          ElMessage({
            message: '发布成功！',
            type: 'success',
          })
          this.$router.go(-1)
        }else{
          alert(res.data.message)
        }
      })
    },
    toUpdateTopic(){
      updateTopic(this.topicInfo).then(res=>{
        if(res.data.success===true){
          ElMessage({
            message: '修改成功！',
            type: 'success',
          })
          this.$router.go(-1)
        }else{
          alert(res.data.message)
        }
      })
    }
  },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()

    // 内容 HTML
    const valueHtml = ref('<p>hello</p>')

    const toolbarConfig = {
      toolbarKeys: [
        'emotion',//表情
        'headerSelect',// 菜单 key
        'bold',//加粗
        // '|',// 分割线
        'uploadImage',
        'divider',  // 插入分割线
        'clearStyle', //清除格式
        'fullScreen', //全屏
      ]
    }

    const editorConfig = { placeholder: '请输入内容...', MENU_CONF:{}}

    editorConfig.maxLength = 1950

    editorConfig.MENU_CONF['uploadImage'] = {
      fieldName: 'file',
      server: config.host+'/file/upload', //服务器地址
      maxFileSize: 1 * 1024 * 1024, // 1M
      maxNumberOfFiles: 10,  //限制上传的数量
      // 自定义增加 http  header
      headers: {
          token: Cookies.get('token')
      },
      // 单个文件上传成功之后
      onSuccess(file, res) {
          console.log('信息：'+`${file.name} 上传成功`, "结果："+res)
      },
      // 单个文件上传失败
      onFailed(file, res) {
          console.log('信息：'+`${file.name} 上传失败`, "结果："+res)
      },
      // 上传错误，或者触发 timeout 超时
      onError(file, err, res) {
        console.log('信息：'+`${file.name} niuniu上传出错`, "错误："+err,"结果："+ res)
        alert("上传出错")
      },
      // 自定义插入图片
      customInsert(res, insertFn) {
        // res 即服务端的返回结果
        // url = config.host + res.data.datas.filePath;
        // alt = res.data.datas.filePath;
        // href = url;
        // 从 res 中找到 url alt href ，然后插图图片
        insertFn(res.datas.filePath, "alt", "#")
      },
    }
    
    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
        const editor = editorRef.value
        if (editor == null) return
        editor.destroy()
    })

    const handleCreated = (editor) => {
      editorRef.value = editor // 记录 editor 实例，重要！
    }

    return {
      editorRef,
      valueHtml,
      mode: 'simple', // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated
    };
  }
}
</script>

<style>

</style>