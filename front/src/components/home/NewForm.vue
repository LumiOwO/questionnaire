<template>
  <div>
    <edit-form :form-data="newform"></edit-form>
  </div>
</template>

<script>
import EditForm from '@/components/form/EditForm'
export default {
  name: 'NewForm',
  components: {EditForm},
  data () {
    return {
      newform: {
        title: '',
        questions: [],
        onceOnly: false,
        userOnly: false,
        repeatDaily: false,
        published: false,
        dueDate: Date.now() + 24 * 3600 * 1000,
        dueTime: Date.now() + 24 * 3600 * 1000
      }
    }
  }
}
</script>

<style scoped>
</style>
