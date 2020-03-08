import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: window.localStorage.getItem('userInfo') != null
      ? JSON.parse(window.localStorage.getItem('userInfo'))
      : { username: '', uid: -1 }
  },
  mutations: {
    login (state, respond) {
      let user = {
        username: respond.username,
        uid: respond.uid
      }
      state.user = user
      window.localStorage.setItem('userInfo', JSON.stringify(user))
    },
    logout (state) {
      let user = { username: '', uid: -1 }
      state.user = user
      window.localStorage.setItem('userInfo', JSON.stringify(user))
    }
  }
})
