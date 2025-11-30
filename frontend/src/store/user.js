import { defineStore } from 'pinia'
import axios from 'axios'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    username: ''
  }),
  actions: {
    async login(username, password) {
      const { data } = await axios.post('/api/auth/login', { username, password })
      this.token = data.data
      this.username = username
      localStorage.setItem('token', this.token)
    },
    logout() {
      this.token = ''
      this.username = ''
      localStorage.removeItem('token')
    }
  }
})
