<template>
    <el-container class="layout-container">
        <el-aside width="200px">
            <el-menu
                router
                :default-active="route.path"
                class="menu"
            >
                <el-menu-item index="/">
                    <el-icon><House /></el-icon>
                    <span>主页</span>
                </el-menu-item>
                <el-menu-item index="/products">
                    <el-icon><Goods /></el-icon>
                    <span>商品管理</span>
                </el-menu-item>
                <el-menu-item index="/categories">
                    <el-icon><Files /></el-icon>
                    <span>分类管理</span>
                </el-menu-item>
                <el-menu-item index="/orders">
                    <el-icon><List /></el-icon>
                    <span>订单管理</span>
                </el-menu-item>
                <el-menu-item index="/suppliers">
                    <el-icon><Van /></el-icon>
                    <span>供应商管理</span>
                </el-menu-item>
                <el-menu-item index="/promotions">
                    <el-icon><Discount /></el-icon>
                    <span>促销管理</span>
                </el-menu-item>
                <el-menu-item index="/users">
                    <el-icon><User /></el-icon>
                    <span>用户管理</span>
                </el-menu-item>
                <el-menu-item index="/stock">
                    <el-icon><Box /></el-icon>
                    <span>库存管理</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header>
                <div class="header-content">
                    <div class="header-title">超市管理系统</div>
                    <div class="header-right">
                        <el-dropdown @command="handleCommand">
                            <el-button type="primary" class="user-button">
                                <el-icon><User /></el-icon>
                                <span>用户</span>
                                <el-icon class="el-icon--right"><ArrowDown /></el-icon>
                            </el-button>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                </div>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { House, Goods, Files, List, Van, Discount, User, Box, ArrowDown } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()

const handleCommand = (command) => {
    if (command === 'logout') {
        localStorage.removeItem('token')
        ElMessage.success('已退出登录')
        router.push('/login')
    }
}
</script>

<style scoped>
.layout-container {
    height: 100vh;
}

.el-aside {
    background: #2F4F4F;
    color: white;
}

.menu {
    background: transparent;
    border-right: none;
}

.el-header {
    background: #20B2AA;
    padding: 0;
    color: white;
}

.header-content {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 20px;
}

.header-title {
    font-size: 20px;
    font-weight: bold;
}

.header-right {
    display: flex;
    align-items: center;
}

.user-button {
    background: transparent;
    border: none;
    color: white;
    display: flex;
    align-items: center;
    gap: 8px;
}

.user-button:hover {
    background: rgba(255, 255, 255, 0.1);
}

:deep(.el-menu) {
    border-right: none;
}

:deep(.el-menu-item) {
    color: #e1e1e1;
}

:deep(.el-menu-item.is-active) {
    background: rgba(255, 255, 255, 0.1);
    color: white;
}

:deep(.el-menu-item:hover) {
    background: rgba(255, 255, 255, 0.1);
    color: white;
}

.el-main {
    background: #f5f7fa;
    padding: 20px;
}
</style> 