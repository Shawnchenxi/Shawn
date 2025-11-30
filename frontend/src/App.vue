<template>
  <el-container style="min-height: 100vh">
    <el-header style="display: flex; justify-content: space-between; align-items: center">
      <h2>Shawn 百货商城</h2>
      <div>
        <el-button type="text" @click="go('login')">登录</el-button>
        <el-button type="text" @click="go('dashboard')">管理后台</el-button>
      </div>
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
const go = (name) => router.push({ name })
</script>
