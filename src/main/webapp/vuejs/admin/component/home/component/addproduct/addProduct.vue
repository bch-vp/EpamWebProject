<template>
  <div class="profile-wrapper">
    <v-container>
      <v-row justify="center" row>
        <v-col sm="9" md="9" lg="8" xl="5">
          <div class="profile-background">
            <div class="profile">

<!--              <v-card-->
<!--                  color="grey lighten-4"-->
<!--              >-->
                <v-img
                    style="margin-top: 2em"
                    max-height="200"
                    :aspect-ratio="16/9"
                    src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
                >

<!--                  <div style="padding-top: 15px">-->
<!--                    <v-btn @click="showCardInfo"-->
<!--                           absolute color="black" class="light-green&#45;&#45;text text&#45;&#45;lighten-2"  fab right >-->
<!--                      <v-icon >close</v-icon>-->
<!--                    </v-btn>-->
<!--                  </div>-->
                </v-img>
                <v-card-text class="pt-6" style="position: relative;">
                  <v-form
                      ref="form"
                      v-model="valid"
                  >
                    <v-row>
                      <v-col>
                        <v-file-input
                            ref="file"
                            prepend-icon="add_a_photo">
                        </v-file-input>
                      </v-col>
                      <v-col>
                        <v-text-field
                            dark
                            v-model="name"
                            :counter="15"
                            :rules="rules.name"
                            v-bind:label=text_page.form_component.input.name.name
                            required
                        ></v-text-field>
                      </v-col>
                     <v-col>
                       <v-text-field
                           dark
                           prefix="$"
                           v-model="price"
                           :counter="13"
                           :rules="rules.price"
                           v-bind:label=text_page.form_component.input.price.name
                           required
                       ></v-text-field>
                     </v-col>
                    </v-row>
                    <div>
                      <v-textarea
                          filled
                          v-model="info"
                          color="orange"
                          :counter="100"
                          rows="2"
                          :rules="rules.info"
                          v-bind:label=text_page.form_component.input.info.name
                      ></v-textarea>
                    </div>
                    <br>
                    <div align="center">
                      <v-progress-circular style="margin-right: 15px"
                                           v-if="spinnerVisible"
                                           indeterminate
                                           color="#8C9EFF"
                      ></v-progress-circular>
                      <v-btn v-if="!spinnerVisible"
                             @click="submit"
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
<!--              </v-card>-->
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  props: ['product'],
  data() {
    return {
      valid: false,
      text_page: text_page,

      spinnerVisible: false,
      isError: false,
      isSuccess: false,

      name: '',
      info: '',
      price: '',

      rules: {
        name: [
          v => !!v || this.text_page.form_component.input.name.error.required,
          v => (v && v.length >= 3) || this.text_page.form_component.input.name.error.min_length,
          v => (v && v.length <= 15) || this.text_page.form_component.input.name.error.max_length,
        ],
        price: [
          v => !!v || this.text_page.form_component.input.price.error.required,
          v => (v && String(this.product.price).length >= 1) || this.text_page.form_component.input.price.error.min_length,
          v => (v && String(this.product.price).length <= 11) || this.text_page.form_component.input.price.error.max_length,
          v => /^[0-9]{1,10}(\.[0-9]{2})?$/.test(v) || this.text_page.form_component.input.price.error.pattern,
        ],
        info: [
          v => !!v || this.text_page.form_component.input.info.error.required,
          v => (v && v.length >= 3) || this.text_page.form_component.input.info.error.min_length,
          v => (v && v.length <= 100) || this.text_page.form_component.input.info.error.max_length
        ],
      }
    }
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

  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        // this.axios({
        //   method: 'post',
        //   url: '/ajax?command=update_product_info',
        //   data: {
        //     id: String(this.product.id),
        //     name: this.name,
        //     info: this.info,
        //     price: this.price,
        //   }
        // }).then(response => {
        //       this.product.name = this.name
        //       this.product.info = this.info
        //       this.product.price = this.price
        //
        //       this.reset()
        //
        //       this.isError = false
        //       this.isSuccess = true
        //
        //       this.await3Seconds()
        //     },
        //     ex => {
        //       this.reset()
        //       this.isSuccess = false
        //       this.isError = true
        //     })
      }
    },
    async await3Seconds() {
      // await new Promise(resolve => setTimeout(resolve, 1000));
      // this.isSuccess = false
    },
    reset() {

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
@media screen and (min-width: 400px) {
  .profile-wrapper {
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
  .profile-wrapper {
    width: 100%;
    position: relative;
    margin-right: -50%;
  }
}

.profile-background {
  box-shadow: 0 0 2px;
  background: rgba(0, 0, 0, 0.93);
  border-radius: 20px;
}


.profile {
  color: white;

  padding-top: 1em;
  padding-right: 2em;
  padding-left: 2em;
  padding-bottom: 2em;
}

</style>