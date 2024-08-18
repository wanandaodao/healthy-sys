<script setup>
import { ref } from 'vue'
import {updateUserPasswordService} from '@/api/user.js'
import {ElMessage} from 'element-plus'
import userInfoStore from '@/stores/userInfo.js'
const userInfostore = userInfoStore();
const userInfo = ref({
    id: userInfostore.info.id,
    password: '',
    rePassword: ''
})

const checkRePassword =(rule,value,callback)=>{
    if(value ===''){
        callback(new Error('请再次输入密码'))
    }else if(value !== userInfo.value.password){
        callback(new Error('两次密码不一致'))
    }else{
        callback()
    }
}

const rules = {
    password: [
        {required:true,message:'请输入密码',trigger:'blur'},
        {min:5,max:16,message:'长度为5~16位',trigger:'blur'}
    ],
    rePassword: [
        {validator:checkRePassword,trigger:'blur'}
    ]
}

const updatePassword = async ()=>{
    let result = await updateUserPasswordService(userInfo.value);
    ElMessage.success('修改成功')
}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>修改密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="20">
                <el-form :model="userInfo" :rules="rules" label-width="150px" size="large">
                    <el-form-item label="请输入新密码" prop="password">
                        <el-input v-model="userInfo.password" type="password"></el-input>
                    </el-form-item>
                    <el-form-item label="请重新输入新密码" prop="rePassword">
                        <el-input v-model="userInfo.rePassword" type="password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updatePassword">确认修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>
