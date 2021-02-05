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
import Profile from 'vuejs/client/component/profile.vue'

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