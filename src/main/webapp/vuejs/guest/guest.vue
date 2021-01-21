<template>
  <v-app>
    <div class="background-image">
      <v-container>
        <v-row style="height: 2em">
          <CustomHeader :showHome="showHome" :showSignIn="showSignIn" :showSignUp="showSignUp" :text_page="text_page"/>
        </v-row>
        <v-row>
          <home v-if="isHome"
                :showSignUp="showSignUp"/>
          <sign-in v-if="isSignIn"
                   :error="text_page.sign_in_component.error"
                   :showSignUp="showSignUp"/>
          <sign-up v-if="isSignUp"
                   :showSignIn="showSignIn"/>
        </v-row>
      </v-container>
    </div>

  </v-app>
</template>

<script>
import CustomHeader from 'vuejs/guest/header.vue'
import Home from 'vuejs/guest/component/home.vue'
import SignIn from 'vuejs/guest/component/signIn.vue'
import SignUp from 'vuejs/guest/component/signUp.vue'

export default {
  components: {
    CustomHeader,
    Home,
    SignIn,
    SignUp
  },
  data() {
    return {
      text_page: {
        header: text_page.header,
        language: text_page.language,
        sign_in_component: {
          error: text_page.sign_in_component.error
        },
        sign_up_component: {
          error: text_page.sign_up_component.error
        }
      },
      isHome: false,
      isSignIn: false,
      isSignUp: false
    }
  },
  created() {
    if (text_page.sign_in_component.error.not_found
        || text_page.sign_in_component.error.database_connection_not_received) {
      this.isSignIn = true
    } else {
      this.showHome()
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
      this.text_page.sign_in_component.error.not_found = undefined
      this.text_page.sign_in_component.error.database_connection_not_received = undefined
      this.text_page.sign_up_component.error.login_not_unique = undefined
      this.text_page.sign_up_component.error.telephone_number_not_unique = undefined
      this.text_page.sign_up_component.error.email_not_unique = undefined
      this.text_page.sign_up_component.error.database_connection_not_received = undefined
    },
    showHome() {
      this.clearAllComponents()
      this.isHome = true
    },
    showSignUp() {
      this.clearAllComponents()
      this.isSignUp = true
    },
    showSignIn() {
      this.clearAllComponents()
      this.isSignIn = true
    }
  }
}
</script>

<style>
.background-image {
  background-image: url("/img/background.jpg");
  background-color: black;
  height: 100vh;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}
</style>