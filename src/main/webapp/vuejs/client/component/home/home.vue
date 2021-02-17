<!---->

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
                      <div class="col-md-4" v-for="product in  displayedPosts">
                        <ProductCard :product="product"/>
                      </div>
                    </v-row>
                  </v-container>
                </div>
              </div>
            </div>
          </v-card>
          <v-row style="padding-top: 2em;">
            <v-col>
              <v-select
                  style="max-width: 320px"
                  dark
                  v-model="filtersValue"
                  :items="filtersItems"
                  chips
                  label="Choose to show only..."
                  multiple
                  outlined
              ></v-select>
            </v-col>
            <v-col>
              <div align="center">
                <v-btn dark style="color: white;" :disabled="page === 1" @click="page--">
                  <v-icon>navigate_before</v-icon>
                </v-btn>
                <v-btn dark style="color: white">
            <span style="color: orange;">
                   {{ page }}
                 </span>
                </v-btn>
                <v-btn dark style="color: white" @click="page++" :disabled="page >= pages.length">
                  <v-icon>navigate_next</v-icon>
                </v-btn>
              </div>
            </v-col>
            <v-col>
              <div align="right">
              <span style="color: white;" class="text-h5">
                   Pages: {{ pages.length }}
                 </span>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <v-row>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ProductCard from "vuejs/client/component/home/component/ProductCard.vue";

export default {
  components: {
    ProductCard
  },
  data() {
    return {
      filtersItems: ['ACTIVE', 'INACTIVE', 'BLOCKED'],
      filtersValue: ['ACTIVE', 'INACTIVE', 'BLOCKED'],
      selectedMethod: undefined,

      page: 1,
      perPage: 1,
      pages: [],
    }
  },
  methods: {

    // filterByStatus(status) {
    //   return this.products.filter(function (product) {
    //     return product.status === status;
    //
    //   })
    // },
    setPages() {
      let numberOfPages = Math.ceil(this.products.length / this.perPage);
      this.pages = []
      for (let index = 1; index <= numberOfPages; index++) {
        this.pages.push(index);
      }
      this.page = 1
    },
    paginate(posts) {
      let page = this.page;
      let perPage = this.perPage;
      let from = (page * perPage) - perPage;
      let to = (page * perPage);
      return posts.slice(from, to);
    }
  },
  computed: {
    filters(){
      let array = [];
      this.filtersValue.forEach(function(filterValue) {
        if(filterValue === 'ACTIVE') {
          array += this.filterActive()
        } else if (filterValue === 'INACTIVE'){
          array += this.filterInactive()
        } else  if (filterValue === 'BLOCKED'){
          array += this.filterBlocked()
        }
        return array
      })
    },
    filterActive(){
      return this.products.filter(function (product) {
        return product.status === 'ACTIVE';
      })
    },
    filterInactive(){
      return this.products.filter(function (product) {
        return product.status === 'INACTIVE';
      })
    },
    filterBlocked(){
      return this.products.filter(function (product) {
        return product.status === 'BLOCKED';
      })
    },
    products() {
      return this.$store.getters.products
    },
    displayedPosts() {
      return this.paginate(this.$store.getters.products);
    }
  },
  created() {
    // this.posts = this.products
    this.setPages();
  },
  watch: {
    products() {
      this.$store.state.App.categories
      this.setPages();
    }
  },
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