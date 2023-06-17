<script setup>

import store from "../../stores/store";
import {get, post} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {reactive, ref} from "vue";
import axios from "axios";

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
const changeUserNameOpen = ref(false)
const changePasswordOpen = ref(false)
const changeEmailOpen = ref(false)

const data = reactive({
  oldPassword: '',
  newPassword: '',
  newEmail: '',
  newUserName: '',
})

const changeUserName = () => {
  post('/api/auth/change-username', {
    username: data.newUserName,
    id: store.state.user.id,
  }, (message) => {
    store.commit('changeUser', {
      id: store.state.user.id,
      username: data.newUserName,
      password: store.state.user.password,
      email: store.state.user.email,
      type: store.state.user.type,})
    ElMessage.success(message)
  })
}
const changePassword = () => {
  post('/api/auth/change-password', {
    password: data.oldPassword,
    newPassword: data.newPassword,
    id: store.state.user.id,
  }, (message) => {
    console.log(message.data)
    store.commit('changeUser', {
      id: store.state.user.id,
      username: store.state.user.username,
      password: message.data,
      email: store.state.user.email,
      type: store.state.user.type,})
    ElMessage.success(message);
  })
}
const changeEmail = () => {
  post('/api/auth/change-email', {
    email: data.newEmail,
    id: store.state.user.id,
  }, (message) => {
    console.log(message.data)
    store.commit('changeUser', {
      id: store.state.user.id,
      username: store.state.user.username,
      password: store.state.user.password,
      email: data.newEmail,
      type: store.state.user.type,})
    ElMessage.success(message)
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
          <el-row justify="space-evenly" style="margin-top: 2vh">
            <el-col :span="6">
              <el-button @click="changePasswordOpen = !changePasswordOpen">修改密码</el-button>
            </el-col>
            <el-col :span="6">
              <el-button @click="changeUserNameOpen = !changeUserNameOpen">修改用户名</el-button>
            </el-col>
            <el-col :span="6">
              <el-button @click="changeEmailOpen = !changeEmailOpen">修改邮箱</el-button>
            </el-col>
            <el-col :span="6" @click="logout">
              <el-button type="danger">退出登录</el-button>
            </el-col>
          </el-row>

          <el-row v-if="changePasswordOpen" justify="start" :gutter="20" style="margin-top: 2vh">
            <el-divider />
            <el-col :span="8">
              <el-text>原密码</el-text>
              <el-input v-model="data.oldPassword"></el-input>
            </el-col>
            <el-col :span="8">
              <el-text>新密码</el-text>
              <el-input v-model="data.newPassword"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" @click="changePassword">修改</el-button>
            </el-col>
          </el-row>
          <el-row v-if="changeUserNameOpen" justify="start" :gutter="20" style="margin-top: 2vh">
            <el-divider />
            <el-col :span="8">
              <el-text>新用户名</el-text>
              <el-input v-model="data.newUserName"></el-input>
            </el-col>
            <el-col :span="4">
              <el-text> </el-text>
              <el-button type="primary" @click="changeUserName">修改</el-button>
            </el-col>
          </el-row>
          <el-row v-if="changeEmailOpen" justify="start" :gutter="20" style="margin-top: 2vh">
            <el-divider />
            <el-col :span="8">
              <el-text>新邮箱</el-text>
              <el-input v-model="data.newEmail"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="primary" @click="changeEmail">修改</el-button>
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