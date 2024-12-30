<template>
    <div class="product-supplier-container">
        <div class="header">
            <el-button type="primary" @click="dialogVisible = true">
                <el-icon><Plus /></el-icon>添加商品供应商关系
            </el-button>
        </div>

        <el-table :data="productSuppliers" border style="width: 100%" v-loading="loading">
            <el-table-column prop="productSupplierId" label="ID" width="80" />
            <el-table-column prop="productName" label="商品名称" />
            <el-table-column prop="supplierName" label="供应商名称" />
            <el-table-column prop="supplyPrice" label="供应价格" width="120">
                <template #default="scope">
                    ¥{{ scope.row.supplyPrice }}
                </template>
            </el-table-column>
            <el-table-column prop="contactName" label="联系人" />
            <el-table-column prop="phone" label="联系电话" />
            <el-table-column label="操作" width="150" fixed="right">
                <template #default="scope">
                    <el-button type="danger" @click="handleDelete(scope.row)">
                        <el-icon><Delete /></el-icon>
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 添加商品供应商关系对话框 -->
        <el-dialog
            v-model="dialogVisible"
            title="添加商品供应商关系"
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
                <el-form-item label="供应商">
                    <el-select v-model="form.supplierId" placeholder="选择供应商" style="width: 100%">
                        <el-option
                            v-for="supplier in suppliers"
                            :key="supplier.supplierId"
                            :label="supplier.supplierName"
                            :value="supplier.supplierId"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="供应价格">
                    <el-input-number 
                        v-model="form.supplyPrice" 
                        :precision="2" 
                        :step="0.1" 
                        :min="0"
                        style="width: 100%"
                    />
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
import { productSupplierApi, productApi, supplierApi } from '../api'

// 数据列表
const productSuppliers = ref([])
const products = ref([])
const suppliers = ref([])
const loading = ref(false)
const dialogVisible = ref(false)
const form = ref({
    productId: null,
    supplierId: null,
    supplyPrice: 0
})

// 加载商品供应商关系列表
const loadProductSuppliers = async () => {
    loading.value = true
    try {
        // 先加载商品和供应商列表
        const [productsData, suppliersData] = await Promise.all([
            productApi.getAll(),
            supplierApi.getAll()
        ])
        
        products.value = productsData
        suppliers.value = suppliersData
        
        // 获取所有供应商的商品关系
        const allSupplierProducts = []
        for (const supplier of suppliersData) {
            const supplierProducts = await productSupplierApi.getBySupplierId(supplier.supplierId)
            allSupplierProducts.push(...supplierProducts)
        }
        
        // 处理数据，添加商品名称和供应商信息
        productSuppliers.value = allSupplierProducts.map(ps => {
            const product = productsData.find(p => p.productId === ps.productId)
            const supplier = suppliersData.find(s => s.supplierId === ps.supplierId)
            return {
                ...ps,
                productName: product?.productName,
                supplierName: supplier?.supplierName,
                contactName: supplier?.contactName,
                phone: supplier?.phone
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
    ElMessageBox.confirm('确定要删除该商品供应商关系吗？', '提示', {
        type: 'warning'
    }).then(async () => {
        try {
            await productSupplierApi.delete(row.productSupplierId)
            ElMessage.success('删除成功')
            loadProductSuppliers()
        } catch (error) {
            console.error('Failed to delete:', error)
            ElMessage.error('删除失败')
        }
    })
}

// 处理保存
const handleSave = async () => {
    try {
        if (!form.value.productId || !form.value.supplierId) {
            ElMessage.warning('请选择商品和供应商')
            return
        }
        if (form.value.supplyPrice <= 0) {
            ElMessage.warning('请输入有效的供应价格')
            return
        }

        await productSupplierApi.create(form.value)
        ElMessage.success('添加成功')
        dialogVisible.value = false
        loadProductSuppliers()
        
        // 重置表单
        form.value = {
            productId: null,
            supplierId: null,
            supplyPrice: 0
        }
    } catch (error) {
        console.error('Failed to save:', error)
        ElMessage.error('添加失败')
    }
}

// 初始化
onMounted(() => {
    loadProductSuppliers()
})
</script>

<style scoped>
.product-supplier-container {
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