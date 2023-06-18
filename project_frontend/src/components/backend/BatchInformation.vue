<script setup>

import store from "@/stores/store";
import {reactive} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import {UploadFilled} from "@element-plus/icons-vue";

const AddForm = reactive({
  id: '',
  username: '',
  password: '',
  email: '',
  type: '',
})

const AddList = reactive([])

const checkFile = (file) => {
  if (file.type !== 'xlxs/json') {
    ElMessage.error('请上传xlxs或json格式文件！')
    return false;
  }
  return true;
}
const processFile = async (file) => {
  if (file.type !== 'xlxs/json') {
    ElMessage.error('请上传xlxs或json格式文件！')
    return;
  }
  let reader = new FileReader();
  reader.onload = function (e) {
    let data = e.target.result;
    //通过XLSX来解析出workbook（工作簿）也就导入的excel文件
    let wb = XLSX.read(data, { type: "binary" });
    let sheetName = wb.SheetNames[0]
    let res = XLSX.utils.sheet_to_json(wb.Sheets[sheetName], {header: 2})
    // console.log(res)
    res.forEach((ele) => {AddList.push(ele)})
  };
  reader.readAsBinaryString(file.raw);
}

const submitInformation = () => {
  axios.post('/api/data/batchAddAccount',
      AddList
  ).then((res) => {
    ElMessage.success(res)
  })
}

const reset = () => {
  AddForm.id = ''
  AddForm.username = ''
  AddForm.password = ''
  AddForm.email = ''
  AddForm.type = ''
}
const add = () => {
  AddList.push(AddForm)
}
</script>

<template>

  <el-scrollbar max-height="75vh">
    <div>
      <!--      拖拽框-->
      <div
          style="background-color: white; border-radius: 12px; padding: 2vh 2vw;"
      >
        <el-upload
            class="upload-demo"
            drag
            action="/"
            multiple
            :auto-upload="false"
            :show-file-list="false"
            :before-upload="checkFile"
            :on-change="processFile"
            v-model="AddList"
        >
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            拖拽文件到此处或者 <em>点击这里上传</em>
          </div>
          <template #tip>
            <div class="el-upload__tip">
              xls/json 文件格式
            </div>
          </template>
        </el-upload>
      </div>
      <!--      列表-->
      <div
          v-if="AddList.length > 0"
          style="background-color: white; border-radius: 12px; padding: 2vh 2vw; margin-top: 2vh"
      >
        <el-card v-for="item in AddList" shadow="hover">
          <el-row align="middle" justify="center">
            <el-col :span="22">
              <el-descriptions>
                <el-descriptions-item label="ID">{{item.id}}</el-descriptions-item>
                <el-descriptions-item label="用户名">{{item.username}}</el-descriptions-item>
                <el-descriptions-item label="密码">{{item.password}}</el-descriptions-item>
                <el-descriptions-item label="邮箱">{{item.email}}</el-descriptions-item>
                <el-descriptions-item label="类型">
                  <el-tag :type="item.type === 'user' ? '' : 'success'"
                          disable-transitions>
                    {{item.type}}
                  </el-tag>
                </el-descriptions-item>
              </el-descriptions>
            </el-col>
            <el-col :span="2">
              <el-button type="danger">删除</el-button>
            </el-col>
          </el-row>

        </el-card>
        <el-button type="success" @click="submitInformation">上传</el-button>
      </div>
    </div>
  </el-scrollbar>
</template>

<style scoped>

</style>