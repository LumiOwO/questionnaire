<template>
  <div class="textarea-choice-field">
    <span class="textarea-num-text">字数限制（50~200字）</span>
    <el-input-number v-model="num" controls-position="right" :precision="0"
                     :min="50" :max="200" label="请输入数字" class="textarea-num-input"></el-input-number>
  </div>
</template>

<script>
  export default {
    name: 'TextArea',
    data () {
      return {
        num: 100
      }
    }
  }
</script>

<style scoped>
  .textarea-choice-field {
    text-align: left;
    margin-top: 10px;
    margin-left: 40px;
  }
  .textarea-num-text {
    display: inline-block;
    min-width: 145px;
  }
  .textarea-num-input {
    margin-left: 15px;
    width: 150px;
  }
</style>
