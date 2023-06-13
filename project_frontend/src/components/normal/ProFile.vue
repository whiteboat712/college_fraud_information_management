<script setup>

import SingleAdd from "@/components/normal/SingleAdd.vue";
import BatchAdd from "@/components/normal/BatchAdd.vue";
</script>

<template>
  <div>
    <el-row>
      <el-col :span="3"></el-col>
      <el-col :span="18">
        <h1 style="font-size: 36px">个人信息</h1>
        <el-tabs tab-position="left" v-model="Select" class="demo-tabs" @tab-click="handleClick">
          <el-tab-pane label="单个提交" name="single">
            <SingleAdd />
          </el-tab-pane>
          <el-tab-pane label="批量提交" name="batch">
            <BatchAdd />
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>
  </div>
</template>

<style scoped>

</style>