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
import { doctorListService, updateDoctorService, deleteDoctorService } from '@/api/doctor.js'
import userInfoStore from '@/stores/userInfo.js'
import { departmentListService, addDoctorService1 } from '@/api/doctor.js'
import { ElMessage, ElMessageBox } from 'element-plus';
const dialogVisible = ref(false)
const userInfo = userInfoStore();
const title = ref('')
const doctors = ref([
    {
        "name": "",
        "age": "",
        "gender": "",
        "phone": "",
        "idCard": "",
        "departmentName": ""
    },
])
const form = ref({
    name: '',
    age: '',
    phone: '',
    idCard: '',
    departmentId: '',
    gender: ''
})
const department = ref([
    {
        "id": 1,
        "name": "儿科"
    },
    {
        "id": 2,
        "name": "内科"
    },
    {
        "id": 3,
        "name": "外科"
    }
])

//获取所有陪诊师信息
const getDoctor = async () => {
    let result = await doctorListService();
    doctors.value = result.data
}
getDoctor();

const onSubmit = async () => {
    //成为陪诊师
    if (title.value === '添加陪诊师') {
        let result = await addDoctorService1(form.value);
    } else {
        let result = await updateDoctorService(form.value)
    }
    ElMessage.success('操作成功')
    dialogVisible.value = false
    getDoctor();
}

//获取所有科室信息用于下拉列表展示
const getDepartment = async () => {
    let result = await departmentListService();
    department.value = result.data;
}

getDepartment();

const add = () => {
    dialogVisible.value = true;
    title.value = '添加陪诊师'
    form.value.name = ''
    form.value.age = ''
    form.value.phone = ''
    form.value.idCard = ''
    form.value.gender = ''
    form.value.departmentId = ''
    form.value.id = ''
}

//编辑陪诊师信息
const edit = (row) => {
    title.value = '编辑陪诊师'
    dialogVisible.value = true;
    //数据回显
    form.value.name = row.name
    form.value.age = row.age
    form.value.phone = row.phone
    form.value.idCard = row.idCard
    form.value.gender = row.gender
    form.value.departmentId = row.departmentId
    form.value.id = row.id
}

const open = (row) => {
    ElMessageBox.confirm(
        '你确认要删除吗?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            let result = await deleteDoctorService(row.id)
            getDoctor();
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消删除',
            })
        })
}

</script>
<template>

    <el-dialog v-model="dialogVisible" :title="title" width="30%">
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
                    <el-option v-for="c in department" :key="c.id" :label="c.name" :value="c.id">
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
                <el-button type="primary" @click="onSubmit">确认</el-button>
                <el-button @click="dialogVisible = false">取消</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>


    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>陪诊师</span>
                <div class="extra">
                    <!-- <el-button type="primary" round @click="add">添加陪诊师</el-button> -->
                    <!-- <button class="cssbuttons-io-button" @click="add">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="10" height="15">
                            <path fill="none" d="M0 0h24v24H0z"></path>
                            <path fill="currentColor" d="M11 11V5h2v6h6v2h-6v6h-2v-6H5v-2z"></path>
                        </svg>
                        <span>添加陪诊师</span>
                    </button> -->

                    <button type="button" class="cbutton" @click="add">
                        <span class="cbutton__text">添加陪诊师</span>
                        <span class="cbutton__icon"><svg xmlns="http://www.w3.org/2000/svg" width="24"
                                viewBox="0 0 24 24" stroke-width="2" stroke-linejoin="round" stroke-linecap="round"
                                stroke="currentColor" height="24" fill="none" class="svg">
                                <line y2="19" y1="5" x2="12" x1="12"></line>
                                <line y2="12" y1="12" x2="19" x1="5"></line>
                            </svg></span>
                    </button>
                </div>
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
                    <el-button type="primary" :icon="Edit" circle @click="edit(row)" />
                    <el-button type="danger" :icon="Delete" circle @click="open(row)" />
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
  --main-focus: #2d8cf0;
  --font-color: #dedede;
  --bg-color-sub: #222;
  --bg-color: #323232;
  --main-color: #dedede;
  position: relative;
  width: 150px;
  height: 40px;
  cursor: pointer;
  display: flex;
  align-items: center;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
  background-color: var(--bg-color);
  border-radius: 10px;
  overflow: hidden;
}

.cbutton, .cbutton__icon, .cbutton__text {
  transition: all 0.3s;
}

.cbutton .cbutton__text {
  transform: translateX(25px);
  color: var(--font-color);
  font-weight: 600;
}

.cbutton .cbutton__icon {
  position: absolute;
  transform: translateX(109px);
  height: 100%;
  width: 39px;
  background-color: var(--bg-color-sub);
  display: flex;
  align-items: center;
  justify-content: center;
}

.cbutton .svg {
  width: 20px;
  stroke: var(--main-color);
}

.cbutton:hover {
  background: var(--bg-color);
}

.cbutton:hover .cbutton__text {
  color: transparent;
}

.cbutton:hover .cbutton__icon {
  width: 148px;
  transform: translateX(0);
}

.cbutton:active {
  transform: translate(3px, 3px);
  box-shadow: 0px 0px var(--main-color);
}
</style>
