<template>
  <el-card class="box">
    <h3>登录</h3>
    <el-form @submit.prevent="onSubmit" label-position="top">
      <el-form-item label="用户名">
        <el-input v-model="form.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password" type="password" />
      </el-form-item>
      <el-button type="primary" @click="onSubmit">登录</el-button>
    </el-form>
  </el-card>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../store/user'
import { ElMessage } from 'element-plus'

const store = useUserStore()
const router = useRouter()
const form = reactive({ username: 'admin', password: 'admin' })

const onSubmit = async () => {
  try {
    await store.login(form.username, form.password)
    ElMessage.success('登录成功')
    router.push({ name: 'dashboard' })
  } catch (e) {
    ElMessage.error('登录失败')
  }
}
</script>

<style scoped>
.box {
  max-width: 360px;
  margin: 80px auto;
}
</style>
