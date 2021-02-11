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
          <v-btn @click="showProfileInfo" color="yellow" outlined small x-small text fab dark>
            <v-icon>
              close
            </v-icon>
          </v-btn>
        </div>
      </v-col>
    </v-row>
    <div v-if="error.login_not_unique">
      <v-row justify="center">
        <div class="title font-weight-regular" style="color: green">{{ success }}</div>
      </v-row>
      <br><br>
    </div>
    <div v-if="error.login_not_unique">
      <v-row>
        <div style="color: red">{{ error.login_not_unique }}</div>
      </v-row>
      <br>
    </div>
    <div v-if="error.telephone_number_not_unique">
      <v-row>
        <div style="color: red">{{ error.telephone_number_not_unique }}</div>
      </v-row>
      <br>
    </div>
    <div v-if="error.email_not_unique">
      <v-row>
        <div style="color: red">{{ error.email_not_unique }}</div>
      </v-row>
    </div>


    <v-row>
      <v-col>
        <div align="center">
          <img src="ajax?command=load_file" class="profile-image"/>
        </div>
      </v-col>
      <v-col>
        <v-form
            ref="form"
            v-model="valid"
        >
          <v-text-field
              dark
              name="login"
              v-model="login"
              :counter="15"
              :rules="rules.login"
              v-bind:label=text_page.form_component.input.login.name
              required
          ></v-text-field>

          <v-text-field
              dark
              name="first_name"
              v-model="first_name"
              :counter="15"
              :rules="rules.first_name"
              v-bind:label=text_page.form_component.input.first_name.name
              required
          ></v-text-field>

          <v-text-field
              dark
              name="last_name"
              v-model="last_name"
              :counter="15"
              :rules="rules.last_name"
              v-bind:label=text_page.form_component.input.last_name.name
              required
          ></v-text-field>

          <v-text-field
              dark
              name="telephone_number"
              v-model="telephone_number"
              :counter="17"
              :rules="rules.telephone_number"
              v-bind:label=text_page.form_component.input.telephone_number.name
              required
          ></v-text-field>

          <v-text-field
              dark
              name="email"
              v-model="email"
              :rules="rules.email"
              :counter="35"
              v-bind:label=text_page.form_component.input.email.name
              required
          ></v-text-field>
          <div align="center">
            <v-progress-circular style="margin-right: 15px"
                                 v-if="spinnerVisible"
                                 indeterminate
                                 color="#8C9EFF"
            ></v-progress-circular>
            <v-btn v-if="!spinnerVisible"
                   @click="submit"
                   :disabled="!valid"
                   dark small text rounded color="#8C9EFF">
              {{ text_page.form_component.button.submit }}
            </v-btn>
            <v-btn @click="reset"
                   outlined small fab color="#8C9EFF">
              <v-icon>autorenew</v-icon>
            </v-btn>
          </div>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: ['showProfileInfo'],
  data() {
    return {
      text_page: text_page,

      success: undefined,
      error: {
        login_not_unique: undefined,
        telephone_number_not_unique: undefined,
        email_not_unique: undefined,
      },

      spinnerVisible: false,

      login: text_page.profile_component.login.value,
      first_name: text_page.profile_component.first_name.value,
      last_name: text_page.profile_component.last_name.value,
      telephone_number: text_page.profile_component.telephone_number.value,
      email: text_page.profile_component.email.value,
      valid: false,

      validCount:0,

      rules: {
        login: [
          v => !!v || this.text_page.form_component.input.login.error.required,
          v => /^[a-zA-Z0-9_.-]+$/.test(v) || this.text_page.form_component.input.login.error.valid_characters,
          v => (v && v.length >= 3) || this.text_page.form_component.input.login.error.min_length,
          v => (v && v.length <= 15) || this.text_page.form_component.input.login.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.form_component.input.login.error.spaces_prohibited,
        ],
        first_name: [
          v => !!v || this.text_page.form_component.input.first_name.error.required,
          v => (v && v.length >= 3) || this.text_page.form_component.input.first_name.error.min_length,
          v => (v && v.length <= 15) || this.text_page.form_component.input.first_name.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.form_component.input.first_name.error.spaces_prohibited,
          v => /^[a-zA-Z]+$/.test(v) || this.text_page.form_component.input.first_name.error.only_letters
        ],
        last_name: [
          v => !!v || this.text_page.form_component.input.last_name.error.required,
          v => (v && v.length >= 3) || this.text_page.form_component.input.last_name.error.min_length,
          v => (v && v.length <= 15) || this.text_page.form_component.input.last_name.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.form_component.input.last_name.error.spaces_prohibited,
          v => /^[a-zA-Z]+$/.test(v)
              || this.text_page.form_component.input.last_name.error.only_letters
        ],
        telephone_number: [
          v => !!v || this.text_page.form_component.input.telephone_number.error.required,
          v => /^\S*$/.test(v) || this.text_page.form_component.input.telephone_number.error.spaces_prohibited,
          v => /^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$/.test(v) ||
              this.text_page.form_component.input.telephone_number.error.pattern
        ],
        email: [
          v => !!v || this.text_page.form_component.input.email.error.required,
          v => /^\S*$/.test(v) || this.text_page.form_component.input.email.error.spaces_prohibited,
          v => (v && v.length <= 55) || this.text_page.form_component.input.email.error.max_length,
          v => /^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\.[a-z]{2,5}$/.test(v)
              || this.text_page.form_component.input.email.error.pattern,
        ]
      }
    }
  },
  watch: {
    login: function () {
      this.checkChange()
    },
    first_name: function () {
      this.checkChange()
    },
    last_name: function () {
      this.checkChange()
    },
    telephone_number: function () {
      this.checkChange()
    },
    email: function () {
      this.checkChange()
    }
  },
  created() {
    this.valid = false
    this.axios.interceptors.request.use(
        conf => {
          this.showSpinner()
          return conf;
        },
        error => {
          this.hideSpinner()
          return Promise.reject(error);
        }
    );
    this.axios.interceptors.response.use(
        response => {
          this.hideSpinner()
          return response;
        },
        error => {
          this.hideSpinner()
          return Promise.reject(error);
        }
    );
  },
  beforeUpdate() {
      this.checkChange()
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.axios({
          method: 'post',
          url: '/ajax?command=update_profile',
          data: {
            old_login: this.text_page.profile_component.login.value,
            login: this.login,
            first_name: this.first_name,
            last_name: this.last_name,
            telephone_number: this.telephone_number,
            email: this.email
          }
        }).then(response => {
              this.error.login_not_unique = undefined
              this.error.telephone_number_not_unique = undefined
              this.error.email_not_unique = undefined

              this.text_page.profile_component.login.value = this.login
              this.text_page.profile_component.first_name.value = this.first_name
              this.text_page.profile_component.last_name.value = this.last_name
              this.text_page.profile_component.telephone_number.value = this.telephone_number
              this.text_page.profile_component.email.value = this.email

              this.success = this.text_page.form_component.info.success
            },
            ex => {
              if (ex.response.status === 400) {
                this.login = this.text_page.profile_component.login.value
                this.first_name = this.text_page.profile_component.first_name.value
                this.last_name = this.text_page.profile_component.last_name.value
                this.telephone_number = this.text_page.profile_component.telephone_number.value
                this.email = this.text_page.profile_component.email.value

                this.error.login_not_unique = ex.response.data.error.login_not_unique
                this.error.telephone_number_not_unique = ex.response.data.error.telephone_number_not_unique
                this.error.email_not_unique = ex.response.data.error.email_not_unique
              }
            })
      }
    },
    reset() {
      this.login = this.text_page.profile_component.login.value
      this.first_name = this.text_page.profile_component.first_name.value
      this.last_name = this.text_page.profile_component.last_name.value
      this.telephone_number = this.text_page.profile_component.telephone_number.value
      this.email = this.text_page.profile_component.email.value

      this.success = undefined
      this.error.login_not_unique = undefined
      this.error.telephone_number_not_unique = undefined
      this.error.email_not_unique = undefined
    },
    checkChange() {
      if (this.login === this.text_page.profile_component.login.value
          && this.first_name === this.text_page.profile_component.first_name.value
          && this.last_name === this.text_page.profile_component.last_name.value
          && this.telephone_number === this.text_page.profile_component.telephone_number.value
          && this.email === this.text_page.profile_component.email.value) {
        this.valid = false
      }else{
        this.valid = true
      }
    },
    showSpinner() {
      this.spinnerVisible = true;
    },
    hideSpinner() {
      this.spinnerVisible = false;
    },
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