import Vue from 'vue'
import Axios from 'axios'
import VueAxios from 'vue-axios'
import Client from 'vuejs/client/client.vue'
import vuetify from 'vuejs/plugin/vuetify' // path to vuetify export
import {store} from 'vuejs/store/client/store.js'

Vue.use(VueAxios, Axios)

Vue.directive('scroll', {
    inserted: function (el, binding) {
        let f = function (evt) {
            if (binding.value(evt, el)) {
                window.removeEventListener('scroll', f)
            }
        }
        window.addEventListener('scroll', f)
    }
})

new Vue({
    el: '#app',
    render: h => h(Client),
    vuetify,
    store
})