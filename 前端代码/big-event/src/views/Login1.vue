<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)
const router = useRouter()
const registerData = ref({
    username: '',
    password: '',
    rePassword: ''
})

const checkRePassword =(rule,value,callback)=>{
    if(value ===''){
        callback(new Error('请再次输入密码'))
    }else if(value !== registerData.value.password){
        callback(new Error('两次密码不一致'))
    }else{
        callback()
    }
}

//表单校验规则
const rules = {
    username: [
        {required:true,message:'请输入用户名',trigger:'blur'},
        {min:5,max:16,message:'长度为5~16位',trigger:'blur'}
    ],
    password: [
        {required:true,message:'请输入密码',trigger:'blur'},
        {min:5,max:16,message:'长度为5~16位',trigger:'blur'}
    ],
    rePassword: [
        {validator:checkRePassword,trigger:'blur'}
    ]
}

const clearRegisterData = () =>{
    //清空用户数据
    registerData.value = {
        username: '',
        password: '',
        rePassword: ''
    }
}

import {userRegisterService,userLoginService} from '@/api/user.js'
import {useTokenStore} from '@/stores/token.js'
const tokenStore = useTokenStore();

//调用后台接口完成注册
const register = async ()=>{
    //后端响应结构
    let result = await userRegisterService(registerData.value)
    //alert('注册成功')
    ElMessage.success('注册成功')
}
const login = async ()=>{
    let result = await userLoginService(registerData.value)
    //alert('登录成功')
    ElMessage.success('登录成功')

    //把得到的token存储到token中
    tokenStore.setToken(result.data)
    
    //页面跳转到主页面
    router.push('/')
}

</script>

<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">
            <!-- 注册表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerData" :rules="rules">
                <el-form-item>
                    <h1>注册</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
                </el-form-item>
                <el-form-item prop="rePassword">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码" v-model="registerData.rePassword"></el-input>
                </el-form-item>
                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register">
                        注册
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false;clearRegisterData()">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>
            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-else :model="registerData" :rules="rules">
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
                </el-form-item>
                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>记住我</el-checkbox>
                        <el-link type="primary" :underline="false">忘记密码？</el-link>
                    </div>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true;clearRegisterData()">
                        注册 →
                    </el-link>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
    height: 100vh;
    background-color: #fff;

    .bg {
        margin: 0px;
        background:url('../assets/login_bg.jpg') no-repeat;
        background-size: 100% 100%;
        background-attachment: fixed;
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}
</style>
