<script setup>

import {Avatar} from "@element-plus/icons-vue";
</script>


<template>
  <div class="navbar">
    <el-row>
      <el-col :span="6">
        <!-- Logo -->
        <el-icon></el-icon>
        LOGO
      </el-col>
      <el-col :span="12" >
        <!-- 导航栏标签 -->
        <el-menu router="router" mode="horizontal">
          <el-menu-item index="/home">首页</el-menu-item>
          <el-menu-item index="/home/add-information">提交信息</el-menu-item>
        </el-menu>

      </el-col>
      <el-col :span="6">
        <!-- Logo -->

      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
  .navbar {
    height: 60px; /* 根据您的需求设置高度 */
    background-color: #f5f7fa; /* 根据您的需求设置背景颜色 */
  }

  .navbar-items {
    display: flex;
    align-items: center;
  }

  .navbar-menu {
    border: none;
  }
</style>