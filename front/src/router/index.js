import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '@/components/home/AppIndex'
import NewForm from '@/components/home/NewForm'
import MyForm from '@/components/home/MyForm'

import Login from '@/components/Login'
import Home from '@/components/Home'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        title: '登录'
      }
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
          component: AppIndex,
          meta: {
            title: '个人中心'
          }
        },
        {
          path: '/newform',
          name: 'NewForm',
          component: NewForm,
          meta: {
            title: '新建表单'
          }
        },
        {
          path: '/myform',
          name: 'MyForm',
          component: MyForm,
          meta: {
            title: '我的表单'
          }
        }
      ]
    },
    {
      path: '*',
      name: 'default',
      redirect: '/index',
      meta: {
        title: '个人中心'
      }
    }
  ]
})
