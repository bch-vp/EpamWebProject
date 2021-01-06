<template>
  <v-app style="background-color: #ff340a">
    <div style="position: absolute; top: 30%; left: 30%; width:40%;">
      <div style="background-color: black;">
        <div class="title font-weight-regular" style="margin-left: 33%; padding-top: 3%; color: white">Sign up...</div>
        <v-form
            @submit="submitSignUp"
            action="HotelWeb?command=sign_up"
            method="post"
            ref="formSignUp"
            v-model="signUpData.valid"
        >
          <v-text-field
              dark
              name="login"
              v-model="signUpData.login"
              :counter="10"
              :rules="signUpRules.login"
              label="Login"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="password"
              v-model="signUpData.password"
              :counter="10"
              :rules="signUpRules.password"
              :append-icon="signUpData.value ? 'visibility' : 'visibility_off'"
              @click:append="() => (signUpData.value = !signUpData.value)"
              :type="signUpData.value ? 'password' : 'text'"
              label="Password"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="name"
              v-model="signUpData.name"
              :counter="10"
              :rules="signUpRules.name"
              label="Name"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="surname"
              v-model="signUpData.surname"
              :counter="10"
              :rules="signUpRules.surname"
              label="Surname"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="phone"
              v-model="signUpData.telephoneNumber"
              :counter="17"
              :rules="signUpRules.telephoneNumber"
              label="Telephone number"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="email"
              v-model="signUpData.email"
              :rules="signUpRules.email"
              :counter="35"
              label="E-mail"
              required
          ></v-text-field>

          <div align="center">
            <v-btn type="submit" :disabled="!signUpData.valid" dark small text rounded color="indigo">submit
            </v-btn>
            <v-btn @click="resetSignUp" outlined small fab color="indigo">
              <v-icon>autorenew</v-icon>
            </v-btn>
          </div>
        </v-form>
      </div>
    </div>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      isHome: false,
      isSignIn: false,
      isSignUp: false,
      signInData: {
        value: String,
        valid: false,
        login: '',
        password: '',
      },
      signUpData: {
        value: String,
        valid: false,
        login: '',
        password: '',
        name: '',
        surname: '',
        telephoneNumber: '',
        email: '',
      },
      signInRules: {
        login: [
          v => !!v || 'Login is required',
          v => /.{3,}/.test(v) || 'Minimum three in length',
          v => (v && v.length <= 10) || 'Login must be less than 10 characters',
          v => /^\S*$/.test(v) || 'Spaces are prohibited',
        ],
        password: [
          v => !!v || 'Password is required',
          v => /.{5,}/.test(v) || 'Minimum five in length',
          v => (v && v.length <= 10) || 'Password must be less than 10 characters',
          v => /^\S*$/.test(v) || 'Spaces are prohibited',
          v => /(?=.*?[a-z])/.test(v) || 'At least one lower case English letter',
          v => /(?=.*?[A-Z])/.test(v) || 'At least one upper case English letter',
          v => /(?=.*?[0-9])/.test(v) || 'At least one digit',
        ],
      },
      signUpRules: {
        login: [
          v => !!v || 'Login is required',
          v => /.{3,}/.test(v) || 'Minimum three in length',
          v => (v && v.length <= 10) || 'Login must be less than 10 characters',
          v => /^\S*$/.test(v) || 'Spaces are prohibited',
        ],
        password: [
          v => !!v || 'Password is required',
          v => /.{5,}/.test(v) || 'Minimum five in length',
          v => (v && v.length <= 10) || 'Password must be less than 10 characters',
          v => /^\S*$/.test(v) || 'Spaces are prohibited',
          v => /(?=.*?[a-z])/.test(v) || 'At least one lower case English letter',
          v => /(?=.*?[A-Z])/.test(v) || 'At least one upper case English letter',
          v => /(?=.*?[0-9])/.test(v) || 'At least one digit',
        ],
        name: [
          v => !!v || 'Name is required',
          v => /^[a-zA-Z]{2,10}$/.test(v) || 'Name is incorrect',
        ],
        surname: [
          v => !!v || 'Surname is required',
          v => /^[a-zA-Z]{2,10}$/.test(v) || 'Surname is incorrect',
        ],
        telephoneNumber: [
          v => !!v || 'Telephone number is required',
          v => /^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$/.test(v) || 'Telephone number must be valid: +XXX(XX)XXX-XX-XX',
        ],
        email: [
          v => !!v || 'E-mail is required',
          v => (v && v.length <= 35) || 'E-mail must be less than 35 characters',
          v => /^[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\.[a-z]{2,5}$/.test(v) || 'E-mail must be valid: +@+.+',
        ]
      }
    }
  },
  methods: {
    submitSignIn: function (event) {
      if (this.$refs.formSignIn.validate()) {
        this.$refs.formSignIn.submit()
        event.preventDefault()
      }
    },
    resetSignIn: function () {
      this.$refs.formSignIn.reset()
    },
    submitSignUp: function () {
      if (this.$refs.formSignUp.validate()) {
        this.$refs.formSignUp.submit()
      }
    },
    resetSignUp: function () {
      this.$refs.formSignUp.reset()
    },
    clearAllComponents() {
      if (this.$refs.formSignUp) {
        this.$refs.formSignUp.reset()
      }
      if (this.$refs.formSignIn) {
        this.$refs.formSignIn.reset()
      }
      this.isSignIn = false
      this.isSignUp = false
      this.isHome = false

    },
    showHome() {
      this.clearAllComponents()
      this.isHome = true
    },
    showSignUp() {
      this.clearAllComponents()
      this.isSignUp = true
    },
    showSignIn() {
      this.clearAllComponents()
      this.isSignIn = true
    }
  }
}
</script>

<style>
.c {
  background-color: #ff340a;
}

.coloe {
  background-color: black;
}

.div_style {
  position: absolute;
  width: 50%;
  left: 25%;
  top: 50%;
  background: #fc0; /* Цвет фона */
}
</style>