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
          path: '',
          name: 'login',
          component: () => import('@/components/loginregister/LoginPage.vue'),
        }
      ]
    },
    {
      path: '/backend/index',
      name: 'backend-index',
      component: () => import('@/views/backend/IndexView.vue'),

    }
  ]
})

export default router
