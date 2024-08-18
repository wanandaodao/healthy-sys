<script setup>
import {
    Check,
    Delete,
    Edit,
    Message,
    Search,
    Star,
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { doctorListService } from '@/api/doctor.js'
import { appointmentService } from '@/api/patient.js'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import userInfoStore from '@/stores/userInfo.js'
const userInfo = userInfoStore();
const router = useRouter();
const dialogVisible = ref(false)
const doctors = ref([
    {
        "id": 3,
        "name": "叨叨",
        "age": "",
        "gender": "男",
        "departmentName": ""
    },
])
const form = ref({
    name: '',
    age: '',
    phone: '',
    gender: '',
    description: '',
    cases: [

    ],
    visitTime: '',
    doctorId: '',
    address: '',
    serviceType: userInfo.info.serviceType
})

//获取所有陪诊师信息
const getDoctor = async () => {
    let result = await doctorListService();
    doctors.value = result.data
}
getDoctor();

const onSubmit = async (row) => {
    let result = await appointmentService(form.value);
    ElMessage.success('预约成功')
    dialogVisible.value = false;
    form.value.name = ''
    form.value.age = ''
    form.value.gender = ''
    form.value.description = ''
    form.value.cases = []
    form.value.visitTime = ''
    form.value.doctorId = ''
    form.value.phone = ''
    form.value.address=''
}

const showDialog = (row) => {
    dialogVisible.value = true
    form.value.doctorId = row.id
}
const back = () => {
    router.back('/serviceType');
}
</script>
<template>
    <el-dialog v-model="dialogVisible" title="请填写就诊人信息" width="30%">
        <el-form :model="form" label-width="auto" style="max-width: 600px">

            <el-form-item label="姓名">
                <el-input v-model="form.name" />
            </el-form-item>

            <el-form-item label="年龄">
                <el-input v-model="form.age" />
            </el-form-item>

            <el-form-item label="联系电话">
                <el-input v-model="form.phone" />
            </el-form-item>

            <el-form-item label="详细地址"
                v-if="userInfo.info.serviceType === '尊享陪诊' || userInfo.info.serviceType === '全程陪诊'">
                <el-input v-model="form.address" />
            </el-form-item>

            <el-form-item label="性别">
                <el-radio-group v-model="form.gender">
                    <el-radio value="男">男</el-radio>
                    <el-radio value="女">女</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="就诊时间">
                <el-col :span="11">
                    <el-date-picker v-model="form.visitTime" type="datetime" placeholder="Pick a Date"
                        format="YYYY/MM/DD hh:mm:ss" value-format="YYYY-MM-DD hh:mm:ss " />
                </el-col>
            </el-form-item>

            <el-form-item label="特殊病例">
                <el-checkbox-group v-model="form.cases">
                    <el-checkbox value="80岁以上" name="type">
                        80岁以上
                    </el-checkbox>
                    <el-checkbox value="就诊人骨折且未进行医护处理" name="type">
                        就诊人骨折且未进行医护处理
                    </el-checkbox>
                    <el-checkbox value="就诊人不能行走且无助走工具或轮椅" name="type">
                        就诊人不能行走且无助走工具或轮椅
                    </el-checkbox>
                    <el-checkbox value="冠状动脉粥样硬化心脏病患者" name="type">
                        冠状动脉粥样硬化心脏病患者
                    </el-checkbox>
                    <el-checkbox value="严重冠心病患者" name="type">
                        严重冠心病患者
                    </el-checkbox>
                    <el-checkbox value="有脑血管疾病" name="type">
                        有脑血管疾病
                    </el-checkbox>
                    <el-checkbox value="就诊人为精神病患者" name="type">
                        就诊人为精神病患者
                    </el-checkbox>
                </el-checkbox-group>
            </el-form-item>

            <el-form-item label="服务需求">
                <el-input v-model="form.description" type="textarea" />
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">确认</el-button>
                <el-button @click="dialogVisible = false">取消</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>陪诊师</span>
                <!-- <input type="text" v-model="form.serviceType"> -->
                <!-- <div class="extra">
                    
                    <button class="cssbuttons-io-button" @click="back">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="10" height="15">
                            <path fill="none" d="M0 0h24v24H0z"></path>
                            <path fill="currentColor" d="M11 11V5h2v6h6v2h-6v6h-2v-6H5v-2z"></path>
                        </svg>
                        <span>返回</span>
                    </button>
                </div> -->


                <button class="cbutton" @click="back">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                        stroke="currentColor" class="w-6 h-6">
                        <path stroke-linecap="round" stroke-linejoin="round"
                            d="M4.5 12h15m0 0l-6.75-6.75M19.5 12l-6.75 6.75"></path>
                    </svg>
                    <div class="text">
                        返回
                    </div>
                </button>

            </div>
        </template>
        <el-table :data="doctors" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="年龄" prop="age"></el-table-column>
            <el-table-column label="性别" prop="gender"></el-table-column>
            <el-table-column label="科室" prop="departmentName"></el-table-column>
            <el-table-column label="选择陪诊师" width="100">
                <template #default="{ row }">
                    <el-button type="success" :icon="Check" circle @click="showDialog(row)" />
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="分类名称" prop="categoryName">
                    <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="分类别名" prop="categoryAlias">
                    <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="title == '添加分类' ? addCategory() : editCategory()"> 确认 </el-button>
                </span>
            </template>
        </el-dialog> -->

    </el-card>
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

.cbutton {
    background-color: #ffffff00;
    color: #000000dd;
    width: 8.5em;
    height: 2.9em;
    border: #000000 0.2em solid;
    border-radius: 11px;
    text-align: right;
    transition: all 0.6s ease;
}

.cbutton:hover {
    background-color: #ffffff1d;
    color: #000
}

.cbutton svg {
    width: 1.6em;
    margin: -0.2em 0.8em 1em;
    position: absolute;
    display: flex;
    transition: all 0.6s ease;
}

.cbutton:hover svg {
    transform: translateX(5px);
}

.text {
    margin: 0 1.5em
}
</style>
