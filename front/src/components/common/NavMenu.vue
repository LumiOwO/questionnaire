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
    <span class="welcome-title">调查问卷系统</span>
    <span class="welcome-username" >欢迎您，{{username}}！</span>
    <el-button class="quit-btn" type="primary" v-on:click="logout">退出</el-button>
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
        this.$confirm('确定要退出吗？', '提示', {
          cancelButtonClass: 'btn-custom-cancel',
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          this.$store.commit('logout')
          this.$router.replace({path: '/login'})
        }).catch(() => {

        })
      }
    }
  }
</script>

<style>
.menu {
  margin-top: -20px;
  min-width: 1300px;
}

.welcome-title {
  position: absolute;
  padding-top: 17px;
  right: 44%;
  font-size: 20px;
  font-weight: bold;
  pointer-events: none;
}

.welcome-username {
  position: absolute;
  padding-top: 17px;
  right: 7%;
  font-size: 20px;
  pointer-events: none;
}

.quit-btn {
  position: absolute;
  margin-top: 12px;
  right: 2%;
  background: #505458;
  border: none;
}
.btn-custom-cancel {
  float: right;
  margin-left: 10px;
}
</style>
