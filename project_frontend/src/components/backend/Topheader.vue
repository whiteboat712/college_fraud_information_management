
<script setup>

import {User} from "@element-plus/icons-vue";
import {ref} from "vue";
import store from "../../stores/store";

const isDark = ref()
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
        <el-text type="primary" size="large">高校诈骗信息管理系统后台</el-text>
        <div style="width: 70%"></div>
        <el-menu-item index="/home/profile">
          <el-icon><User /></el-icon>
          <el-text>{{store.state.user.username}}</el-text>
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
</style>