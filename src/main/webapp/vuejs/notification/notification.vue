<template>
  <v-app>
    <div class="background-image">
      <div class="home-wrapper">
        <v-container>
          <v-row justify="center" row>
            <v-col sm="12" md="12" lg="12" xl="11">
              <div class="home-background">
                <div class="home">
                  <v-container>
                    <v-row justify="center" style="margin-top: 3em; margin-bottom: 3em">
                  <span class="text-h2 center" style="text-align: center; font-family: 'Monoton', cursive;">
                    {{text_page.notification_component.title}}
                  </span>
                    </v-row>
                    <br>
                    <v-row justify="center">
                  <span class="text-h6 font-weight-regular center" style="text-align: center; padding-bottom: 3em;">
                    {{text_page.notification_component.text}}
                  </span>
                    </v-row>
                    <v-row justify="center">
                      <v-btn href="HotelWeb?command=passing_by_guest" color="green" style="margin-bottom: 3em" text outlined rounded>
                    <span class="text-h6 center">
                      {{text_page.notification_component.button}}
                    </span>
                      </v-btn>
                    </v-row>
                  </v-container>
                </div>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      text_page: {
        notification_component: text_page.notification_component
      }
    }
  }
}
</script>

<style scoped>
.background-image {
  background-image: url("/img/background.jpg");
  background-color: black;
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}


@media screen and (min-width: 400px) {
  .home-wrapper {
    width: 100%;
    margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);
  }
}

@media screen and (max-width: 400px) {
  .home-wrapper {
    width: 100%;
    position: relative;
    margin-right: -50%;
  }
}

.home-background {
  box-shadow: 0 0 25px;
  background: rgba(0, 0, 0, 0.93);
  border-radius: 20px;
}


.home {
  color: white;
  margin-top: 5em;
  padding: 2em;
}

</style>