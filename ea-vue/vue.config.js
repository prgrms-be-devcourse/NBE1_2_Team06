const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  lintOnSave: false, // 여기 추가하시면 됩니다
  transpileDependencies: true,
  devServer: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/api/v1',
        },
      },
    },
  },
});
