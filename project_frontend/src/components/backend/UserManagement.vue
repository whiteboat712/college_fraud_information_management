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

const addAccount = reactive({
  id: '',
  username: '',
  password: '',
  email: '',
  type: '',
})

let updateAccount = reactive({
  id: '',
  username: '',
  password: '',
  email: '',
  type: '',
})

const exportDataToExcel = () => {
  let worksheet = XLSX.utils.json_to_sheet(state.data)
  let workbook = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheep1');
  XLSX.writeFile(workbook, 'AccountData.xlsx')
}

onBeforeMount(() => {
  axios
      .get("http://localhost:8080/api/data/getAllAccounts")
      .then((res) => {

        state.data = res.data.data
        // console.log(data)
      })
})

// 添加诈骗信息
const addAccountSubmit = () => {
  axios
      .post("http://localhost:8080/api/data/addAccount", addAccount)
      .then((res) => {
        state.data = res.data.data
      })
  addPageOpen.value = false;
}

const deleteAccountSubmit = (row) => {
  // console.log(row.id)
  axios
      .post("http://localhost:8080/api/data/deleteAccount", row).then((res) => {
    ElMessage.info(res.message)
  })
}
const openUpdatePage = (index) => {
  updatePageOpen.value = true
  // console.log(data)
  updateId.value = state.data[index].id
  updateAccount = state.data[index]
}
const updateAccountSubmit = () => {
  axios
      .post("http://localhost:8080/api/data/updateAccount", updateAccount)
      .then((res) => {
        state.data = res.data.data
      })
  updatePageOpen.value = false
}

const typeFilters = store.state.types.map(x => ({
  text: x,
  value: x,
}));

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
        <h1 style="font-size: 50px">用户管理</h1>
        <el-table :data="filterTableData" max-height="70vh">
          <el-table-column sortable prop="id" label="ID" />
          <el-table-column prop="username" label="用户名" />
          <el-table-column prop="email" label="邮箱" />
          <el-table-column prop="type" label="类型" :filters="typeFilters" :filter-method="filterType" >
            <template #default="scope">
              <el-tag
                  :type="scope.row.type === 'user' ? '' : 'success'"
                  disable-transitions
              >{{ scope.row.type }}</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="180">
            <template #header>
              <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
              <el-button size="large" @click="openUpdatePage(scope.$index)"
              >Edit
              </el-button>
              <el-button @click="deleteAccountSubmit(scope.row)" type="danger" size="large">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button style="width: 100%" @click="addPageOpen = true">Add Item</el-button>
        <el-button type="text" @click="exportDataToExcel">导出数据到Excel</el-button>
        <el-dialog v-model="addPageOpen" title="添加诈骗信息" width="50vw">
          <el-form :model="addAccount" label-width="80px">
            <el-form-item label="用户名">
              <el-input v-model="addAccount.username" />
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="addAccount.password" />
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="addAccount.email" />
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="addAccount.type" placeholder="请选择账户类型">
                <el-option label="管理员" value="admin" />
                <el-option label="普通用户" value="user" />
              </el-select>
            </el-form-item>
          </el-form>
          <el-button type="default">取消</el-button>
          <el-button type="primary" @click="addAccountSubmit()">提交</el-button>
        </el-dialog>
        <el-dialog v-model="updatePageOpen" title="编辑账户信息" width="50vw">
          <el-form :model="updateAccount" label-width="80px">
            <el-form-item label="用户名">
              <el-input v-model="updateAccount.username" />
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="updateAccount.password" />
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="updateAccount.email" />
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="updateAccount.type" placeholder="请选择账户类型">
                <el-option label="管理员" value="admin" />
                <el-option label="普通用户" value="user" />
              </el-select>
            </el-form-item>
          </el-form>
          <el-button type="default">取消</el-button>
          <el-button type="primary" @click="updateAccountSubmit()">提交</el-button>
        </el-dialog>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>

</style>