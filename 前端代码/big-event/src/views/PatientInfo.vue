<script setup>
import { ref } from 'vue';
import {patientInfoService} from '@/api/patient.js'
const patientInfo = ref([
    {
        "name":'',
        "age":'',
        "phone":'',
        "cases":'',
        "description":'',
        "address":'无',
        "serviceType":''
    }
])

//获取当前已预约患者信息
const getPatientInfo = async ()=>{
    let result = await patientInfoService();
    patientInfo.value = result.data;
}
getPatientInfo();
</script>

<template>
    <el-table :data="patientInfo" style="width: 100%">
        <el-table-column prop="name" label="患者姓名" width="100" />
        <el-table-column prop="age" label="年龄" width="100" />
        <el-table-column prop="phone" label="联系电话" width="100" />
        <el-table-column prop="cases" label="特殊病例" width="300" />
        <el-table-column prop="description" label="服务需求" width="180" />
        <el-table-column prop="address" label="接送地址" width="180" />
        <el-table-column prop="serviceType" label="服务类型" width="180" />
    </el-table>
</template>

<style></style>