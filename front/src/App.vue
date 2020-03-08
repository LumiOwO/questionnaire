<template>
  <div id="app">
    <transition name="switch-page" mode="out-in" appear>
      <router-view/>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'App'
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

</style>
