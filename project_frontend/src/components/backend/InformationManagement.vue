<script setup>
import {computed, onBeforeMount, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import store from "@/stores/store";

const state = reactive({
  data: [],
})
const addPageOpen = ref(false)
const updatePageOpen = ref(false)
const updateId = ref()

const addInformation = reactive({
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

let updateInformation = reactive({
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

const exportDataToExcel = () => {
  let worksheet = XLSX.utils.json_to_sheet(state.data)
  let workbook = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheep1');
  XLSX.writeFile(workbook, 'InformationData.xlsx')
}

onBeforeMount(() => {
  axios
      .get("http://localhost:8080/api/data/getallinformation")
      .then((res) => {
        // console.log(res.data.data)
        state.data = res.data.data
      })
})

// 添加诈骗信息
const addInformationSubmit = () => {

  axios
      .post("http://localhost:8080/api/data/addInformation", addInformation)
      .then((res) => {
        state.data = res.data.data
      })
}

const deleteInformationSubmit = (row) => {
  // console.log(row.id)
  axios
      .post("http://localhost:8080/api/data/deleteInformation", row).then((res) => {
          ElMessage.info(res.message)
      })
}
const openUpdatePage = (index) => {
  updatePageOpen.value = true
  updateId.value = state.data[index].id
  updateInformation = state.data[index]
}
const updateInformationSubmit = () => {
  axios
      .post("http://localhost:8080/api/data/updateInformation", updateInformation)
      .then((res) => {
        state.data = res.data.data
      })
  updatePageOpen.value = false
}

const collegeFilters = store.state.colleges.map(x => ({
  text: x,
  value: x,
}));

const typeFilters = store.state.types.map(x => ({
  text: x,
  value: x,
}));
const filterCollege = (value, row) => {
  // value: 筛选输入的值
  // row: 当前行的数据对象
  // 返回值为布尔类型，表示是否显示该行数据
  // 自定义筛选逻辑，这里使用简单的包含匹配
  return row.scollege === value;// 判断学院是否包含筛选输入的值
}

const filterType = (value, row) => {
  // value: 筛选输入的值
  // row: 当前行的数据对象
  // 返回值为布尔类型，表示是否显示该行数据
  // 自定义筛选逻辑，这里使用严格匹配
  return row.type === value;
}
const search = ref('')
const filterTableData = computed(() =>
    state.data.filter(item => {
      return !search.value || Object.values(item).some(value => typeof value === 'string' && value.includes(search.value));
    })
)

</script>

<template>
  <div style="flex: 1">
    <el-row justify="center">
      <el-col :span="18">
        <h1 style="font-size: 50px">诈骗信息</h1>
        <el-table :data="filterTableData" max-height="70vh" style="width: 100%">
          <el-table-column sortable prop="id" label="ID" width="80" />
          <el-table-column prop="sname" label="姓名" width="80" />
          <el-table-column prop="sid" label="学号" width="150" />
          <el-table-column prop="scollege" label="学院" width="180" :filters="collegeFilters" :filter-method="filterCollege" />
          <el-table-column prop="phone" label="联系方式" width="125" />
          <el-table-column prop="type" label="类型" width="110" :filters="typeFilters" :filter-method="filterType" />
          <el-table-column sortable prop="amount" label="金额" width="80" />
          <el-table-column sortable prop="fraudTime" label="诈骗时间" width="250" />
          <el-table-column sortable prop="time" label="上传时间" width="250" />
          <el-table-column fixed="right" label="操作" width="180">
            <template #header>
              <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
              <el-button size="large" @click="openUpdatePage(scope.$index)"
              >Edit
              </el-button>
              <el-button @click="deleteInformationSubmit(scope.row)" type="danger" size="large">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button style="width: 100%" @click="addPageOpen = true">Add Item</el-button>
        <el-button type="text" @click="exportDataToExcel">导出数据到Excel</el-button>
        <el-dialog v-model="addPageOpen" title="添加诈骗信息" width="50vw">
          <el-form :model="addInformation" label-width="80px">
            <el-form-item label="姓名">
              <el-input v-model="addInformation.sname" />
            </el-form-item>
            <el-form-item label="学号">
              <el-input v-model="addInformation.sid" />
            </el-form-item>
            <el-form-item label="学院">
              <el-select v-model="addInformation.scollege" placeholder="请选择你的学院">
                <el-option label="人工智能与大数据学院" value="人工智能与大数据学院" />
              </el-select>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="addInformation.phone" />
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="addInformation.type" placeholder="请选择诈骗类型">
                <el-option label="电信诈骗" value="电信诈骗" />
              </el-select>
            </el-form-item>
            <el-form-item label="受骗时间">
              <el-date-picker type="datetime" v-model="addInformation.fraudTime" placeholder="选择时间" />
            </el-form-item>
            <el-form-item label="提交时间">
              <el-date-picker type="datetime" v-model="addInformation.time" placeholder="选择时间" />
            </el-form-item>
            <el-form-item label="损失金额">
              <el-input-number v-model="addInformation.amount" :precision="2" />
            </el-form-item>
          </el-form>

          <el-button type="default">取消</el-button>
          <el-button type="primary" @click="addInformationSubmit()">提交</el-button>
        </el-dialog>
        <el-dialog v-model="updatePageOpen" title="编辑诈骗信息" width="50vw">
          <el-form :model="updateInformation" label-width="80px">
            <el-form-item label="ID">
              <el-input v-model="updateInformation.id" />
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="updateInformation.sname" />
            </el-form-item>
            <el-form-item label="学号">
              <el-input v-model="updateInformation.sid" />
            </el-form-item>
            <el-form-item label="学院">
              <el-select v-model="updateInformation.scollege" placeholder="请选择你的学院">
                <el-option label="人工智能与大数据学院" value="人工智能与大数据学院" />
              </el-select>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="updateInformation.phone" />
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="updateInformation.type" placeholder="请选择诈骗类型">
                <el-option label="电信诈骗" value="电信诈骗" />
              </el-select>
            </el-form-item>
            <el-form-item label="受骗时间">
              <el-date-picker type="datetime" v-model="updateInformation.fraudTime" placeholder="选择时间" />
            </el-form-item>
            <el-form-item label="提交时间">
              <el-date-picker type="datetime" v-model="updateInformation.time" placeholder="选择时间" />
            </el-form-item>
            <el-form-item label="损失金额">
              <el-input-number v-model="updateInformation.amount" :precision="2" />
            </el-form-item>
          </el-form>

          <el-button type="default">取消</el-button>
          <el-button type="primary" @click="updateInformationSubmit()">提交</el-button>
        </el-dialog>
      </el-col>
    </el-row>
  </div>

</template>

<style scoped>

</style>