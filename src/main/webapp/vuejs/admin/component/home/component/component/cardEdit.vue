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
<!--        <v-row>-->
<!--          <v-col>-->
<!--            <v-text-field-->
<!--                dark-->
<!--                v-model="newPassword"-->
<!--                :counter="20"-->
<!--                :rules="rules.new_password"-->
<!--                :append-icon="valueNewPassword ? 'visibility' : 'visibility_off'"-->
<!--                @click:append="() => (valueNewPassword = !valueNewPassword)"-->
<!--                :type="valueNewPassword ? 'password' : 'text'"-->
<!--                v-bind:label=text_page.form_component.input.new_password.name-->
<!--                required-->
<!--            ></v-text-field>-->
<!--          </v-col>-->
<!--          <v-col>-->
<!--            <v-text-field-->
<!--                dark-->
<!--                v-model="passwordRepeat"-->
<!--                :counter="20"-->
<!--                :rules="rules.password_repeat"-->
<!--                :append-icon="valuePasswordRepeat ? 'visibility' : 'visibility_off'"-->
<!--                @click:append="() => (valuePasswordRepeat = !valuePasswordRepeat)"-->
<!--                :type="valuePasswordRepeat ? 'password' : 'text'"-->
<!--                v-bind:label=text_page.form_component.input.password_repeat.name-->
<!--                required-->
<!--            ></v-text-field>-->
<!--          </v-col>-->
<!--        </v-row>-->
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
  data(){
    return{
      valid: false,
      text_page:text_page,
      spinnerVisible: false,
      name: this.product.name,
      rules: {
        name: [
          v => !!v || this.text_page.form_component.input.first_name.error.required,
          v => (v && v.length >= 3) || this.text_page.form_component.input.first_name.error.min_length,
          v => (v && v.length <= 15) || this.text_page.form_component.input.first_name.error.max_length,
          v => /^\S*$/.test(v) || this.text_page.form_component.input.first_name.error.spaces_prohibited,
          v => /^[a-zA-Z]+$/.test(v) || this.text_page.form_component.input.first_name.error.only_letters
        ],
      }
    }
  },
  methods:{
    reset() {
      this.$refs.form.reset()
    },
  }
}
</script>

<style scoped>

</style>