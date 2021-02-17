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
              class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
              style="height: 100%;">
            ${{product.price}}
          </div>
        </v-expand-transition>
      </v-img>
      <v-card-text class="pt-6" style="position: relative; background-color: grey">
        <v-btn absolute color="orange" class="white--text" fab  right top>
          <v-icon>shopping_cart</v-icon>
        </v-btn>

        <div class="text-h5 font-weight-light orange--text mb-2">
          {{product.name}}
        </div>
        <div style="color: white" class="text-h6 font-weight-light white--text title mb-1">
          {{product.status}}
        </div>
        <div style="color: black" class="text-subtitle-1 font-weight-light title mb-2">
          Our Vintage kitchen utensils delight any chef.
          Made of bamboo by handOur Vintage Vintage Vintage Vintage
        </div>
      </v-card-text>
    </v-card>
  </v-hover>
<!--  <v-card-->
<!--      color="grey"-->
<!--      class="mx-auto"-->
<!--      max-width="400"-->
<!--  >-->
<!--    <v-img-->
<!--        class="white&#45;&#45;text align-end"-->
<!--        height="200px"-->
<!--        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"-->
<!--    >-->
<!--      <v-card-title>{{ product.name }}</v-card-title>-->
<!--    </v-img>-->

<!--    <v-card-subtitle class="pb-0">-->
<!--      Number 10-->
<!--    </v-card-subtitle>-->

<!--    <v-card-text class="text&#45;&#45;primary">-->
<!--      <div>{{ product.status }}</div>-->
<!--      <div>{{ product.info }}</div>-->
<!--    </v-card-text>-->

<!--    <v-card-actions>-->
<!--      <v-btn color="orange" text>-->
<!--        Share-->
<!--      </v-btn>-->

<!--      <v-btn color="orange" text>-->
<!--        Explore-->
<!--      </v-btn>-->
<!--    </v-card-actions>-->
<!--  </v-card>-->
</template>

<script>
export default {
  props: ['product']
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