const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    client: {
      overlay: false
    },
    proxy: {  
      '/api': {  
        target: 'http://localhost:8080', // 实际请求地址  
        changeOrigin: true,  
        pathRewrite: {  
          '^/api': '' // 移除路径中的 /api  
        }  
      }  
    }  
  }
})
