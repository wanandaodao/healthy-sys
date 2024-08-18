//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus'
import router  from '@/router';
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({baseURL})

import {useTokenStore} from '@/stores/token.js'
//添加请求拦截器
instance.interceptors.request.use(
    (config)=>{
        //请求前的回调函数
        //添加token
        const tokenStore = useTokenStore();
        if(tokenStore.token){
            //如果有token则将token加入到请求头中
            config.headers.Authorization = tokenStore.token
        }
        return config;
    },
    (err)=>{
        //请求错误的回调
        Promise.reject(err)
    }
)
//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        if(result.data.code ===0){
            return result.data;
        }
        
        //alert(result.data.message?result.data.message:'服务异常')
        ElMessage.error(result.data.message?result.data.message:'服务异常')
        return Promise.reject(result.data);//异步的状态转化成失败的状态
    },
    err=>{
        //如果响应码为401说明未登录，需要跳转到登录页面
        if(err.response.status === 401){
            ElMessage.error('请先登录')
            router.push('/login')
        }else{
            ElMessage.error('服务异常')
        }
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;