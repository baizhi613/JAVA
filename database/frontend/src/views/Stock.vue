<template>
    <div class="stock-container">
        <div class="header">
            <div class="search-group">
                <el-input
                    v-model="searchQuery"
                    placeholder="搜索商品"
                    class="search-input"
                    clearable
                />
                <el-button type="primary" @click="handleSearch">
                    <el-icon><Search /></el-icon>搜索
                </el-button>
            </div>
            <el-button type="primary" @click="handleAdd">
                <el-icon><Plus /></el-icon>添加库存记录
            </el-button>
        </div>

        <el-table
            :data="stockRecords"
            border
            v-loading="loading"
            style="width: 100%">
            <el-table-column prop="stockRecordId" label="ID" width="80" />
            <el-table-column prop="productName" label="商品名称" />
            <el-table-column prop="quantity" label="数量" width="120" />
            <el-table-column prop="type" label="类型" width="120">
                <template #default="scope">
                    <el-tag :type="scope.row.type === 'In' ? 'success' : 'danger'">
                        {{ scope.row.type === 'In' ? '入库' : '出库' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="date" label="日期" width="180" />
            <el-table-column prop="note" label="备注" />
            <el-table-column label="操作" width="150" fixed="right">
                <template #default="scope">
                    <el-button-group>
                        <el-button
                            type="primary"
                            :icon="Edit"
                            @click="handleEdit(scope.row)"
                            text
                        >
                            编辑
                        </el-button>
                        <el-button
                            type="danger"
                            :icon="Delete"
                            @click="handleDelete(scope.row)"
                            text
                        >
                            删除
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

        <!-- 添加/编辑库存记录对话框 -->
        <el-dialog
            v-model="dialogVisible"
            :title="form.stockRecordId ? '编辑库存记录' : '添加库存记录'"
            width="500px">
            <el-form :model="form" label-width="100px">
                <el-form-item label="商品">
                    <el-select v-model="form.productId" placeholder="选择商品" style="width: 100%">
                        <el-option
                            v-for="product in products"
                            :key="product.productId"
                            :label="product.productName"
                            :value="product.productId"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="类型">
                    <el-select v-model="form.type" placeholder="选择类型" style="width: 100%">
                        <el-option label="入库" value="In" />
                        <el-option label="出库" value="Out" />
                    </el-select>
                </el-form-item>
                <el-form-item label="数量">
                    <el-input-number v-model="form.quantity" :min="1" style="width: 100%" />
                </el-form-item>
                <el-form-item label="日期">
                    <el-date-picker
                        v-model="form.date"
                        type="datetime"
                        placeholder="选择日期时间"
                        style="width: 100%"
                        value-format="YYYY-MM-DD HH:mm:ss"
                    />
                </el-form-item>
                <el-form-item label="备注">
                    <el-input
                        v-model="form.note"
                        type="textarea"
                        :rows="3"
                        placeholder="请输入备注"
                    />
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
import { Delete, Edit, Plus, Search } from '@element-plus/icons-vue'
import { stockRecordApi, productApi } from '../api'

// 数据列表
const stockRecords = ref([])
const products = ref([])
const loading = ref(false)
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const searchQuery = ref('')
const dialogVisible = ref(false)

// 表单数据
const form = ref({
    stockRecordId: null,
    productId: null,
    type: 'In',
    quantity: 1,
    date: null,
    note: ''
})

// 加载库存记录列表
const loadStockRecords = async () => {
    loading.value = true
    try {
        const response = await stockRecordApi.getAll()
        stockRecords.value = response
        total.value = response.length
        ElMessage.success('加载库存记录成功')
    } catch (error) {
        console.error('Failed to load stock records:', error)
        ElMessage.error('加载库存记录失败')
    } finally {
        loading.value = false
    }
}

// 加载商品列表
const loadProducts = async () => {
    try {
        const response = await productApi.getAll()
        products.value = response
    } catch (error) {
        console.error('Failed to load products:', error)
        ElMessage.error('加载商品列表失败')
    }
}

// 处理搜索
const handleSearch = () => {
    loadStockRecords()
}

// 处理添加
const handleAdd = () => {
    form.value = {
        stockRecordId: null,
        productId: null,
        type: 'In',
        quantity: 1,
        date: new Date().toISOString().slice(0, 19).replace('T', ' '),
        note: ''
    }
    dialogVisible.value = true
}

// 处理编辑
const handleEdit = (row) => {
    form.value = { ...row }
    dialogVisible.value = true
}

// 处理删除
const handleDelete = (row) => {
    ElMessageBox.confirm('确定要删除该库存记录吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await stockRecordApi.delete(row.stockRecordId)
            ElMessage.success('删除成功')
            loadStockRecords()
        } catch (error) {
            console.error('Failed to delete stock record:', error)
            ElMessage.error('删除失败')
        }
    })
}

// 处理保存
const handleSave = async () => {
    try {
        if (!form.value.productId) {
            ElMessage.warning('请选择商品')
            return
        }
        if (!form.value.type) {
            ElMessage.warning('请选择类型')
            return
        }
        if (!form.value.date) {
            ElMessage.warning('请选择日期')
            return
        }

        const stockRecord = {
            ...form.value,
            date: form.value.date
        }

        if (form.value.stockRecordId) {
            await stockRecordApi.update(form.value.stockRecordId, stockRecord)
            ElMessage.success('更新成功')
        } else {
            await stockRecordApi.create(stockRecord)
            ElMessage.success('创建成功')
        }

        dialogVisible.value = false
        loadStockRecords()
    } catch (error) {
        console.error('Failed to save stock record:', error)
        ElMessage.error('保存失败')
    }
}

// 分页处理
const handleSizeChange = (val) => {
    pageSize.value = val
    loadStockRecords()
}

const handleCurrentChange = (val) => {
    currentPage.value = val
    loadStockRecords()
}

// 初始化
onMounted(() => {
    loadStockRecords()
    loadProducts()
})
</script>

<style scoped>
.stock-container {
    padding: 20px;
}

.header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
}

.search-group {
    display: flex;
    gap: 10px;
}

.search-input {
    width: 200px;
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