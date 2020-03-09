<template>
  <div v-if="!isMobile">
    <!--电脑端-->
    <edit-form-p-c :form-data="formData"></edit-form-p-c>
  </div>

  <div v-else>
    <!--移动端-->
    移动端
  </div>
</template>

<script>
import App from '../../App'
import EditFormPC from './EditFormPC'

export default {
  name: 'EditForm',
  props: ['formData'],
  components: {EditFormPC},
  data () {
    return {
      isMobile: App.methods._isMobile()
    }
  }
}
</script>

<style scoped>
</style>
