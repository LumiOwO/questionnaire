<template>
  <div v-if="!isMobile">
    <!--电脑端界面-->
    <nav-menu></nav-menu>
    <div>
      <transition name="fade" mode="out-in">
        <router-view/>
      </transition>
    </div>
  </div>
  <div v-else>
    <!--手机端界面-->
    <!--弹出框-->
    <mt-popup v-model="showPopUp" position="center"
              popup-transition="popup-fade" class="popup">
      <mt-picker :slots="slots" @change="onValuesChange" class="popup-picker" value-key="key"
                 item-height="70" visible-item-count="3" showToolbar ref="picker">
        <div class="picker-toolbar-title">
          <div class="usi-btn-cancel" @click="switchPopUpShowing">取消</div>
          <div class="">切换页面</div>
          <div class="usi-btn-sure" @click="switchPage">确定</div>
        </div>
      </mt-picker>
    </mt-popup>
    <!--主界面-->
    <div class="welcome-panel-mobile">
      <span class="welcome-title-mobile">调查问卷系统</span>
      <span v-on:click="switchPopUpShowing" class="welcome-btn-mobile">
        <i class="mint-toast-icon mintui mintui-more"></i>
      </span>
    </div>
    <div>
      <transition name="fade" mode="out-in">
        <router-view/>
      </transition>
    </div>
  </div>
</template>

<script>
  import App from '../App'
  import NavMenu from './common/NavMenu'
  import { MessageBox } from 'mint-ui'

  export default {
    name: 'Home',
    components: {NavMenu},
    data () {
      return {
        isMobile: App.methods._isMobile(),
        value: null,
        slots: [{
          values: [
            {key: '新建表单', path: '/newform'},
            {key: '我的表单', path: '/myform'},
            {key: '个人中心', path: '/index'},
            {key: '退出', path: '/login'}
          ],
          textAlign: 'center',
          defaultIndex: 1
        }],
        showPopUp: false
      }
    },
    methods: {
      onValuesChange (picker, values) {
        this.value = values[0]
      },
      switchPopUpShowing () {
        this.showPopUp = !this.showPopUp
      },
      switchPage () {
        this.switchPopUpShowing()
        let path = this.value.path
        if (path === '/login') {
          // 退出
          MessageBox({
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            message: '确定要退出吗?',
            showCancelButton: true})
            .then(action => {
              if (action === 'confirm') {
                this.$store.commit('logout')
                this.$router.replace({path: '/login'})
              }
            })
            .catch(() => {

            })
        } else if (this.$route.path !== path) {
          this.$router.replace(this.value.path)
        }
      }
    }
  }
</script>

<style>
.fade-enter-active, .fade-leave-active {
  transition: all .5s ease;
}
.fade-enter, .fade-leave-to{
  opacity: 0;
}

.popup {
  border-radius: 0.8rem;
}

.popup-picker {
  width: 20rem;
}

.picker-toolbar-title {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  height: 40px;
  line-height: 40px;
  font-size: 16px;
}
.usi-btn-cancel, .usi-btn-sure {
  color: #FF6600
}

.welcome-panel-mobile {
  background-clip: padding-box;
  height: 2rem;
  margin-top: -3rem;
  padding: 0.5rem 0.5rem 0.5rem 0.5rem;
  background: #fff;
  border: 1px solid #eaeaea;
  border-radius: 0.3rem;
}

.welcome-title-mobile {
  position: absolute;
  right: 33%;
  font-size: 20px;
  font-weight: bold;
  pointer-events: none;
}

.welcome-btn-mobile{
  position: absolute;
  margin-top: 0.4rem;
  right: 6%;
  background: none;
  border: none;
}
</style>
