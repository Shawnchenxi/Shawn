<template>
  <div class="page-wrapper">
    <div class="content-card">
      <div class="card-header d-flex justify-between align-center mb-3">
        <div class="title-group">
          <p class="subtitle">运营后台 · 用户管理</p>
          <h3>用户管理</h3>
        </div>
        <div class="actions">
          <button class="btn" @click="refreshData">
            <span class="btn-icon">⟳</span>
            刷新
          </button>
          <button class="btn btn-light" @click="exportData">
            <span class="btn-icon">⇩</span>
            导出
          </button>
        </div>
      </div>

      <div class="table-wrapper">
        <table class="data-table">
          <thead>
            <tr>
              <th>用户ID</th>
              <th>用户名</th>
              <th>用户类型</th>
              <th>状态</th>
              <th>注册时间</th>
              <th class="text-right">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, i) in userlist" :key="item.id">
              <td>{{ item.id }}</td>
              <td class="username-cell">
                <div class="avatar" :data-initial="item.name?.[0] || 'U'"></div>
                <div>
                  <div class="name">{{ item.name }}</div>
                  <div class="muted">{{ item.email }}</div>
                </div>
              </td>
              <td>
                <span :class="['badge', 'badge-soft', typeColor[item.type] || 'badge-blue']">
                  {{ item.type }}
                </span>
              </td>
              <td>
                <span :class="['badge', item.status === '正常' ? 'badge-success' : 'badge-warning']">
                  {{ item.status }}
                </span>
              </td>
              <td>{{ item.registerTime }}</td>
              <td class="text-right">
                <router-link :to="'/home/users/' + item.id" class="btn-link">详情</router-link>
                <button class="btn-link ml-1" @click.prevent="openEditDialog(item)">编辑</button>
                <button
                  class="btn-link ml-1"
                  :class="item.status === '正常' ? 'text-warning' : 'text-success'"
                  @click.prevent="toggleUserStatus(item)"
                >
                  {{ item.status === '正常' ? '禁用' : '启用' }}
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div v-if="showEditDialog" class="modal-overlay" @click="closeEditDialog">
      <div class="modal-content" @click.stop>
        <div class="modal-header">
          <div>
            <p class="subtitle">用户中心</p>
            <h4>编辑用户</h4>
          </div>
          <button class="btn-close" @click="closeEditDialog">×</button>
        </div>

        <div class="modal-body">
          <div class="form-row">
            <div class="form-group">
              <label>用户ID</label>
              <input type="text" class="form-control" v-model="editingUser.id" disabled>
            </div>
            <div class="form-group">
              <label>用户名</label>
              <input type="text" class="form-control" v-model="editingUser.name" disabled>
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>用户类型</label>
              <select class="form-control" v-model="editingUser.type">
                <option value="管理员">管理员</option>
                <option value="VIP用户">VIP用户</option>
                <option value="普通用户">普通用户</option>
                <option value="测试用户">测试用户</option>
              </select>
            </div>
            <div class="form-group">
              <label>状态</label>
              <select class="form-control" v-model="editingUser.status">
                <option value="正常">正常</option>
                <option value="禁用">禁用</option>
              </select>
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label>邮箱</label>
              <input type="email" class="form-control" v-model="editingUser.email" disabled>
            </div>
            <div class="form-group">
              <label>手机号</label>
              <input type="tel" class="form-control" v-model="editingUser.phone" disabled>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button class="btn btn-light mr-2" @click="closeEditDialog">取消</button>
          <button class="btn" @click="saveUser">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'

const typeColor = {
  管理员: 'badge-orange',
  VIP用户: 'badge-purple',
  普通用户: 'badge-green',
  测试用户: 'badge-blue'
}

const userlist = ref([
  { id: '1001', name: 'admin', type: '管理员', status: '正常', registerTime: '2023-01-15', email: 'admin@example.com', phone: '13800138001' },
  { id: '1002', name: '张三', type: '普通用户', status: '正常', registerTime: '2023-02-20', email: 'zhangsan@example.com', phone: '13800138002' },
  { id: '1003', name: '李四', type: '普通用户', status: '正常', registerTime: '2023-03-10', email: 'lisi@example.com', phone: '13800138003' },
  { id: '1004', name: '王五', type: 'VIP用户', status: '正常', registerTime: '2023-04-05', email: 'wangwu@example.com', phone: '13800138004' },
  { id: '1005', name: '赵六', type: '普通用户', status: '禁用', registerTime: '2023-05-12', email: 'zhaoliu@example.com', phone: '13800138005' },
  { id: '1006', name: '测试用户', type: '测试用户', status: '正常', registerTime: '2023-06-18', email: 'test@example.com', phone: '13800138006' }
])

const showEditDialog = ref(false)
const editingUser = reactive({
  id: '',
  name: '',
  type: '',
  status: '',
  email: '',
  phone: ''
})

const openEditDialog = (user) => {
  Object.assign(editingUser, user)
  showEditDialog.value = true
}

const closeEditDialog = () => {
  showEditDialog.value = false
  Object.keys(editingUser).forEach((key) => {
    editingUser[key] = ''
  })
}

const saveUser = () => {
  const index = userlist.value.findIndex((user) => user.id === editingUser.id)
  if (index !== -1) {
    userlist.value[index] = { ...editingUser }
    alert('用户信息更新成功')
    closeEditDialog()
  }
}

const toggleUserStatus = (user) => {
  user.status = user.status === '正常' ? '禁用' : '正常'
  const action = user.status === '正常' ? '启用' : '禁用'
  alert(`已${action}用户 ${user.name}`)
}

const refreshData = () => {
  console.log('刷新数据')
}

const exportData = () => {
  console.log('导出数据')
}
</script>

<style scoped>
:global(body) {
  background: radial-gradient(circle at 10% 20%, #f7faff 0%, #f0f5ff 35%, #f9fbff 100%);
  font-family: 'Inter', 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
}

.page-wrapper {
  padding: 24px;
  max-width: 1200px;
  margin: 0 auto;
}

.content-card {
  background: #ffffff;
  border-radius: 16px;
  box-shadow: 0 18px 45px rgba(15, 23, 42, 0.08);
  padding: 20px 24px 28px;
  border: 1px solid #e9eef5;
}

.card-header {
  border-bottom: 1px solid #eef2f7;
  padding-bottom: 16px;
}

.subtitle {
  margin: 0;
  color: #8892a6;
  font-size: 13px;
}

h3 {
  margin: 4px 0 0;
  font-size: 22px;
  color: #1f2937;
}

.actions {
  display: flex;
  gap: 10px;
}

.d-flex {
  display: flex;
}

.justify-between {
  justify-content: space-between;
}

.align-center {
  align-items: center;
}

.mb-3 {
  margin-bottom: 16px;
}

.btn {
  border: none;
  background: linear-gradient(120deg, #4f46e5, #8b5cf6);
  color: #ffffff;
  border-radius: 10px;
  padding: 9px 14px;
  cursor: pointer;
  font-weight: 600;
  font-size: 14px;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  transition: transform 0.15s ease, box-shadow 0.15s ease;
  box-shadow: 0 12px 25px rgba(79, 70, 229, 0.25);
}

.btn:hover {
  transform: translateY(-1px);
  box-shadow: 0 16px 30px rgba(79, 70, 229, 0.28);
}

.btn:active {
  transform: translateY(0);
}

.btn.btn-light {
  background: #f8fafc;
  color: #0f172a;
  box-shadow: none;
  border: 1px solid #e5e7eb;
}

.btn-icon {
  font-size: 12px;
}

.table-wrapper {
  margin-top: 16px;
  overflow: hidden;
  border-radius: 12px;
  border: 1px solid #e9eef5;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  background: #ffffff;
}

.data-table th,
.data-table td {
  padding: 14px 16px;
  text-align: left;
  font-size: 14px;
  color: #1f2937;
}

.data-table thead {
  background: #f8fafc;
}

.data-table th {
  font-weight: 700;
  color: #475569;
}

.data-table tbody tr {
  border-top: 1px solid #f1f5f9;
  transition: background 0.1s ease;
}

.data-table tbody tr:hover {
  background: #f9fafb;
}

.text-right {
  text-align: right;
}

.username-cell {
  display: flex;
  align-items: center;
  gap: 10px;
}

.avatar {
  width: 36px;
  height: 36px;
  border-radius: 10px;
  background: linear-gradient(140deg, #e0e7ff, #ede9fe);
  color: #4338ca;
  font-weight: 700;
  display: grid;
  place-items: center;
  position: relative;
  overflow: hidden;
}

.avatar::after {
  content: attr(data-initial);
  text-transform: uppercase;
}

.name {
  font-weight: 600;
  color: #111827;
}

.muted {
  color: #94a3b8;
  font-size: 12px;
}

.badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 4px 10px;
  border-radius: 12px;
  font-weight: 600;
  font-size: 12px;
  letter-spacing: 0.01em;
}

.badge-success {
  background: #ecfdf3;
  color: #166534;
  border: 1px solid #bbf7d0;
}

.badge-warning {
  background: #fff7ed;
  color: #c2410c;
  border: 1px solid #fed7aa;
}

.badge-soft {
  border: 1px solid transparent;
}

.badge-orange {
  background: #fff7ed;
  color: #c2410c;
  border-color: #fed7aa;
}

.badge-purple {
  background: #f5f3ff;
  color: #5b21b6;
  border-color: #ddd6fe;
}

.badge-green {
  background: #ecfdf3;
  color: #166534;
  border-color: #bbf7d0;
}

.badge-blue {
  background: #eff6ff;
  color: #1d4ed8;
  border-color: #bfdbfe;
}

.btn-link {
  border: none;
  background: none;
  color: #4f46e5;
  text-decoration: none;
  font-size: 13px;
  cursor: pointer;
  padding: 0;
}

.btn-link:hover {
  text-decoration: underline;
}

.ml-1 {
  margin-left: 8px;
}

.text-warning {
  color: #f59e0b;
}

.text-success {
  color: #16a34a;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(15, 23, 42, 0.45);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
  padding: 16px;
  backdrop-filter: blur(2px);
}

.modal-content {
  background: #ffffff;
  border-radius: 14px;
  width: 96%;
  max-width: 620px;
  box-shadow: 0 24px 60px rgba(15, 23, 42, 0.2);
  overflow: hidden;
  border: 1px solid #e2e8f0;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 18px 20px 14px;
  border-bottom: 1px solid #f1f5f9;
}

.modal-header h4 {
  margin: 4px 0 0;
  font-size: 18px;
  font-weight: 700;
  color: #111827;
}

.btn-close {
  background: #f3f4f6;
  border: none;
  font-size: 18px;
  cursor: pointer;
  color: #6b7280;
  padding: 6px 10px;
  border-radius: 10px;
  transition: background 0.15s ease, transform 0.15s ease;
}

.btn-close:hover {
  background: #e5e7eb;
  transform: translateY(-1px);
}

.modal-body {
  padding: 20px;
  background: linear-gradient(180deg, #f8fafc, #ffffff 30%);
}

.modal-footer {
  padding: 14px 20px;
  border-top: 1px solid #f1f5f9;
  text-align: right;
}

.form-row {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
  gap: 16px;
  margin-bottom: 8px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  margin-bottom: 6px;
  font-weight: 600;
  color: #334155;
  font-size: 13px;
}

.form-control {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #e2e8f0;
  border-radius: 10px;
  font-size: 14px;
  background: #ffffff;
  transition: border-color 0.15s ease, box-shadow 0.15s ease;
}

.form-control:focus {
  outline: none;
  border-color: #6366f1;
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.12);
}

.form-control:disabled {
  background: #f8fafc;
  color: #94a3b8;
  cursor: not-allowed;
}

@media (max-width: 768px) {
  .content-card {
    padding: 16px;
  }

  .data-table th,
  .data-table td {
    padding: 12px 10px;
  }

  .actions {
    gap: 6px;
  }
}
</style>
