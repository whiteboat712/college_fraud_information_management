<script setup>

import Navition from "@/components/normal/Navition.vue";
</script>

<template>
  <div>
    <el-container>
      <el-header style="padding: 0">
        <Navition />
      </el-header>
      <el-main style="height: calc(100vh - 60px); padding: 30px 0 0 0; background-color: #F1F2F5">
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>

</style>