<script setup>

import store from "../../stores/store";
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";

const logout = () => {
  get('/api/auth/logout', (message) => {
    store.commit('changeUser', {
      id: '',
      username: '',
      password: '',
      email: '',
      type: '',})
    ElMessage.success(message)
    router.push('/login')
  })
}

</script>

<template>
  <div>
    <el-row>
      <el-col :span="3"></el-col>
      <el-col :span="18">
        <div style="background-color: white; border-radius: 12px; padding: 2vh 2vw;">
          <h1 style="font-size: 36px">个人信息</h1>
          <el-row justify="space-around" >
            <el-col :span="14">
              <el-descriptions column="1" border>
                <el-descriptions-item label="用户名">{{store.state.user.username}}</el-descriptions-item>
                <el-descriptions-item label="邮箱">{{store.state.user.email}}</el-descriptions-item>
                <el-descriptions-item label="ID">{{store.state.user.id}}</el-descriptions-item>
                <el-descriptions-item label="类型"><el-tag>{{store.state.user.type}}</el-tag></el-descriptions-item>
              </el-descriptions>
            </el-col>
            <el-col :span="8">
              <el-avatar shape="square" :size="162" style="font-size: 36px">{{store.state.user.username}}</el-avatar>
            </el-col>
          </el-row>
          <el-row justify="start">
            <el-col :span="2">
              <el-button type="text">修改密码</el-button>
            </el-col>
            <el-col :span="2">
              <el-button>修改用户名</el-button>
            </el-col>
            <el-col :span="2">
              <el-button>修改邮箱</el-button>
            </el-col>
            <el-col :span="2" @click="logout">
              <el-button>退出登录</el-button>
            </el-col>
          </el-row>
        </div>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>
  </div>
</template>

<style scoped>

</style>