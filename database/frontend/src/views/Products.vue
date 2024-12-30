<template>
    <div class="products-container">
        <div class="header">
            <el-button type="primary" @click="dialogVisible = true">
                <el-icon><Plus /></el-icon>添加商品
            </el-button>
            <el-input
                v-model="searchQuery"
                placeholder="搜索商品名称"
                class="search-input"
                clearable
                @clear="loadProducts"
                @keyup.enter="loadProducts">
                <template #prefix>
                    <el-icon><Search /></el-icon>
                </template>
            </el-input>
        </div>

        <el-table :data="products" border style="width: 100%" v-loading="loading">
            <el-table-column prop="productId" label="ID" width="80" />
            <el-table-column prop="productName" label="商品名称" />
            <el-table-column prop="categoryId" label="分类" />
            <el-table-column prop="price" label="价格" width="120">
                <template #default="scope">
                    ¥{{ scope.row.price }}
                </template>
            </el-table-column>
            <el-table-column prop="stock" label="库存" width="120" />
            <el-table-column prop="createdAt" label="创建时间" width="180" />
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

        <!-- 添加/编辑商品对话框 -->
        <el-dialog
            v-model="dialogVisible"
            :title="form.productId ? '编辑商品' : '添加商品'"
            width="500px">
            <el-form :model="form" label-width="100px">
                <el-form-item label="商品名称">
                    <el-input v-model="form.productName" />
                </el-form-item>
                <el-form-item label="分类">
                    <el-select v-model="form.categoryId" placeholder="请选择分类">
                        <el-option
                            v-for="category in categories"
                            :key="category.categoryId"
                            :label="category.categoryName"
                            :value="category.categoryId"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="价格">
                    <el-input-number v-model="form.price" :precision="2" :step="0.1" :min="0" />
                </el-form-item>
                <el-form-item label="库存">
                    <el-input-number v-model="form.stock" :min="0" />
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
import { productApi, categoryApi } from '../api'

// 数据列表
const products = ref([])
const categories = ref([])
const loading = ref(false)
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const searchQuery = ref('')
const dialogVisible = ref(false)
const form = ref({
    productId: null,
    productName: '',
    categoryId: null,
    price: 0,
    stock: 0
})

// 加载商品列表
const loadProducts = async () => {
    loading.value = true
    try {
        const response = await productApi.getAll()
        products.value = response
        total.value = response.length
        ElMessage.success('加载商品列表成功')
    } catch (error) {
        ElMessage.error('加载商品列表失败')
    } finally {
        loading.value = false
    }
}

// 加载分类列表
const loadCategories = async () => {
    try {
        categories.value = await categoryApi.getAll()
        ElMessage.success('加载分类列表成功')
    } catch (error) {
        ElMessage.error('加载分类列表失败')
    }
}

// 处理编辑
const handleEdit = (row) => {
    form.value = { ...row }
    dialogVisible.value = true
}

// 处理删除
const handleDelete = (row) => {
    ElMessageBox.confirm('确定要删除该商品吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await productApi.delete(row.productId)
            ElMessage.success('删除成功')
            loadProducts()
        } catch (error) {
            ElMessage.error('删除失败')
        }
    })
}

// 处理保存
const handleSave = async () => {
    try {
        if (form.value.productId) {
            await productApi.update(form.value.productId, form.value)
            ElMessage.success('更新成功')
        } else {
            await productApi.create(form.value)
            ElMessage.success('添加成功')
        }
        dialogVisible.value = false
        loadProducts()
    } catch (error) {
        ElMessage.error(form.value.productId ? '更新失败' : '添加失败')
    }
}

// 分页处理
const handleSizeChange = (val) => {
    pageSize.value = val
    loadProducts()
}

const handleCurrentChange = (val) => {
    currentPage.value = val
    loadProducts()
}

// 初始化
onMounted(() => {
    loadProducts()
    loadCategories()
})
</script>

<style scoped>
.products-container {
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