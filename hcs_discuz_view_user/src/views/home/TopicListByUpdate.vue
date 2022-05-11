<template>
  <div class="topiclistbyupdate">
    <el-row>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
      <el-col :xs="24" :sm="16" :md="14" :lg="12" :xl="9">
        <div class="grid-content">
          <topic-list :isHot="false" />
        </div>
      </el-col>
      <el-col :xs="0" :sm="8" :md="8" :lg="6" :xl="5">
        <!-- 创作中心 -->
        <div class="grid-content">
          <creative-center/>
        </div>
        <!-- 轮播图 -->
        <div class="grid-content" style="padding: 0px">
          <carousel-diagram/>
        </div>
        <!-- 留言板 -->
        <div class="grid-content">
          <message-board/>
        </div>
        <!-- 关于 -->
        <div style="margin: 5px; padding: 10px;">
          <about/>
        </div>
      </el-col>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
    </el-row>
  </div>
</template>

<script>
import About from '../../components/right/About.vue'
import CarouselDiagram from '../../components/right/CarouselDiagram.vue'
import CreativeCenter from '../../components/right/CreativeCenter.vue'
import MessageBoard from '../../components/right/MessageBoard.vue'
import TopicList from './childcomponents/TopicList.vue'

// @ is an alias to /src

export default {
  name: 'Home',
  components: {
    TopicList,
    CreativeCenter,
    MessageBoard,
    About,
    CarouselDiagram
  }
}
</script>

<style scoped>
.grid-content{
  margin: 5px;
  box-shadow: 0px 0px 5px #ddd;
  border-radius: 2px;
  background-color: #fff;
  padding: 20px
}
</style>
