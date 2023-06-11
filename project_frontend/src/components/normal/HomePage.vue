<script setup>


const ImageStyle = 'cover'
</script>

<template>
  <div>
    <el-row>
      <el-col :span="3">

      </el-col>
      <el-col :span="18">
        <el-carousel :interval="4000" type="card" height="400px">
          <el-carousel-item>
            <a href="https://www.bilibili.com/video/BV1VQ4y1y7VY/" target="_blank">
              <el-image fit="cover" src="src/assets/img/fanzha.jpeg" />
            </a>
          </el-carousel-item>
          <el-carousel-item>
            <a href="https://110.baidu.com/fqz/page/news/75" target="_blank">
              <el-image  src="https://110.baidu.com/fqz/uploads/images/74807362d52fab43e68c7c0982a9b21b.JPG" />
            </a>
          </el-carousel-item>
          <el-carousel-item>
            <a href="https://www.gov.cn/xinwen/2021-04/27/content_5603288.htm" target="_blank">
              <el-image  src="src/assets/img/fanzha2.png" />
            </a>
          </el-carousel-item>
        </el-carousel>
        <
      </el-col>
      <el-col :span="3">

      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>