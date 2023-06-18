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
      style="height: calc(100vh - 60px)"
  >
    <el-menu-item index="1" @click="changePage('overview')">
      <template #title>
        <el-icon><location /></el-icon>
        <span>数据中心</span>
      </template>
    </el-menu-item>
    <el-sub-menu index="2">
      <template #title>
        <el-icon><icon-menu /></el-icon>
        <span>管理中心</span>
      </template>
      <el-menu-item index="2-1" @click="changePage('informationmanagement')">信息管理</el-menu-item>
      <el-menu-item index="2-2" @click="changePage('usermanagement')">用户管理</el-menu-item>
      <el-menu-item index="2-3" @click="changePage('batchAdd')">批量添加</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<style scoped>

</style>