<template>
  <div class="numberline-choice-field">
    <div style="margin-bottom: 10px">
      <span class="numberline-num-text">保留小数位</span>
      <el-input-number v-model="precision" controls-position="right" :precision="0" @change="changePrecision"
                       :step="1" :min="0" :max="5" label="请输入数字"
                       class="numberline-num-input"></el-input-number>
      <span class="numberline-num-text">限定输入范围</span>
      <el-switch v-model="hasBound" class="numberline-num-switch"
                 active-color="#2e8fbc"></el-switch>
    </div>
    <div v-if="hasBound">
      <span class="numberline-num-text">最小值</span>
      <el-input-number v-model="min" controls-position="right" :precision="precision" @change="changeMin"
                       :step="step" label="请输入数字" class="numberline-num-input"></el-input-number>
      <span class="numberline-num-text">最大值</span>
      <el-input-number v-model="max" controls-position="right" :precision="precision" @change="changeMax"
                       :step="step" label="请输入数字" class="numberline-num-input"></el-input-number>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'NumberLine',
    data () {
      return {
        precision: 0,
        step: 1,
        hasBound: false,
        min: 0,
        max: 10
      }
    },
    methods: {
      changePrecision (value) {
        let s = 1
        for (let i = 0; i < value; i++) {
          s *= 0.1
        }
        this.step = s
      },
      changeMin (value) {
        if (value > this.max) this.max = value
      },
      changeMax (value) {
        if (value < this.min) this.min = value
      }
    }
  }
</script>

<style scoped>
.numberline-choice-field {
  text-align: left;
  margin-top: 10px;
  margin-left: 40px;
}
.numberline-num-text {
  display: inline-block;
  min-width: 100px;
}
.numberline-num-input {
  margin-left: 15px;
  margin-right: 40px;
  width: 150px;
}
.numberline-num-switch {
  margin-left: 15px;
}
</style>
