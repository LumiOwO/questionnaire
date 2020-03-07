<template>
  <transition name="fade" mode="out-in">
    <el-form v-if="!showSignupUI" key="login"
             class="login-container" label-position="left" label-width="0px">
      <h3 class="login_title">登录</h3>
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
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      userInfo: {
        username: '',
        password: '',
        email: ''
      },
      showSignupUI: false
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
            this.$store.commit('login', successResponse.data.username)
            this.$router.replace({path: '/index'})
          } else {
            alert(successResponse.data.msg)
          }
        })
        .catch(failResponse => {
          alert('未知错误')
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
          alert(successResponse.data.msg)
          if (successResponse.data.succeed) {
            this.switchUI()
          }
        })
        .catch(failResponse => {
          alert('未知错误')
        })
    },
    switchUI () {
      this.showSignupUI = !this.showSignupUI
      this.userInfo = {
        username: '',
        password: '',
        email: ''
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

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.btn {
  width: 30%;
  background: #505458;
  border: none;
  margin: 10px 20px auto 20px;
}

.fade-enter-active, .fade-leave-active {
  transition: all .5s ease;
}
.fade-enter, .fade-leave-to{
  transform: translateY(-40px);
  opacity: 0;
}
</style>
