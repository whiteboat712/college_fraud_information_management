<script setup>

import {reactive} from "vue";
import axios from "axios";

// 我的组件
import Sidebar from "@/components/backend/Sidebar.vue";
import Topheader from "@/components/backend/Topheader.vue";
import {useRouter} from "vue-router";

const router = useRouter()

</script>

<template>
  <div>
    <el-container>
      <el-header>
        <!--        放个组件        -->
        <Topheader/>
      </el-header>
      <el-container>
        <el-aside>
          <!--        放个组件        -->
          <Sidebar/>
        </el-aside>
        <el-main style="background-color: #f6f8fb">
          <!--        放个组件        -->
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<style scoped>

</style>