<script setup>

import {reactive} from "vue";
import axios from "axios";

// 我的组件
import Sidebar from "@/components/backend/Sidebar.vue";
import Topheader from "@/components/backend/Topheader.vue";
import {useRouter} from "vue-router";

const router = useRouter()



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
  <div>
    <el-container>
      <el-header>
        <!--        放个组件        -->
        <Topheader/>
      </el-header>
      <el-container>
        <el-aside>
          <!--        放个组件        -->
          <Sidebar/>
        </el-aside>
        <el-main style="background-color: #f6f8fb">
          <!--        放个组件        -->
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<style scoped>

</style>