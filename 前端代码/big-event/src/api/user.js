
//导入request.js请求工具
import request from '@/utils/request.js'

//提供调用注册接口的函数
export const userRegisterService = (registerData)=>{

    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key]);
    }
    return request.post('/user/register',params)
}

//提供调用登录接口的函数
export const userLoginService = (loginData)=>{
    const params = new URLSearchParams()
    for(let key in loginData){
        params.append(key,loginData[key]);
    }
    return request.post('/user/login',params);
}

//获取用户基本个人信息
export const userInfoService = ()=>{
    return request.get('/user/userInfo')
}

export const updateUserInfoService = (userData)=>{
    return request.put('/user/update',userData)
}

export const updateUserAvatarService = (avatarUrl)=>{
    const params = new URLSearchParams();
    params.append('avatarUrl',avatarUrl)
    return request.patch('/user/updateAvatar',params)
}

export const updateUserPasswordService = (userInfo)=>{
    return request.put('/user/password',userInfo)
}