<template>
  <v-card
      class="mx-auto"
      color="grey lighten-4"
      max-width="600"
  >
    <v-img
        :aspect-ratio="16/9"
        src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
    >
      <!--      <v-expand-transition>-->
      <!--        <div v-if="hover"-->
      <!--             class="d-flex transition-fast-in-fast-out light-green accent-2 v-card&#45;&#45;reveal display-3 black&#45;&#45;text"-->
      <!--             style="height: 100%;">-->
      <!--          ${{product.price}}-->
      <!--        </div>-->
      <!--      </v-expand-transition>-->

    </v-img>
    <v-card-text class="pt-6" style="position: relative; background-color: grey">

      <v-btn @click="showCardInfo"
             absolute color="black" class="light-green--text text--lighten-2" fab right top>
        <v-icon>close</v-icon>
      </v-btn>
      <v-form
          ref="form"
          v-model="valid"
      >
        <div>
          <v-text-field
              dark
              v-model="name"
              :counter="15"
              :rules="rules.name"
              label="Name"
              required
          ></v-text-field>
        </div>
        <div>
          <v-text-field
              dark
              v-model="price"
              :counter="10"
              :rules="rules.pricee"
              label="Price"
              required
          ></v-text-field>
        </div>
        <div>
          <v-text-field
              dark
              v-model="info"
              :counter="100"
              :rules="rules.info"
              label="Info"
              required
          ></v-text-field>
        </div>
        <br>
        <div align="center">
          <v-progress-circular style="margin-right: 15px"
                               v-if="spinnerVisible"
                               indeterminate
                               color="#8C9EFF"
          ></v-progress-circular>
          <v-btn v-if="!spinnerVisible"
                 @click=""
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

    </v-card-text>
  </v-card>
</template>

<script>
export default {
  props: ['showCardInfo', 'product'],
  data() {
    return {
      valid: false,
      text_page: text_page,

      spinnerVisible: false,

      name: this.product.name,
      info: this.product.info,
      price: String(this.product.price),

      rules: {
        name: [
          v => !!v || this.text_page.form_component.input.first_name.error.required,
          v => (v && v.length >= 3) || this.text_page.form_component.input.first_name.error.min_length,
          v => (v && v.length <= 15) || this.text_page.form_component.input.first_name.error.max_length,
        ],
        pricee: [
          v => !!v || this.text_page.form_component.input.first_name.error.required,
          v => (v && v.length > 2) || this.text_page.form_component.input.first_name.error.min_length,
          v => (v && v.length <= 10) || this.text_page.form_component.input.first_name.error.max_length
        ],
        info: [
          v => !!v || this.text_page.form_component.input.first_name.error.required,
          v => (v && v.length >= 3) || this.text_page.form_component.input.first_name.error.min_length,
          v => (v && v.length <= 100) || this.text_page.form_component.input.first_name.error.max_length
        ],
      }
    }
  },
  watch: {
    name: function () {
      this.checkChange()
    },
    info: function () {
      this.checkChange()
    },
    price: function () {
      this.checkChange()
    },
  },
  created() {
    //info
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
        // this.axios({
        //   method: 'post',
        //   url: '/ajax?command=update_profile',
        //   data: {
        //     old_login: this.text_page.profile_component.login.value,
        //     login: this.login,
        //     first_name: this.first_name,
        //     last_name: this.last_name,
        //     telephone_number: this.telephone_number,
        //     email: this.email
        //   }
        // }).then(response => {
        //       this.reset()
        //       this.success = this.text_page.form_component.info.success
        //       this.error.login_not_unique = undefined
        //       this.error.telephone_number_not_unique = undefined
        //       this.error.email_not_unique = undefined
        //
        //       this.text_page.profile_component.login.value = this.login
        //       this.text_page.profile_component.first_name.value = this.first_name
        //       this.text_page.profile_component.last_name.value = this.last_name
        //       this.text_page.profile_component.telephone_number.value = this.telephone_number
        //       this.text_page.profile_component.email.value = this.email
        //     },
        //     ex => {
        //       if (ex.response.status === 400) {
        //         this.reset()
        //         this.login = this.text_page.profile_component.login.value
        //         this.first_name = this.text_page.profile_component.first_name.value
        //         this.last_name = this.text_page.profile_component.last_name.value
        //         this.telephone_number = this.text_page.profile_component.telephone_number.value
        //         this.email = this.text_page.profile_component.email.value
        //
        //         this.error.login_not_unique = ex.response.data.error.login_not_unique
        //         this.error.telephone_number_not_unique = ex.response.data.error.telephone_number_not_unique
        //         this.error.email_not_unique = ex.response.data.error.email_not_unique
        //       }
        //     })
      }
    },
    reset() {
      this.name = this.product.name
      this.info = this.product.info
      this.price = String(this.product.price)
    },
    checkChange() {
      if (this.name === this.product.name
          && this.info === this.product.info
          && this.price === String(this.product.price)) {
        this.valid = false
      } else {
        if (this.$refs.form.validate()) {
          this.valid = true
        } else {
          this.valid = false
        }
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

</style>