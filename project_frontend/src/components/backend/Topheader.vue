<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";



const logout = () => {
  get('/api/auth/logout', (message) => {
    this.$state.commit('changeUser', {
      id: '',
      username: '',
      password: '',
      email: '',
      type: '',})
    ElMessage.success(message)
    router.push('/')
  })
}

</script>

<template>
  <div>
    <el-row :gutter="20" align="middle">
      <el-col :span="21">
        <h1>高校诈骗信息管理系统后台</h1>
      </el-col>
      <el-col :span="3">
        <div>
          <el-dropdown @command="handleCommand">
            <span>
              <el-avatar
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              />
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="a">Action 1</el-dropdown-item>
                <el-dropdown-item command="b">Action 2</el-dropdown-item>
                <el-dropdown-item @click="logout()" command="c">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>

.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>