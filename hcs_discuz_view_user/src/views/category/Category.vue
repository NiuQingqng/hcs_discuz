<template>
  <div class="category">
    <el-row>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
      <el-col :xs="24" :sm="16" :md="14" :lg="12" :xl="9">
        <div class="grid-content" v-if="isShow">
          <div>
            <el-card shadow="hover"
              style="margin: 5px;font-size:16px; width: 30%; display: inline-block;"
              v-for="item in categoryList" :key="item"
              @click="handleCategory(item.categoryId,true)"
              >
              {{item.categoryName}}
            </el-card>
          </div>
          <div style="text-align: right">
            <span style="padding-right: 15px">
              <el-button type="text" style="font-size:16px"  @click="handleCategory(null,true)">收起</el-button>
            </span>
          </div>
        </div>
        <div class="grid-content" v-if="!isShow">
          <span style="padding-right: 15px" v-for="item in categoryList.slice(0,5)" :key="item">
            <el-button type="text" style="font-size:16px" @click="handleCategory(item.categoryId,false)">{{item.categoryName}} </el-button>
          </span>
          <span style="padding-right: 15px">
            <el-button type="text" style="font-size:16px"  @click="handleCategory(null,true)">展开分类</el-button>
          </span>
        </div>
        <div class="grid-content">
          <topic-list-by-category :categoryId="categoryId" :key="categoryId" />
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
import CreativeCenter from '../../components/right/CreativeCenter.vue'
import CarouselDiagram from '../../components/right/CarouselDiagram.vue'
import MessageBoard from '../../components/right/MessageBoard.vue'
import TopicListByCategory from './childcomponents/TopicListByCategory.vue'
import {getCategoryList} from '../../network/category'

// @ is an alias to /src

export default {
  name: 'Home',
  mounted(){
    this.toGetCategoryList()
  },
  data(){
    return {
      categoryList: [],
      categoryId: null,
      isShow: true
    }
  },
  methods: {
    toGetCategoryList(){
      getCategoryList().then(res=>{
        if(res.data.success===true){
          this.categoryList = res.data.datas.categoryList
        }else{
          alert(res.data.message)
        }
      })
    },
    handleCategory(categoryId,isOption){
      if (categoryId != null)
        this.categoryId = categoryId
      if (isOption)
        this.isShow = !this.isShow
    }
  },
  components: {
    TopicListByCategory,
    CreativeCenter,
    MessageBoard,
    CarouselDiagram,
    About
   
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
