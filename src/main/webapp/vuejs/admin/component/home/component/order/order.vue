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
          <div>
            {{order.dateCreatedAt}}
          </div>
          <div>
            Adds: <strong> {{ order.address }}</strong>
          </div>
        </div>
      </v-list-item-title>
    </v-list-item-content>


    <v-list-item-action >
      <v-row>
        <v-col>
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  dark
                  outlined text
                  v-bind="attrs"
                  v-on="on"
              >
                Products
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                  v-for="order in order.products"
                  :key="order.id"
              >
                <v-list-item-title>
                  <v-container>
                    <v-row>
                      <v-col>
                        {{ order.name }}
                      </v-col>
                      <v-col>
                        {{ order.price }}$
                      </v-col>
                    </v-row>
                  </v-container>

                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-col>
        <v-col>
          <v-btn @click="" :disabled="order.status === 'CONFIRMED'" style="width: 11em" outlined text>
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