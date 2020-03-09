import Vue from 'vue'
import Router from 'vue-router'

import Login from '@/components/Login'
import Home from '@/components/Home'

import AppIndex from '@/components/home/AppIndex'
import NewForm from '@/components/home/NewForm'
import MyForm from '@/components/home/MyForm'

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
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex
        },
        {
          path: '/newform',
          name: 'NewForm',
          component: NewForm
        },
        {
          path: '/myform',
          name: 'MyForm',
          component: MyForm
        }
      ]
    },
    {
      path: '*',
      name: 'default',
      redirect: '/index'
    }
  ]
})
