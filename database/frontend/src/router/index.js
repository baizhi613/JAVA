import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../views/Layout.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta: {
        title: '登录 - 超市管理系统'
      }
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
      meta: {
        title: '注册 - 超市管理系统'
      }
    },
    {
      path: '/home',
      component: Layout,
      children: [
        {
          path: '',
          name: 'home',
          component: () => import('../views/Home.vue'),
          meta: {
            title: '主页 - 超市管理系统'
          }
        },
        {
          path: 'products',
          name: 'products',
          component: () => import('../views/Products.vue'),
          meta: {
            title: '商品管理 - 超市管理系统'
          }
        },
        {
          path: 'categories',
          name: 'categories',
          component: () => import('../views/Categories.vue'),
          meta: {
            title: '分类管理 - 超市管理系统'
          }
        },
        {
          path: 'orders',
          name: 'orders',
          component: () => import('../views/Orders.vue'),
          meta: {
            title: '订单管理 - 超市管理系统'
          }
        },
        {
          path: 'suppliers',
          name: 'suppliers',
          component: () => import('../views/Suppliers.vue'),
          meta: {
            title: '供应商管理 - 超市管理系统'
          }
        },
        {
          path: 'promotions',
          name: 'promotions',
          component: () => import('../views/Promotions.vue'),
          meta: {
            title: '促销管理 - 超市管理系统'
          }
        },
        {
          path: 'users',
          name: 'users',
          component: () => import('../views/Users.vue'),
          meta: {
            title: '用户管理 - 超市管理系统'
          }
        },
        {
          path: 'stock',
          name: 'stock',
          component: () => import('../views/Stock.vue'),
          meta: {
            title: '库存管理 - 超市管理系统'
          }
        },
        {
          path: 'product-promotions',
          name: 'productPromotions',
          component: () => import('../views/ProductPromotion.vue'),
          meta: {
            title: '商品促销关系 - 超市管理系统'
          }
        },
        {
          path: 'product-suppliers',
          name: 'productSuppliers',
          component: () => import('../views/ProductSupplier.vue'),
          meta: {
            title: '商品供应商关系 - 超市管理系统'
          }
        }
      ]
    }
  ]
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  document.title = to.meta.title || '超市管理系统'
  
  // 检查登录状态
  const token = localStorage.getItem('token')
  if (to.path !== '/login' && to.path !== '/register' && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router 