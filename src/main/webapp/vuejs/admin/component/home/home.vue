<!---->

<template>
  <div style="width: 100%; margin-top: 80px;">
    <v-container>
      <v-row justify="center" row>
        <v-col ms="12" md="12" lg="11" xl="8">
          <v-btn @click="show_isAddProduct" :disabled="isAddProduct" color="black"  style="margin-left: 3em" class="light-green--text text--lighten-2">
            + {{text_page.form_component.button.add}}
          </v-btn>
          <v-btn @click="show_isProducts" :disabled="isProducts" color="black"  class="light-green--text text--lighten-2">
            {{text_page.form_component.button.products}}
          </v-btn>
          <v-btn @click="show_isOrders" :disabled="isOrders" style="margin-left: 3em"  color="black" class="light-green--text text--lighten-2">
            {{text_page.form_component.button.orders}}
          </v-btn>
          <v-btn @click="show_isUsers" :disabled="isUsers"  color="black" class="light-green--text text--lighten-2">
            {{text_page.form_component.button.users}}
          </v-btn>
          <v-btn @click="show_isCategories" :disabled="isCategories"  color="black" class="light-green--text text--lighten-2">
            {{text_page.form_component.button.categories}}
          </v-btn>
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

                      <div v-if="isProducts" class="col-md-4" v-for="product in  productsWithFiltersAndPagination"
                           v-bind:key="product.id">
                        <ProductCard :product="product"/>
                      </div>

                      <div v-if="isAddProduct">
                        <AddProduct />
                      </div>

                      <v-container v-if="isUsers">
                        <v-row justify="center" row>
                          <v-col style="width: 70%">
                            <v-list>
                              <div v-for="user in $store.state.App.users" :key="user.id">
                                <User :user="user"/>
                              </div>
                            </v-list>
                          </v-col>
                        </v-row>
                      </v-container>

                    </v-row>
                  </v-container>
                </div>
              </div>
            </div>
          </v-card>
          <v-row v-if="isProducts" style="padding-top: 2em;">
            <v-col>
              <v-select
                  style="max-width: 330px"
                  dark
                  v-model="filtersValue"
                  :items="filtersItems"
                  chips
                  v-bind:label=text_page.form_component.button.choose_status_for_ordering
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
            <span class="light-green--text text--lighten-1">
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
              <span style="color: white; padding-right: 5px" class="text-h5">
                {{ text_page.page_info.pages }}:&nbsp {{ pages.length }}
              </span>
                <span style="color: white;" class="text-h5">
                |
              </span>
                <span style="color: white; padding-left: 5px" class="text-h5">
                {{ text_page.page_info.products }}:&nbsp {{ productsWithFilters.length }}
              </span>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ProductCard from "vuejs/admin/component/home/component/product/ProductCard.vue";
import AddProduct from "vuejs/admin/component/home/component/addproduct/addProduct.vue";
import User from "vuejs/admin/component/home/component/user/user.vue";

export default {
  components: {
    ProductCard,
    AddProduct,
    User
  },
  data() {
    return {
      filtersItems: ['ACTIVE', 'INACTIVE', 'BLOCKED'],
      filtersValue: ['ACTIVE', 'INACTIVE', 'BLOCKED'],

      text_page:text_page,

      isProducts: true,
      isAddProduct:false,
      isOrders: false,
      isUsers: false,
      isCategories:false,

      oldPage: 1,
      page: 1,
      perPage: 12,
      pages: [],
    }
  },
  methods: {
    clear_allComponents() {
      this.isProducts = false
      this.isAddProduct = false
      this.isOrders = false
      this.isUsers = false
    },
    show_isProducts() {
      this.clear_allComponents()
      this.isProducts = true
    },
    show_isCategories(){
      this.clear_allComponents()
      this.isCategories = true
    },
    show_isAddProduct(){
      this.clear_allComponents()
      this.isAddProduct = true
    },
    show_isOrders() {
      this.clear_allComponents()
      this.isOrders = true
    },
    show_isUsers() {
      this.clear_allComponents()
      this.isUsers = true
    }
  },
  computed: {
    productsWithFilters() {
      var array = [];

      for (var i = 0; i < this.filtersValue.length; i++) {
        if (this.filtersValue[i] === 'ACTIVE') {
          var arrayConcat = this.$store.state.App.products.filter(function (product) {
            return product.status === 'ACTIVE';
          })
          array = array.concat(arrayConcat)

        } else if (this.filtersValue[i] === 'INACTIVE') {
          var arrayConcat = this.$store.state.App.products.filter(function (product) {
            return product.status === 'INACTIVE';
          })
          array = array.concat(arrayConcat)
        } else if (this.filtersValue[i] === 'BLOCKED') {
          var arrayConcat = this.$store.state.App.products.filter(function (product) {
            return product.status === 'BLOCKED';
          })
          array = array.concat(arrayConcat)
        }
      }
      return array
    },
    productsWithFiltersAndPagination() {
      var array = this.productsWithFilters;

      let numberOfPages = Math.ceil(array.length / this.perPage);
      this.pages = []
      for (let index = 1; index <= numberOfPages; index++) {
        this.pages.push(index);
      }

      if (this.page === this.oldPage + 1 || this.page === this.oldPage - 1) {
        this.oldPage = this.page
      } else {
        this.page = 1
        this.oldPage = 1
      }

      let page = this.page;
      let perPage = this.perPage;
      let from = (page * perPage) - perPage;
      let to = (page * perPage);

      return array.slice(from, to);
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
    height: 68vh;
    overflow-x: hidden;
    overflow-y: auto;
  }
}

@media screen and (max-height: 1100px) {
  .scroll {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 60vh;
    overflow-x: hidden;
    overflow-y: auto;
  }
}
</style>