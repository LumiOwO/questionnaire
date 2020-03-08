<template>
  <div v-if="!isMobile">
    <!--PC端登录界面-->
    <transition name="slide-up" mode="out-in" key="login-UI">
      <el-form v-if="!showSignupUI" key="login"
               class="login-container" label-position="left" label-width="0px">
        <h3 class="login-title">登录</h3>
        <el-form-item>
          <el-input type="text" v-model="userInfo.email" v-on:keyup.enter.native="login"
                    auto-complete="off" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="userInfo.password" v-on:keyup.enter.native="login"
                    auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
          <el-button class="btn" type="primary" v-on:click="login">登录</el-button>
          <el-button class="btn" type="primary" v-on:click="switchUI">注册</el-button>
        </el-form-item>
      </el-form>

      <el-form v-else key="signup"
               class="login-container" label-position="left" label-width="0px">
        <h3 class="login_title">注册</h3>
        <el-form-item>
          <el-input type="text" v-model="userInfo.email" v-on:keyup.enter.native="signup"
                    auto-complete="off" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="text" v-model="userInfo.username" v-on:keyup.enter.native="signup"
                    auto-complete="off" placeholder="昵称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password" v-model="userInfo.password" v-on:keyup.enter.native="signup"
                    auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
          <el-button class="btn" type="primary" v-on:click="signup">确定</el-button>
          <el-button class="btn" type="primary" v-on:click="switchUI">取消</el-button>
        </el-form-item>
      </el-form>
    </transition>
  </div>

  <div v-else>
    <!--移动端登录界面-->
    <transition name="slide-up" mode="out-in" key="login-UI">
      <div class="login-container-mobile" v-if="!showSignupUI" key="login-mobile">
        <h2 class="login-title-mobile">登录</h2>
        <mt-field type="text" v-model="userInfo.email"
                  auto-complete="off" placeholder="邮箱"> </mt-field>
        <mt-field type="password" v-model="userInfo.password"
                  auto-complete="off" placeholder="密码"> </mt-field>
        <mt-button class="btn-mobile" type="primary" v-on:click="login">登录</mt-button>
        <mt-button class="btn-mobile" type="primary" v-on:click="switchUI">注册</mt-button>
      </div>
      <div class="login-container-mobile" v-else key="signup-mobile">
        <h2 class="login-title-mobile">注册</h2>
        <mt-field type="text" v-model="userInfo.email"
                  auto-complete="off" placeholder="邮箱"> </mt-field>
        <mt-field type="text" v-model="userInfo.username"
                  auto-complete="off" placeholder="昵称"> </mt-field>
        <mt-field type="password" v-model="userInfo.password"
                  auto-complete="off" placeholder="密码"> </mt-field>
        <mt-button class="btn-mobile" type="primary" v-on:click="signup">确定</mt-button>
        <mt-button class="btn-mobile" type="primary" v-on:click="switchUI">取消</mt-button>
      </div>
      </transition>
  </div>
</template>

<script>
import App from '../App'
import { MessageBox } from 'mint-ui'

export default {
  name: 'Login',
  data () {
    return {
      userInfo: {
        username: '',
        password: '',
        email: ''
      },
      showSignupUI: false,
      isMobile: App.methods._isMobile()
    }
  },
  methods: {
    login () {
      this.$axios
        .post('/login', {
          email: this.userInfo.email,
          password: this.userInfo.password
        })
        .then(successResponse => {
          if (successResponse.data.succeed) {
            this.$store.commit('login', successResponse.data)
            let path = this.$route.query.redirect
            this.$router.replace({path:
                path === '/' || path === undefined ? '/index' : path})
          } else {
            this.alertError(successResponse.data.msg)
              .catch(() => {})
          }
        })
        .catch(failResponse => {
          this.alertError('未知错误')
            .catch(() => {})
        })
    },
    signup () {
      this.$axios
        .post('/signup', {
          username: this.userInfo.username,
          password: this.userInfo.password,
          email: this.userInfo.email
        })
        .then(successResponse => {
          if (successResponse.data.succeed) {
            this.alertInfo(successResponse.data.msg)
              .then(() => { this.switchUI() })
              .catch(() => {})
          } else {
            this.alertError(successResponse.data.msg)
              .catch(() => {})
          }
        })
        .catch(failResponse => {
          this.alertError('未知错误')
            .catch(() => {})
        })
    },
    switchUI () {
      this.showSignupUI = !this.showSignupUI
      this.userInfo = {
        username: '',
        password: '',
        email: ''
      }
    },
    alertError (msg) {
      if (!this.isMobile) {
        // PC端
        return this.$alert(msg, '错误', {
          confirmButtonText: '确定',
          type: 'error'
        })
      } else {
        // 移动端
        return MessageBox.alert(msg, '错误')
      }
    },
    alertInfo (msg) {
      if (!this.isMobile) {
        // PC端
        return this.$alert(msg, '提示', {
          confirmButtonText: '确定',
          type: 'info'
        })
      } else {
        // 移动端
        return MessageBox.alert(msg, '提示')
      }
    }
  }
}
</script>

<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 120px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login-title {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #505458;
}

.btn {
  width: 30%;
  background: #505458;
  border: none;
  margin: 10px 20px auto 20px;
}

.login-container-mobile {
  border-radius: 1rem;
  background-clip: padding-box;
  margin: 4rem auto;
  width: 16.5rem;
  padding: 2rem 2rem 1rem 2rem;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 1.5rem #cac6c6;
}

.login-title-mobile {
  margin: 0 auto 2rem auto;
  text-align: center;
  color: #505458;
}

.btn-mobile {
  width: 37%;
  background: #505458;
  border: none;
  margin: 1.5rem 0.5rem 1rem 0.5rem;
}

.slide-up-enter-active, .slide-up-leave-active {
  transition: all .5s ease;
}
.slide-up-enter, .slide-up-leave-to{
  transform: translateY(-40px);
  opacity: 0;
}
</style>
