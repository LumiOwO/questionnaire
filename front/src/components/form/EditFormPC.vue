<template>
  <div style="width: 100%" v-if="formdata">
    <el-container class="edit-form-container" direction="horizontal">
      <el-scrollbar class="edit-form-left">
        <!--表单设置-->
        <div class="blue-header"></div>
        <div class="header-info-left">表单设置</div>
        <el-row type="flex" justify="space-between"
                class="ctrl-row">
          已注册用户才能填表
          <el-switch v-model="form.userOnly"
                     class="ctrl-switch"
                     active-color="#2e8fbc">
          </el-switch>
        </el-row>
        <el-row type="flex" justify="space-between"
                class="ctrl-row">
          每人限填一次
          <el-switch v-model="form.onceOnly"
                     class="ctrl-switch"
                     active-color="#2e8fbc">
          </el-switch>
        </el-row>
        <el-row type="flex" justify="space-between"
                class="ctrl-row">
          <span :style="{'color': form.onceOnly? '#2c3e50': '#dcdfe6'}">
            第二天可再次填写</span>
          <el-switch v-model="form.repeatDaily"
                     class="ctrl-switch" :disabled="!form.onceOnly"
                     active-color="#2e8fbc">
          </el-switch>
        </el-row>
        <el-row type="flex" justify="space-between"
                class="ctrl-row">
          截止时间
        </el-row>
        <el-row type="flex" justify="space-between"
                class="ctrl-row">
          <div>
            <el-date-picker
              v-model="form.dueDate"
              type="date"
              placeholder="选择日期"
              :picker-options="datePickerOptions">
            </el-date-picker>
          </div>
        </el-row>
        <el-row type="flex" justify="space-between"
                class="ctrl-row">
          <div>
            <el-time-picker v-model="form.dueTime" placeholder="选择时间"
                            :picker-options="{ selectableRange: '00:00:00 - 23:59:59'}">
            </el-time-picker>
          </div>
        </el-row>
        <el-divider></el-divider>
        <el-row type="flex" justify="space-between"
                class="ctrl-row">
          发布表单
          <el-switch v-model="form.published"
                     class="ctrl-switch"
                     active-color="#2e8fbc">
          </el-switch>
        </el-row>
        <el-row>
          <el-button type="primary" class="save-btn"
                     v-on:click="save">
            保存
          </el-button>
          <el-button type="primary" id="publish_btn" class="publish-btn"
                     v-on:click="generateLink" :disabled="!form.published">
            生成链接
          </el-button>
        </el-row>
      </el-scrollbar>

      <el-scrollbar class="edit-form-scroll" ref="mainScroll">
        <!--表单主界面-->
        <div class="blue-header"></div>
        <el-form class="edit-form-main">
          <transition-group name="list-transition" @before-leave="beforeLeave">
            <el-form-item class="edit-form-title" key="main-title">
              <el-input type="text" v-model="form.title" class="edit-form-title-text"
                        auto-complete="off" placeholder="表单标题"
                        maxlength="25" show-word-limit clearable></el-input>
              <el-input type="textarea" v-model="form.description" class="edit-form-desc-text"
                        auto-complete="off" placeholder="表单描述"
                        maxlength="50" show-word-limit :autosize="{minRows: 2}"></el-input>
            </el-form-item>
            <el-form-item class="edit-form-item" v-for="(q, num) in form.questions"
                          v-bind:key="q.id">
              <el-divider></el-divider>
              <el-row class="question-ctrl-buttons">
                <el-button icon="el-icon-arrow-up" size="mini"
                           circle v-on:click="moveup(q)"></el-button>
                <el-button icon="el-icon-arrow-down" size="mini"
                           circle v-on:click="movedown(q)"></el-button>
                <el-button icon="el-icon-delete" size="mini" type="danger"
                           circle v-on:click="deleteQ(q)"></el-button>
              </el-row>
              <el-row class="edit-question-info-row" type="flex" align="left">
                <span class="question-num">{{num+1}}</span>
                <div class="type-text"><span v-html="q.text"> </span></div>
                <span class="cascade-text">设置为级联问题</span>
                <el-switch v-model="q.hasParent" @change="beginSelect(q)"
                           class="cascade-switch"
                           active-color="#2e8fbc"></el-switch>
                <span v-if="q.hasParent" class="select-parent">
                  仅当单选题
                  <el-select class="select-parent-options" clearable
                             v-model="q.pQuestion" no-data-text="没有可用的单选题"
                             value-key="id" @change="selectParent(q)">
                    <el-option
                      class="select-parent-option"
                      v-for="item in singleChoices.filter((i) =>
                            {return form.questions.indexOf(i) < form.questions.indexOf(q)})"
                      :key="form.questions.indexOf(item) + 1"
                      :label="form.questions.indexOf(item) + 1"
                      :value="item"></el-option>
                  </el-select>
                  的
                  <el-select class="select-parent-options" clearable
                             v-model="q.pAnswer" no-data-text="请先选择单选题号"
                             value-key="id">
                    <el-option
                      v-for="(item, i) in q.pAnswers"
                      :key="String.fromCharCode('A'.charCodeAt(0) + i)"
                      :label="String.fromCharCode('A'.charCodeAt(0) + i)"
                      :value="item"></el-option>
                  </el-select>
                  选项选中时才显示该问题
                </span>
              </el-row>
              <el-input type="text"  class="edit-question-desc-text"
                        auto-complete="off" placeholder="问题描述"></el-input>
              <component v-bind:is="q.component" :ref="q.id.toString()"></component>
            </el-form-item>
          </transition-group>
        </el-form>
      </el-scrollbar>

      <el-scrollbar class="edit-form-right">
        <!--添加组件-->
        <div class="blue-header"></div>
        <div class="header-info-right">添加组件</div>
        <el-form class="edit-form-main">
          <el-form-item class="form-element" v-for="e in elements"
                        v-bind:key="e.text">
            <el-card class="form-element-card"
                     v-on:click.native="addQuestion(e)">
              <el-row type="flex" align="left" class="form-element-desc">
                <div class="icon-wrap"> <component v-bind:is="e.icon"></component> </div>
                <div class="desc-wrap"><span v-html="e.text"></span></div>
              </el-row>
            </el-card>
          </el-form-item>
        </el-form>
      </el-scrollbar>

    </el-container>
  </div>
</template>

<script>
import SingleChoiceForm from './SingleChoiceForm'
import MultiChoiceForm from './MultiChoiceForm'
import NumberLineForm from './NumberLineForm'
import TextLineForm from './TextLineForm'
import TextAreaForm from './TextAreaForm'
import PositionForm from './PositionForm'
import RankForm from './RankForm'

import SingleChoiceIcon from '../icons/SingleChoiceIcon'
import MultiChoiceIcon from '../icons/MultiChoiceIcon'
import NumberLineIcon from '../icons/NumberLineIcon'
import TextLineIcon from '../icons/TextLineIcon'
import TextAreaIcon from '../icons/TextAreaIcon'
import PositionIcon from '../icons/PositionIcon'
import RankIcon from '../icons/RankIcon'

import Question from './js/Question'
import Form from './js/Form'

export default {
  name: 'EditFormPC',
  props: {
    formdata: {
      type: Form
    },
    baseid: {
      default: 0
    }
  },
  components: {
    SingleChoiceForm,
    MultiChoiceForm,
    NumberLineForm,
    TextLineForm,
    TextAreaForm,
    PositionForm,
    RankForm,
    SingleChoiceIcon,
    MultiChoiceIcon,
    NumberLineIcon,
    TextLineIcon,
    TextAreaIcon,
    PositionIcon,
    RankIcon
  },
  data () {
    return {
      form: this.formdata,
      id_cnt: this.baseid,
      singleChoices: [],
      datePickerOptions: {
        disabledDate (time) {
          return time.getTime() < Date.now() - 24 * 3600 * 1000
        }
      },
      elements: [
        {
          component: 'SingleChoiceForm',
          text: '&nbsp;&nbsp;&nbsp;单选',
          icon: 'SingleChoiceIcon'
        },
        {
          component: 'MultiChoiceForm',
          text: '&nbsp;&nbsp;&nbsp;多选',
          icon: 'MultiChoiceIcon'
        },
        {
          component: 'RankForm',
          text: '&nbsp;&nbsp;&nbsp;评分',
          icon: 'RankIcon'
        },
        {
          component: 'NumberLineForm',
          text: '&nbsp;&nbsp;&nbsp;数字',
          icon: 'NumberLineIcon'
        },
        {
          component: 'TextLineForm',
          text: '单行文本',
          icon: 'TextLineIcon'
        },
        {
          component: 'TextAreaForm',
          text: '多行文本',
          icon: 'TextAreaIcon'
        },
        {
          component: 'PositionForm',
          text: '地理位置',
          icon: 'PositionIcon'
        }
      ]
    }
  },
  methods: {
    generateLink () {
    },
    save () {

    },
    addQuestion (element) {
      // 创建Question对象
      // let q = new Question(element, this.form.questions.length)
      let q = new Question(element, this.id_cnt++)
      this.form.questions.push(q)
      if (q.component === 'SingleChoiceForm') {
        this.singleChoices.push(q)
      }
      // 滚到底部
      let div = this.$refs['mainScroll'].$refs['wrap']
      this.$nextTick(() => {
        div.scrollTop = div.scrollHeight
      })
    },
    moveup (question) {
      let index = this.form.questions.indexOf(question)
      if (index > 0) {
        let q = this.form.questions
        let t = q[index]
        q.splice(index, 1, q[index - 1])
        q.splice(index - 1, 1, t)
        this.checkParentValid(q[index - 1])
      }
    },
    movedown (question) {
      let index = this.form.questions.indexOf(question)
      if (index < this.form.questions.length - 1) {
        let q = this.form.questions
        let t = q[index]
        q.splice(index, 1, q[index + 1])
        q.splice(index + 1, 1, t)
        this.checkParentValid(q[index])
      }
    },
    checkParentValid (question) {
      if (question.hasParent) {
        let q = this.form.questions
        if (q.indexOf(question.pQuestion) >= q.indexOf(question)) {
          question.pQuestion = undefined
          question.pAnswer = undefined
        }
      }
    },
    deleteQ (question) {
      let index = this.form.questions.indexOf(question)
      this.form.questions.splice(index, 1)
      index = this.singleChoices.indexOf(question)
      if (index >= 0) {
        this.singleChoices.splice(index, 1)
      }
    },
    beginSelect (question) {
      question.pQuestion = undefined
      question.pAnswer = undefined
      question.pAnswers = []
    },
    selectParent (question) {
      question.pAnswer = undefined
      question.pAnswers = !question.pQuestion ? []
        : this.$refs[question.pQuestion.id.toString()][0].getAnswers()
    },
    beforeLeave (el) {
      const {marginLeft, marginTop, width, height} = window.getComputedStyle(el)
      el.style.left = `${el.offsetLeft - parseFloat(marginLeft)}px`
      el.style.top = `${el.offsetTop - parseFloat(marginTop)}px`
      el.style.width = width
      el.style.height = height
    }
  }
}
</script>

<style scoped>
.edit-form-container {
  min-height: 620px;
  max-height: 620px;
  margin: 15px auto;
}

.edit-form-scroll {
  width: 100%;
  background: #fff;
}

.edit-form-main {
  margin: 0 10px 0 10px;
  background-clip: padding-box;
  background: #fff;
}

.edit-form-left, .edit-form-right {
  max-width: 18%;
  min-width: 18%;
  background-clip: padding-box;
  background: #fff;
  border: 1px solid #eaeaea;
}
.edit-form-left {
  margin-right: 20px;
}
.edit-form-right {
  margin-left: 20px;
}
.blue-header {
  background: #2e8fbc;
  height: 10px;
}
.header-info-left, .header-info-right {
  padding-top: 10px;
  color: #aaaaaa;
}
.header-info-left{
  padding-bottom: 40px;
}
.header-info-right {
  padding-bottom: 20px;
}
.ctrl-row {
  font-family: Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  margin-left: 25px;
  margin-right: 25px;
  margin-bottom: 35px;
}
.ctrl-switch {
  margin-top: 2px;
}

.save-btn {
  margin-right: 10px;
  width: 100px;
}
.publish-btn {
  margin-left: 10px;
  width: 100px;
}
.publish-btn:hover, .publish-btn:focus {
  background: #66b1ff;
  border-color: #66b1ff;
  color: #fff
}

.form-element {
  margin: 0 0 10px 0;
}

.form-element-card {
  height: 67px;
}

.form-element-card:hover {
  background: #2e8fbc;
}

.form-element-desc {
  min-width: 200px;
  margin-left: 45px;
  margin-right: 40px;
  margin-top: -5px;
  font-size: 20px;
}

.icon-wrap {
  margin-right: 25px;
  margin-top: 2px;
}

.desc-wrap {
  user-select: none;
}

.edit-form-title {
  margin: 25px 80px -10px 80px;
  transition: all 1.3s;
}
.edit-form-title-text {
  font-family: Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  font-size: 1.5rem;
}
.edit-form-desc-text {
  font-family: Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  font-size: 1rem;
}
.edit-question-desc-text {
  font-family: Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  font-size: 1rem;
  width: 91%;
  right: 1%;
}

.question-ctrl-buttons {
  position: absolute;
  right: 5px;
  top: 5px;
}

.edit-question-info-row {
  margin-left: 15px;
  margin-bottom: 15px;
  margin-top: 30px;
}

.question-num {
  margin-top: -20px;
  margin-left: 15px;
}

.type-text {
  margin-top: -20px;
  margin-left: 20px;
  font-weight: bold;
  min-width: 60px;
  text-align: left;
}

.cascade-text {
  margin-top: -20px;
  margin-left: 20px;
}

.cascade-switch {
  margin-top: -10px;
  margin-left: 10px;
}

.select-parent {
  margin-top: -20px;
  margin-left: 40px;
  user-select: none;
}

.select-parent-options {
  width: 130px;
}

.select-parent-option {
}

.edit-form-item {
  margin: 20px 10px 20px 10px;
  transition: all 1.3s;
  animation-fill-mode: forwards;
}
.list-transition-enter{
  opacity: 0;
  transform: translateX(500px);
}
.list-transition-leave-to {
  opacity: 0;
  transform: translateX(500px);
}
.list-transition-leave-active {
  position: absolute;
}
.list-transition-move {
  transition: transform 1s;
}
</style>
