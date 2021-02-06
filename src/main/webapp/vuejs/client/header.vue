<template>
  <div style="color: white; ">
    <v-app-bar absolute flat fixed dark color="transparent" style="box-shadow: 0 0 50px black; ">
      <v-toolbar-title class="font-weight-medium"
                       style="margin-left: 2%; font-size: 40px; font-family: 'Monoton', cursive;">
        HOTEL
      </v-toolbar-title>


      <v-spacer></v-spacer>

      <!--      <v-btn v-on:click="showHome" :disabled="this.$parent.isHome" text rounded-->
      <!--             style="margin-left: 3%; margin-right: 3%">-->
      <!--        <v-icon v-if="this.$parent.isHome" color="#616161">home</v-icon>-->
      <!--        <div v-else class="text-subtitle-1 font-weight-black">-->
      <!--          <v-icon>home</v-icon>-->
      <!--        </div>-->
      <!--      </v-btn>-->

      <!--      <v-btn v-on:click="showSignIn" :disabled="this.$parent.isSignIn" rounded text style="margin-right: 1%">-->
      <!--        <div v-if="this.$parent.isSignIn" style="color: #616161">{{ text_page.header.sing_in }}</div>-->
      <!--        <div v-else class="text-subtitle-1 font-weight-black"> {{ text_page.header.sing_in }}</div>-->
      <!--      </v-btn>-->

      <!--      <v-btn v-on:click="showSignUp" :disabled="this.$parent.isSignUp" text rounded style="margin-right: 3%">-->
      <!--        <div v-if="this.$parent.isSignUp" style="color: #616161">{{ text_page.header.sing_up }}</div>-->
      <!--        <div v-else class="text-subtitle-1 font-weight-black">{{ text_page.header.sing_up }}</div>-->
      <!--      </v-btn>-->

      <v-btn rounded text>
        <v-icon>
          perm_identity
        </v-icon>
        &nbsp
        {{text_page.header.role}}
      </v-btn>
      |

      <v-menu offset-y style="margin-left: 3%; margin-right: 3%">
        <template v-slot:activator="{ on, attrs }">
          <v-btn style="margin-right: 1%" v-bind="attrs" v-on="on" text rounded>
            <v-icon>language</v-icon>
            <span v-if="text_page.language">&nbsp;{{ text_page.language }}</span>
            <span v-else>&nbsp;EN</span>
          </v-btn>
        </template>
        <v-list dark>
          <v-list-item class="list-item">
            <v-btn href="do?command=change_language&language=ru" v-if="text_page.language !== 'ru'">
              ru
            </v-btn>
            <div v-else>
              RU
            </div>
          </v-list-item>
          <v-list-item class="list-item">
            <v-btn href="do?command=change_language&language=en" v-if="text_page.language !== 'en'
                                                                                && text_page.language !== ''">
              en
            </v-btn>
            <div v-else>
              EN
            </div>
          </v-list-item>
        </v-list>
      </v-menu>

      <v-btn rounded text href="do?command=sign_out" style="padding-left: 2em">
        <v-icon>
          exit_to_app
        </v-icon>
      </v-btn>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  data(){
    return{
      text_page:text_page
    }
  },
  methods: {
    accExit: function () {

    }
  }
}
</script>

<style scoped>
.list-item {
  justify-content: center;
}
</style>