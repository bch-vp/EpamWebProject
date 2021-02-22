<template>
  <v-card
      class="mx-auto"
      color="grey lighten-4"
      max-width="600"
  >
    <v-img
        :aspect-ratio="16/9"
        src="https://cdn.vuetifyjs.com/images/cards/kitchen.png"
    >

      <div style="padding-top: 15px">
      <v-btn @click="showCardInfo"
             absolute color="black" class="light-green--text text--lighten-2"  fab right >
        <v-icon >close</v-icon>
      </v-btn>
      </div>
    </v-img>
    <v-card-text class="pt-6" style="position: relative; background-color: grey">

      <v-btn @click="showEditStatusAndLocation"
             v-if="isEditInfo"
             absolute color="black" class="light-green--text text--lighten-2" small fab right top>
        <v-icon>navigate_next</v-icon>
      </v-btn>

      <v-btn @click="showEditInfo"
             v-if="isEditStatusAndLocation"
             absolute color="black" class="light-green--text text--lighten-2" small fab left top>
        <v-icon>navigate_before</v-icon>
      </v-btn>

      <EditInfo v-if="isEditInfo" :product="product"/>
      <EditStatusAndLocation v-if="isEditStatusAndLocation" :showCardInfo="showCardInfo" :product="product"/>


    </v-card-text>
  </v-card>
</template>

<script>
import EditInfo from 'vuejs/admin/component/home/component/component/component/editInfo.vue'
import EditStatusAndLocation from 'vuejs/admin/component/home/component/component/component/editStatusAndLocation.vue'

export default {
  props: ['showCardInfo', 'product'],
  components: {
    EditInfo,
    EditStatusAndLocation
  },
  data() {
    return {
      valid: false,
      text_page: text_page,

      isEditInfo: true,
      isEditStatusAndLocation: false,

    }
  },
  methods: {
    clearAllComponents() {
      this.isEditStatusAndLocation = false
      this.isEditInfo = false
    },
    showEditInfo() {
      this.clearAllComponents()
      this.isEditInfo = true
    },
    showEditStatusAndLocation() {
      this.clearAllComponents()
      this.isEditStatusAndLocation = true
    }
  }
}
</script>

<style scoped>

</style>