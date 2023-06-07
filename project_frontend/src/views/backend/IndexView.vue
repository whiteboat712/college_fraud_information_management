<script setup>

import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import informationApi from "@/api/informationApi.js";
import {reactive} from "vue";
import axios from "axios";

const information = reactive({
  id: '',
  sid: '',
  sname: '',
  // scollege: '',
  // phone: '',
  // type: '',
  // amount: '',
  // fraudtime: '',
  // time: '',
})

const logout = () => {
  get('/api/auth/logout', (message) => {
    ElMessage.success(message)
    router.push('/')
  })
}

const getInformation = () => {
  axios
      .get("http://localhost:8080/api/data")
      .then((res) => {
        information.id = res.data.data.id
        console.log(res)
        console.log(res.data.data.id)
        console.log(res.data.data.sid)
        information.sid = res.data.data.sid
        information.sname = res.data.data.sname
      })
  // informationApi.informationApi({
  //
  // }).then(res => {
  //   console.log(res)
  //   ElMessage.info(res.message);
  //   information.id = res.data.id;
  //   information.sid = res.data.sid;
  //   information.sname = res.data.sname;
  // }).catch(error => {
  //   console.log(error);
  // })
}



</script>

<template>
  <h1>主页</h1>
  <ElButton @click="logout()" type="danger">退出登录</ElButton>
  <ElButton @click="getInformation()" type="primary">获取数据</ElButton>
  <el-input v-model="information.id">123</el-input>
  <el-input v-model="information.sid">123</el-input>
  <el-input v-model="information.sname">123</el-input>
  <router-view/>
</template>

<style scoped>

</style>