import request from '@/utils/request.js'

//患者预约陪诊 
export const appointmentService = (patient)=>{
    return request.put('/patient',patient)
}

//获取当前预约信息
export const appointmentedService = ()=>{
    return request.get('/patient')
}

//获取当前患者信息
export const patientInfoService = ()=>{
    return request.get('/patient/patientInfo')
}