<script setup>
import { ref } from 'vue'
import {appointmentedService} from '@/api/patient.js'
const appointmentedInfo = ref([
    {
        "name": '',
        "age": '',
        "visitTime":'',
        "doctorName":'',
        "doctorPhone":'',
        "serviceType":''
    },
])

//获取当前用户已预约信息
const getAppointmentedInfo = async ()=>{
    let result = await appointmentedService();
    appointmentedInfo.value = result.data
}
getAppointmentedInfo();
</script>

<template>
    <el-table :data="appointmentedInfo" style="width: 100%">
        <el-table-column prop="name" label="患者姓名" width="187" />
        <el-table-column prop="age" label="患者年龄" width="187" />
        <el-table-column prop="visitTime" label="就诊时间" width="187" />
        <el-table-column prop="doctorName" label="陪诊师姓名" width="187" />
        <el-table-column prop="doctorPhone" label="陪诊师电话" width="187" />
        <el-table-column prop="serviceType" label="陪诊类型" width="187" />
    </el-table>
</template>

<style></style>