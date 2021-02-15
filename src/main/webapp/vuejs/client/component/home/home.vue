<!--<template>-->
<!--  <div class="container">-->
<!--    <div class="row">-->
<!--      <div class="col-md-4" v-for="post in displayedPosts">-->
<!--        <div class="card mb-4 box-shadow post-cards">-->
<!--          <div class="card-body">-->
<!--            <h5 style="color: white" class="capitalize">{{ post.title }}</h5>-->
<!--            <p style="color: white" class="card-text">{{ post.body.slice(0, 120) }}...</p>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--    &lt;!&ndash;     Перебираем через цикл массив pages, чтобы вывести каждый номер страницы &ndash;&gt;-->
<!--    <div class="clearfix btn-group col-md-2 offset-md-5">-->
<!--      <button style="color: white" type="button" class="btn btn-sm btn-outline-secondary" v-if="page != 1" @click="page&#45;&#45;"> <<</button>-->
<!--      <button style="color: white" type="button" class="btn btn-sm btn-outline-secondary" v-for="pageNumber in pages.slice(page-1, page+3)"-->
<!--              @click="page = pageNumber"> {{ pageNumber }}-->
<!--      </button>-->
<!--      <button style="color: white" type="button" @click="page++" v-if="page < pages.length" class="btn btn-sm btn-outline-secondary"> >>-->
<!--      </button>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      posts: [],-->
<!--      baseUrl: '//jsonplaceholder.typicode.com/',-->
<!--      page: 1,-->
<!--      perPage: 9,-->
<!--      pages: [],-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    getPosts() {-->
<!--      this.axios.get(this.baseUrl + 'posts')-->
<!--          .then(response => {-->
<!--            this.posts = response.data;-->
<!--          })-->
<!--          .catch(response => {-->
<!--            console.log(response);-->
<!--          });-->
<!--    },-->
<!--    setPages() {-->
<!--      let numberOfPages = Math.ceil(this.posts.length / this.perPage);-->
<!--      for (let index = 1; index <= numberOfPages; index++) {-->
<!--        this.pages.push(index);-->
<!--      }-->
<!--    },-->
<!--    paginate(posts) {-->
<!--      let page = this.page;-->
<!--      let perPage = this.perPage;-->
<!--      let from = (page * perPage) - perPage;-->
<!--      let to = (page * perPage);-->
<!--      return posts.slice(from, to);-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.getPosts();-->
<!--  },-->
<!--  watch: {-->
<!--    posts() {-->
<!--      this.setPages();-->
<!--    }-->
<!--  },-->
<!--  computed: {-->
<!--    displayedPosts() {-->
<!--      return this.paginate(this.posts);-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->


<template>
  <div style="width: 100%; height: 100%; padding-top: 100px">
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
              <v-col>
                <v-card class="mx-auto" style="margin-left: 2em;margin-right: 2em; margin-bottom: 1em" color="grey"
                        width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 2em;margin-right: 2em;margin-bottom: 1em" color="grey"
                        width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 2em;margin-right: 2em; margin-bottom: 1em" color="grey"
                        width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
                      Share
                    </v-btn>

                    <v-btn color="orange" text>
                      Explore
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-col>
              <v-col>
                <v-card class="mx-auto" style="margin-left: 3em;margin-right: 3em; margin-top: 1em;margin-bottom: 1em"
                        color="grey" width="300">
                  <v-img class="white--text align-end"
                         height="200px"
                         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text style="height: 50px" class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>

                  <v-card-actions>
                    <v-btn color="red" text>
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
        </div>
      </div>
</div>
    </v-card>
            </v-col>
          </v-row>
    <v-row>
      <div class="mx-auto" style="margin-top: 4em">
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
.scroll {
  padding-top: 4em;
  padding-bottom: 4em;
}

.scroll {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 900px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>