<template>
    <div class="home-container">
        <el-row :gutter="20">
            <el-col :span="6">
                <div class="stat-card users">
                    <div class="icon-wrapper">
                        <el-icon><User /></el-icon>
                    </div>
                    <div class="stat-info">
                        <div class="label">用户总数</div>
                        <div class="value">{{ stats.userCount }}</div>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="stat-card products">
                    <div class="icon-wrapper">
                        <el-icon><Goods /></el-icon>
                    </div>
                    <div class="stat-info">
                        <div class="label">商品总数</div>
                        <div class="value">{{ stats.productCount }}</div>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="stat-card orders">
                    <div class="icon-wrapper">
                        <el-icon><List /></el-icon>
                    </div>
                    <div class="stat-info">
                        <div class="label">订单总数</div>
                        <div class="value">{{ stats.orderCount }}</div>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="stat-card suppliers">
                    <div class="icon-wrapper">
                        <el-icon><Van /></el-icon>
                    </div>
                    <div class="stat-info">
                        <div class="label">供应商总数</div>
                        <div class="value">{{ stats.supplierCount }}</div>
                    </div>
                </div>
            </el-col>
        </el-row>

        <el-row :gutter="20" style="margin-top: 20px;">
            <el-col :span="8">
                <div class="data-card">
                    <div class="card-header">
                        <h3>库存预警</h3>
                    </div>
                    <div class="card-content">
                        <el-table :data="lowStockProducts" style="width: 100%" size="small">
                            <el-table-column prop="productName" label="商品名称" />
                            <el-table-column prop="stock" label="当前库存" width="100">
                                <template #default="scope">
                                    <el-tag type="danger" effect="dark">{{ scope.row.stock }}</el-tag>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </div>
            </el-col>
            <el-col :span="8">
                <div class="data-card">
                    <div class="card-header">
                        <h3>最新订单</h3>
                    </div>
                    <div class="card-content">
                        <el-table :data="recentOrders" style="width: 100%" size="small">
                            <el-table-column prop="orderId" label="订单号" width="80" />
                            <el-table-column prop="totalAmount" label="金额">
                                <template #default="scope">
                                    ¥{{ scope.row.totalAmount }}
                                </template>
                            </el-table-column>
                            <el-table-column prop="status" label="状态" width="100">
                                <template #default="scope">
                                    <el-tag :type="getOrderStatusType(scope.row.status)">
                                        {{ getOrderStatusText(scope.row.status) }}
                                    </el-tag>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </div>
            </el-col>
            <el-col :span="8">
                <div class="data-card">
                    <div class="card-header">
                        <h3>热销商品</h3>
                    </div>
                    <div class="card-content">
                        <el-table :data="hotProducts" style="width: 100%" size="small">
                            <el-table-column prop="productName" label="商品名称" />
                            <el-table-column prop="salesCount" label="销量" width="100">
                                <template #default="scope">
                                    <el-tag type="success" effect="dark">{{ scope.row.salesCount }}</el-tag>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { User, Goods, List, Van } from '@element-plus/icons-vue'
import { userApi, productApi, orderApi, supplierApi } from '../api'

const stats = ref({
    userCount: 0,
    productCount: 0,
    orderCount: 0,
    supplierCount: 0
})

const lowStockProducts = ref([])
const recentOrders = ref([])
const hotProducts = ref([])

const loadStats = async () => {
    try {
        // 获取用户总数
        const users = await userApi.getAll()
        stats.value.userCount = users.length

        // 获取商品总数
        const products = await productApi.getAll()
        stats.value.productCount = products.length

        // 获取订单总数
        const orders = await orderApi.getAll()
        stats.value.orderCount = orders.length

        // 获取供应商总数
        const suppliers = await supplierApi.getAll()
        stats.value.supplierCount = suppliers.length

        // 获取库存预警商品（库存小于10的商品）
        lowStockProducts.value = products
            .filter(p => p.stock < 10)
            .slice(0, 5)

        // 获取最新订单
        recentOrders.value = orders
            .sort((a, b) => new Date(b.orderDate) - new Date(a.orderDate))
            .slice(0, 5)

        // 模拟热销商品数据（实际应该从后端获取）
        hotProducts.value = products
            .map(p => ({ ...p, salesCount: Math.floor(Math.random() * 100) }))
            .sort((a, b) => b.salesCount - a.salesCount)
            .slice(0, 5)
    } catch (error) {
        console.error('Failed to load stats:', error)
    }
}

const getOrderStatusType = (status) => {
    const map = {
        'Pending': 'warning',
        'Completed': 'success',
        'Cancelled': 'danger'
    }
    return map[status]
}

const getOrderStatusText = (status) => {
    const map = {
        'Pending': '待处理',
        'Completed': '已完成',
        'Cancelled': '已取消'
    }
    return map[status]
}

onMounted(() => {
    loadStats()
})
</script>

<style scoped>
.home-container {
    padding: 20px;
}

.stat-card {
    background: #fff;
    border-radius: 12px;
    padding: 20px;
    display: flex;
    align-items: center;
    gap: 20px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
    transition: all 0.3s ease;
}

.stat-card:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}

.icon-wrapper {
    width: 60px;
    height: 60px;
    border-radius: 12px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 24px;
    color: #fff;
}

.users .icon-wrapper {
    background: linear-gradient(135deg, #64b3f4 0%, #70f0c7 100%);
}

.products .icon-wrapper {
    background: linear-gradient(135deg, #6ce6b3 0%, #70d0f0 100%);
}

.orders .icon-wrapper {
    background: linear-gradient(135deg, #70f0ae 0%, #64b3f4 100%);
}

.suppliers .icon-wrapper {
    background: linear-gradient(135deg, #70d0f0 0%, #6ce6b3 100%);
}

.stat-info {
    flex-grow: 1;
}

.stat-info .label {
    color: #666;
    font-size: 14px;
    margin-bottom: 4px;
}

.stat-info .value {
    color: #2c3e50;
    font-size: 24px;
    font-weight: bold;
}

.data-card {
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
    height: 100%;
}

.card-header {
    padding: 16px 20px;
    border-bottom: 1px solid #eee;
}

.card-header h3 {
    margin: 0;
    font-size: 16px;
    color: #2c3e50;
}

.card-content {
    padding: 20px;
}

:deep(.el-table) {
    --el-table-border-color: #eee;
}

:deep(.el-table th) {
    background-color: #f8f9fa;
}

:deep(.el-table--small) {
    font-size: 13px;
}
</style> 