<template>
  <div class="sign-up-wrapper">
    <v-container>
      <v-row justify="center" row>
        <v-col sm="8" md="6" lg="5" xl="4">
          <div class="sign-up-background">
            <div class="sign-up">
              <div class="title font-weight-regular" style="color: white; text-align: center;">
                <span class="text-h6 font-weight-regular center">Change password...</span><br>
                <div style="color: green">{{ info.success }}</div>
              </div>
              <div style="color: yellow">{{ info.check_email }}</div>
              <div style="color: red">{{ error.login_not_found }}</div>
              <div style="color: red">{{ error.incorrect_email }}</div>
              <v-form
                  ref="formFirstStepLogic"
                  v-model="validFormFirstStepLogic"
              >
                <v-text-field
                    dark
                    :disabled="!isFirstStepLogic"
                    v-model="login"
                    :counter="15"
                    :rules="rules.login"
                    label="Login"
                    required
                ></v-text-field>
              </v-form>
              <v-form
                  ref="formSecondStepLogic"
                  v-model="validFormSecondStepLogic"
              >
                <v-text-field
                    dark
                    :disabled="!isSecondStepLogic"
                    v-model="email"
                    :counter="55"
                    :rules="rules.email"
                    label="Email"
                    required
                ></v-text-field>
                <v-row>
                  <v-col>
                    <v-text-field
                        dark
                        :disabled="!isSecondStepLogic"
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
                        :disabled="!isSecondStepLogic"
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

              <v-form
                  ref="formThirdStepLogic"
                  v-model="validFormThirdStepLogic"
              >
                <div v-if="isThirdStepLogic">
                  <v-text-field
                      dark
                      :disabled="!isThirdStepLogic"
                      v-model="uniqueKey"
                      :rules="rules.uniqueKey"
                      :counter="20"
                      label="Unique key from email"
                      required
                  ></v-text-field>
                </div>
              </v-form>

              <br>
              <div align="center">
                <v-btn v-if="isFirstStepLogic"
                       @click="submitFormFirstStepLogic"
                       :disabled="!validFormFirstStepLogic"
                       dark small text rounded
                       color="#8C9EFF">
                  Сheck the existence of login
                </v-btn>
                <v-btn v-if="isSecondStepLogic"
                       @click="submitFormSecondStepLogic"
                       :disabled="!validFormSecondStepLogic"
                       dark small text rounded
                       color="#8C9EFF">
                  submit
                </v-btn>
                <v-btn v-if="isThirdStepLogic"
                       @click="submitFormThirdStepLogic"
                       :disabled="!validFormThirdStepLogic"
                       dark small text rounded
                       color="#8C9EFF">
                  submit
                </v-btn>
                <v-btn @click="allFormsReset" outlined small fab color="#8C9EFF">
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
      isFormLogin: true,
      isFormLoginAndPassword: false,
      info: {
        check_email: undefined,
        success: undefined
      },
      error: {
        login_not_found: undefined,
        incorrect_email: undefined,
        time_expired: undefined
      },

      isFirstStepLogic: true,
      isSecondStepLogic: false,
      isThirdStepLogic: false,

      validFormFirstStepLogic: false,
      validFormSecondStepLogic: false,
      validFormThirdStepLogic: false,

      valueNewPassword: String,
      valueNewPasswordRepeat: String,
      valueEmail: String,
      login: '',
      email: '',
      newPassword: '',
      newPasswordRepeat: '',
      uniqueKey: '',
      rules: {
        login: [
          v => !!v || this.text_page.sign_up_component.login.error.required,
          v => /^[a-zA-Z0-9_.-]+$/.test(v) || this.text_page.sign_up_component.login.error.valid_characters,
          v => (v && v.length >= 3) || this.text_page.sign_up_component.login.error.min_length,
          v => (v && v.length <= 15) || this.text_page.sign_up_component.login.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.login.error.spaces_prohibited,
        ],
        email: [
          v => !!v || this.text_page.sign_up_component.email.error.required,
          v => /^\S*$/.test(v) || this.text_page.sign_up_component.email.error.spaces_prohibited,
          v => (v && v.length <= 55) || this.text_page.sign_up_component.email.error.max_length,
          v => /^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\.[a-z]{2,5}$/.test(v)
              || this.text_page.sign_up_component.email.error.pattern,
        ],
        newPassword: [
          v => !!v || this.text_page.sign_up_component.password.error.required,
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
        uniqueKey: [
          v => !!v || "Can't be empty",
          v => (v && v.length <= 20) || "Can't be more then 20 symbols",
        ]
      }
    }
  },
  created() {
    this.showFirstStepLogic()
  },
  methods: {
    clearAllStepsLogic: function () {
      this.isFirstStepLogic = false
      this.isSecondStepLogic = false
      this.isThirdStepLogic = false

      this.info.check_email = undefined
      this.info.success = undefined
      this.error.incorrect_email = undefined
      this.error.login_not_found = undefined
    },
    showFirstStepLogic: function () {
      this.clearAllStepsLogic()
      this.isFirstStepLogic = true
    },
    showSecondStepLogic: function () {
      this.clearAllStepsLogic()
      this.isSecondStepLogic = true
    },
    showThirdStepLogic: function () {
      this.clearAllStepsLogic()
      this.isThirdStepLogic = true
    },
    submitFormFirstStepLogic: function () {
      if (this.$refs.formFirstStepLogic.validate()) {
        this.axios({
          method: 'post',
          url: '/ajax?command=check_login_existence',
          data: {
            login: this.login,
          }
        }).then(response => {
              this.showSecondStepLogic()
            },
            ex => {
              this.error.login_not_found = "Login didn't found"
            })
      }
    },
    submitFormSecondStepLogic: function () {
      if (this.$refs.formSecondStepLogic.validate()) {
        this.axios({
          method: 'post',
          url: '/ajax?command=change_password_by_email',
          data: {
            login: this.login,
            email: this.email,
            new_password: this.newPassword,
          }
        }).then(resp => {
            },
            ex => {
              if (ex.response.status === 401) {
                this.showThirdStepLogic()
                this.info.check_email = ex.response.data.error
              } else if (ex.response.status === 400) {
                this.error.incorrect_email = ex.response.data.error
              }
            })
      }
    },
    submitFormThirdStepLogic: function () {
      if (this.$refs.formThirdStepLogic.validate()) {
        this.axios({
          method: 'post',
          url: '/ajax?command=change_password_by_email',
          data: {
            login: this.login,
            email: this.email,
            new_password: this.newPassword,
            unique_key: this.uniqueKey
          }
        }).then(resp => {
              this.allFormsReset()
              this.info.success = text_page.change_password_by_email_component.success
            },
            ex => {
              if (ex.response.status === 400) {
                this.error.time_expired = ex.response.data.error
              }
            })
      }
    },
    allFormsReset: function () {
      this.$refs.formFirstStepLogic.reset()
      this.$refs.formSecondStepLogic.reset()
      this.$refs.formThirdStepLogic.reset()
      this.clearAllStepsLogic()
      this.showFirstStepLogic()
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