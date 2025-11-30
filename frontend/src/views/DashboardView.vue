<template>
  <div>
    <el-alert title="欢迎来到管理后台" type="success" show-icon :closable="false" />
    <el-space direction="vertical" style="margin-top: 20px">
      <el-button type="primary" @click="$router.push('/products')">查看商品</el-button>
      <el-button @click="$router.push('/error')">查看错误页面</el-button>
    </el-space>
  </div>
</template>
