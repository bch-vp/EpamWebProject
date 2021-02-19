<template>
  <div style="color: white; ">
  <v-app-bar absolute flat fixed dark color="transparent" style="box-shadow: 0 0 50px black;  padding-top: 1em ">
    <v-toolbar-title class="font-weight-medium"
                     style="margin-left: 2%; font-size: 40px; font-family: 'Monoton', cursive;">
      JEWELRY
    </v-toolbar-title>


    <v-spacer></v-spacer>

    <v-btn v-on:click="showHome" :disabled="isHome"  rounded small outlined text
           style="margin-left: 3%; margin-right: 3%">
      <v-icon>home</v-icon>

    </v-btn>

    <v-btn v-on:click="showSignIn" :disabled="isSignIn" rounded text >
      <div class="text-subtitle-1 font-weight-black"> {{ text_page.header.sing_in }}</div>
    </v-btn>
    |
    <v-btn v-on:click="showSignUp" :disabled="isSignUp" text rounded style="margin-right: 3%">
      <div class="text-subtitle-1 font-weight-black">{{ text_page.header.sing_up }}</div>
    </v-btn>

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
  </v-app-bar>
  </div>
</template>

<script>
export default {
  props:['text_page', 'showHome', 'showSignIn', 'showSignUp', 'isHome', 'isSignUp', 'isSignIn']
}
</script>

<style scoped>
.list-item {
  justify-content: center;
}
</style>