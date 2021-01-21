<template>
  <div class="sign-up-wrapper">
    <v-container>
      <v-row justify="center" row>
        <v-col sm="8" md="6" lg="5" xl="4">
          <div class="sign-up-background">
            <div class="sign-up">
              <br>
              <v-form
                  ref="formSignUp"
                  v-model="valid"
              >
                <div class="title font-weight-regular" style="color: white; text-align: center;">
                  <span class="text-h6 font-weight-regular center">{{ text_page.sign_up_component.name }}...</span><br>
                </div>
                <div style="color: yellow">{{ error.database_connection_not_received }}</div>
                <div style="color: red">{{ error.login_not_unique }}</div>
                <div style="color: red">{{ error.telephone_number_not_unique }}</div>
                <div style="color: red">{{ error.email_not_unique }}</div>
                <v-text-field
                    dark
                    name="login"
                    v-model="login"
                    :counter="15"
                    :rules="rules.login"
                    v-bind:label=text_page.sign_up_component.login.name
                    required
                ></v-text-field>
                <v-row>
                  <v-col>
                    <v-text-field
                        dark
                        name="password"
                        v-model="password"
                        :counter="20"
                        :rules="rules.password"
                        :append-icon="valuePassword ? 'visibility' : 'visibility_off'"
                        @click:append="() => (valuePassword = !valuePassword)"
                        :type="valuePassword ? 'password' : 'text'"
                        v-bind:label=text_page.sign_up_component.password.name
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col>
                    <v-text-field
                        dark
                        v-model="passwordRepeat"
                        :counter="20"
                        :rules="rules.passwordRepeat"
                        :append-icon="valuePasswordRepeat ? 'visibility' : 'visibility_off'"
                        @click:append="() => (valuePasswordRepeat = !valuePasswordRepeat)"
                        :type="valuePasswordRepeat ? 'password' : 'text'"
                        label='Repeat password'
                        required
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-text-field
                    dark
                    name="first_name"
                    v-model="first_name"
                    :counter="15"
                    :rules="rules.first_name"
                    v-bind:label=text_page.sign_up_component.first_name.name
                    required
                ></v-text-field>

                <v-text-field
                    dark
                    name="last_name"
                    v-model="last_name"
                    :counter="15"
                    :rules="rules.last_name"
                    v-bind:label=text_page.sign_up_component.last_name.name
                    required
                ></v-text-field>

                <v-text-field
                    dark
                    name="telephone_number"
                    v-model="telephoneNumber"
                    :counter="17"
                    :rules="rules.telephoneNumber"
                    v-bind:label=text_page.sign_up_component.telephone_number.name
                    required
                ></v-text-field>

                <v-text-field
                    dark
                    name="email"
                    v-model="email"
                    :rules="rules.email"
                    :counter="35"
                    v-bind:label=text_page.sign_up_component.email.name
                    required
                ></v-text-field>
                <br>
                <v-row>
                  <v-btn @click="showSignIn" color="green" text outlined rounded>
                    <span style="">{{ text_page.sign_up_component.go_to_component.sign_in }}</span>
                  </v-btn>
                </v-row>
                <br>
                <div align="center">
                  <v-btn @click="submit" :disabled="!valid" dark small text rounded color="#8C9EFF">
                    {{ text_page.sign_up_component.submit }}
                  </v-btn>
                  <v-btn @click="reset" outlined small fab color="#8C9EFF">
                    <v-icon>autorenew</v-icon>
                  </v-btn>
                </div>
              </v-form>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  props: ['showSignIn'],
  data() {
    return {
      text_page: {
        sign_up_component: text_page.sign_up_component,
      },
      error: {
        login_not_unique: "",
        telephone_number_not_unique: "",
        email_not_unique: "",
        database_connection_not_received: ""
      },
      valuePassword: String,
      valuePasswordRepeat: String,
      valid: false,
      login: '',
      password: '',
      passwordRepeat: '',
      first_name: '',
      last_name: '',
      telephoneNumber: '',
      email: '',
      rules: {
        login: [
          v => !!v || this.text_page.sign_up_component.login.error.required,
          v => /^[a-zA-Z0-9_.-]+$/.test(v) || this.text_page.sign_up_component.login.error.valid_characters,
          v => (v && v.length >= 3) || this.text_page.sign_up_component.login.error.min_length,
          v => (v && v.length <= 15) || this.text_page.sign_up_component.login.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.login.error.spaces_prohibited,
        ],
        password: [
          v => !!v || this.text_page.sign_up_component.password.error.required,
          v => (v && v.length >= 5) || this.text_page.sign_up_component.password.error.min_length,
          v => (v && v.length <= 20) || this.text_page.sign_up_component.password.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.password.error.spaces_prohibited,
          v => /(?=.*?[a-z])/.test(v) || this.text_page.sign_up_component.password.error.one_lower_case_letter,
          v => /(?=.*?[A-Z])/.test(v) || this.text_page.sign_up_component.password.error.one_upper_case_letter,
          v => /(?=.*?[0-9])/.test(v) || this.text_page.sign_up_component.password.error.one_digit,
        ],
        passwordRepeat: [
          v => !!v || this.text_page.sign_up_component.password_repeat.error.required,
          v => (v && this.passwordRepeat === this.password) ||
              this.text_page.sign_up_component.password_repeat.error.not_equal,
        ],
        first_name: [
          v => !!v || this.text_page.sign_up_component.first_name.error.required,
          v => (v && v.length >= 3) || this.text_page.sign_up_component.first_name.error.min_length,
          v => (v && v.length <= 15) || this.text_page.sign_up_component.first_name.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.first_name.error.spaces_prohibited,
          v => /^[a-zA-Z]+$/.test(v) || this.text_page.sign_up_component.first_name.error.only_letters
        ],
        last_name: [
          v => !!v || this.text_page.sign_up_component.last_name.error.required,
          v => (v && v.length >= 3) || this.text_page.sign_up_component.last_name.error.min_length,
          v => (v && v.length <= 15) || this.text_page.sign_up_component.last_name.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.last_name.error.spaces_prohibited,
          v => /^[a-zA-Z]+$/.test(v)
              || this.text_page.sign_up_component.last_name.error.only_letters
        ],
        telephoneNumber: [
          v => !!v || this.text_page.sign_up_component.telephone_number.error.required,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.telephone_number.error.spaces_prohibited,
          v => (v && v.length <= 17) || this.text_page.sign_up_component.telephone_number.error.max_length,
          v => /^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$/.test(v) ||
              this.text_page.sign_up_component.telephone_number.error.pattern
        ],
        email: [
          v => !!v || this.text_page.sign_up_component.email.error.required,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.email.error.spaces_prohibited,
          v => (v && v.length <= 55) || this.text_page.sign_up_component.email.error.max_length,
          v => /^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\.[a-z]{2,5}$/.test(v)
              || this.text_page.sign_up_component.email.error.pattern,
        ]
      }
    }
  },
  methods: {
    submit: function () {
      if (this.$refs.formSignUp.validate()) {

        this.axios({
          method: 'post',
          url: '/ajax?command=sign_up',
          data: {
            login: this.login,
            password: this.password,
            first_name: this.first_name,
            last_name: this.last_name,
            telephone_number: this.telephoneNumber,
            email: this.email
          }
        }).then(response => {
              // show NOTIFICATION to confirm email
            },
            ex => {
              alert(ex.response.status)
              if (ex.response.status === 400) {
                this.$refs.formSignUp.reset()
                this.error.login_not_unique = ex.response.data.error.login_not_unique
                this.error.telephone_number_not_unique = ex.response.data.error.telephone_number_not_unique
                this.error.email_not_unique = ex.response.data.error.email_not_unique
              } else if (ex.response.status === 500) {
                this.error.database_connection_not_received = ex.response.data.error.database_connection_not_received
              }
            })
      }
    },
    reset: function () {
      this.$refs.formSignUp.reset()
      this.error.login_not_unique = undefined
      this.error.telephone_number_not_unique = undefined
      this.error.email_not_unique = undefined
      this.error.database_connection_not_received = undefined
    },
  }
}
</script>

<style>
@media screen and (min-width: 400px) {
  .sign-up-wrapper {
    width: 100%;
    margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);
  }
}

@media screen and (max-width: 400px) {
  .sign-up-wrapper {
    width: 100%;
    position: relative;
    margin-right: -50%;
  }
}

.sign-up-background {
  box-shadow: 0 0 25px;
  background: rgba(0, 0, 0, 0.93);
  border-radius: 20px;
}

@media screen and (max-width: 400px) {
  .sign-up {
    margin-top: 4em;
    padding: 2em;
  }
}

@media screen and (min-width: 400px) {
  .sign-up {
    padding-left: 45px;
    padding-right: 45px;
    padding-top: 30px;
    padding-bottom: 30px;
  }
}
</style>