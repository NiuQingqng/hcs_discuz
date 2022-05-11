<template>
  <div>
    <el-carousel>
      <el-carousel-item v-for="item in url" :key="item">
        <el-image :src="item" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import config from '../../assets/js/config'
export default {
  data(){
    return {
      url: config.bannerUrls
    }
  },
}
</script>

<style>

</style>