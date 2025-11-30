import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import DashboardView from '../views/DashboardView.vue'
import ProductsView from '../views/ProductsView.vue'
import ErrorView from '../views/ErrorView.vue'

const routes = [
  { path: '/', redirect: '/dashboard' },
  { path: '/login', name: 'login', component: LoginView },
  { path: '/dashboard', name: 'dashboard', component: DashboardView },
  { path: '/products', name: 'products', component: ProductsView },
  { path: '/error', name: 'error', component: ErrorView }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
