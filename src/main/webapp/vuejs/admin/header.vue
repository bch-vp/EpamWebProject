<template>
  <div>
    <v-app-bar absolute flat fixed dark color="transparent" style="box-shadow: 0 0 50px black; padding-top: 1em ">
      <v-toolbar-title class="font-weight-medium light-green--text text--lighten-2"
                       style="margin-left: 2%; font-size: 40px; font-family: 'Monoton', cursive; ">
        JEWELRY
      </v-toolbar-title>

      <v-btn v-on:click="$store.commit('show_home')" :disabled="$store.state.App.isHome" text rounded small outlined fab
             style="margin-left: 4%; margin-right: 1em ">
        <v-icon>home</v-icon>
      </v-btn>
      |
      <v-col cols="2">
        <v-select
            style="padding-left: 1em"
            v-model="selectCategory"
            v-bind:label=text_page.form_component.button.choose_category
            :items="$store.state.App.categories"
            item-text="name"
            :disabled="!$store.state.App.isHome"
            persistent-hint
            return-object
            single-line
        ></v-select>
      </v-col>

      <v-spacer></v-spacer>

      <v-btn v-on:click="$store.commit('show_profile')" :disabled="$store.state.App.isProfile" rounded text>
        <img v-if="$store.state.Profile.isAvatarExists" :src="$store.state.Profile.avatarUrl" class="avatar"/>
        <v-icon v-if="!$store.state.Profile.isAvatarExists">
          perm_identity
        </v-icon>
        &nbsp
        {{ text_page.header.role }}
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
  data() {
    return {
      text_page: text_page,
      isAvatarExists: undefined,

      selectCategory: undefined
    }
  },
  watch: {
    selectCategory() {
      this.$store.commit('set_selectCategory', this.selectCategory)
      console.log('updating');
      this.axios({
        method: 'post',
        url: '/ajax?command=load_all_products_by_category',
        data: this.selectCategory
      }).then(response => {
            this.$store.commit('set_products', response.data.data)
          },
          ex => {
          })
    }
  },
  created() {
    this.axios({
      method: 'post',
      url: '/ajax?command=load_all_categories'
    }).then(response => {
          this.$store.commit('set_categories', response.data.data)
          this.selectCategory = response.data.data[0]
          console.log('success categories')
        },
        ex => {
          console.log('error categories')
        })

    this.axios({
      method: 'post',
      url: '/ajax?command=load_shopping_cart'
    }).then(response => {
          this.$store.commit('set_shoppingCart', response.data.data)
        },
        ex => {

        })

    this.axios({
      method: 'post',
      url: '/ajax?command=load_all_clients'
    }).then(response => {
          var users = response.data.users.sort(function(a,b){
            return response.data.users[a]-response.data.users[b]
          })
          this.$store.commit('set_users', response.data.users)
        },
        ex => {

        })
  },
  methods: {}
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