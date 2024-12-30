<template>
    <div class="product-promotion-container">
        <div class="header">
            <el-button type="primary" @click="dialogVisible = true">
                <el-icon><Plus /></el-icon>添加商品促销关系
            </el-button>
        </div>

        <el-table :data="productPromotions" border style="width: 100%" v-loading="loading">
            <el-table-column prop="productPromotionId" label="ID" width="80" />
            <el-table-column prop="productName" label="商品名称" />
            <el-table-column prop="promotionName" label="促销活动" />
            <el-table-column prop="discountRate" label="折扣率" width="100">
                <template #default="scope">
                    {{ (scope.row.discountRate * 100).toFixed(0) }}%
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150" fixed="right">
                <template #default="scope">
                    <el-button type="danger" @click="handleDelete(scope.row)">
                        <el-icon><Delete /></el-icon>
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 添加商品促销关系对话框 -->
        <el-dialog
            v-model="dialogVisible"
            title="添加商品促销关系"
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
                <el-form-item label="促销活动">
                    <el-select v-model="form.promotionId" placeholder="选择促销活动" style="width: 100%">
                        <el-option
                            v-for="promotion in promotions"
                            :key="promotion.promotionId"
                            :label="promotion.promotionName"
                            :value="promotion.promotionId"
                        />
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
import { Plus, Delete } from '@element-plus/icons-vue'
import { productPromotionApi, productApi, promotionApi } from '../api'

// 数据列表
const productPromotions = ref([])
const products = ref([])
const promotions = ref([])
const loading = ref(false)
const dialogVisible = ref(false)
const form = ref({
    productId: null,
    promotionId: null
})

// 加载商品促销关系列表
const loadProductPromotions = async () => {
    loading.value = true
    try {
        // 先加载商品和促销活动列表
        const [productsData, promotionsData] = await Promise.all([
            productApi.getAll(),
            promotionApi.getAll()
        ])
        
        products.value = productsData
        promotions.value = promotionsData
        
        // 获取所有促销活动的商品关系
        const allPromotionProducts = []
        for (const promotion of promotionsData) {
            const promotionProducts = await productPromotionApi.getByPromotionId(promotion.promotionId)
            allPromotionProducts.push(...promotionProducts)
        }
        
        // 处理数据，添加商品名称和促销活动名称
        productPromotions.value = allPromotionProducts.map(pp => {
            const product = productsData.find(p => p.productId === pp.productId)
            const promotion = promotionsData.find(p => p.promotionId === pp.promotionId)
            return {
                ...pp,
                productName: product?.productName,
                promotionName: promotion?.promotionName,
                discountRate: promotion?.discountRate
            }
        })
    } catch (error) {
        console.error('Failed to load data:', error)
        ElMessage.error('加载数据失败')
    } finally {
        loading.value = false
    }
}

// 处理删除
const handleDelete = (row) => {
    ElMessageBox.confirm('确定要删除该商品促销关系吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await productPromotionApi.delete(row.productPromotionId)
            ElMessage.success('删除成功')
            loadProductPromotions()
        } catch (error) {
            console.error('Failed to delete:', error)
            ElMessage.error('删除失败')
        }
    })
}

// 处理保存
const handleSave = async () => {
    try {
        if (!form.value.productId || !form.value.promotionId) {
            ElMessage.warning('请选择商品和促销活动')
            return
        }

        await productPromotionApi.create(form.value)
        ElMessage.success('添加成功')
        dialogVisible.value = false
        loadProductPromotions()
        
        // 重置表单
        form.value = {
            productId: null,
            promotionId: null
        }
    } catch (error) {
        console.error('Failed to save:', error)
        ElMessage.error('添加失败')
    }
}

// 初始化
onMounted(() => {
    loadProductPromotions()
})
</script>

<style scoped>
.product-promotion-container {
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