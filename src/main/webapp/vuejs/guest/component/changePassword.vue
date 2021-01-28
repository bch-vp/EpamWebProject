<template>
  <div class="sign-up-wrapper">
    <v-container>
      <v-row justify="center" row>
        <v-col sm="8" md="6" lg="5" xl="4">
          <div class="sign-up-background">
            <div class="sign-up">
              <div class="title font-weight-regular" style="color: white; text-align: center;">
                <span class="text-h6 font-weight-regular center">Change password...</span><br>
                <div style="color: green">{{ success }}</div>
              </div>

              <div style="color: red">{{ error.login_not_found }}</div>
              <!--                <div style="color: red">{{ error.telephone_number_not_unique }}</div>-->
              <!--                <div style="color: red">{{ error.email_not_unique }}</div>-->
              <v-form
                  ref="formLogin"
                  v-model="validFormLogin"
              >
                <v-text-field
                    dark
                    :disabled="!isFormLogin"
                    name="login"
                    v-model="login"
                    :counter="15"
                    :rules="rules.login"
                    label="Login"
                    required
                ></v-text-field>
              </v-form>
              <v-form
                  ref="formLoginAndPassword"
                  v-model="validFromLoginAndPassword"
              >
                <v-text-field
                    dark
                    :disabled="!isFormLoginAndPassword"
                    name="password"
                    v-model="oldPassword"
                    :counter="20"
                    :rules="rules.oldPassword"
                    :append-icon="valueOldPasswordRepeat ? 'visibility' : 'visibility_off'"
                    @click:append="() => (valueOldPasswordRepeat = !valueOldPasswordRepeat)"
                    :type="valueOldPasswordRepeat ? 'password' : 'text'"
                    label="Old password"
                    required
                ></v-text-field>
                <v-row>
                  <v-col>
                    <v-text-field
                        dark
                        :disabled="!isFormLoginAndPassword"
                        name="password"
                        v-model="newPassword"
                        :counter="20"
                        :rules="rules.newPassword"
                        :append-icon="valueNewPassword ? 'visibility' : 'visibility_off'"
                        @click:append="() => (valueNewPassword = !valueNewPassword)"
                        :type="valueNewPassword ? 'password' : 'text'"
                        label="New password"
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col>
                    <v-text-field
                        dark
                        :disabled="!isFormLoginAndPassword"
                        v-model="newPasswordRepeat"
                        :counter="20"
                        :rules="rules.newPasswordRepeat"
                        :append-icon="valueNewPasswordRepeat ? 'visibility' : 'visibility_off'"
                        @click:append="() => (valueNewPasswordRepeat = !valueNewPasswordRepeat)"
                        :type="valueNewPasswordRepeat ? 'password' : 'text'"
                        label='Repeat new password'
                        required
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-form>
                <br>
                <div align="center">
                  <v-btn v-if="isFormLogin" @click="formLoginSubmit" :disabled="!validFormLogin" dark small text rounded
                         color="#8C9EFF">
                    Сheck the existence of login
                  </v-btn>
                  <v-btn v-if="isFormLoginAndPassword" @click="formLoginAndPasswordSubmit"
                         :disabled="!validFromLoginAndPassword"
                         dark small text rounded
                         color="#8C9EFF">
                    submit
                  </v-btn>
                  <v-btn @click="fullFormReset" outlined small fab color="#8C9EFF">
                    <v-icon>autorenew</v-icon>
                  </v-btn>
                </div>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  props: ['showSignIn', 'showNotification'],
  data() {
    return {
      text_page: {
        sign_up_component: text_page.sign_up_component,
      },
      success:'',
      isFormLogin: true,
      isFormLoginAndPassword: false,
      error: {
        login_not_found: "",
      },
      valueNewPassword: String,
      valueNewPasswordRepeat: String,
      valueOldPasswordRepeat: String,
      validFormLogin: false,
      validFromLoginAndPassword: false,
      login: '',
      oldPassword: '',
      newPassword: '',
      newPasswordRepeat: '',
      rules: {
        login: [
          v => !!v || this.text_page.sign_up_component.login.error.required,
          v => /^[a-zA-Z0-9_.-]+$/.test(v) || this.text_page.sign_up_component.login.error.valid_characters,
          v => (v && v.length >= 3) || this.text_page.sign_up_component.login.error.min_length,
          v => (v && v.length <= 15) || this.text_page.sign_up_component.login.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.login.error.spaces_prohibited,
        ],
        oldPassword: [
          v => !!v || this.text_page.sign_up_component.password.error.required,
          v => (v && v.length >= 5) || this.text_page.sign_up_component.password.error.min_length,
          v => (v && v.length <= 20) || this.text_page.sign_up_component.password.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.password.error.spaces_prohibited,
          v => /(?=.*?[a-z])/.test(v) || this.text_page.sign_up_component.password.error.one_lower_case_letter,
          v => /(?=.*?[A-Z])/.test(v) || this.text_page.sign_up_component.password.error.one_upper_case_letter,
          v => /(?=.*?[0-9])/.test(v) || this.text_page.sign_up_component.password.error.one_digit,
        ],
        newPassword: [
          v => !!v || this.text_page.sign_up_component.password.error.required,
          v => (v && this.newPassword !== this.oldPassword) || 'cant be like old pass',
          v => (v && v.length >= 5) || this.text_page.sign_up_component.password.error.min_length,
          v => (v && v.length <= 20) || this.text_page.sign_up_component.password.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.password.error.spaces_prohibited,
          v => /(?=.*?[a-z])/.test(v) || this.text_page.sign_up_component.password.error.one_lower_case_letter,
          v => /(?=.*?[A-Z])/.test(v) || this.text_page.sign_up_component.password.error.one_upper_case_letter,
          v => /(?=.*?[0-9])/.test(v) || this.text_page.sign_up_component.password.error.one_digit,
        ],
        newPasswordRepeat: [
          v => !!v || this.text_page.sign_up_component.password_repeat.error.required,
          v => (v && this.newPassword === this.newPasswordRepeat) ||
              this.text_page.sign_up_component.password_repeat.error.not_equal,
        ],
      }
    }
  },
  methods: {
    formLoginSubmit: function () {
      if (this.$refs.formLogin.validate()) {

        this.axios({
          method: 'post',
          url: '/ajax?command=check_login_existence',
          data: {
            login: this.login,
          }
        }).then(response => {
              this.error.login_not_found = ""
              this.isFormLogin = false
              this.isFormLoginAndPassword = true
            },
            ex => {
              if (ex.response.status === 404) {
                this.fullFormReset()
                this.error.login_not_found = "Login didn't find"
              }
            })
      }
    },
    fullFormReset: function () {
      this.$refs.formLogin.reset()
      this.$refs.formLoginAndPassword.reset()
      this.success=""
      this.error.login_not_found = ""
      this.isFormLogin = true
      this.isFormLoginAndPassword = false
    },
    formLoginAndPasswordSubmit: function () {
      if (this.$refs.formLoginAndPassword.validate()) {

        this.axios({
          method: 'post',
          url: '/ajax?command=change_password',
          data: {
            login: this.login,
            new_password: this.newPassword,
            old_password: this.oldPassword
          }
        }).then(response => {
              this.fullFormReset()
              this.success = 'Success'
            },
            ex => {
              if (ex.response.status === 400) {
                this.fullFormReset()
                this.error.login_not_found = "Password wrong"
              }
            })
      }
    }
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