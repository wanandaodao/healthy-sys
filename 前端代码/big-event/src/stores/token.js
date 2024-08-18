
//定义store
import { defineStore } from "pinia"
import {ref} from 'vue'

export const useTokenStore = defineStore('token',()=>{
    //定义状态的内容

    const token = ref('')

    //修改token值
    const setToken = (newToken)=>{
        token.value = newToken
    }

    //移除token

    const removeToken = ()=>{
        token.value = ''
    }

    //返回值为定义的三个函数
    return {
        token,setToken,removeToken
    }
},{
    persist:true
})