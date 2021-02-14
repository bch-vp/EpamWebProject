<template>

  <div class="wrapper">
    <v-container>
      <v-row justify="center" row>
        <v-col sm="12" md="12" lg="10" xl="7">
          <v-card
              style="box-shadow: 0 0 25px;background: rgba(0, 0, 0, 0.93);border-radius: 20px;"
              dark
              elevation="16"
              class="mx-auto"
          >
            <div class="scroll">
            <v-virtual-scroll
                :bench="benched"
                :items="items"
                height="800"
                item-height="420px"
            >

                <template v-slot:default="{ item }">
                  <v-list-item :key="item">
                    <v-container>
                    <v-row>
                      <v-col>
                        <v-card class="mx-auto" color="grey" width="300">
                          <v-img class="white--text align-end"
                                 height="200px"
                                 src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                            <v-card-title>Top 10 Australian beaches</v-card-title>
                          </v-img>

                          <v-card-subtitle class="pb-0">
                            Number 10
                          </v-card-subtitle>

                          <v-card-text class="text--primary">
                            <div>Whitehaven Beach</div>

                            <div>Whitsunday Island, Whitsunday Islands</div>
                          </v-card-text>

                          <v-card-actions>
                            <v-btn color="orange" text>
                              Share
                            </v-btn>

                            <v-btn color="orange" text>
                              Explore
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-col>
                      <v-col>
                        <v-card class="mx-auto"  color="grey" width="300">
                          <v-img class="white--text align-end"
                                 height="200px"
                                 src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                            <v-card-title>Top 10 Australian beaches</v-card-title>
                          </v-img>

                          <v-card-subtitle class="pb-0">
                            Number 10
                          </v-card-subtitle>

                          <v-card-text class="text--primary">
                            <div>Whitehaven Beach</div>

                            <div>Whitsunday Island, Whitsunday Islands</div>
                          </v-card-text>

                          <v-card-actions>
                            <v-btn color="orange" text>
                              Share
                            </v-btn>

                            <v-btn color="orange" text>
                              Explore
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-col>

                    </v-row>
                    </v-container>
                  </v-list-item>
                </template>

            </v-virtual-scroll>
            </div>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <div class="mx-auto" style="margin-top: 2em">
          <v-pagination
              dark
              v-model="page"
              :length="15"
              :total-visible="7"
              prev-icon="navigate_before"
              next-icon="navigate_next"
          ></v-pagination>
        </div>
      </v-row>
    </v-container>
  </div>

</template>

<script>
export default {
  data() {
    return {
      benched: 4,
      page: 1,
    }
  },
  computed: {
    items() {
      return Array.from({length: this.length}, (k, v) => v + 1)
    },
    length() {
      return 9
    },
  }
}
</script>

<style scoped>
.scroll{
  padding-top: 4em;
  padding-bottom: 4em;
}
.container {

  padding-left: 3em;
  padding-right: 3em;

}

.wrapper {
  width: 100%;
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-right: -50%;
  transform: translate(-50%, -50%);
}
</style>