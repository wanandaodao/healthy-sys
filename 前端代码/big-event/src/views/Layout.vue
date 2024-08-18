<script setup>
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
import {userInfoService} from '@/api/user.js'
import userInfoStore from '@/stores/userInfo.js'
import { useRouter} from 'vue-router'
import { ElMessage} from 'element-plus';
import {useTokenStore} from '@/stores/token.js'
const tokenStore = useTokenStore();
const router =useRouter()
const userInfo = userInfoStore();
const getUserInfo = async ()=>{

    //获取当前登录的用户的个人信息
    let result = await userInfoService();

    //将用户信息存储在pinia中
    userInfo.setInfo(result.data)
}
getUserInfo();

const handleCommand= (command)=>{
    if(command ==='logout'){
        //退出登录
        ElMessage.success('退出成功')
        //清除token和pinia中存储的个人信息
        tokenStore.removeToken();
        userInfo.removeInfo();
        //跳转到登录页面
        router.push('/login')
    }else{
        //跳转对应路由页面
        router.push('/user/'+command)
    }
}

</script>

<template>
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"
                router>
                <el-menu-item index="/home">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>首页</span>
                </el-menu-item>
                <el-menu-item index="/doctor/manage" v-if="userInfo.info.role === 'admin'">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>管理陪诊师</span>
                </el-menu-item>
                <el-menu-item index="/serviceType">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>预约陪诊</span>
                </el-menu-item>
                <el-menu-item index="/appointmented">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>已预约信息</span>
                </el-menu-item>
                <el-menu-item index="/patientInfo" v-if="userInfo.info.role === 'doctor' || userInfo.info.role === 'admin'">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>已预约患者信息</span>
                </el-menu-item>
                <el-menu-item index="/article/Manage">
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>兼职陪诊师</span>
                </el-menu-item>
                <el-sub-menu >
                    <template #title>
                        <el-icon>
                            <UserFilled />
                        </el-icon>
                        <span>个人中心</span>
                    </template>
                    <el-menu-item index="/user/info">
                        <el-icon>
                            <User />
                        </el-icon>
                        <span>基本资料</span>
                    </el-menu-item>
                    <el-menu-item index="/user/avatar">
                        <el-icon>
                            <Crop />
                        </el-icon>
                        <span>更换头像</span>
                    </el-menu-item>
                    <el-menu-item index="/user/resetPassword">
                        <el-icon>
                            <EditPen />
                        </el-icon>
                        <span>重置密码</span>
                    </el-menu-item>
                </el-sub-menu>
            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>
            <!-- 头部区域 -->
            <el-header>
                <div><strong>欢迎您: {{userInfo.info.nickname}}</strong></div>
                <!-- 下拉菜单
                command：条目被点击后触发 -->
                <el-dropdown placement="bottom-end" @command="handleCommand">
                    <span class="el-dropdown__box">
                        <el-avatar :src="userInfo.info.userPic?userInfo.info.userPic:avatar" />
                        <el-icon>
                            <CaretBottom />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
                            <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
                            <el-dropdown-item command="resetPassword" :icon="EditPen">重置密码</el-dropdown-item>
                            <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>
            <!-- 中间区域 -->
            <el-main>
                <router-view></router-view>
            </el-main>
            <!-- 底部区域 -->
            <el-footer>©2024 Created by 叨叨</el-footer>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

        &__logo {
            height: 120px;
            background: url('@/assets/icon.png') no-repeat center / 120px auto;
        }

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>