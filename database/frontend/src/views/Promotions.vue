<template>
    <div class="promotions-container">
        <div class="header">
            <el-button type="primary" @click="dialogVisible = true">
                <el-icon><Plus /></el-icon>添加促销活动
            </el-button>
            <el-input
                v-model="searchQuery"
                placeholder="搜索活动名称"
                class="search-input"
                clearable
                @clear="loadPromotions"
                @keyup.enter="loadPromotions">
                <template #prefix>
                    <el-icon><Search /></el-icon>
                </template>
            </el-input>
        </div>

        <el-table :data="promotions" border style="width: 100%" v-loading="loading">
            <el-table-column prop="promotionId" label="ID" width="80" />
            <el-table-column prop="promotionName" label="活动名称" />
            <el-table-column prop="discountRate" label="折扣率" width="100">
                <template #default="scope">
                    {{ (scope.row.discountRate * 100).toFixed(0) }}%
                </template>
            </el-table-column>
            <el-table-column prop="startDate" label="开始时间" width="180" />
            <el-table-column prop="endDate" label="结束时间" width="180" />
            <el-table-column label="状态" width="100">
                <template #default="scope">
                    <el-tag :type="getPromotionStatus(scope.row).type">
                        {{ getPromotionStatus(scope.row).text }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="280" fixed="right">
                <template #default="scope">
                    <el-button-group>
                        <el-button type="primary" @click="handleEdit(scope.row)">
                            <el-icon><Edit /></el-icon>
                        </el-button>
                        <el-button type="success" @click="handleManageProducts(scope.row)">
                            <el-icon><Goods /></el-icon>
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

        <!-- 添加/编辑促销活动对话框 -->
        <el-dialog
            v-model="dialogVisible"
            :title="form.promotionId ? '编辑促销活动' : '添加促销活动'"
            width="500px">
            <el-form :model="form" label-width="100px">
                <el-form-item label="活动名称">
                    <el-input v-model="form.promotionName" />
                </el-form-item>
                <el-form-item label="折扣率">
                    <el-input-number
                        v-model="form.discountRate"
                        :precision="2"
                        :step="0.1"
                        :min="0"
                        :max="1"
                        style="width: 180px" />
                    <span class="discount-tip">折扣率范围：0-1，如0.8表示8折</span>
                </el-form-item>
                <el-form-item label="活动时间">
                    <el-date-picker
                        v-model="dateRange"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        value-format="YYYY-MM-DD"
                        @change="handleDateRangeChange" />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleSave">确定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 管理促销商品对话框 -->
        <el-dialog
            v-model="productsDialogVisible"
            :title="'管理促销商品 - ' + currentPromotion.promotionName"
            width="800px">
            <div class="products-header">
                <el-input
                    v-model="productSearchQuery"
                    placeholder="搜索商品名称"
                    class="search-input"
                    clearable>
                    <template #prefix>
                        <el-icon><Search /></el-icon>
                    </template>
                </el-input>
            </div>

            <el-table
                :data="filteredProducts"
                border
                style="width: 100%"
                v-loading="productsLoading">
                <el-table-column type="selection" width="55" />
                <el-table-column prop="productId" label="商品ID" width="80" />
                <el-table-column prop="productName" label="商品名称" />
                <el-table-column prop="price" label="原价" width="120">
                    <template #default="scope">
                        ¥{{ scope.row.price }}
                    </template>
                </el-table-column>
                <el-table-column label="促销价" width="120">
                    <template #default="scope">
                        ¥{{ (scope.row.price * form.discountRate).toFixed(2) }}
                    </template>
                </el-table-column>
            </el-table>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="productsDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="handleSaveProducts">确定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { promotionApi, productApi, productPromotionApi } from '../api'

// 数据列表
const promotions = ref([])
const products = ref([])
const loading = ref(false)
const productsLoading = ref(false)
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(10)
const searchQuery = ref('')
const productSearchQuery = ref('')
const dialogVisible = ref(false)
const productsDialogVisible = ref(false)
const dateRange = ref([])
const currentPromotion = ref({})

// 表单数据
const form = ref({
    promotionId: null,
    promotionName: '',
    discountRate: 0.9,
    startDate: '',
    endDate: ''
})

// 获取促销活动状态
const getPromotionStatus = (promotion) => {
    const now = new Date()
    const startDate = new Date(promotion.startDate)
    const endDate = new Date(promotion.endDate)

    if (now < startDate) {
        return { type: 'info', text: '未开始' }
    } else if (now > endDate) {
        return { type: 'danger', text: '已结束' }
    } else {
        return { type: 'success', text: '进行中' }
    }
}

// 过滤后的商品列表
const filteredProducts = computed(() => {
    if (!productSearchQuery.value) {
        return products.value
    }
    return products.value.filter(product =>
        product.productName.toLowerCase().includes(productSearchQuery.value.toLowerCase())
    )
})

// 加载促销活动列表
const loadPromotions = async () => {
    loading.value = true
    try {
        const response = await promotionApi.getAll()
        promotions.value = response
        total.value = response.length
        ElMessage.success('加载促销活动列表成功')
    } catch (error) {
        ElMessage.error('加载促销活动列表失败')
    } finally {
        loading.value = false
    }
}

// 加载商品列表
const loadProducts = async () => {
    productsLoading.value = true
    try {
        const response = await productApi.getAll()
        products.value = response
        ElMessage.success('加载商品列表成功')
    } catch (error) {
        ElMessage.error('加载商品列表失败')
    } finally {
        productsLoading.value = false
    }
}

// 处理日期范围变更
const handleDateRangeChange = (val) => {
    if (val) {
        form.value.startDate = val[0]
        form.value.endDate = val[1]
    } else {
        form.value.startDate = ''
        form.value.endDate = ''
    }
}

// 处理编辑
const handleEdit = (row) => {
    form.value = { ...row }
    dateRange.value = [row.startDate, row.endDate]
    dialogVisible.value = true
}

// 处理删除
const handleDelete = (row) => {
    ElMessageBox.confirm('确定要删除该促销活动吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await promotionApi.delete(row.promotionId)
            ElMessage.success('删除成功')
            loadPromotions()
        } catch (error) {
            ElMessage.error('删除失败')
        }
    })
}

// 处理管理商品
const handleManageProducts = async (row) => {
    currentPromotion.value = row
    productsDialogVisible.value = true
    await loadProducts()
    
    // 加载已关联的商品
    try {
        const promotionProducts = await productPromotionApi.getByPromotionId(row.promotionId)
        const promotionProductIds = promotionProducts.map(pp => pp.productId)
        products.value.forEach(product => {
            product.selected = promotionProductIds.includes(product.productId)
        })
    } catch (error) {
        ElMessage.error('加载促销商品失败')
    }
}

// 处理保存
const handleSave = async () => {
    try {
        if (form.value.promotionId) {
            await promotionApi.update(form.value.promotionId, form.value)
            ElMessage.success('更新成功')
        } else {
            await promotionApi.create(form.value)
            ElMessage.success('添加成功')
        }
        dialogVisible.value = false
        loadPromotions()
    } catch (error) {
        ElMessage.error(form.value.promotionId ? '更新失败' : '添加失败')
    }
}

// 处理保存商品
const handleSaveProducts = async () => {
    try {
        const selectedProducts = products.value.filter(p => p.selected)
        
        // 删除旧的关联
        await productPromotionApi.deleteByPromotionId(currentPromotion.value.promotionId)
        
        // 创建新的关联
        for (const product of selectedProducts) {
            await productPromotionApi.create({
                productId: product.productId,
                promotionId: currentPromotion.value.promotionId
            })
        }
        
        ElMessage.success('保存促销商品成功')
        productsDialogVisible.value = false
    } catch (error) {
        ElMessage.error('保存促销商品失败')
    }
}

// 分页处理
const handleSizeChange = (val) => {
    pageSize.value = val
    loadPromotions()
}

const handleCurrentChange = (val) => {
    currentPage.value = val
    loadPromotions()
}

// 初始化
onMounted(() => {
    loadPromotions()
})
</script>

<style scoped>
.promotions-container {
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

.discount-tip {
    margin-left: 10px;
    color: #909399;
    font-size: 12px;
}

.products-header {
    margin-bottom: 20px;
}
</style> 