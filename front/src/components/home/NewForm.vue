<template>
  <div>
    <edit-form></edit-form>
  </div>
</template>

<script>
import EditForm from '@/components/form/EditForm'
export default {
  name: 'NewForm',
  components: {EditForm},
  beforeRouteLeave (to, from, next) {
    this.$confirm('正在离开本页面，本页面内所有未保存数据都会丢失', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      cancelButtonClass: 'btn-custom-cancel',
      type: 'warning'
    }).then(() => {
      next()
    }).catch(() => {
      next(false)
    })
  }
}
</script>

<style scoped>
</style>
