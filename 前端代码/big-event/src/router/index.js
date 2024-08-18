import {createRouter,createWebHashHistory} from 'vue-router'


//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import ArticleCategory from '@/views/Appointment.vue'
import ArticleManage from '@/views/BecomeDoctor.vue'
import UserAvatar from '@/views/UserAvatar.vue'
import UserInfo from '@/views/UserInfo.vue'
import UserResetPassword from '@/views/UserResetPassword.vue'
import Home from '@/views/Home.vue'
import DoctorManage from '@/views/DoctorManage.vue'
import Appointmented from '@/views/AppointmentedInfo.vue'
import PatientInfo from '@/views/PatientInfo.vue'
import ServiceType from '@/views/ServiceType.vue'
//import demo from '@/views/Login.vue'

//定义路由关系
const routes = [
    {path:'/login',component:LoginVue},
    //{path:'/demo',component:demo},
    {path:'/',component:LayoutVue,redirect:'/home',children:[
        {path:'/home',component:Home},
        {path:'/serviceType',component:ServiceType},
        {path:'/article/category',component:ArticleCategory},
        {path:'/patientInfo',component:PatientInfo},
        {path:'/appointmented',component:Appointmented},
        {path:'/doctor/manage',component:DoctorManage},
        {path:'/article/manage',component:ArticleManage},
        {path:'/user/avatar',component:UserAvatar},
        {path:'/user/info',component:UserInfo},
        {path:'/user/resetPassword',component:UserResetPassword},
    ]}
]


//创建路由器
const router = createRouter({
    history:createWebHashHistory(),
    routes:routes
})

//导出路由
export default router