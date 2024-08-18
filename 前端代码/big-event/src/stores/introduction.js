
//定义store
import { defineStore } from "pinia"
import {ref} from 'vue'

export const useIntroductionStore = defineStore('introduction',()=>{
    //定义状态的内容

    const introduction = ref('一个没什么用的健康管理系统')

    //修改token值
    const setIntroduction = (newIntroduction)=>{
        introduction.value = newIntroduction
    }

    //移除token

    const removeIntroduction = ()=>{
        introduction.value = ''
    }

    //返回值为定义的三个函数
    return {
        introduction,setIntroduction,removeIntroduction
    }
},{
    persist:true
})

export default useIntroductionStore;