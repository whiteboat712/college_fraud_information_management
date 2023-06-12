<script setup>

import BatchAdd from "@/components/normal/BatchAdd.vue";
import SingleAdd from "@/components/normal/SingleAdd.vue";
import {ref} from "vue";
const Select = ref('single')
</script>

<template>
  <div>
    <el-row>
      <el-col :span="2"></el-col>
      <el-col :span="20">
        <el-tabs tab-position="left" v-model="Select" class="demo-tabs" @tab-click="handleClick">
          <el-tab-pane label="单个提交" name="single">
            <SingleAdd />
          </el-tab-pane>
          <el-tab-pane label="批量提交" name="batch">
            <BatchAdd />
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="2"></el-col>
    </el-row>
  </div>
</template>

<style scoped>

</style>