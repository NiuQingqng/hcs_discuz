<template lang="html">  
  <div class="editor">  
    <div ref="toolbar" class="toolbar"></div>  
    <div ref="editor" class="text"></div>  
  </div>  
</template>  
  
<script>  
  import E from 'wangeditor'  
  export default {  
    name: 'editoritem', 
    data() {  
      return {  
        // uploadPath,  
        editor: null,  
        info_: null  
      }  
    },  
    model: {  
      prop: 'value',  
      event: 'change'  
    },  
    props: {  
      value: {  
        type: String,  
        default: ''  
      },  
      isClear: {  
        type: Boolean,  
        default: false  
      }  
    },  
    mounted() {  
      this.seteditor()  
      this.editor.txt.html(this.value)  
    },  
    methods: {  
      seteditor() {  
        this.editor = new E(this.$refs.toolbar, this.$refs.editor)  
        this.editor.create()  
      }  
    }  
  }  
</script>  
  
<style lang="css">  
  .editor {  
    width: 100%;  
    margin: 0 auto;  
    position: relative;  
    z-index: 0;  
  }  
  .toolbar {  
    border: 1px solid #ccc;  
  }  
  .text {  
    border: 1px solid #ccc;  
    min-height: 500px;  
  }  
</style>  