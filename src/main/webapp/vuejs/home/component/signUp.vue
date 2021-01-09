<template>
  <div class="sign-up-wrapper">
    <div class="sign-up-background">
      <div class="sign-up-shadow-in">
        <div class="sign-up">
          <div class="title font-weight-regular" style="color: white; text-align: center">
            {{ text_page.sign_up_component.name }}...<br>
          </div>
          <v-form
              @submit="submit"
              action="HotelWeb?command=sign_up"
              method="post"
              ref="formSignUp"
              v-model="valid"
          >
            <v-text-field
                dark
                name="login"
                v-model="login"
                :counter="15"
                :rules="rules.login"
                v-bind:label=text_page.sign_up_component.login.name
                required
            ></v-text-field>

            <v-text-field
                dark
                name="password"
                v-model="password"
                :counter="20"
                :rules="rules.password"
                :append-icon="value ? 'visibility' : 'visibility_off'"
                @click:append="() => (value = !value)"
                :type="value ? 'password' : 'text'"
                v-bind:label=text_page.sign_up_component.password.name
                required
            ></v-text-field>

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

            <div align="center">
              <v-btn type="submit" :disabled="!valid" dark small text rounded color="#8C9EFF">
                {{ text_page.sign_up_component.submit }}
              </v-btn>
              <v-btn @click="reset" outlined small fab color="#8C9EFF">
                <v-icon>autorenew</v-icon>
              </v-btn>
            </div>
          </v-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      text_page: {
        sign_up_component: text_page.sign_up_component,
      },
      value: String,
      valid: false,
      login: '',
      password: '',
      first_name: '',
      last_name: '',
      telephoneNumber: '',
      email: '',
      rules: {
        login: [
          v => !!v || this.text_page.sign_up_component.login.error.required,
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
        this.$refs.formSignUp.submit()
      }
    },
    reset: function () {
      this.$refs.formSignUp.reset()
    },
  }
}
</script>

<style>
.sign-up-wrapper {
  width: 30%;
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-right: -50%;
  transform: translate(-50%, -50%);
}

.sign-up-background {
  box-shadow: 0 0 300px;
  background: rgba(128, 128, 128, 0.1);
  border-radius: 20px;
}

.sign-up-shadow-in {
  box-shadow: inset 0 0 20px;
  border-radius: 20px;
}

.sign-up {
  padding: 45px;
}
</style>