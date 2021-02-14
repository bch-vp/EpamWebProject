<template>
  <v-app>
    <div class="background-image">
      <v-container>
        <v-row style="height: 2em;margin-top: 2em">
          <CustomHeader/>

        </v-row>
        <v-row>
          <profile v-if="$store.state.App.isProfile"/>
          <home v-if="$store.state.App.isHome"/>
        </v-row>
      </v-container>
    </div>

  </v-app>
</template>

<script>
import CustomHeader from 'vuejs/client/header.vue'
import Profile from 'vuejs/client/component/profile/profile.vue'
import Home from 'vuejs/client/component/home/home.vue'

export default {
  components: {
    CustomHeader,
    Profile,
    Home
  },
  data() {
    return {
      text_page: text_page,
    }
  },
  created() {
    this.$store.commit('show_home') // cahnge

    this.axios({
      method: 'post',
      url: '/ajax?command=load_profile_image',
    }).then(response => {
      this.$store.commit('set_isAvatarExists', true)
    }, ex => {
      this.$store.commit('set_isAvatarExists', false)
    })
  },
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