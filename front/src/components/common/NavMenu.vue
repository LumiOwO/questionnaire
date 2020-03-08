<template>
  <el-menu :default-active="this.$route.path"
    router
    class="menu"
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="#2e8fbc">

    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name" style="min-width: 120px">
      {{ item.navItem }}
    </el-menu-item>
    <span class="welcome_title">调查问卷系统</span>
    <span class="welcome_username" >欢迎您，{{username}}！</span>
    <el-button class="quit_btn" type="primary" v-on:click="logout">退出</el-button>
  </el-menu>
</template>

<script>
  export default {
    data () {
      return {
        navList: [
          {name: '/newform', navItem: '新建表单'},
          {name: '/myform', navItem: '我的表单'},
          {name: '/index', navItem: '个人中心'}
        ],
        username: JSON.parse(window.localStorage.getItem('userInfo')).username
      }
    },
    methods: {
      logout () {
        this.$store.commit('logout')
        this.$router.replace({path: '/login'})
      }
    }
  }
</script>

<style>
.menu {
  margin-top: -20px;
  min-width: 1300px;
}

.welcome_title {
  position: absolute;
  padding-top: 17px;
  right: 44%;
  font-size: 20px;
  font-weight: bold;
  pointer-events: none;
}

.welcome_username {
  position: absolute;
  padding-top: 17px;
  right: 7%;
  font-size: 20px;
  pointer-events: none;
}

.quit_btn {
  position: absolute;
  margin-top: 10px;
  right: 2%;
  background: #505458;
  border: none;
}
</style>
