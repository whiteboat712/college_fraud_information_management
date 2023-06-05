<script setup>
import {Lock, User} from '@element-plus/icons-vue'
</script>

<template>
  <div style="width: 100vw; height: 100vh; overflow: hidden; display: flex">
    <div style="flex: 1">
      <el-image
          style="width: 100%; height: 100%"
          fit="cover"
          src="https://images6.alphacoders.com/131/1314602.png"/>
    </div>
    <div style="width: 400px; background-color: lightblue">
      <div style="text-align: center; margin-top: 150px; padding: 0 35px 0 35px">
        <div style="font-size: 30px; color: white">高校诈骗信息管理系统</div>
        <div style="font-size: 14px; color: white; margin-top: 20px">在进入系统之前请输入用户名和密码</div>
        <div style="margin-top: 30px">
          <el-input type="text" placeholder="用户名/邮箱">
            <template #prefix>
              <el-icon><User/></el-icon>
            </template>
          </el-input>
          <el-input type="password" style="margin-top: 10px" placeholder="密码">
            <template #prefix>
              <el-icon><Lock/></el-icon>
            </template>
          </el-input>
        </div>
        <div style="margin-top: 10px">
          <el-row>
            <el-col :span="12" style="text-align: left">
              <el-checkbox v-model="checked1" label="记住我" size="large" />
            </el-col>
            <el-col :span="12" style="text-align: right">
              <el-link>忘记密码</el-link>>
            </el-col>
          </el-row>
        </div>
        <div style="margin-top: 30px">
          <el-button style="width: 200px" type="success" plain>登录</el-button>
        </div>
        <div style="margin-top: 10px">
          <el-button style="width: 200px" type="warning" plain>注册账号</el-button>
        </div>

      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
