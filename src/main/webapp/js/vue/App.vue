<template>
  <div>
    <v-card
        color="grey lighten-4"
        flat
        height="200px"
        tile
    >
      <v-toolbar dense>
        <v-app-bar-nav-icon></v-app-bar-nav-icon>

        <v-toolbar-title>Title</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-heart</v-icon>
        </v-btn>

        <v-btn icon>
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
      </v-toolbar>
    </v-card>
    <v-main>
    <div class="div_style">
fafafa fawfawfawffafafafawfaw fawffafafafawfawfawffafafaf awfawfawffafafafawfawfawf
    </div>
    </v-main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: 'Welcome to Vue.jssssss'
    }
  }
}
</script>

<style>
.div_style{
  width: 40%; /* Ширина элемента в пикселах */
  padding: 10%; /* Поля вокруг текста */
  margin: auto; /* Выравниваем по центру */
  background: #fc0; /* Цвет фона */
}
</style>