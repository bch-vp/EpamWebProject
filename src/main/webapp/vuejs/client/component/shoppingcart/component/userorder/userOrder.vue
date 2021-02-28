<template>
  <v-list-item>

    <v-list-item-action>
      <v-list-item-title style="width: 5em">
        <span style="color: white;" class="text-h6" >
                {{ order.totalPrice }}
                <span class="light-green--text text--accent-2">
                  $
                </span>
              </span>
      </v-list-item-title>
    </v-list-item-action>


    <v-list-item-content>
      <v-list-item-title>
        <div align="left" style="padding-left: 1em">
          Adds: <strong> {{ order.address }}</strong>
        </div>
      </v-list-item-title>
    </v-list-item-content>


    <v-list-item-action >
      <v-row>
        <v-col>
          <v-btn outlined text>
            Products
          </v-btn>
        </v-col>
        <v-col>
          <v-btn :disabled="true" style="width: 11em" outlined text>
          {{order.status}}
          </v-btn>
        </v-col>
      </v-row>
    </v-list-item-action>
  </v-list-item>
</template>

<script>
export default {
  props: ['order'],
  data() {
    return {
      text_page: text_page,
    }
  },
}
</script>

<style scoped>

</style>