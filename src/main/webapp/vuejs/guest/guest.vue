<template>
  <v-app>
    <div class="background-image">
      <v-app-bar app flat fixed dark color="transparent" style="box-shadow: 0 0 50px black;" dark app>
        <v-toolbar-title class="font-weight-medium" style="margin-left: 2%">{{
            text_page.header.hotel
          }}
        </v-toolbar-title>



        <v-spacer></v-spacer>

        <v-btn v-on:click="showHome" :disabled="isHome" text rounded
               style="margin-left: 3%; margin-right: 3%">
          <v-icon v-if="isHome" color="#616161">home</v-icon>
          <div v-else class="text-subtitle-1 font-weight-black">
            <v-icon>home</v-icon>
          </div>
        </v-btn>

        <v-btn v-on:click="showSignIn" :disabled="isSignIn" rounded text style="margin-right: 1%">
          <div v-if="isSignIn" style="color: #616161">{{ text_page.header.sing_in }}</div>
          <div v-else class="text-subtitle-1 font-weight-black"> {{ text_page.header.sing_in }}</div>
        </v-btn>

        <v-btn v-on:click="showSignUp" :disabled="isSignUp" text rounded style="margin-right: 3%">
          <div v-if="isSignUp" style="color: #616161">{{ text_page.header.sing_up }}</div>
          <div v-else class="text-subtitle-1 font-weight-black">{{ text_page.header.sing_up }}</div>
        </v-btn>

        <v-menu offset-y style="margin-left: 3%; margin-right: 3%">
          <template v-slot:activator="{ on, attrs }">
            <v-btn style="margin-right: 1%" v-bind="attrs" v-on="on" text rounded>
              <v-icon>language</v-icon>
              <span>&nbsp;{{ text_page.language }}</span>
            </v-btn>
          </template>
          <v-list dark>
            <v-list-item class="list-item">
              <v-btn href="HotelWeb?command=change_language&language=ru" v-if="text_page.language !== 'ru'">
                ru
              </v-btn>
              <div v-else>
                RU
              </div>
            </v-list-item>
            <v-list-item class="list-item">
              <v-btn href="HotelWeb?command=change_language&language=en" v-if="text_page.language !== 'en'
                                                                                && text_page.language !== ''">
                en
              </v-btn>
              <div v-else>
                EN
              </div>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-app-bar>

      <home v-if="isHome"/>
      <sign-in v-if="isSignIn"
               :error="text_page.sign_in_component.error"/>
      <sign-up v-if="isSignUp"
               :error="text_page.sign_up_component.error"/>
    </div>
  </v-app>
</template>

<script>
import Home from 'vuejs/guest/component/home.vue'
import SignIn from 'vuejs/guest/component/signIn.vue'
import SignUp from 'vuejs/guest/component/signUp.vue'

export default {
  components: {
    Home,
    SignIn,
    SignUp
  },
  data() {
    return {
      items: [
        {title: 'Click Me'},
        {title: 'Click Me'},
        {title: 'Click Me'},
        {title: 'Click Me 2'},
      ],
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
    if (text_page.sign_in_component.error.not_found) {
      this.isSignIn = true
    } else if (text_page.sign_up_component.error.login_not_unique
        || text_page.sign_up_component.error.telephone_number_not_unique
        || text_page.sign_up_component.error.email_not_unique) {
      this.isSignUp = true
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
      this.text_page.sign_up_component.error.login_not_unique = undefined
      this.text_page.sign_up_component.error.telephone_number_not_unique = undefined
      this.text_page.sign_up_component.error.email_not_unique = undefined
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
  background-color: #cccccc;
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.active {
  color: purple;
}

.shadow {
  text-shadow: 0px 3px 0px #b2a98f,
  0px 14px 10px rgba(0, 0, 0, 0.15),
  0px 24px 2px rgba(0, 0, 0, 0.1),
  0px 34px 30px rgba(0, 0, 0, 0.1);
}

.list-item {
  justify-content: center;
}
</style>