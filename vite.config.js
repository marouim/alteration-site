import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    host: true, // listen on all addresses
    allowedHosts: true // allow any hostname (useful behind proxies/containers)
  }
})
