<template>
  <v-app>
    <v-app-bar dark app>
      <v-toolbar-title class="font-weight-medium" style="margin-left: 2%">{{text_page.name_hotel}}</v-toolbar-title>
      <v-btn v-on:click="showHome" outlined text rounded
             style="margin-left: 3%">
        <v-icon>home</v-icon>
      </v-btn>
      <v-spacer></v-spacer>

      <v-btn v-on:click="showSignIn" outlined text rounded
             style="margin-right: 1%">{{text_page.sing_in}}
      </v-btn>
      <v-btn v-on:click="showSignUp" outlined text rounded
             style="margin-right: 2%">{{text_page.sing_up}}
      </v-btn>

      <v-menu offset-y style="margin-left: 3%">
        <template v-slot:activator="{ on, attrs }">
          <v-btn style="margin-right: 1%" v-bind="attrs" v-on="on" rounded>
            <v-icon>language</v-icon>
            <span>{{text_page.language}}</span>
          </v-btn>
        </template>
        <v-list dark>
          <v-list-item class="list-item">
            <v-btn href="HotelWeb?command=change_language&language=ru" v-if="language !== 'ru'">
              ru
            </v-btn>
            <div v-else>
              RU
            </div>
          </v-list-item>
          <v-list-item class="list-item">
            <v-btn href="HotelWeb?command=change_language&language=en" v-if="language !== 'en' && language !== ''">
              en
            </v-btn>
            <div v-else>
              EN
            </div>
          </v-list-item>
        </v-list>
      </v-menu>
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
    } else {
      this.showHome()
    }
  },
  data() {
    return {
      text_page : text_page,
      language : language,
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

.list-item {
  justify-content: center;
}
</style>