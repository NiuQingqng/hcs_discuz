<template>
  <div class="menu">
    <el-menu
      :default-active="this.$route.name"
      class="el-menu-vertical"
      router=true
      >
      <el-menu-item index="/">
        <el-icon><location /></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-menu-item index="/userlist">
        <el-icon><avatar /></el-icon>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="/topiclist">
        <el-icon><document /></el-icon>
        <span>帖子管理</span>
      </el-menu-item>
      <el-menu-item index="/commentlist">
        <el-icon><expand /></el-icon>
        <span>评论和回复管理</span>
      </el-menu-item>
      <el-menu-item index="/categorylist">
        <el-icon><icon-menu /></el-icon>
        <span>文章分类管理</span>
      </el-menu-item>
      <el-menu-item index="/adviselist">
        <el-icon><setting /></el-icon>
        <span>举报信息管理</span>
      </el-menu-item>
      <el-menu-item index="/messageboard">
        <el-icon><data-board /></el-icon>
        <span>留言板</span>
      </el-menu-item>
      
      <el-menu-item index="/about">
        <el-icon><setting /></el-icon>
        <span>关于</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  import {
    Avatar,
    Location,
    Document,
    Expand,
    Menu as IconMenu,
    DataBoard,
    Setting,
  } from '@element-plus/icons-vue'
export default {
  components:{
    Avatar,
    Location,
    Document,
    Expand,
    IconMenu,
    DataBoard,
    Setting,
  },
}
</script>

<style> 

</style>