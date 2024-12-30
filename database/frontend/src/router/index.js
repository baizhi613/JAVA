import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../views/Layout.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Stock from '../views/Stock.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '',
          name: 'home',
          component: () => import('../views/Home.vue')
        },
        {
          path: 'products',
          name: 'products',
          component: () => import('../views/Products.vue')
        },
        {
          path: 'categories',
          name: 'categories',
          component: () => import('../views/Categories.vue')
        },
        {
          path: 'orders',
          name: 'orders',
          component: () => import('../views/Orders.vue')
        },
        {
          path: 'suppliers',
          name: 'suppliers',
          component: () => import('../views/Suppliers.vue')
        },
        {
          path: 'promotions',
          name: 'promotions',
          component: () => import('../views/Promotions.vue')
        },
        {
          path: 'users',
          name: 'users',
          component: () => import('../views/Users.vue')
        },
        {
          path: 'stock',
          name: 'stock',
          component: Stock,
          meta: {
            requiresAuth: true,
            title: '库存管理'
          }
        }
      ]
    }
  ]
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.path !== '/login' && to.path !== '/register' && !token) {
    next('/login')
  } else {
    next()
  }
})

export default router 