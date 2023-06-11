import { createRouter, createWebHistory } from 'vue-router'

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
      path: '/home',
      name: 'home',
      component: () => import('@/views/normal/HomeView.vue')
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
        }
      ]

    }
  ]
})

export default router
