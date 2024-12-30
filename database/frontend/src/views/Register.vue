<template>
    <div class="register-container">
        <div class="register-box">
            <div class="register-header">
                <h2>超市管理系统</h2>
                <p>创建新账号</p>
            </div>
            <el-form :model="registerForm" :rules="rules" ref="registerFormRef">
                <el-form-item prop="username">
                    <el-input
                        v-model="registerForm.username"
                        placeholder="用户名"
                        prefix-icon="User"
                    />
                </el-form-item>
                <el-form-item prop="email">
                    <el-input
                        v-model="registerForm.email"
                        placeholder="邮箱"
                        prefix-icon="Message"
                    />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        v-model="registerForm.password"
                        type="password"
                        placeholder="密码"
                        prefix-icon="Lock"
                        show-password
                    />
                </el-form-item>
                <el-form-item prop="confirmPassword">
                    <el-input
                        v-model="registerForm.confirmPassword"
                        type="password"
                        placeholder="确认密码"
                        prefix-icon="Lock"
                        show-password
                    />
                </el-form-item>
                <el-form-item prop="role">
                    <el-select v-model="registerForm.role" placeholder="选择角色" style="width: 100%">
                        <el-option label="管理员" value="Admin" />
                        <el-option label="普通用户" value="Customer" />
                    </el-select>
                </el-form-item>
                <el-button type="primary" class="register-button" @click="handleRegister">
                    注册
                </el-button>
                <div class="login-link">
                    已有账号？
                    <el-link type="primary" :underline="false" @click="goToLogin">立即登录</el-link>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { User, Lock, Message } from '@element-plus/icons-vue'
import { userApi } from '../api'

const router = useRouter()
const registerFormRef = ref(null)

const registerForm = ref({
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    role: ''
})

const validatePass = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入密码'))
    } else {
        if (registerForm.value.confirmPassword !== '') {
            registerFormRef.value.validateField('confirmPassword')
        }
        callback()
    }
}

const validatePass2 = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== registerForm.value.password) {
        callback(new Error('两次输入密码不一致!'))
    } else {
        callback()
    }
}

const rules = {
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
    ],
    email: [
        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
        { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
    ],
    password: [
        { validator: validatePass, trigger: 'blur' },
        { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
    ],
    confirmPassword: [
        { validator: validatePass2, trigger: 'blur' }
    ],
    role: [
        { required: true, message: '请选择角色', trigger: 'change' }
    ]
}

const handleRegister = () => {
    registerFormRef.value.validate(async (valid) => {
        if (valid) {
            try {
                const { confirmPassword, ...userData } = registerForm.value
                await userApi.create(userData)
                ElMessage.success('注册成功')
                router.push('/login')
            } catch (error) {
                ElMessage.error('注册失败：' + error.message)
            }
        }
    })
}

const goToLogin = () => {
    router.push('/login')
}
</script>

<style scoped>
.register-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, #e8f5ff 0%, #e8fff5 100%);
}

.register-box {
    width: 400px;
    padding: 40px;
    background: rgba(255, 255, 255, 0.9);
    border-radius: 16px;
    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(8px);
}

.register-header {
    text-align: center;
    margin-bottom: 40px;
}

.register-header h2 {
    margin: 0;
    color: #2c3e50;
    font-size: 28px;
    font-weight: 500;
}

.register-header p {
    margin: 10px 0 0;
    color: #666;
    font-size: 16px;
}

:deep(.el-input__wrapper) {
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

:deep(.el-input__wrapper:hover) {
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

:deep(.el-input__wrapper.is-focus) {
    box-shadow: 0 2px 8px rgba(100, 179, 244, 0.2);
}

:deep(.el-select .el-input__wrapper) {
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

:deep(.el-select .el-input__wrapper:hover) {
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.register-button {
    width: 100%;
    height: 44px;
    border-radius: 8px;
    font-size: 16px;
    background: linear-gradient(90deg, #64b3f4 0%, #70f0c7 100%);
    border: none;
    margin-bottom: 20px;
    transition: all 0.3s ease;
}

.register-button:hover {
    transform: translateY(-1px);
    box-shadow: 0 4px 12px rgba(100, 179, 244, 0.3);
}

.login-link {
    text-align: center;
    color: #666;
}
</style> 