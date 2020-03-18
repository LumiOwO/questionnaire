<template>
  <div class="textline-choice-field">
    <span class="textline-num-text">字数限制（5~50字）</span>
    <el-input-number v-model="num" controls-position="right" :precision="0"
                     :min="5" :max="50" label="请输入数字" class="textline-num-input"></el-input-number>
  </div>
</template>

<script>
export default {
  name: 'TextLine',
  data () {
    return {
      num: 25
    }
  }
}
</script>

<style scoped>
.textline-choice-field {
  text-align: left;
  margin-top: 10px;
  margin-left: 40px;
}
.textline-num-text {
  display: inline-block;
  min-width: 145px;
}
.textline-num-input {
  margin-left: 15px;
  width: 150px;
}
</style>
