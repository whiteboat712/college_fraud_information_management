<script setup>

import {Avatar, User} from "@element-plus/icons-vue";
import {ref} from "vue";

const isDark = ref()
const userName = ref('User')
</script>


<template>
  <div class="navbar">
    <el-row style="display: flex; background-color: white">
        <!-- 导航栏标签 -->
      <el-menu
          router="router"
          mode="horizontal"
          popper-effect="light"
          style="width: 100%"
      >
        <el-text type="primary" size="large">高校反诈</el-text>

        <el-menu-item index="/home">首页</el-menu-item>

        <el-menu-item index="/home/add-information">提交诈骗信息</el-menu-item>
        <div style="width: 70%"></div>
        <el-menu-item>
          <el-switch
              v-model="isDark"
              size="large"
          />
        </el-menu-item>
        <el-menu-item>
          <el-icon><User /></el-icon>
          <el-text>Username</el-text>
        </el-menu-item>

      </el-menu>
    </el-row>
  </div>
</template>

<style scoped>
  .navbar {
    height: 60px; /* 根据您的需求设置高度 */
    width: 100%;
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