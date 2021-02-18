<template>

  <div style="width: 100%; margin-top: 80px;">
    <v-container>
      <v-row justify="center" row>
        <v-col ms="12" md="12" lg="11" xl="8">

          <v-card
              style="box-shadow: 0 0 25px;background: rgba(0, 0, 0, 0.93);border-radius: 20px;"
              dark
              elevation="16"
              class="mx-auto"
          >

            <div style="padding-top: 3em; padding-bottom: 3em;">
              <div class="scroll">
                <div style="padding-right: 2em;padding-left: 2em">
                  <v-container>
                    <v-row>
                      <div class="col-md-4" v-for="product in this.$store.state.App.shoppingCart">
                        <ProductCard :product="product"/>
                      </div>
                    </v-row>
                  </v-container>
                </div>
              </div>
            </div>
          </v-card>
<!--          <v-row style="padding-top: 2em;">-->
<!--            <v-col>-->
<!--              <v-select-->
<!--                  style="max-width: 230px"-->
<!--                  dark-->
<!--                  v-model="filtersValue"-->
<!--                  :items="filtersItems"-->
<!--                  chips-->
<!--                  label="Choose to show only..."-->
<!--                  multiple-->
<!--                  outlined-->
<!--              ></v-select>-->
<!--            </v-col>-->
<!--            <v-col>-->
<!--              <div align="center">-->
<!--                <v-btn dark style="color: white;" :disabled="page === 1" @click="page&#45;&#45;">-->
<!--                  <v-icon>navigate_before</v-icon>-->
<!--                </v-btn>-->
<!--                <v-btn dark style="color: white">-->
<!--            <span style="color: orange;">-->
<!--                   {{ page }}-->
<!--                 </span>-->
<!--                </v-btn>-->
<!--                <v-btn dark style="color: white" @click="page++" :disabled="page >= pages.length">-->
<!--                  <v-icon>navigate_next</v-icon>-->
<!--                </v-btn>-->
<!--              </div>-->
<!--            </v-col>-->
<!--            <v-col>-->
<!--              <div align="right">-->
<!--              <span style="color: white; padding-right: 5px" class="text-h5">-->
<!--                Pages: {{ pages.length }}-->
<!--              </span>-->
<!--                <span style="color: white;" class="text-h5">-->
<!--                |-->
<!--              </span>-->
<!--                <span style="color: white; padding-left: 5px" class="text-h5">-->
<!--                Products: {{ productsWithFilters.length }}-->
<!--              </span>-->
<!--              </div>-->
<!--            </v-col>-->
<!--          </v-row>-->
        </v-col>
      </v-row>
    </v-container>
  </div>

  <!--<span style="color: white; padding-left: 5px" class="text-h5">-->
  <!--                Products: {{ $store.state.App.shoppingCart.length }}-->
  <!--</span>-->

</template>

<script>
import ProductCard from "vuejs/client/component/shoppingcart/component/ProductCard.vue";

export default {
  components:{
    ProductCard
  }
}
</script>

<style scoped>

@media screen and (min-height: 1100px) {
  .scroll {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 950px;
    overflow-x: hidden;
    overflow-y: auto;
  }
}

@media screen and (max-height: 1100px) {
  .scroll {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 600px;
    overflow-x: hidden;
    overflow-y: auto;
  }
}
</style>