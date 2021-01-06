import Vue from 'vue'
import Home from 'vuejs/home/home.vue'
import vuetify from 'vuejs/plugin/vuetify' // path to vuetify export

new Vue({
    el: '#app',
    render: h => h(Home),
    vuetify
})