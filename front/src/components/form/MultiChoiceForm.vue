<template>
  <div class="multi-choice-field">
    <span class="multi-num-text">选项数量</span>
    <el-input-number v-model="num" @change="changeNum" controls-position="right" :precision="0"
                     :min="2" :max="26" label="请输入数字" class="multi-num-input"></el-input-number>
    <span class="multi-num-text">最少选择数</span>
    <el-input-number v-model="minCheck" @change="changeMin" controls-position="right" :precision="0"
                     :min="1" :max="num" label="请输入数字" class="multi-num-input"></el-input-number>
    <span class="multi-num-text">最多选择数</span>
    <el-input-number v-model="maxCheck" @change="changeMax" controls-position="right" :precision="0"
                     :min="2" :max="num" label="请输入数字" class="multi-num-input"></el-input-number>

    <div v-for="(a, i) in answers" v-bind:key="i">
      <span class="multi-option-no">{{String.fromCharCode('A'.charCodeAt(0) + i) + '.'}}</span>
      <el-input class="multi-option" placeholder="请输入选项内容"
                v-model="a.text"></el-input>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'MultiChoice',
    data () {
      return {
        num: 2,
        minCheck: 1,
        maxCheck: 2,
        answers: [{text: ''}, {text: ''}]
      }
    },
    methods: {
      changeNum (value) {
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
        if (value < this.maxCheck) this.maxCheck = value
        if (value < this.minCheck) this.minCheck = value
      },
      changeMin (value) {
        if (value > this.maxCheck) this.maxCheck = value
      },
      changeMax (value) {
        if (value < this.minCheck) this.minCheck = value
      }
    }
  }
</script>

<style scoped>
.multi-choice-field {
  text-align: left;
  margin-top: 10px;
  margin-left: 40px;
}
.multi-num-text {
  /*margin-left: 20px;*/
}
.multi-num-input {
  margin-left: 15px;
  margin-right: 40px;
  width: 150px;
}
.multi-option-no {
  display: inline-block;
  min-width: 25px;
}
.multi-option {
  width: 90%;
  right: 0;
  margin-top: 15px;
}
</style>
