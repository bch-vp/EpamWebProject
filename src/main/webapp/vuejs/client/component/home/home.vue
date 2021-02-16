<template>
  <div class="container">
    <div class="row">

    </div>
    <!--     Перебираем через цикл массив pages, чтобы вывести каждый номер страницы -->

  </div>
</template>


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
                      <div class="col-md-4" v-for="post in displayedPosts">
                        <v-card
                            color="grey"
                            class="mx-auto"
                            max-width="400"
                        >
                          <v-img
                              class="white--text align-end"
                              height="200px"
                              src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                          >
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
                            <v-btn
                                color="orange"
                                text
                            >
                              Share
                            </v-btn>

                            <v-btn
                                color="orange"
                                text
                            >
                              Explore
                            </v-btn>
                          </v-card-actions>
                        </v-card>
<!--                        <div class="card mb-4 box-shadow post-cards">-->
<!--                          <div class="card-body">-->
<!--                            <h5 style="color: white" class="capitalize">{{ post.title }}</h5>-->
<!--                            <p style="color: white" class="card-text">{{ post.body.slice(0, 120) }}...</p>-->
<!--                          </div>-->
<!--                        </div>-->
                      </div>
                    </v-row>
                  </v-container>
                </div>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
      <v-row>


        <div class="mx-auto" style="padding-top: 1em">
          <v-pagination
              dark
              v-model="page"
              :length="pages.length"
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
      posts: [],
      baseUrl: '//jsonplaceholder.typicode.com/',
      page: 1,
      perPage: 20,
      pages: [],

    }
  },
  methods: {
    getPosts() {
      this.axios.get(this.baseUrl + 'posts')
          .then(response => {
            this.posts = response.data;
          })
          .catch(response => {
            console.log(response);
          });
    },
    setPages() {
      let numberOfPages = Math.ceil(this.posts.length / this.perPage);
      for (let index = 1; index <= numberOfPages; index++) {
        this.pages.push(index);
      }
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
    items() {
      return Array.from({length: this.length}, (k, v) => v + 1)
    },
    length() {
      return 9
    },
    displayedPosts() {
      return this.paginate(this.posts);
    }
  },
  created() {
    this.getPosts();
  },
  watch: {
    posts() {
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