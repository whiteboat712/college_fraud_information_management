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
  amount: '',
  fraudTime: '',
  time: '',
  description: '',
})

const AddList = reactive([])

const checkFile = async (file) => {
  // console.log(file)
  let reader = new FileReader();
  reader.onload = function (e) {
    let data = e.target.result;
    //通过XLSX来解析出workbook（工作簿）也就导入的excel文件
    let wb = XLSX.read(data, { type: "binary" });
    let sheetName = wb.SheetNames[0]
    let res = XLSX.utils.sheet_to_json(wb.Sheets[sheetName], {header: 2})
    console.log(res)
  };
  reader.readAsBinaryString(file.raw);
}

const addInformationSubmit = () => {
  axios
      .post("http://localhost:8080/api/data/addInformation", AddForm,)
      .then((res) => {
        AddForm.value = res.data.data
        ElMessage.success("提交成功")
      })
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
            :on-change="checkFile"
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
          style="background-color: white; border-radius: 12px; padding: 2vh 2vw;"
      >

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