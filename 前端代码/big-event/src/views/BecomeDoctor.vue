<script setup>
import { ref } from 'vue'
import {departmentListService,addDoctorService} from '@/api/doctor.js'
import { ElMessage } from 'element-plus';
import userInfoStore from '@/stores/userInfo.js'
const userInfo = userInfoStore();
const form = ref({
  name: '',
  age: '',
  phone: '',
  idCard: '',
  departmentId:'',
  gender: ''
})
const department = ref([
    {
        "id":1,
        "name":"儿科"
    }, 
    {
        "id":2,
        "name":"内科"
    },
    {
        "id":3,
        "name":"外科"
    }
])

const onSubmit = async() => {
  //成为陪诊师
  let result = await addDoctorService(form.value);
  ElMessage.success('申请成功')
}

//获取所有科室信息用于下拉列表展示
const getDepartment = async()=>{
    let result = await departmentListService();
    department.value = result.data;
}

getDepartment();
</script>
<template>
    <el-form :model="form" label-width="auto" style="max-width: 600px">

        <el-form-item label="姓名">
            <el-input v-model="form.name" />
        </el-form-item>

        <el-form-item label="年龄">
            <el-input v-model="form.age" />
        </el-form-item>

        <el-form-item label="电话">
            <el-input v-model="form.phone" />
        </el-form-item>

        <el-form-item label="身份证号">
            <el-input v-model="form.idCard" />
        </el-form-item>

        <el-form-item label="科室">
            <el-select v-model="form.departmentId" placeholder="请选择你的科室">
                <el-option
                    v-for="c in department"
                    :key = "c.id"
                    :label = "c.name"
                    :value="c.id">
                </el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="性别">
            <el-radio-group v-model="form.gender">
                <el-radio value="男">男</el-radio>
                <el-radio value="女">女</el-radio>
            </el-radio-group>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="onSubmit">成为陪诊师</el-button>
            <el-button>取消</el-button>
        </el-form-item>
    </el-form>
</template>
<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>
