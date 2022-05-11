<template>
  <div class="write-comment">
    <div style="border: 1px solid #ccc; margin: 10px">
      <Editor
        style="height: 200px; overflow-y: hidden;"
        v-model="contentHtml"
        :defaultConfig="editorConfig"
        :mode="mode"
        @onCreated="handleCreated"
      />
      <div style="background-color: #ccc; height: 1px"></div>
      <div style="background-color: #fff; height: 50px; line-height:50px; text-align: right; padding-right: 20px">
        <el-button type="primary" @click="handleSubmit">发送</el-button>
      </div>
    </div>
  </div>
  
</template>

<script>
import '@wangeditor/editor/dist/css/style.css' // 引入 css

import { onBeforeUnmount, shallowRef, onMounted } from 'vue'
import { Editor } from '@wangeditor/editor-for-vue'
import {addComment} from '../../../network/comment'
import { ElMessage } from 'element-plus'

export default {
  mounted(){
    
  },
  props:{
    topicId:{
      default(){
        return 0
      }
    }
  },
  data(){
    return {
      contentHtml: null
    } 
  },
  components: { Editor },
  methods: {
    handleSubmit(){
      const comment = {
        topicId: this.topicId,
        content: this.contentHtml,
      }
      addComment(comment).then(res=>{
        if(res.data.success===true){
          ElMessage({
            message: '发送成功！',
            type: 'success',
          })
          this.$emit("afterAddComment")
          this.contentHtml = null
        }else{
          alert(res.data.message)
        }
      })
    },
  },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()

    // 模拟 ajax 异步获取内容
    onMounted(() => {
      
        // setTimeout(() => {
        //     valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>'
        // }, 1500)
    })

    const editorConfig = { placeholder: '请输入内容...' }
    editorConfig.maxLength = 1950

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
      mode: 'simple', // 或 'simple'
      editorConfig,
      handleCreated
    };
  }
}
</script>

<style>

</style>