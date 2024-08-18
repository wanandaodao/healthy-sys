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
import { ElMessage } from 'element-plus'
import { serviceTypetListService } from '@/api/serviceType.js'
import { useRouter } from 'vue-router';
import userInfoStore from '@/stores/userInfo.js'
const userInfo = userInfoStore();
const router = useRouter();

const serviceType = ref([
    {
        "id": 3,
        "type": "",
        "price": "",
        "description": ""
    },
])

//获取所有服务类型

const getServiceType = async () => {
    let result = await serviceTypetListService();
    serviceType.value = result.data;
}
getServiceType();
const xx = ref('')
const choose = (row) => {
    userInfo.info.serviceType = row.type;
    router.push('/article/category')
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>陪诊服务</span>
                <!-- <div class="extra">
                    <el-button type="primary"
                        @click="dialogVisible = true; title = '添加分类'; clearCategoryData()">添加分类</el-button>
                </div> -->
            </div>
        </template>
        <el-table :data="serviceType" style="width: 100%">
            <el-table-column label="序号" width="200" type="index"> </el-table-column>
            <el-table-column label="服务类型" prop="type" width="200"></el-table-column>
            <el-table-column label="价格" prop="price" width="200"></el-table-column>
            <el-table-column label="服务详情" prop="description" width="330"></el-table-column>
            <el-table-column label="请选择服务类型" width="130">
                <template #default="{ row }">
                    <el-button type="success" :icon="Check" circle @click="choose(row)" />
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
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

</style>
