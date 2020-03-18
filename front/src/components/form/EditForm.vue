<template>
  <div v-if="!isMobile">
    <!--电脑端-->
    <edit-form-p-c :formdata="formdata"></edit-form-p-c>
  </div>

  <div v-else>
    <!--移动端-->
    移动端
  </div>
</template>

<script>
import App from '../../App'
import EditFormPC from './EditFormPC'
import Form from './js/Form'

export default {
  name: 'EditForm',
  props: {
    formdata: {
      type: Form,
      default: () => { return new Form(false) }
    }
  },
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
