<template>
  <div id="app">
    <transition name="switch-page" mode="out-in" appear>
      <router-view/>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    _isMobile () {
      return navigator.userAgent.match(
        /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i) != null
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

body {
  background: #fcf0e7;
}

.switch-page-enter-active, .switch-page-leave-active {
  transition: all .5s ease;
}
.switch-page-enter, .switch-page-leave-to{
  transform: translateY(-15px);
  opacity: 0;
}
.el-button--primary{
  color: #fff;
  background: #505458;
  border: none;
}
.el-scrollbar__wrap{
  overflow-x: hidden;
}
.el-textarea__inner {
  font-family: Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
</style>
