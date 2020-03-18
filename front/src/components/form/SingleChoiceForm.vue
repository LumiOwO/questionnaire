<template>
  <div class="single-choice-field">
    <span class="single-num-text">选项数量</span>
    <el-input-number v-model="num" @change="handleChange" controls-position="right" :precision="0"
                     :min="2" :max="26" label="请输入数字" class="single-num-input"></el-input-number>
    <div v-for="(a, i) in answers" v-bind:key="i">
      <span class="single-option-no">{{String.fromCharCode('A'.charCodeAt(0) + i) + '.'}}</span>
      <el-input class="single-option" placeholder="请输入选项内容"
                v-model="a.text"></el-input>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SingleChoice',
  data () {
    return {
      num: 2,
      answers: [{text: ''}, {text: ''}]
    }
  },
  methods: {
    handleChange (value) {
      let delta = value - this.answers.length
      if (delta > 0) {
        for (let i = 0; i < delta; i++) {
          this.answers.push({text: ''})
        }
      } else if (delta < 0) {
        for (let i = 0; i < -delta; i++) {
          this.answers.pop()
        }
      }
    },
    getAnswers () {
      return this.answers
    }
  }
}
</script>

<style scoped>
.single-choice-field {
  text-align: left;
  margin-top: 10px;
  margin-left: 40px;
}
.single-num-text {
  /*margin-left: 20px;*/
}
.single-num-input {
  margin-left: 15px;
  width: 150px;
}
.single-option-no {
  display: inline-block;
  min-width: 25px;
}
.single-option {
  width: 90%;
  right: 0;
  margin-top: 15px;
}
</style>
