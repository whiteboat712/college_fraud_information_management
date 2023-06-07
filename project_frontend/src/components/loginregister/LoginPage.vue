<script setup>

import {Lock, User} from "@element-plus/icons-vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router";

const form = reactive({
  username: '',
  password: '',
  remember: false
})

const login = () => {
  if (!form.username || !form.password) {
    ElMessage.warning('请填写用户名和密码！')
  } else {
    post('/api/auth/login', {
      username: form.username,
      password: form.password,
      remember: form.remember
    }, (message) => {
      ElMessage.success(message)
      router.push('/backend/index')
    })
  }

}

</script>

<template>
  <div style="text-align: center; margin-top: 150px; padding: 0 35px 0 35px">
    <div style="font-size: 30px; color: white">高校诈骗信息管理系统</div>
    <div style="font-size: 14px; color: white; margin-top: 20px">在进入系统之前请输入用户名和密码</div>
    <div style="margin-top: 30px">
      <el-input v-model="form.username" type="text" placeholder="用户名/邮箱">
        <template #prefix>
          <el-icon><User/></el-icon>
        </template>
      </el-input>
      <el-input v-model="form.password" type="password" style="margin-top: 10px" placeholder="密码">
        <template #prefix>
          <el-icon><Lock/></el-icon>
        </template>
      </el-input>
    </div>
    <div style="margin-top: 10px">
      <el-row>
        <el-col :span="12" style="text-align: left">
          <el-checkbox v-model="form.remember" label="记住我" size="large" />
        </el-col>
        <el-col :span="12" style="text-align: right">
          <el-link>忘记密码</el-link>>
        </el-col>
      </el-row>
    </div>
    <div style="margin-top: 30px">
      <el-button @click="login()" style="width: 200px" type="success" plain>登录</el-button>
    </div>
    <div style="margin-top: 10px">
      <el-button style="width: 200px" type="warning" plain>注册账号</el-button>
    </div>
  </div>
</template>

<style scoped>

</style>