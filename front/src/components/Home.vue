<template>
    <div>
      <nav-menu></nav-menu>
      <div>
        <transition name="fade" mode="out-in">
          <router-view/>
        </transition>
      </div>
    </div>
</template>

<script>
    import NavMenu from './common/NavMenu'
    export default {
      name: 'Home',
      components: {NavMenu}
    }
</script>

<style>
.fade-enter-active, .fade-leave-active {
  transition: all .5s ease;
}
.fade-enter, .fade-leave-to{
  opacity: 0;
}
</style>
