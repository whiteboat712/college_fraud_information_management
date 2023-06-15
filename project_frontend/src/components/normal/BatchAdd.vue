<script setup>

import store from "@/stores/store";
import {reactive} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import {UploadFilled} from "@element-plus/icons-vue";

const AddForm = reactive({
  id: '',
  sname: '',
  sid: '',
  scollege: '',
  phone: '',
  type: '',
  amount: 0,
  fraudTime: '',
  time: '',
  description: '',
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

const addInformation = () => {

}

const reset = () => {
  AddForm.id = ''
  AddForm.sname = ''
  AddForm.sid = ''
  AddForm.scollege = ''
  AddForm.phone = ''
  AddForm.type = ''
  AddForm.amount = 0
  AddForm.fraudTime = ''
  AddForm.time = ''
  AddForm.description = ''
}

const submit = () => {
    axios
        .post("http://localhost:8080/api/data/addInformation", AddForm,)
        .then((res) => {
          AddForm.value = res.data.data
          ElMessage.success("提交成功")
        })
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
                <el-descriptions-item label="姓名">{{item.sname}}</el-descriptions-item>
                <el-descriptions-item label="学号">{{item.sid}}</el-descriptions-item>
                <el-descriptions-item label="学院">{{item.scollege}}</el-descriptions-item>
                <el-descriptions-item label="金额">{{item.amount}}</el-descriptions-item>
                <el-descriptions-item label="类型"><el-tag>{{item.type}}</el-tag></el-descriptions-item>
              </el-descriptions>
            </el-col>
            <el-col :span="2">
              <el-button type="danger">删除</el-button>
            </el-col>
          </el-row>

        </el-card>
        <el-button type="success">上传</el-button>
      </div>

      <div
          style="margin-top: 3vh; background-color: white; border-radius: 12px; padding: 2vh 2vw;"
      >

        <el-row justify="center">
          <el-form :model="AddForm" label-width="120px" >
            <el-form-item label="受骗人姓名">
              <el-input v-model="AddForm.sname" />
            </el-form-item>
            <el-form-item label="学号">
              <el-input v-model="AddForm.sid" />
            </el-form-item>
            <el-form-item label="学院">
              <el-select v-model="AddForm.scollege" placeholder="请选择你的学院" >
                <el-option v-for="col in store.state.colleges" :label="col" :value="col" />
              </el-select>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="AddForm.phone" />
            </el-form-item>
            <el-form-item label="诈骗类型">
              <el-select v-model="AddForm.type" placeholder="请选择诈骗类型">
                <el-option v-for="col in store.state.types" :label="col" :value="col" />
              </el-select>
            </el-form-item>
            <el-form-item label="受骗时间">
              <el-date-picker type="datetime" v-model="AddForm.fraudTime" placeholder="选择时间" />
            </el-form-item>
            <el-form-item label="提交时间">
              <el-date-picker type="datetime" v-model="AddForm.time" placeholder="选择时间" />
            </el-form-item>
            <el-form-item label="损失金额">
              <el-input-number v-model="AddForm.amount" :precision="2" />
            </el-form-item>
          </el-form>
        </el-row>
        <el-row justify="center">
          <el-button type="default" @click="reset">重置</el-button>
          <el-button type="primary" @click="addInformationSubmit">提交</el-button>
        </el-row>

      </div>
    </div>
  </el-scrollbar>
</template>

<style scoped>

</style>