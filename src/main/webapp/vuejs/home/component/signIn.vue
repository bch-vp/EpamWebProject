<template>
  <v-container>
    <div class="title font-weight-regular" style="color: white">Sign in...</br></br></div>
    <v-form
        @submit="submit"
        action="HotelWeb?command=sign_in"
        method="post"
        ref="formSignIn"
        v-model="valid"
    >
      <div style="color: red">{{ signInErrorMessage }}</div>
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

      <div align="center">
        <v-btn type="submit" :disabled="!valid" dark small text rounded color="indigo">submit
        </v-btn>
        <v-btn @click="reset" outlined small fab color="indigo">
          <v-icon>autorenew</v-icon>
        </v-btn>
      </div>
    </v-form>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      signInErrorMessage: signInErrorMessage,
      value: String,
      valid: false,
      login: '',
      password: '',
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
    },
  }
}
</script>

<style>

</style>