<template>
  <v-container>
    <v-row>
      <v-col></v-col>
      <v-col style=" margin-bottom: 1em">
        <div align="center" class="text-h5 center"
             style="text-align: center;font-family: 'Monoton', cursive;">
          <v-icon color="white">
            perm_identity
          </v-icon>
          {{ text_page.header.role }}
        </div>

      </v-col>
      <v-col>
        <div align="right">
          <v-btn @click="showProfileEdit" color="yellow" outlined rounded  small x-small text  dark>
            edit info&nbsp
            <v-icon>
              edit
            </v-icon>
          </v-btn>
        </div>
      </v-col>
    </v-row>

    <v-row align="center"
           justify="center">
      <v-col>
        <div align="center">
          <div class="background-image">
            <div align="left">
              <v-btn small rounded fab text dark color="yellow">
                <v-icon>
                  add_a_photo
                </v-icon>
              </v-btn>
            </div>

          </div>
        </div>
      </v-col>
      <v-col>

        <v-row>
          <span style="color: darkgray">
            {{ text_page.profile_component.login.key }}:&nbsp
          </span>
          {{ text_page.profile_component.login.value }}
        </v-row>
        <br>
        <v-row>
                  <span style="color: darkgray">
                    {{ text_page.profile_component.first_name.key }}:&nbsp
                  </span>
          {{ text_page.profile_component.first_name.value }}
        </v-row>
        <br>
        <v-row>
          <span style="color: darkgray">
            {{ text_page.profile_component.last_name.key }}:&nbsp
          </span>
          {{ text_page.profile_component.last_name.value }}
        </v-row>
        <br>
        <v-row>
          <span style="color: darkgray">
            {{ text_page.profile_component.telephone_number.key }}:&nbsp
          </span>
          {{ text_page.profile_component.telephone_number.value }}
        </v-row>
        <br>
        <v-row>
          <div style="color: darkgray">
            {{ text_page.profile_component.email.key }}:&nbsp
          </div>
          {{ text_page.profile_component.email.value }}
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: ['showProfileEdit'],
  data() {
    return {
      text_page: text_page
    }
  }
}
</script>

<style scoped>
.background-image {
  background-image: url("/img/background.jpg");
  background-color: black;
  height: 11em;
  width: 14em;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}


</style>