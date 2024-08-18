<template>
    <div class="backimg" style="height: 100vh ;display: flex ;
   align-items: center ;justify-content: center">
        <div style="display: flex ;  width: 60% ;border-radius: 10px ; overflow:hidden">
            <div style="flex: 1; display:flex ; align-items:center ; justify-content: center">

                <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerData"
                    :rules="rules" style="width: 40%">
                    <div
                        style="font-size:20px ; font-weight:bold ; text-align:center ; margin-bottom:20px ;margin-top:20px">
                        注册</div>

                    <el-form-item prop="username">
                        <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码"
                            v-model="registerData.password"></el-input>
                    </el-form-item>
                    <el-form-item prop="rePassword">
                        <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码"
                            v-model="registerData.rePassword"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button class="testbutton" type="primary" auto-insert-space @click="register" style="width:100%">
                            注册
                        </el-button>
                        
                    
                    </el-form-item>
                    <el-form-item class="flex">
                        <el-link type="info" :underline="false" @click="isRegister = false; clearRegisterData()">
                            ← 返回
                        </el-link>
                    </el-form-item>
                </el-form>

                <el-form ref="form" size="large" autocomplete="off" v-else :model="registerData" :rules="rules"
                    style="width: 40%">
                    <div style="font-size:20px ; font-weight:bold ; text-align:center ; margin-bottom:20px">登录</div>

                    <el-form-item prop="username">
                        <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"
                            v-model="registerData.password"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button class="testbutton" type="primary" auto-insert-space @click="login"
                            style="width:100%">登录</el-button>
                        
                    </el-form-item>
                    <el-form-item class="flex">
                        <el-link type="info" :underline="false" @click="isRegister = true; clearRegisterData()">
                            注册 →
                        </el-link>
                    </el-form-item>
                </el-form>

            </div>
        </div>
    </div>
</template>

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

const checkRePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== registerData.value.password) {
        callback(new Error('两次密码不一致'))
    } else {
        callback()
    }
}

//表单校验规则
const rules = {
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 5, max: 16, message: '长度为5~16位', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 5, max: 16, message: '长度为5~16位', trigger: 'blur' }
    ],
    rePassword: [
        { validator: checkRePassword, trigger: 'blur' }
    ]
}

const clearRegisterData = () => {
    //清空用户数据
    registerData.value = {
        username: '',
        password: '',
        rePassword: ''
    }
}

import { userRegisterService, userLoginService } from '@/api/user.js'
import { useTokenStore } from '@/stores/token.js'
const tokenStore = useTokenStore();

//调用后台接口完成注册
const register = async () => {
    //后端响应结构
    let result = await userRegisterService(registerData.value)
    //alert('注册成功')
    ElMessage.success('注册成功')
}
const login = async () => {
    let result = await userLoginService(registerData.value)
    //alert('登录成功')
    ElMessage.success('登录成功')

    //把得到的token存储到token中
    tokenStore.setToken(result.data)

    //页面跳转到主页面
    router.push('/')
}
</script>

<style>
.backimg {
    background: url('@/assets/image.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    padding: 0px;
    width: 100%;
    height: 15%;
    top: 0;
    left: 0;
}

.testbutton {
  display: block;
  font-family: helvetica;
  color: #000;
  font-size: 16px;
  font-weight: bold;
  box-shadow: 9px 8px 0px -1px#BEE2F9, 9px 8px 0px 1px #000;
  padding: 12px 25px;
  border-radius: 11px;
  border: 2px solid #000;
  background: linear-gradient(to top, #63B8EE, #468CCF);
  transition: .3s;
  cursor: pointer;
}

.testbutton:hover {
  transition: .3s;
  transform: translate(-.3em, -.3em);
  box-shadow: 14px 13px 0px -1px #BEE2F9, 14px 13px 0px 1px #000;
  border: 2px solid #000;
}

.testbutton:active {
  transition: .3s;
  transform: translate(.6em, .6em);
  box-shadow: 0px 0px 0px -1px #BEE2F9, 0px 0px 0px 0.1px#000;
}

</style>