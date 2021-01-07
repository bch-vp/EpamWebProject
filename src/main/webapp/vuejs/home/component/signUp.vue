<template>
  <div class="sign-up-wrapper">
    <div class="sign-up-background">
      <div class="sign-up">
        <div class="title font-weight-regular" style="color: white">Sign up...</br></br></div>
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
              :counter="10"
              :rules="rules.login"
              label="Login"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="password"
              v-model="password"
              :counter="10"
              :rules="rules.password"
              :append-icon="value ? 'visibility' : 'visibility_off'"
              @click:append="() => (value = !value)"
              :type="value ? 'password' : 'text'"
              label="Password"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="name"
              v-model="name"
              :counter="10"
              :rules="rules.name"
              label="Name"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="surname"
              v-model="surname"
              :counter="10"
              :rules="rules.surname"
              label="Surname"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="phone"
              v-model="telephoneNumber"
              :counter="17"
              :rules="rules.telephoneNumber"
              label="Telephone number"
              required
          ></v-text-field>

          <v-text-field
              dark
              name="email"
              v-model="email"
              :rules="rules.email"
              :counter="35"
              label="E-mail"
              required
          ></v-text-field>

          <div align="center">
            <v-btn type="submit" :disabled="!valid" dark small text rounded color="indigo">submit
            </v-btn>
            <v-btn @click="reset" outlined small fab color="indigo">
              <v-icon>autorenew</v-icon>
            </v-btn>
          </div>
        </v-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: String,
      valid: false,
      login: '',
      password: '',
      name: '',
      surname: '',
      telephoneNumber: '',
      email: '',
      rules: {
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
  background: rgba(128, 128, 128, 0.1);
  border-radius: 20px;
}

.sign-up{
  padding: 15px;
}
</style>