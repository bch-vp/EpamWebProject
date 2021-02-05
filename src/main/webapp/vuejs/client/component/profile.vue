<template>
  <div class="sign-up-wrapper">
    <v-container>
      <v-row justify="center" row>
        <v-col sm="9" md="8" lg="7" xl="6">
          <div class="sign-up-background">
            <div class="sign-up">
              <v-container>
                <v-row justify="center" style="margin-top: 1em; margin-bottom: 1em">
                  <span class="text-h4 center" style="text-align: center; font-family: 'Monoton', cursive;">
                    Client
                  </span>
                </v-row>
                <br>
                <v-row justify="">
                  <span class="text-h6 font-weight-regular center" style="text-align: center; padding-bottom: 1em;">
                    Text
                  </span>
                </v-row>
                <v-row justify="center">
<!--                  af-->
                </v-row>
              </v-container>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>

export default {
  data() {
    return {
      text_page: text_page
    }
  }
}
</script>

<style scoped>
@media screen and (min-width: 400px) {
  .sign-up-wrapper {
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
  .sign-up-wrapper {
    width: 100%;
    position: relative;
    margin-right: -50%;
  }
}

.sign-up-background {
  box-shadow: 0 0 25px;
  background: rgba(0, 0, 0, 0.93);
  border-radius: 20px;
}


.sign-up {
  color: white;
  margin-top: 5em;
  padding: 2em;
}

</style>