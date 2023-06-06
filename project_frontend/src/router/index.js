import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login&register',
      component: () => import('@/views/LoginView.vue'),
      children: [
        {
          path: '',
          name: 'login',
          component: () => import('@/components/login&register/LoginPage.vue'),
        }
      ]
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('@/views/IndexView.vue'),

    }
  ]
})

export default router
