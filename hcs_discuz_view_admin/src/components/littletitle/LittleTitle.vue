<template>
  <div class="little-title">
    <el-page-header :content="content" @back="goBack" />
    <el-divider />
  </div>
  
</template>

<script>
export default {
  props:{
    content:{
      type:String,
      default(){
        return "detail"
      }
    }
  },
  methods:{
    goBack(){
      this.$router.go(-1)
    }
  }
}
</script>

<style>

</style>