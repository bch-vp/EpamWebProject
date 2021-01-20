import Vue from 'vue'
import Axios from 'axios'
import VueAxios from 'vue-axios'
import Home from 'vuejs/guest/guest.vue'
import vuetify from 'vuejs/plugin/vuetify' // path to vuetify export

Vue.use(VueAxios, Axios)

new Vue({
    el: '#app',
    render: h => h(Home),
    vuetify
})