<template>
    <div class="suppliers-container">
        <div class="header">
            <el-button type="primary" @click="dialogVisible = true">
                <el-icon><Plus /></el-icon>添加供应商
            </el-button>
            <el-input
                v-model="searchQuery"
                placeholder="搜索供应商名称"
                class="search-input"
                clearable
                @clear="loadSuppliers"
                @keyup.enter="loadSuppliers">
                <template #prefix>
                    <el-icon><Search /></el-icon>
                </template>
            </el-input>
        </div>

        <el-table :data="suppliers" border style="width: 100%" v-loading="loading">
            <el-table-column prop="supplierId" label="ID" width="80" />
            <el-table-column prop="supplierName" label="供应商名称" />
            <el-table-column prop="contactName" label="联系人" />
            <el-table-column prop="phone" label="联系电话" width="120" />
            <el-table-column prop="address" label="地址" show-overflow-tooltip />
            <el-table-column label="操作" width="200" fixed="right">
                <template #default="scope">
                    <el-button-group>
                        <el-button type="primary" @click="handleEdit(scope.row)">
                            <el-icon><Edit /></el-icon>
                        </el-button>
                        <el-button type="danger" @click="handleDelete(scope.row)">
                            <el-icon><Delete /></el-icon>
                        </el-button>
                    </el-button-group>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[10, 20, 50, 100]"
            layout="total, sizes, prev, pager, next"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            class="pagination"
        />

        <!-- 添加/编辑供应商对话框 -->
        <el-dialog
            v-model="dialogVisible"
            :title="form.supplierId ? '编辑供应商' : '添加供应商'"
            width="500px">
            <el-form :model="form" label-width="100px">
                <el-form-item label="供应商名称">
                    <el-input v-model="form.supplierName" />
                </el-form-item>
                <el-form-item label="联系人">
                    <el-input v-model="form.contactName" />
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model="form.phone" />
                </el-form-item>
                <el-form-item label="地址">
                    <el-input
                        v-model="form.address"
                        type="textarea"
                        :rows="3"
                        placeholder="请输入详细地址" />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleSave">确定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { supplierApi } from '../api'

// 数据列表
const suppliers = ref([])
const loading = ref(false)
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const searchQuery = ref('')
const dialogVisible = ref(false)
const form = ref({
    supplierId: null,
    supplierName: '',
    contactName: '',
    phone: '',
    address: ''
})

// 加载供应商列表
const loadSuppliers = async () => {
    loading.value = true
    try {
        const response = await supplierApi.getAll()
        suppliers.value = response
        total.value = response.length
        ElMessage.success('加载供应商列表成功')
    } catch (error) {
        ElMessage.error('加载供应商列表失败')
    } finally {
        loading.value = false
    }
}

// 处理编辑
const handleEdit = (row) => {
    form.value = { ...row }
    dialogVisible.value = true
}

// 处理删除
const handleDelete = (row) => {
    ElMessageBox.confirm('确定要删除该供应商吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await supplierApi.delete(row.supplierId)
            ElMessage.success('删除成功')
            loadSuppliers()
        } catch (error) {
            ElMessage.error('删除失败')
        }
    })
}

// 处理保存
const handleSave = async () => {
    try {
        if (form.value.supplierId) {
            await supplierApi.update(form.value.supplierId, form.value)
            ElMessage.success('更新成功')
        } else {
            await supplierApi.create(form.value)
            ElMessage.success('添加成功')
        }
        dialogVisible.value = false
        loadSuppliers()
    } catch (error) {
        ElMessage.error(form.value.supplierId ? '更新失败' : '添加失败')
    }
}

// 分页处理
const handleSizeChange = (val) => {
    pageSize.value = val
    loadSuppliers()
}

const handleCurrentChange = (val) => {
    currentPage.value = val
    loadSuppliers()
}

// 初始化
onMounted(() => {
    loadSuppliers()
})
</script>

<style scoped>
.suppliers-container {
    padding: 20px;
}

.header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
}

.search-input {
    width: 300px;
}

.pagination {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
}

.dialog-footer {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
}
</style> 