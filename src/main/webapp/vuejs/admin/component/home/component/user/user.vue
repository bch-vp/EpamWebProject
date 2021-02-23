<template>
  <v-list-item>
    <v-list-item-action>
      <v-btn
          fab
          small
          depressed
          color="primary"
      >
        {{ user.id }}
      </v-btn>
    </v-list-item-action>

    <v-progress-circular style="margin-right: 15px"
                         v-if="spinnerVisible"
                         indeterminate
                         color="#8C9EFF"
    ></v-progress-circular>

    <div v-if="isError" class="text-subtitle-1 font-weight-medium mb-2">
          <span style="color: orangered">{{ text_page.form_component.error.notification }}:&nbsp
          </span>{{ text_page.form_component.error.need_reload_page }}
    </div>

    <v-list-item-content v-if="!spinnerVisible && !isError">
      <v-list-item-title>
        User Database Record <strong>ID {{ user.id }}</strong>
      </v-list-item-title>
    </v-list-item-content>
    <v-list-item-content v-if="!spinnerVisible && !isError">
      <div style="color: red" align="center" v-if="user.status === 'NOT_ACTIVATED'">
        NOT ACTIVATED!
      </div>
    </v-list-item-content>

    <v-list-item-action v-if="!spinnerVisible && !isError">
      <v-row>
        <v-btn @click="submit(user.id, 1, 'ACTIVATED')" style="margin-right: 2em" outlined text
               :disabled="user.status === 'ACTIVATED'">
          ACTIVATED
        </v-btn>
        <v-btn @click="submit(user.id,3, 'BANNED')" style="margin-right: 2em" outlined text
               :disabled="user.status === 'BANNED'">
          BANNED
        </v-btn>
      </v-row>
    </v-list-item-action>
  </v-list-item>
</template>

<script>
export default {
  props: ['user'],
  data() {
    return {
      text_page: text_page,

      spinnerVisible: false,
      isError: false,
      isSuccess: false
    }
  },
  methods: {
    submit: function (id_user, id_status, status) {
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
        this.axios({
          method: 'post',
          url: '/ajax?command=update_client_status',
          data: {
            id_user: String(id_user),
            id_status: String(id_status)
          }
        }).then(response => {
              this.user.status = status
            },
            ex => {
              this.isError = true
            })
    },
    async await3Seconds() {
      await new Promise(resolve => setTimeout(resolve, 1000));
      this.isSuccess = false
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