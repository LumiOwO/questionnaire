<template>
  <div v-if="!isMobile">
    <!--电脑端-->
    <el-form class="my-form">
      <el-form-item>
        <el-card>myform</el-card>
      </el-form-item>
    </el-form>
  </div>

  <div v-else>
    <!--移动端-->
  </div>
</template>

<script>
import App from '../../App'
export default {
  name: 'MyForm',
  data () {
    return {
      isMobile: App.methods._isMobile()
    }
  }
}
</script>

<style scoped>
.my-form {
  background-clip: padding-box;
  margin: 15px auto;
  min-height: 550px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
}
</style>
