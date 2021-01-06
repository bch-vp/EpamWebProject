<template>
  <v-app>
    <v-app-bar dark app>
      <v-toolbar-title class="font-weight-medium" style="margin-left: 2%">HotelWeb</v-toolbar-title>
      <v-btn v-on:click="showHome" outlined text rounded
             style="margin-left: 2%">
        <v-icon>home</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn v-on:click="showSignIn" outlined text rounded
             style="margin-right: 1%">Sign in
      </v-btn>
      <v-btn v-on:click="showSignUp" outlined text rounded
             style="margin-right: 2%">Sign up
      </v-btn>
    </v-app-bar>
    <v-main>
      <div class="hero-image">
        <home v-if="isHome"/>
        <sign-in v-if="isSignIn"/>
        <sign-up v-if="isSignUp"/>
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Home from 'vuejs/home/component/home.vue'
import SignIn from 'vuejs/home/component/signIn.vue'
import SignUp from 'vuejs/home/component/signUp.vue'

export default {
  components: {
    Home,
    SignIn,
    SignUp
  },
  created() {
    if (signInErrorMessage) {
      this.isSignIn = true
    }else{
      this.showHome()
    }
  },
  data() {
    return {
      signInErrorMessage: signInErrorMessage,
      isHome: false,
      isSignIn: false,
      isSignUp: false
    }
  },
  methods: {
    clearAllComponents() {
      if (this.$refs.formSignUp) {
        this.$refs.formSignUp.reset()
      }
      if (this.$refs.formSignIn) {
        this.$refs.formSignIn.reset()
      }
      this.isSignIn = false
      this.isSignUp = false
      this.isHome = false
      signInErrorMessage = undefined
    }
    ,
    showHome() {
      this.clearAllComponents()
      this.isHome = true
    }
    ,
    showSignUp() {
      this.clearAllComponents()
      this.isSignUp = true
    }
    ,
    showSignIn() {
      this.clearAllComponents()
      this.isSignIn = true
    }
  }
}
</script>

<style>
.hero-image {
  background-image: url("/img/background.jpg");
  background-color: #cccccc;
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}
</style>