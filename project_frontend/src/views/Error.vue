<script setup>

</script>

<template>
  <el-row justify="center" align="middle">
      <el-result
          icon="error"
          title="无权限"
          sub-title="返回上一级 或 去登录"
      >
        <template #extra>
          <el-button @click="this.$router.go(-1)" type="primary">返回</el-button>
          <el-button @click="this.$router.push('/login')" type="primary">登录</el-button>
        </template>
      </el-result>
  </el-row>
</template>

<style scoped>

</style>