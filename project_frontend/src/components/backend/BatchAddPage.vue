<script setup>

import BatchAdd from "@/components/normal/BatchAdd.vue";
import {ref} from "vue";
import BatchInformation from "@/components/backend/BatchInformation.vue";
const Select = ref('single')



</script>

<template>
  <div>
    <el-row>
      <el-col :span="3"></el-col>
      <el-col :span="18">
        <h1 style="font-size: 36px">批量添加信息</h1>
        <el-tabs tab-position="right" v-model="Select" class="demo-tabs">
          <el-tab-pane label="批量提交诈骗信息" name="information">
            <BatchAdd />
          </el-tab-pane>
          <el-tab-pane label="批量提交账户信息" name="account">
            <BatchInformation />
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>
  </div>
</template>

<style scoped>
:deep(.el-tabs__item) {
  font-size: 20px;
  font-weight: bold;
}
</style>