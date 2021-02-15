<template>
  <div>


    <v-app-bar absolute flat fixed dark color="transparent" style="box-shadow: 0 0 50px black; padding-top: 1em ">
      <v-toolbar-title class="font-weight-medium"
                       style="margin-left: 2%; font-size: 40px; font-family: 'Monoton', cursive;">
        HOTEL
      </v-toolbar-title>

      <v-btn v-on:click="$store.commit('show_home')" :disabled="$store.state.App.isHome" text rounded small outlined fab
             style="margin-left: 4%; margin-right: 1em ">
        <v-icon>home</v-icon>
      </v-btn>
      |
      <v-col cols="2">
        <v-select
            style="padding-left: 1em"
            v-model="select"
            hint="Please choose category"
            :items="items"
            item-text="state"
            item-value="abbr"
            label="Select"
            :disabled="!$store.state.App.isHome"
            persistent-hint
            return-object
            single-line
        ></v-select>
      </v-col>

      <v-spacer></v-spacer>

      <v-btn v-on:click="$store.commit('show_profile')" :disabled="$store.state.App.isProfile" rounded text>
        <img v-if="$store.state.Profile.isAvatarExists" :src="$store.state.Profile.avatarUrl" class="avatar"/>
        <v-icon  v-if="!$store.state.Profile.isAvatarExists">
          perm_identity
        </v-icon>
        &nbsp
        {{text_page.header.role}}
      </v-btn>
      |
      <v-btn v-on:click="$store.commit('show_shoppingBasket')" :disabled="$store.state.App.isShoppingBasket"  rounded text>
        <v-icon >
          shopping_cart
        </v-icon>
        &nbsp
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
        <v-icon>exit_to_app</v-icon>
      </v-btn>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  data(){
    return{
      text_page:text_page,
      isAvatarExists:undefined,


      select:
          { state: 'Please choose category', abbr: '' }
          ,
      items: [
        { state: 'Florida', abbr: 'FL' },
        { state: 'Georgia', abbr: 'GA' },
        { state: 'Nebraska', abbr: 'NE' },
        { state: 'California', abbr: 'CA' },
        { state: 'New York', abbr: 'NY' },
      ],
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
.avatar {
  vertical-align: middle;
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
</style>