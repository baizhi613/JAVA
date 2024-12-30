<template>
    <div class="users-container">
        <div class="header">
            <el-button type="primary" @click="handleAdd">添加用户</el-button>
            <el-input
                v-model="searchQuery"
                placeholder="搜索用户..."
                style="width: 200px; margin-left: 16px"
                @input="handleSearch"
            />
        </div>

        <el-table :data="users" style="width: 100%; margin-top: 16px">
            <el-table-column prop="userId" label="ID" width="80" />
            <el-table-column prop="username" label="用户名" width="120" />
            <el-table-column prop="email" label="邮箱" width="180" />
            <el-table-column prop="role" label="角色" width="100">
                <template #default="{ row }">
                    <el-tag :type="row.role === 'Admin' ? 'danger' : 'success'">
                        {{ row.role === 'Admin' ? '管理员' : '普通用户' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createdAt" label="创建时间" width="180" />
            <el-table-column label="操作" width="200">
                <template #default="{ row }">
                    <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog
            v-model="dialogVisible"
            :title="form.userId ? '编辑用户' : '添加用户'"
            width="500px"
        >
            <el-form :model="form" label-width="80px">
                <el-form-item label="用户名">
                    <el-input v-model="form.username" />
                </el-form-item>
                <el-form-item label="密码" v-if="!form.userId">
                    <el-input v-model="form.password" type="password" />
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" />
                </el-form-item>
                <el-form-item label="角色">
                    <el-select v-model="form.role" style="width: 100%">
                        <el-option label="管理员" value="Admin" />
                        <el-option label="普通用户" value="Customer" />
                    </el-select>
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
import { userApi } from '../api'

const users = ref([])
const searchQuery = ref('')
const dialogVisible = ref(false)
const form = ref({
    userId: null,
    username: '',
    password: '',
    email: '',
    role: 'Customer',
    createdAt: null
})

const loadUsers = async () => {
    try {
        const response = await userApi.getAll()
        users.value = response
    } catch (error) {
        console.error('Failed to load users:', error)
        ElMessage.error('加载用户列表失败')
    }
}

const handleSearch = () => {
    loadUsers()
}

const handleAdd = () => {
    form.value = {
        userId: null,
        username: '',
        password: '',
        email: '',
        role: 'Customer',
        createdAt: null
    }
    dialogVisible.value = true
}

const handleEdit = (row) => {
    form.value = {
        userId: row.userId,
        username: row.username,
        email: row.email,
        role: row.role,
        createdAt: row.createdAt
    }
    dialogVisible.value = true
}

const handleDelete = async (row) => {
    try {
        await ElMessageBox.confirm('确定要删除该用户吗？', '提示', {
            type: 'warning'
        })
        await userApi.delete(row.userId)
        ElMessage.success('删除成功')
        loadUsers()
    } catch (error) {
        if (error !== 'cancel') {
            console.error('Failed to delete user:', error)
            ElMessage.error('删除用户失败')
        }
    }
}

const handleSave = async () => {
    try {
        const userData = { ...form.value }
        delete userData.createdAt

        console.log('Saving user with data:', userData)
        if (userData.userId) {
            const response = await userApi.update(userData.userId, userData)
            console.log('Update response:', response)
            ElMessage.success('更新成功')
            dialogVisible.value = false
            loadUsers()
        } else {
            const response = await userApi.create(userData)
            console.log('Create response:', response)
            if (response) {
                ElMessage.success('添加成功')
                dialogVisible.value = false
                loadUsers()
            } else {
                throw new Error('服务器响应异常')
            }
        }
    } catch (error) {
        console.error('Failed to save user. Error details:', error)
        console.error('Error response:', error.response)
        const errorMessage = error.response?.data?.message || '保存用户失败'
        ElMessage.error(errorMessage)
    }
}

onMounted(() => {
    loadUsers()
})
</script>

<style scoped>
.users-container {
    padding: 20px;
}

.header {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

.dialog-footer {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
}
</style> 