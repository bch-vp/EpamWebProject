<template>
  <v-app>
    <div class="background-image">
      <v-container>
        <v-row style="height: 2em">
          <CustomHeader/>
        </v-row>
        <v-row>
          <profile/>
        </v-row>
      </v-container>
    </div>

  </v-app>
</template>

<script>
import CustomHeader from 'vuejs/client/header.vue'
import Profile from 'vuejs/client/component/profile/profile.vue'

export default {
  components: {
    CustomHeader,
    Profile
  },
  data() {
    return {
      text_page: text_page,
    }
  },
  created() {
    //avatar
    this.axios({
      method: 'get',
      url: '/ajax?command=load_profile_image',
    }).then(response => {
      console.log('avatar exist')
      this.isAvatarExists = true
    }, ex => {
      console.log('avatar exist')
      this.isAvatarExists = false
    })
  },
  methods: {
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