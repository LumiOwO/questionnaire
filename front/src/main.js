// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 设置反向代理，前端请求默认发送到 http://localhost:8080/api
let axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8080/api'
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next()
  } else if (store.state.user.username) {
    if (to.path === '/' || to.path === undefined) {
      next({path: '/index'})
    } else {
      next()
    }
  } else {
    next({
      path: '/login',
      query: {redirect: to.fullPath}
    })
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
