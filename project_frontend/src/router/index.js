import { createRouter, createWebHistory } from 'vue-router'
import store from "@/stores/store";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import('@/views/LoginView.vue'),
      children: [
        {
          path: 'login',
          name: 'login',
          component: () => import('@/components/loginregister/LoginPage.vue'),
        },
        {
          path: 'register',
          name: 'register',
          component: () => import('@/components/loginregister/RegisterPage.vue'),
        }
      ]
    },
    {
      path: '/error',
      name: 'error',
      component: () => import('@/views/Error.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('@/views/normal/HomeView.vue'),
      children: [
        {
          path: '',
          name: 'home',
          component: () => import('@/components/normal/HomePage.vue')
        },
        {
          path: 'add-information',
          name: '添加信息',
          component: () => import('@/components/normal/AddPage.vue')
        },
        {
          path: 'profile',
          name: '个人信息',
          component: () => import('@/components/normal/ProFile.vue')
        },
      ]
    },
    {
      path: '/backend/index',
      name: 'backend-index',
      component: () => import('@/views/backend/IndexView.vue'),
      children: [
        {
          path: 'overview',
          name: 'overview',
          component: () => import('@/components/backend/overview.vue'),
        },
        {
          path: 'analysis',
          name: 'data-analysis',
          component: () => import('@/components/backend/analysis.vue'),
        },
        {
          path: 'informationmanagement',
          name: 'information-management',
          component: () => import('@/components/backend/InformationManagement.vue'),
        },
        {
          path: 'usermanagement',
          name: 'user-management',
          component: () => import('@/components/backend/UserManagement.vue'),
        },
        {
          path: 'batchAdd',
          name: 'batch-add',
          component: () => import('@/components/backend/BatchAddPage.vue')
        },
        {
          path: 'profile',
          name: 'backend-profile',
          component: () => import('@/components/normal/ProFile.vue'),
        }
      ]

    }
  ]
})

router.beforeEach((to, from, next) => {
  store.commit('loadUser')
  if (store.state.user.type !== 'user' && store.state.user.type !== 'admin') {
    if (to.name !== 'error' && to.name !== 'login' && to.name !== 'register') {
      next({path: '/error'})
    }
    next()
  } else {
    if (store.state.user.type === 'admin') {
      next()
    } else if (store.state.user.type === 'user') {
      if (to.name === 'backend-index') {
        next({path: '/error'})
      }
      next()
    } else {
      next({path: '/error'})
    }
  }
})

export default router
