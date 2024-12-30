import axios from 'axios'

// 创建axios实例
const api = axios.create({
    baseURL: 'http://localhost:8080', // 后端API地址
    timeout: 5000, // 请求超时时间
    headers: {
        'Content-Type': 'application/json'
    }
})

// 请求拦截器
api.interceptors.request.use(
    config => {
        // 在发送请求之前做些什么
        console.log('发送请求:', {
            url: config.url,
            method: config.method,
            data: config.data,
            params: config.params
        })
        return config
    },
    error => {
        // 对请求错误做些什么
        console.error('请求拦截器错误:', error)
        return Promise.reject(error)
    }
)

// 响应拦截器
api.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        console.log('接收响应:', {
            url: response.config.url,
            status: response.status,
            data: response.data
        })
        return response.data
    },
    error => {
        // 对响应错误做点什么
        console.error('响应拦截器错误:', {
            url: error.config?.url,
            status: error.response?.status,
            data: error.response?.data,
            message: error.message
        })
        return Promise.reject(error)
    }
)

// 通用请求处理函数
const request = async (config) => {
    try {
        const response = await api(config)
        return response
    } catch (error) {
        console.error(`请求失败:`, error)
        throw error
    }
}

// 用户相关接口
export const userApi = {
    getById: (id) => api.get(`/users/${id}`),
    getAll: () => api.get('/users'),
    create: (data) => api.post('/users', data),
    update: (id, data) => api.put(`/users/${id}`, data),
    delete: (id) => api.delete(`/users/${id}`)
}

// 商品相关接口
export const productApi = {
    getById: (id) => api.get(`/products/${id}`),
    getAll: () => api.get('/products'),
    getByCategoryId: (categoryId) => api.get(`/products/category/${categoryId}`),
    create: (data) => api.post('/products', data),
    update: (id, data) => api.put(`/products/${id}`, data),
    delete: (id) => api.delete(`/products/${id}`),
    updateStock: (id, data) => api.put(`/products/${id}/stock`, data)
}

// 订单相关接口
export const orderApi = {
    getById: (id) => api.get(`/orders/${id}`),
    getAll: () => api.get('/orders'),
    create: (data) => api.post('/orders', data),
    createWithDetails: (data) => api.post('/orders/with-details', data),
    update: (id, data) => api.put(`/orders/${id}`, data),
    delete: (id) => api.delete(`/orders/${id}`)
}

// 订单详情相关接口
export const orderDetailApi = {
    getById: (id) => api.get(`/orderDetails/${id}`),
    getByOrderId: (orderId) => api.get(`/orderDetails/order/${orderId}`),
    create: (data) => api.post('/orderDetails', data),
    update: (id, data) => api.put(`/orderDetails/${id}`, data),
    delete: (id) => api.delete(`/orderDetails/${id}`)
}

// 类别相关接口
export const categoryApi = {
    getById: (id) => api.get(`/categories/${id}`),
    getAll: () => api.get('/categories'),
    create: (data) => api.post('/categories', data),
    update: (id, data) => api.put(`/categories/${id}`, data),
    delete: (id) => api.delete(`/categories/${id}`)
}

// 供应商相关接口
export const supplierApi = {
    getById: (id) => api.get(`/suppliers/${id}`),
    getAll: () => api.get('/suppliers'),
    create: (data) => api.post('/suppliers', data),
    update: (id, data) => api.put(`/suppliers/${id}`, data),
    delete: (id) => api.delete(`/suppliers/${id}`)
}

// 库存记录管理 API
export const stockRecordApi = {
    getAll: () => api.get('/stockRecords'),
    getById: (id) => api.get(`/stockRecords/${id}`),
    create: (data) => api.post('/stockRecords', data),
    update: (id, data) => api.put(`/stockRecords/${id}`, data),
    delete: (id) => api.delete(`/stockRecords/${id}`)
}

// 促销相关接口
export const promotionApi = {
    getById: (id) => api.get(`/promotions/${id}`),
    getAll: () => api.get('/promotions'),
    getActive: () => api.get('/promotions/active'),
    create: (data) => api.post('/promotions', data),
    update: (id, data) => api.put(`/promotions/${id}`, data),
    delete: (id) => api.delete(`/promotions/${id}`)
}

// 商品供应商关系接口
export const productSupplierApi = {
    getById: (id) => api.get(`/productSuppliers/${id}`),
    getByProductId: (productId) => api.get(`/productSuppliers/product/${productId}`),
    getBySupplierId: (supplierId) => api.get(`/productSuppliers/supplier/${supplierId}`),
    create: (data) => api.post('/productSuppliers', data),
    update: (id, data) => api.put(`/productSuppliers/${id}`, data),
    delete: (id) => api.delete(`/productSuppliers/${id}`),
    deleteByProductId: (productId) => api.delete(`/productSuppliers/product/${productId}`),
    deleteBySupplierId: (supplierId) => api.delete(`/productSuppliers/supplier/${supplierId}`)
}

// 商品促销关系接口
export const productPromotionApi = {
    getById: (id) => api.get(`/productPromotions/${id}`),
    getByProductId: (productId) => api.get(`/productPromotions/product/${productId}`),
    getByPromotionId: (promotionId) => api.get(`/productPromotions/promotion/${promotionId}`),
    create: (data) => api.post('/productPromotions', data),
    delete: (id) => api.delete(`/productPromotions/${id}`),
    deleteByProductId: (productId) => api.delete(`/productPromotions/product/${productId}`),
    deleteByPromotionId: (promotionId) => api.delete(`/productPromotions/promotion/${promotionId}`)
}

export default api 