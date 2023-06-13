<script setup>

import {reactive} from "vue";
import axios from "axios";

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

const addInformationSubmit = () => {
  axios
      .post("http://localhost:8080/api/data/addInformation", addInformation)
      .then((res) => {
        AddForm.value = res.data.data
      })
}
</script>

<template>
  <div
      style="height: 500px; background-color: white; border-radius: 12px;"
  >

  </div>
</template>

<style scoped>

</style>