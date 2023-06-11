
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
// 导入elementplus css
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/dark/css-vars.css'
import * as echarts from 'echarts';
import axios from "axios";
import { useDark, useToggle } from '@vueuse/core'

const isDark = useDark()
const toggleDark = useToggle(isDark)


axios.defaults.baseURL = 'http://localhost:8080'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.config.globalProperties.echarts = echarts;
app.mount('#app')
