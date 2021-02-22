<template>
  <v-row>
    <v-col>
      <div v-if="isError" class="text-subtitle-1 font-weight-medium mb-2">
          <span style="color: orangered">{{ text_page.form_component.error.notification }}:&nbsp
          </span>{{ text_page.form_component.error.need_reload_page }}
      </div>
      <div align="center" v-if="isSuccess && !isError" class="text-subtitle-1 font-weight-medium mb-2">
        <span style="color: greenyellow">{{ text_page.form_component.info.success }}</span>
      </div>
      <div v-if="!isError && !isSuccess">
        <v-select
            v-model="selectStatus"
            v-bind:hint=text_page.form_component.button.choose_status
            :items="itemsStatus"
            item-text="name"
            persistent-hint
            return-object
            single-line
        ></v-select>
        <v-select
            v-model="selectCategory"
            v-bind:hint=text_page.form_component.button.choose_category
            :items="$store.state.App.categories"
            item-text="name"
            persistent-hint
            return-object
            single-line
        ></v-select>
        <div align="center" style="padding-top: 2em; padding-bottom: 0">
          <v-progress-circular style="margin-right: 15px"
                               v-if="spinnerVisible"
                               indeterminate
                               color="#8C9EFF"
          ></v-progress-circular>
          <v-btn v-if="!spinnerVisible"
                 @click="submitCategory"
                 :disabled="selectCategory === $store.state.App.selectCategory
                                && selectStatus === product.status"
                 dark text rounded color="#8C9EFF">
            {{ text_page.form_component.button.submit }}
          </v-btn>
        </div>
      </div>
    </v-col>
  </v-row>
</template>

<script>
export default {
  props: ['product', 'showCardInfo'],
  data() {
    return {
      text_page: text_page,

      spinnerVisible: false,

      selectCategory: this.$store.state.App.selectCategory,
      selectStatus: this.product.status,

      isError: false,
      isSuccess: false,

      itemsStatus: [
        {id: 1, name: 'ACTIVE'},
        {id: 2, name: 'INACTIVE'},
        {id: 3, name: 'BLOCKED'}
      ],
    }
  },
  methods: {
    submitCategory() {
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
      if (this.selectStatus !== this.product.status) {
        this.axios({
          method: 'post',
          url: '/ajax?command=update_product_status',
          data: {
            id_status: String(this.selectStatus.id),
            id_product: String(this.product.id)
          }
        }).then(response => {
          this.product.status = this.selectStatus.name
              this.isSuccess = true
            },
            ex => {
              this.isError = true
            })
      }
      if (this.selectCategory !== this.$store.state.App.selectCategory && !this.isError) {
        this.axios({
          method: 'post',
          url: '/ajax?command=update_product_category',
          data: {
            id_category: String(this.selectCategory.id),
            id_product: String(this.product.id)
          }
        }).then(response => {
              this.isSuccess = true
          this.removeAfter3Seconds()
            },
            ex => {
              this.isError = true
            })
      }
    },
     async removeAfter3Seconds(){
      await new Promise(resolve => setTimeout(resolve, 1000));
       this.$store.commit('remove_productToProducts', this.product)
       this.showCardInfo()
    },
    showSpinner() {
      this.spinnerVisible = true;
    },
    hideSpinner() {
      this.spinnerVisible = false;
    },
  },
}
</script>

<style scoped>

</style>