<template>
  <el-menu :default-active="'/index'"
    router
    class="menu"
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red">

    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name" style="min-width: 120px">
      {{ item.navItem }}
    </el-menu-item>
    <span class="welcome_title">调查问卷系统</span>
    <span class="welcome_username" >欢迎您，admin！</span>
    <el-button class="quit_btn" type="primary" v-on:click="exit">退出</el-button>
  </el-menu>
</template>

<script>
  export default {
    data () {
      return {
        navList: [
          {name: '/index', navItem: '首页'},
          {name: '/newform', navItem: '新建表单'},
          {name: '/myform', navItem: '我的表单'},
          {name: '/account', navItem: '账户管理'}
        ]
      }
    },
    methods: {
      exit () {
        this.$store.commit('logout')
        this.$router.replace({path: '/login'})
      }
    }
  }
</script>

<style scoped>
.menu {
  margin-top: -20px;
  min-width: 1300px;
}

.welcome_title {
  position: absolute;
  padding-top: 17px;
  right: 40%;
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
