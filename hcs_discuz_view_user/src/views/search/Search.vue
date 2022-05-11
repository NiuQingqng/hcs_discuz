<template>
  <div class="search">
    <el-row>
      <el-col :xs="0" :sm="1" :md="2" :lg="4" :xl="6"></el-col>
      <el-col :xs="24" :sm="22" :md="20" :lg="16" :xl="12">
        <div class="grid-content">
          <topic-list-by-search :topicTitle="topicTitle" :key="topicTitle" />
        </div>
      </el-col>
      <el-col :xs="0" :sm="1" :md="2" :lg="4" :xl="6"></el-col>
    </el-row>
  </div>
</template>

<script>
import TopicListBySearch from './childcomponents/TopicListBySearch.vue'

// @ is an alias to /src

export default {
  name: 'Search',
  mounted(){
    this.topicTitle = this.$route.params.topicTitle
  },
  data(){
    return {
      topicTitle: null
    }
  },
  methods: {
    
  },
  components: {
    TopicListBySearch
  }
}
</script>

<style scoped>
.grid-content{
  margin: 5px;
  box-shadow: 0px 0px 5px #ddd;
  border-radius: 2px;
  background-color: #fff;
  padding: 20px;
}
</style>
