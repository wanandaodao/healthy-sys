import request from '@/utils/request.js'

//查询当前所有的科室信息
export const departmentListService = ()=>{
    return request.get('/doctor/department')
}


//申请成为陪诊师
export const addDoctorService = (doctor)=>{
    return request.put('/doctor',doctor)
}

//申请成为陪诊师
export const addDoctorService1 = (doctor)=>{
    return request.put('/doctor/add',doctor)
}

//获取所有陪诊师信息
export const doctorListService = ()=>{
    return request.get('/doctor')
}

export const updateDoctorService = (doctor)=>{
    return request.post('/doctor',doctor)
}

//删除陪诊师信息
export const deleteDoctorService = (id)=>{
    return request.delete('/doctor?id='+id)
}