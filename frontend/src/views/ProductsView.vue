<template>
  <div>
    <h3>商品列表</h3>
    <el-table :data="products" v-loading="loading">
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="stock" label="库存" />
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const products = ref([])
const loading = ref(false)

const load = async () => {
  loading.value = true
  try {
    const { data } = await axios.get('/api/products')
    products.value = data.data
  } finally {
    loading.value = false
  }
}

onMounted(load)
</script>
