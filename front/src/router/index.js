import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      name: 'AppIndex',
      path: '/index',
      component: AppIndex
    }
  ]
})
