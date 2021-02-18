<template>
  <v-hover v-slot="{ hover }">
    <v-card
        class="mx-auto"
        color="grey lighten-4"
        max-width="600"
    >
      <v-img
          :aspect-ratio="16/9"
          src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
      >
        <v-expand-transition>
          <div v-if="hover"
               class="d-flex transition-fast-in-fast-out light-green accent-2 v-card--reveal display-3 black--text"
               style="height: 100%;">
            ${{product.price}}
          </div>
        </v-expand-transition>
      </v-img>
      <v-card-text class="pt-6" style="position: relative; background-color: grey">
        <v-btn @click="" absolute color="black" class="white--text" fab  right top>
          <v-icon>close</v-icon>
        </v-btn>

        <div class="text-h5 font-weight-light white--text mb-2">
          {{product.name}}
        </div>
        <div style="color: black" class="text-subtitle-1 font-weight-light title mb-2">
         {{product.info}}
        </div>
      </v-card-text>
    </v-card>
  </v-hover>
</template>

<script>
export default {
  props: ['product'],
  methods:{
    // addProductToShoppingCart(){
    //   this.$store.commit('add_productToShoppingCart', this.product)
    // }
  }
}
</script>

<style scoped>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}
</style>