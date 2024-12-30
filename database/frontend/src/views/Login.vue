<template>
    <div class="login-container">
        <div class="login-box">
            <div class="login-header">
                <h2>超市管理系统</h2>
                <p>欢迎回来</p>
            </div>
            <el-form :model="loginForm" :rules="rules" ref="loginFormRef">
                <el-form-item prop="username">
                    <el-input
                        v-model="loginForm.username"
                        placeholder="用户名"
                        prefix-icon="User"
                    />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        v-model="loginForm.password"
                        type="password"
                        placeholder="密码"
                        prefix-icon="Lock"
                        show-password
                    />
                </el-form-item>
                <div class="form-footer">
                    <el-checkbox v-model="rememberMe">记住我</el-checkbox>
                    <el-link type="primary" :underline="false" @click="forgotPassword">忘记密码？</el-link>
                </div>
                <el-button type="primary" class="login-button" @click="handleLogin">
                    登录
                </el-button>
                <div class="register-link">
                    还没有账号？
                    <el-link type="primary" :underline="false" @click="goToRegister">立即注册</el-link>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'

const router = useRouter()
const loginFormRef = ref(null)
const rememberMe = ref(false)

const loginForm = ref({
    username: '',
    password: ''
})

const rules = {
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
    ]
}

const handleLogin = () => {
    loginFormRef.value.validate(async (valid) => {
        if (valid) {
            try {
                // TODO: 调用登录 API
                localStorage.setItem('token', 'your-token-here') // 保存token
                ElMessage.success('登录成功')
                router.push('/home')
            } catch (error) {
                ElMessage.error('登录失败：' + error.message)
            }
        }
    })
}

const forgotPassword = () => {
    ElMessage.info('请联系管理员重置密码')
}

const goToRegister = () => {
    router.push('/register')
}
</script>

<style scoped>
.login-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, #e8f5ff 0%, #e8fff5 100%);
}

.login-box {
    width: 400px;
    padding: 40px;
    background: rgba(255, 255, 255, 0.9);
    border-radius: 16px;
    box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(8px);
}

.login-header {
    text-align: center;
    margin-bottom: 40px;
}

.login-header h2 {
    margin: 0;
    color: #2c3e50;
    font-size: 28px;
    font-weight: 500;
}

.login-header p {
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

.form-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.login-button {
    width: 100%;
    height: 44px;
    border-radius: 8px;
    font-size: 16px;
    background: linear-gradient(90deg, #64b3f4 0%, #70f0c7 100%);
    border: none;
    margin-bottom: 20px;
    transition: all 0.3s ease;
}

.login-button:hover {
    transform: translateY(-1px);
    box-shadow: 0 4px 12px rgba(100, 179, 244, 0.3);
}

.register-link {
    text-align: center;
    color: #666;
}

:deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
    background: linear-gradient(90deg, #64b3f4 0%, #70f0c7 100%);
    border-color: transparent;
}
</style> 