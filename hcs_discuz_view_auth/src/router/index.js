import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/login/Login.vue'
import Register from '../views/register/Register.vue'
import ResetPassword from '../views/resetpassword/ResetPassword'

const routes = [
  {
    path: '/',
    name: '/',
    component: Login
  },
  {
    path: '/register',
    name: '/register',
    component: Register
  },
  {
    path: '/resetpassword',
    name: '/resetpassword',
    component: ResetPassword
  },
]

const router = createRouter({
  //history: createWebHistory(process.env.BASE_URL),
  history: createWebHashHistory(),
  routes
})

export default router
