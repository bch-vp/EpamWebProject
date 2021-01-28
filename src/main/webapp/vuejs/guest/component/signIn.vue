<template>
  <div class="sign-in-wrapper">
    <v-container>
      <v-row justify="center" row>
        <v-col sm="8" md="6" lg="5" xl="4">
          <div class="sign-in-background">
            <div class="sign-in">
              <div class="title font-weight-regular" style="color: white; text-align: center">
                <span class="text-h6 font-weight-regular center ">
                  {{ text_page.sign_in_component.name }}...
                </span><br></div>
              <v-form
                  @submit="submit"
                  action="do?command=sign_in"
                  method="post"
                  ref="formSignIn"
                  v-model="valid"
              >
                <div style="color: red">{{ error.not_found }}</div>
                <div style="color: yellow">{{ error.database_connection_not_received }}</div>
                <v-text-field
                    dark
                    name="login"
                    v-model="login"
                    :counter="15"
                    :rules="rules.login"
                    v-bind:label=text_page.sign_in_component.login.name
                    required
                >
                </v-text-field>

                <v-text-field
                    dark
                    name="password"
                    v-model="password"
                    :counter="20"
                    :rules="rules.password"
                    :append-icon="value ? 'visibility' : 'visibility_off'"
                    @click:append="() => (value = !value)"
                    :type="value ? 'password' : 'text'"
                    v-bind:label=text_page.sign_in_component.password.name
                    required
                ></v-text-field><br>
                <v-row>
                  <v-btn @click="showChangePassword" color="red" text outlined rounded>
                    <span style="">forget password?</span>
                  </v-btn>
                </v-row>
                <br>
                <v-row>
                  <v-btn @click="showSignUp" color="green" text outlined rounded>
                    <span style="">{{ text_page.sign_in_component.go_to_component.sign_up }}</span>
                  </v-btn>
                </v-row>
                <div align="center">
                  <v-btn type="submit" :disabled="!valid" dark small text rounded color="#8C9EFF">
                    {{ text_page.sign_in_component.submit }}
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
  props: ['error', 'showSignUp', 'showChangePassword'],
  data() {
    return {
      text_page: {
        sign_in_component: text_page.sign_in_component,
      },
      value: String,
      valid: false,
      login: '',
      password: '',
      rules: {
        login: [
          v => !!v || this.text_page.sign_in_component.login.error.required,
          v => /^[a-zA-Z0-9_.-]+$/.test(v) || this.text_page.sign_in_component.login.error.valid_characters,
          v => (v && v.length >= 3) || this.text_page.sign_in_component.login.error.min_length,
          v => (v && v.length <= 15) || this.text_page.sign_in_component.login.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_in_component.login.error.spaces_prohibited,
        ],
        password: [
          v => !!v || this.text_page.sign_in_component.password.error.required,
          v => (v && v.length >= 5) || this.text_page.sign_in_component.password.error.min_length,
          v => (v && v.length <= 20) || this.text_page.sign_in_component.password.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.sign_in_component.password.error.spaces_prohibited,
          v => /(?=.*?[a-z])/.test(v) || this.text_page.sign_in_component.password.error.one_lower_case_letter,
          v => /(?=.*?[A-Z])/.test(v) || this.text_page.sign_in_component.password.error.one_upper_case_letter,
          v => /(?=.*?[0-9])/.test(v) || this.text_page.sign_in_component.password.error.one_digit,
        ],
      },
    }
  },
  methods: {
    submit: function (event) {
      if (this.$refs.formSignIn.validate()) {
        this.$refs.formSignIn.submit()
      }
    },
    reset: function () {
      this.$refs.formSignIn.reset()
      this.error.not_found = undefined
      this.error.database_connection_not_received = undefined
    },
  }
}
</script>

<style scoped>

.sign-in-wrapper {
  width: 100%;
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-right: -50%;
  transform: translate(-50%, -50%);
}


.sign-in-background {
  box-shadow: 0 0 25px;
  background: rgba(0, 0, 0, 0.93);
  border-radius: 20px;
}

@media screen and (max-width: 400px) {
  .sign-in {
    padding: 2em;
  }
}

@media screen and (min-width: 400px) {
  .sign-in {
    padding-left: 45px;
    padding-right: 45px;
    padding-top: 30px;
    padding-bottom: 30px;
  }
}

</style>