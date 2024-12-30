<template>
    <div class="orders-container">
        <div class="header">
            <el-button type="primary" @click="dialogVisible = true">
                <el-icon><Plus /></el-icon>创建订单
            </el-button>
            <div class="search-group">
                <el-input
                    v-model="searchQuery"
                    placeholder="搜索订单ID"
                    class="search-input"
                    clearable
                    @clear="loadOrders"
                    @keyup.enter="loadOrders">
                    <template #prefix>
                        <el-icon><Search /></el-icon>
                    </template>
                </el-input>
                <el-select v-model="statusFilter" placeholder="订单状态" clearable @change="loadOrders">
                    <el-option label="待处理" value="Pending" />
                    <el-option label="已完成" value="Completed" />
                    <el-option label="已取消" value="Cancelled" />
                </el-select>
            </div>
        </div>

        <el-table :data="orders" border style="width: 100%" v-loading="loading">
            <el-table-column prop="orderId" label="订单ID" width="100" />
            <el-table-column prop="userId" label="用户ID" width="100" />
            <el-table-column prop="totalAmount" label="总金额" width="120">
                <template #default="scope">
                    ¥{{ scope.row.totalAmount }}
                </template>
            </el-table-column>
            <el-table-column prop="orderDate" label="下单时间" width="180" />
            <el-table-column prop="status" label="状态" width="100">
                <template #default="scope">
                    <el-tag :type="getStatusType(scope.row.status)">
                        {{ getStatusText(scope.row.status) }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200" fixed="right">
                <template #default="scope">
                    <el-button-group>
                        <el-button type="primary" @click="handleViewDetails(scope.row)">
                            <el-icon><View /></el-icon>
                        </el-button>
                        <el-button
                            type="success"
                            v-if="scope.row.status === 'Pending'"
                            @click="handleComplete(scope.row)">
                            <el-icon><Check /></el-icon>
                        </el-button>
                        <el-button
                            type="danger"
                            v-if="scope.row.status === 'Pending'"
                            @click="handleCancel(scope.row)">
                            <el-icon><Close /></el-icon>
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

        <!-- 订单详情对话框 -->
        <el-dialog
            v-model="detailsVisible"
            title="订单详情"
            width="800px">
            <el-descriptions :column="2" border>
                <el-descriptions-item label="订单ID">{{ currentOrder.orderId }}</el-descriptions-item>
                <el-descriptions-item label="用户ID">{{ currentOrder.userId }}</el-descriptions-item>
                <el-descriptions-item label="总金额">¥{{ currentOrder.totalAmount }}</el-descriptions-item>
                <el-descriptions-item label="订单状态">
                    <el-tag :type="getStatusType(currentOrder.status)">
                        {{ getStatusText(currentOrder.status) }}
                    </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="下单时间" :span="2">
                    {{ currentOrder.orderDate }}
                </el-descriptions-item>
            </el-descriptions>

            <el-table :data="orderDetails" border style="margin-top: 20px">
                <el-table-column prop="productId" label="商品ID" width="100" />
                <el-table-column prop="productName" label="商品名称" />
                <el-table-column prop="quantity" label="数量" width="100" />
                <el-table-column prop="subtotal" label="小计" width="120">
                    <template #default="scope">
                        ¥{{ scope.row.subtotal }}
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>

        <!-- 创建订单对话框 -->
        <el-dialog
            v-model="dialogVisible"
            title="创建订单"
            width="800px">
            <el-form :model="form" label-width="100px">
                <el-form-item label="用户">
                    <el-select v-model="form.userId" placeholder="选择用户" style="width: 100%">
                        <el-option
                            v-for="user in users"
                            :key="user.userId"
                            :label="user.username"
                            :value="user.userId"
                        />
                    </el-select>
                </el-form-item>
                <el-divider>订单商品</el-divider>
                <div v-for="(item, index) in form.orderDetails" :key="index" class="order-item">
                    <el-form-item :label="'商品' + (index + 1)">
                        <el-select
                            v-model="item.productId"
                            placeholder="选择商品"
                            @change="handleProductChange($event, index)">
                            <el-option
                                v-for="product in products"
                                :key="product.productId"
                                :label="product.productName"
                                :value="product.productId"
                            />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="数量">
                        <el-input-number
                            v-model="item.quantity"
                            :min="1"
                            @change="calculateSubtotal(index)" />
                    </el-form-item>
                    <el-form-item label="小计">
                        ¥{{ item.subtotal }}
                    </el-form-item>
                    <el-button type="danger" @click="removeOrderItem(index)">
                        <el-icon><Delete /></el-icon>
                    </el-button>
                </div>
                <el-button type="primary" @click="addOrderItem">
                    <el-icon><Plus /></el-icon>添加商品
                </el-button>
                <el-divider />
                <el-form-item label="总金额">
                    <span class="total-amount">¥{{ calculateTotal() }}</span>
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
import { ref, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { orderApi, orderDetailApi, productApi, userApi } from '../api'

// 数据列表
const orders = ref([])
const orderDetails = ref([])
const products = ref([])
const users = ref([])
const loading = ref(false)
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const searchQuery = ref('')
const statusFilter = ref('')
const dialogVisible = ref(false)
const detailsVisible = ref(false)
const currentOrder = ref({})

// 表单数据
const form = ref({
    userId: null,
    orderDetails: [
        {
            productId: null,
            quantity: 1,
            subtotal: 0
        }
    ]
})

// 状态映射
const getStatusType = (status) => {
    const map = {
        'Pending': 'warning',
        'Completed': 'success',
        'Cancelled': 'danger'
    }
    return map[status]
}

const getStatusText = (status) => {
    const map = {
        'Pending': '待处理',
        'Completed': '已完成',
        'Cancelled': '已取消'
    }
    return map[status]
}

// 加载订单列表
const loadOrders = async () => {
    loading.value = true
    try {
        const response = await orderApi.getAll()
        orders.value = response
        total.value = response.length
        ElMessage.success('加载订单列表成功')
    } catch (error) {
        ElMessage.error('加载订单列表失败')
    } finally {
        loading.value = false
    }
}

// 加载商品列表
const loadProducts = async () => {
    try {
        const response = await productApi.getAll()
        products.value = response
        ElMessage.success('加载商品列表成功')
    } catch (error) {
        ElMessage.error('加载商品列表失败')
    }
}

// 加载用户列表
const loadUsers = async () => {
    try {
        const response = await userApi.getAll()
        users.value = response
    } catch (error) {
        ElMessage.error('加载用户列表失败')
    }
}

// 查看订单详情
const handleViewDetails = async (row) => {
    currentOrder.value = row
    try {
        orderDetails.value = await orderDetailApi.getByOrderId(row.orderId)
        detailsVisible.value = true
        ElMessage.success('加载订单详情成功')
    } catch (error) {
        ElMessage.error('加载订单详情失败')
    }
}

// 处理订单完成
const handleComplete = (row) => {
    ElMessageBox.confirm('确定要将订单标记为完成吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await orderApi.update(row.orderId, { ...row, status: 'Completed' })
            ElMessage.success('订单已完成')
            loadOrders()
        } catch (error) {
            ElMessage.error('操作失败')
        }
    })
}

// 处理订单取消
const handleCancel = (row) => {
    ElMessageBox.confirm('确定要取消该订单吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await orderApi.update(row.orderId, { ...row, status: 'Cancelled' })
            ElMessage.success('订单已取消')
            loadOrders()
        } catch (error) {
            ElMessage.error('操作失败')
        }
    })
}

// 添加订单商品
const addOrderItem = () => {
    form.value.orderDetails.push({
        productId: null,
        quantity: 1,
        subtotal: 0
    })
}

// 移除订单商品
const removeOrderItem = (index) => {
    form.value.orderDetails.splice(index, 1)
}

// 处理商品选择变更
const handleProductChange = (productId, index) => {
    const product = products.value.find(p => p.productId === productId)
    if (product) {
        form.value.orderDetails[index].subtotal = product.price * form.value.orderDetails[index].quantity
    }
}

// 计算小计
const calculateSubtotal = (index) => {
    const item = form.value.orderDetails[index]
    const product = products.value.find(p => p.productId === item.productId)
    if (product) {
        item.subtotal = product.price * item.quantity
    }
}

// 计算总金额
const calculateTotal = () => {
    return form.value.orderDetails.reduce((total, item) => total + (item.subtotal || 0), 0)
}

// 处理保存
const handleSave = async () => {
    try {
        // 检查是否选择了用户
        if (!form.value.userId) {
            ElMessage.warning('请选择用户')
            return
        }

        // 检查是否添加了商品
        if (!form.value.orderDetails.length || !form.value.orderDetails[0].productId) {
            ElMessage.warning('请至少添加一个商品')
            return
        }

        // 创建订单数据
        const orderData = {
            order: {
                userId: form.value.userId,
                totalAmount: calculateTotal(),
                orderDate: new Date().toISOString().replace('T', ' ').split('.')[0],
                status: 'Pending'
            },
            orderDetails: form.value.orderDetails.map(item => ({
                productId: item.productId,
                quantity: item.quantity,
                subtotal: item.subtotal
            }))
        }

        console.log('Creating order with data:', orderData)
        await orderApi.createWithDetails(orderData)
        ElMessage.success('创建订单成功')
        dialogVisible.value = false
        loadOrders()
        // 重置表单
        form.value = {
            userId: null,
            orderDetails: [
                {
                    productId: null,
                    quantity: 1,
                    subtotal: 0
                }
            ]
        }
    } catch (error) {
        console.error('Failed to create order:', error)
        ElMessage.error('创建订单失败')
    }
}

// 分页处理
const handleSizeChange = (val) => {
    pageSize.value = val
    loadOrders()
}

const handleCurrentChange = (val) => {
    currentPage.value = val
    loadOrders()
}

// 初始化
onMounted(() => {
    loadOrders()
    loadProducts()
    loadUsers()
})
</script>

<style scoped>
.orders-container {
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

.order-item {
    display: flex;
    gap: 20px;
    align-items: flex-start;
    margin-bottom: 20px;
}

.total-amount {
    font-size: 20px;
    color: #f56c6c;
    font-weight: bold;
}
</style> 