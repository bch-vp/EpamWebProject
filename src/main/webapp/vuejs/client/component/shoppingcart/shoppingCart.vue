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
                      <div class="col-md-4" v-if="isShoppingCart" v-for="product in this.$store.state.App.shoppingCart">
                        <ProductCard :product="product"/>
                      </div>
                      <div v-if="isOrder">
                        <Order :close_isOrder="show_isShoppingCart"/>
                      </div>
                    </v-row>
                  </v-container>
                </div>
              </div>
            </div>
          </v-card>
          <v-row v-if="isShoppingCart" style="padding-top: 2em;">
            <v-col >
              <span style="color: white; padding-left: 5px" class="text-h5">
                {{text_page.page_info.total_price}}:&nbsp {{ calculateOrderPrice }}
                <span class="light-green--text text--lighten-2">
                  $
                </span>
              </span>
            </v-col>
            <v-col>
              <div align="center">
                <v-btn @click="show_isOrder" :disabled="$store.state.App.shoppingCart.length === 0" dark rounded outlined color="light-green accent-2" class="text-h6 white--text" text>
                  {{text_page.form_component.button.order_products}}
                </v-btn>
              </div>
            </v-col>
            <v-col>
              <div align="right">
                <span style="color: white; padding-left: 5px" class="text-h5">
                {{text_page.page_info.products}}:&nbsp {{ $store.state.App.shoppingCart.length }}
              </span>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>

  <!--<span style="color: white; padding-left: 5px" class="text-h5">-->
  <!--                Products: {{ $store.state.App.shoppingCart.length }}-->
  <!--</span>-->

</template>

<script>
import ProductCard from "vuejs/client/component/shoppingcart/component/product/ProductCard.vue";
import Order from "vuejs/client/component/shoppingcart/component/order/order.vue";

export default {
  components: {
    ProductCard,
    Order
  },
  data() {
    return {
      isOrder:false,
      isShowOrders:false,
      isShoppingCart:true,

      text_page:text_page
    }
  },
  methods:{
    clear_allComponents(){
      this.isOrder = false
      this.isShowOrders = false
      this.isShoppingCart = false
    },
    show_isShoppingCart(){
      this.clear_allComponents()
      this.isShoppingCart = true
    },
    show_isOrder(){
      this.clear_allComponents()
      this.isOrder = true
    },
    show_isShowOrders(){
      this.clear_allComponents()
      this.isShowOrders =true
    }
  },
  computed:{
    calculateOrderPrice(){
      var orderPrice = 0
      this.$store.state.App.shoppingCart.forEach(function (product) {
        orderPrice += product.price
      })
      return orderPrice
    }
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