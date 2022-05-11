<template>
  <div class="profile">
    <el-row>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
      <el-col :xs="24" :sm="24" :md="22" :lg="18" :xl="14">
        <div class="grid-content profile-header" style="background-color: #fff; ">
          <el-image :src="wapperUrl" />
          <div style="margin: 20px;margin-bottom: 0px" v-if="!isSelf"><h3>{{userInfo.username}}的主页</h3></div>
          <el-row>
              <el-col :span="12">
                <div class="profile-info" style="padding:20px">
                  <el-row>
                    <el-col :span="12">
                      <el-avatar shape="square" :size="100" :src="userInfo.avatar" />
                    </el-col>
                    <el-col :span="12">
                      <div  style="padding-left: 10px; padding-top: 46px;">
                        <b style="font-size: 24px;">{{userInfo.username}}</b><br>
                        <el-button type="text" @click="isActive=1" v-if="isSelf"><span style="font-size: 14px; color: #666">查看详细信息</span></el-button>
                        <span v-if="!isSelf">{{userInfo.sex}}</span>
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-col>
              <el-col :span="12">
                <div style="text-align: right; padding-right: 20px; padding-top: 50px" v-if="isSelf">
                  <el-button @click="isActive=1">编辑个人资料</el-button>
                </div>
              </el-col>
            </el-row>
        </div>
      </el-col>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
    </el-row>
    <el-row>
      <el-col :xs="0" :sm="0" :md="1" :lg="3" :xl="5"></el-col>
      <el-col :xs="24" :sm="16" :md="14" :lg="12" :xl="9">
        <el-row>
          <el-col :xs="24" :sm="0" :md="0" :lg="0" :xl="0">
            <div class="grid-content">
              <user-content-count :userInfo="userInfo"/>
            </div>
          </el-col>
        </el-row>
        <div class="grid-content profile-menu">
          <span class="profile-menu-item"
            :class="{active: isActive === index}"
            v-for="(item,index) of profileMenu"
            @click="changeClass(index)"
            :key="index" >
            <el-button type="text" @click="handleMenuClick(index)">{{item}}</el-button>
          </span>
          <div style="height: 1px; background-color: #ddd; margin: 10px"></div>
          <div class="profile-content">
            <div v-if="isActive===0">
              <user-topic-list :userId="userId" :isSelf="isSelf" v-if="isLoaded"/>
            </div>
            <div v-if="isActive===1 && isSelf">
              <user-info-detail :userInfoProps="userInfo"/>
            </div>
          </div>
        </div>
        
      </el-col>
      <el-col :xs="0" :sm="8" :md="8" :lg="6" :xl="5">
        <div class="grid-content" style="background-color: #fff; padding: 20px" v-if="isSelf">
          <creative-center :userInfo="userInfo"/>
        </div>
        <div class="grid-content" style="background-color: #fff; padding: 20px" v-if="!isSelf">
          <other-user-info-detail :userInfo="userInfo"/>
        </div>
        <!-- 轮播图 -->
        <div class="grid-content" style="padding: 0px">
          <carousel-diagram/>
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
import {UserFind} from '../../network/login'
import {getUserInfo} from '../../network/user'
import UserInfoDetail from './childcomponents/UserInfoDetail.vue'
import OtherUserInfoDetail from './childcomponents/OtherUserInfoDetail.vue'
import UserTopicList from './childcomponents/UserTopicList.vue'
import CreativeCenter from '../../components/right/CreativeCenter.vue'
import CarouselDiagram from '../../components/right/CarouselDiagram.vue'
import UserContentCount from './childcomponents/UserContentCount.vue'
import About from '../../components/right/About.vue'
import config from '../../assets/js/config'
export default {
  mounted(){
    this.userId = this.$route.query.userId
    this.toGetUserInfo()
  },
  data(){
    return {
      userId: '',
      userInfo: {},
      wapperUrl: config.wapperUrl,
      profileMenu: ["帖子","资料"],
      isActive: 0,
      isLoaded: false,
      isSelf: false, //是否是自己的主页
    }
  },
  methods: {
    toGetUserInfo(){
      UserFind().then(res=>{
        if(res.data.success===true){
          if(parseInt(this.userId)===0||parseInt(this.userId)===parseInt(res.data.datas.user.userId)){
            this.userInfo = res.data.datas.user;
            this.isSelf = true
          }else{
            // 如果传过来的userid不是自己的,加载他人信息
            getUserInfo(this.userId).then(res=>{
              if(res.data.success===true){
                this.userInfo = res.data.datas.userInfo;
                this.profileMenu = ["帖子"];
                this.isSelf = false
              }else{
                alert(res.data.message)
              }
            })
          }
          //用户信息加载完成再去生成帖子列表组件，否则帖子列表组件会在userId传递之前加载帖子列表
          this.isLoaded = true;
        }else{
          alert("未登录!")
        }
      })
    },
    changeClass(i) {
      this.isActive = i;
    },
    handleMenuClick(index){
      console.log(index);
    }
  },
  components:{
    UserTopicList,
    UserInfoDetail,
    CreativeCenter,
    UserContentCount,
    OtherUserInfoDetail,
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
}
.profile-content{
  margin-left: 25px;
  margin-right: 25px;
}
.profile-info{
  width: 200px;
}
.profile-menu{
  background-color: #fff; 
  padding: 10px;
}
.profile-menu-item{
  padding: 5px;
  margin-left: 18px;
}
.active {
  border-bottom: 3px solid rgb(98, 98, 255);
}
</style>