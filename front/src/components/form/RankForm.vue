<template>
  <div class="rank-choice-field">
    <span class="rank-text">差评文字</span>
    <el-input v-model="bad_text" class="rank-input"
                     placeholder="默认值：不满意"></el-input>
    <span class="rank-text">好评文字</span>
    <el-input v-model="good_text" class="rank-input"
              placeholder="默认值：满意"></el-input>
  </div>
</template>

<script>
export default {
  name: 'RankForm',
  data () {
    return {
      bad_text: '',
      good_text: ''
    }
  }
}
</script>

<style scoped>
.rank-choice-field {
  text-align: left;
  margin-top: 10px;
  margin-left: 40px;
}
.rank-text {
  /*margin-left: 20px;*/
}
.rank-input {
  margin-left: 15px;
  margin-right: 40px;
  width: 150px;
}
</style>
