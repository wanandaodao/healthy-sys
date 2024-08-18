import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './assets/main.scss'
import router from '@/router'
import { createPinia } from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'
import locale from 'element-plus/dist/locale/zh-cn.js'//element中文包

const pinia = createPinia()
pinia.use(createPersistedState())
createApp(App).use(ElementPlus,{locale}).use(router).use(pinia).mount('#app');
