<template>
  <v-app>

<!--    <v-app-bar-->
<!--        style="box-shadow: 0 0 300px black;"-->
<!--        app-->
<!--        dark>-->



<!--      <template v-slot:img="{ props }">-->
<!--        <v-img-->
<!--            v-bind="props">-->
<!--&lt;!&ndash;            gradient="to top right, rgba(100,115,201,.7), rgba(25,32,72,.7)"&ndash;&gt;-->
<!--        </v-img>-->
<!--      </template>-->

<!--      <v-app-bar-nav-icon></v-app-bar-nav-icon>-->

<!--      <v-toolbar-title>Titlebhkhb</v-toolbar-title>-->

<!--      <v-spacer></v-spacer>-->

<!--      <v-btn icon>-->
<!--        <v-icon>mdi-magnify</v-icon>-->
<!--      </v-btn>-->

<!--      <v-btn icon>-->
<!--        <v-icon>mdi-heart</v-icon>-->
<!--      </v-btn>-->

<!--      <v-btn icon>-->
<!--        <v-icon>mdi-dots-vertical</v-icon>-->
<!--      </v-btn>-->

<!--      <template  v-slot:extension>-->
<!--        <v-tabs align-with-title style="margin-left: 500px">-->
<!--          <v-tab>Tab 1</v-tab>-->
<!--          <v-tab>Tab 2</v-tab>-->
<!--          <v-tab>Tab 3</v-tab>-->
<!--        </v-tabs>-->
<!--      </template>-->
<!--    </v-app-bar>-->

<!--    <v-main>-->
<!--            <div class="hero-image">-->
<!--              <home v-if="isHome"/>-->
<!--              <sign-in v-if="isSignIn"/>-->
<!--              <sign-up />-->
<!--            </div>-->
<!--          </v-main>-->


    <v-app-bar style="box-shadow: 0 0 100px black;"  dark app>
      <v-toolbar-title class="font-weight-medium" style="margin-left: 2%">{{ text_page.header.hotel }}</v-toolbar-title>
      <v-btn v-on:click="showHome" :disabled="isHome"  rounded
             style="margin-left: 3%">
        <v-icon v-if="isHome" color="#616161">home</v-icon>
        <v-icon v-else>home</v-icon>
      </v-btn>
      <v-spacer></v-spacer>

      <v-btn v-on:click="showSignIn" :disabled="isSignIn" rounded text style="margin-right: 1%">
        <div v-if="isSignIn" style="color: #616161">{{ text_page.header.sing_in }}</div>
        <div v-else>{{ text_page.header.sing_in }}</div>
      </v-btn>

      <v-btn v-on:click="showSignUp" :disabled="isSignUp" text rounded style="margin-right: 3%">
        <div v-if="isSignUp" style="color: #616161">{{ text_page.header.sing_up }}</div>
        <div v-else>{{ text_page.header.sing_up }}</div>
      </v-btn>

      <v-menu offset-y style="margin-left: 3%; margin-right: 3%">
        <template v-slot:activator="{ on, attrs }">
          <v-btn style="margin-right: 1%" v-bind="attrs" v-on="on" rounded>
            <v-icon>language</v-icon>
            <span>&nbsp;{{ text_page.header.language }}</span>
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
  data() {
    return {
      items: [
        { title: 'Click Me' },
        { title: 'Click Me' },
        { title: 'Click Me' },
        { title: 'Click Me 2' },
      ],
      text_page: {
        header: text_page.header
      },
      language: language,
      signInErrorMessage: signInErrorMessage,
      isHome: false,
      isSignIn: false,
      isSignUp: false
    }
  },
  created() {
    if (signInErrorMessage) {
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

.active {
  color: purple;
}

.list-item {
  justify-content: center;
}
</style>