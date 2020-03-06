<template>
    <div>
      <nav-menu></nav-menu>
      <router-view/>
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
body {
  background: #F5F5F5;
}
</style>
