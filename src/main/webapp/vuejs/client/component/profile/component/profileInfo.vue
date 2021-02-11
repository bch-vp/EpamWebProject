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
          <v-btn @click="showProfileEdit" color="yellow" outlined rounded small x-small text dark>
            edit info&nbsp
            <v-icon>
              edit
            </v-icon>
          </v-btn>
        </div>
      </v-col>
    </v-row>
    <v-row >
      <v-col>
        <div align="center">
          <img :src="pathh" class="profile-image"/>
          <div align="">
            <v-file-input v-on:change="handleFileUpload()"
                          v-model="file"
                          dark
                          ref="file"
                          prepend-icon="add_a_photo">
            </v-file-input>
          </div>
        </div>
      </v-col>
      <v-col>
        <v-container>
          <v-row>
            <v-col>

              <v-row>
          <span style="color: darkgray">
            {{ text_page.profile_component.login.key }}:&nbsp
          </span>
                {{ text_page.profile_component.login.value }}
              </v-row>
              <br><br>
              <v-row>
          <span style="color: darkgray">
            {{ text_page.profile_component.first_name.key }}:&nbsp
          </span>
                {{ text_page.profile_component.first_name.value }}
              </v-row>
              <br><br>
              <v-row>
          <span style="color: darkgray">
            {{ text_page.profile_component.last_name.key }}:&nbsp
          </span>
                {{ text_page.profile_component.last_name.value }}
              </v-row>
              <br><br>
              <v-row>
          <span style="color: darkgray">
            {{ text_page.profile_component.telephone_number.key }}:&nbsp
          </span>
                {{ text_page.profile_component.telephone_number.value }}
              </v-row>
              <br><br>
              <v-row>
                <div style="color: darkgray">
                  {{ text_page.profile_component.email.key }}:&nbsp
                </div>
                {{ text_page.profile_component.email.value }}
              </v-row>
            </v-col>
          </v-row>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: ['showProfileEdit'],
  data() {
    return {
      text_page: text_page,
      file: undefined,
      profileImage: undefined,
      pathh: "ajax?command=load_file",
    }
  },
  methods: {
    handleFileUpload() {

      let formData = new FormData();
      formData.append('file', this.file);

      this.axios({
        method: 'post',
        url: '/ajax?command=upload_file',
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        data: formData
      }).then(response => {
        console.log('SUCCESS!!');
        this.pathh = "ajax?command=load_file&s=" + Date.now()
      }, ex => {
        console.log('FAILURE!!');
      })
    }
  }

}
</script>

<style scoped>
.profile-image {
  background-color: black;
  height: 14em;
  width: 15em;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}
</style>