<script setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import {useRouter} from "vue-router";

const router = useRouter()

const changePage = (url) => {
  router.push(url);
}

</script>

<template>
  <el-menu
      default-active="1-1"
      class="el-menu-vertical-demo"
      on-select=""
      style="height: 100vh"
  >
    <el-sub-menu index="1">
      <template #title>
        <el-icon><location /></el-icon>
        <span>数据中心</span>
      </template>
      <el-menu-item index="1-1" @click="changePage('overview')">概览</el-menu-item>
      <el-menu-item index="1-2" @click="changePage('analysis')">数据分析</el-menu-item>
      <el-menu-item index="1-3">诈骗信息列表</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="2">
      <template #title>
        <el-icon><icon-menu /></el-icon>
        <span>管理中心</span>
      </template>
      <el-menu-item index="2-1" @click="changePage('informationmanagement')">信息管理</el-menu-item>
      <el-menu-item index="2-2" @click="changePage('usermanagement')">用户管理</el-menu-item>
    </el-sub-menu>
    <el-menu-item index="3">
      <el-icon><setting /></el-icon>
      <span>设置</span>
    </el-menu-item>
  </el-menu>
</template>

<style scoped>

</style>