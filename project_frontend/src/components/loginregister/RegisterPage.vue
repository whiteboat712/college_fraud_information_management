<script setup>

import {Connection, Lock, Message, User} from "@element-plus/icons-vue";
import router from "@/router";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";

const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  type: 'user',
})

// 校验用户名
const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if (!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/i.test(value)) {
    callback(new Error('用户名不能包含特殊字符，只能是英文和数字'))
  } else {
    callback()
  }
}

// 校验重复密码
const validatePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password) {
    callback(new Error("输入的密码不一致"))
  } else {
    callback()
  }
}

// 表单检验规则
const rules = {
  username: [
    { validator: validateUsername, trigger: ['blur', 'change'] },
    { min: 5, max: 20, message: '用户名长度必须在5-20个字符之间', trigger: ['blur', 'change'] },
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    { min: 5, max: 20, message: '密码长度必须在5-20个字符之间', trigger: ['blur', 'change'] },
  ],
  password_repeat: [
    {required: true, message: '请输入重复密码', trigger: 'blur'},
    { validator: validatePassword, trigger: ['blur', 'change'] },
  ],
  email: [
    {
      type: 'email',
      required: true,
      message: '请输入正确的邮箱',
      trigger: ['blur', 'change'],
    },
  ]
}
const formRef = ref()
const isEmailValid = ref(false)

const onValidate = (prop, isvalid, _) => {
  if (prop === 'email') {
    isEmailValid.value = isvalid
  }
}

const register = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post('/api/auth/register', form, () => {
        ElMessage.success("注册成功")
      })
    } else {
      ElMessage.warning('请完整填写内容')
    }
  }).then()
}


</script>

<template>
  <div v-show="true" style="text-align: center; margin-top: 150px; padding: 0 35px 0 35px">
    <div style="font-size: 30px; color: white">注册</div>
    <div style="font-size: 14px; color: white; margin-top: 20px">欢迎注册管理系统</div>
    <el-form
        :model="form"
        :rules="rules"
        @validate="onValidate"
        ref="formRef"
    >
      <el-form-item prop="username">
        <el-input v-model="form.username" type="text" placeholder="用户名">
          <template #prefix>
            <el-icon><User/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="form.password" type="password" placeholder="密码">
          <template #prefix>
            <el-icon><Lock/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password_repeat">
        <el-input v-model="form.password_repeat" type="password" placeholder="重复密码">
          <template #prefix>
            <el-icon><Lock/></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="email">
        <el-input v-model="form.email" type="email" placeholder="邮箱">
          <template #prefix>
            <el-icon><Message/></el-icon>
          </template>
        </el-input>
      </el-form-item>
    </el-form>
    <div style="margin-top: 50px;">




    </div>
    <div style="margin-top: 10px">

    </div>
    <div style="margin-top: 50px;">
      <el-button  type="warning" style="width: 200px;" @click="register">注册</el-button>
    </div>
    <div style="margin-top: 50px;">
      <el-link @click="router.push('/login')" style="width: 200px;">已有账号？登录</el-link>
    </div>
 </div>
</template>

<style scoped>

</style>