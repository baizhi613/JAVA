<template>
    <div class="categories-container">
        <div class="header">
            <el-button type="primary" @click="dialogVisible = true">
                <el-icon><Plus /></el-icon>添加分类
            </el-button>
        </div>

        <el-table :data="categories" border style="width: 100%" v-loading="loading">
            <el-table-column prop="categoryId" label="ID" width="80" />
            <el-table-column prop="categoryName" label="分类名称" />
            <el-table-column prop="description" label="描述" />
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

        <!-- 添加/编辑分类对话框 -->
        <el-dialog
            v-model="dialogVisible"
            :title="form.categoryId ? '编辑分类' : '添加分类'"
            width="500px">
            <el-form :model="form" label-width="100px">
                <el-form-item label="分类名称">
                    <el-input v-model="form.categoryName" />
                </el-form-item>
                <el-form-item label="描述">
                    <el-input
                        v-model="form.description"
                        type="textarea"
                        :rows="3"
                        placeholder="请输入分类描述" />
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
import { categoryApi } from '../api'

// 数据列表
const categories = ref([])
const loading = ref(false)
const dialogVisible = ref(false)
const form = ref({
    categoryId: null,
    categoryName: '',
    description: ''
})

// 加载分类列表
const loadCategories = async () => {
    loading.value = true
    try {
        categories.value = await categoryApi.getAll()
        ElMessage.success('加载分类列表成功')
    } catch (error) {
        ElMessage.error('加载分类列表失败')
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
    ElMessageBox.confirm('确定要删除该分类吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await categoryApi.delete(row.categoryId)
            ElMessage.success('删除成功')
            loadCategories()
        } catch (error) {
            ElMessage.error('删除失败')
        }
    })
}

// 处理保存
const handleSave = async () => {
    try {
        console.log('准备保存的分类数据:', form.value)
        if (form.value.categoryId) {
            const result = await categoryApi.update(form.value.categoryId, form.value)
            console.log('更新分类结果:', result)
            ElMessage.success('更新成功')
        } else {
            const result = await categoryApi.create(form.value)
            console.log('创建分类结果:', result)
            ElMessage.success('添加成功')
        }
        // 延迟关闭对话框，确保用户能看到成功消息
        setTimeout(() => {
            dialogVisible.value = false
            loadCategories()
        }, 1000)
    } catch (error) {
        console.error('保存分类时出错:', error)
        if (error.response) {
            console.error('错误响应:', error.response.data)
            ElMessage.error(`保存失败: ${error.response.data.message || '未知错误'}`)
        } else if (error.request) {
            console.error('请求错误:', error.request)
            ElMessage.error('网络请求失败，请检查网络连接')
        } else {
            console.error('错误信息:', error.message)
            ElMessage.error(form.value.categoryId ? '更新失败' : '添加失败')
        }
    }
}

// 初始化
onMounted(() => {
    loadCategories()
})
</script>

<style scoped>
.categories-container {
    padding: 20px;
}

.header {
    margin-bottom: 20px;
}

.dialog-footer {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
}
</style> 