import Vue from 'vue'
import Home from 'vuejs/guest/guest.vue'
import vuetify from 'vuejs/plugin/vuetify' // path to vuetify export

new Vue({
    el: '#app',
    render: h => h(Home),
    vuetify
})