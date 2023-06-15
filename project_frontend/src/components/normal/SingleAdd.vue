<script setup>

import {reactive} from "vue";
import axios from "axios";
import store from "@/stores/store";
import {ElMessage} from "element-plus";

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
    <div
        style="background-color: white; border-radius: 12px;"
    >
      <el-scrollbar max-height="80vh" style="padding: 5vh 5vw">
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
      </el-scrollbar>
    </div>
</template>

<style scoped>

</style>