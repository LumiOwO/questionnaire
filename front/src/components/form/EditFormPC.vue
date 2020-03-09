<template>
  <div style="width: 100%">
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
        <el-form class="edit-form-main">
          <transition-group name="list-transition">
            <el-form-item class="edit-form-title" key="main-title">
              <el-input type="text" v-model="form.title" class="edit-form-title-text"
                        auto-complete="off" placeholder="请输入表单标题"></el-input>
            </el-form-item>
            <el-form-item class="edit-form-item" v-for="v in form.questions"
                          v-bind:key="v.id">
              <el-divider></el-divider>
              <el-card>
                {{v.text}}
              </el-card>
            </el-form-item>
          </transition-group>
        </el-form>
      </el-scrollbar>

      <el-scrollbar class="edit-form-right">
        <!--添加组件-->
        <div class="blue-header"></div>
        <div class="header-info-right">添加组件</div>
        <el-form class="edit-form-main">
          <el-form-item class="form-element" v-for="(e, i) in elements"
                        v-bind:key="i">
            <el-card class="form-element-card"
                     v-on:click.native="addQuestion(e)">
              <i :class="e.iconClass"></i>
              {{e.type}}
            </el-card>
          </el-form-item>
        </el-form>
      </el-scrollbar>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'EditFormPC',
  props: ['formData'],
  data () {
    return {
      datePickerOptions: {
        disabledDate (time) {
          return time.getTime() < Date.now() - 24 * 3600 * 1000
        }
      },
      form: this.formData,
      elements: [
        {
          name: 'single',
          type: '单选',
          iconClass: 'el-icon-info'
        },
        {
          name: 'multi',
          type: '多选',
          iconClass: 'el-icon-info'
        },
        {
          name: 'rank',
          type: '评分',
          iconClass: 'el-icon-info'
        },
        {
          name: 'number',
          type: '数字',
          iconClass: 'el-icon-info'
        },
        {
          name: 'singleLineText',
          type: '单行文本',
          iconClass: 'el-icon-info'
        },
        {
          name: 'multiLineText',
          type: '多行文本',
          iconClass: 'el-icon-info'
        }
      ]
    }
  },
  methods: {
    generateLink () {

    },
    save () {

    },
    addQuestion (question) {
      // 深拷贝
      this.form.questions.push({
        text: question.name,
        id: this.form.questions.length
      })
      let div = this.$refs['mainScroll'].$refs['wrap']
      this.$nextTick(() => {
        div.scrollTop = div.scrollHeight
      })
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

.form-element-card:hover {
  background: #2e8fbc;
}

.edit-form-title {
  background: #2e8fbc;
  margin: 25px 80px -10px 80px;
  padding-top: 30px;
  padding-bottom: 30px;
}
.edit-form-title-text {
  font-family: Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  font-size: 1.7rem;
  background: #2e8fbc;
  width: 75%;
}

.edit-form-item {
  margin: 20px 10px 20px 10px;
  transition: all 1.3s;
}
.list-transition-enter, .list-transition-leave-to {
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
