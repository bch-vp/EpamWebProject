import Vue from 'vue'
import App from './App.vue'
import vuetify from '../../vuejs/plugin/vuetify' // path to vuetify export

new Vue({
    el: '#app',
    render: h => h(App),
    vuetify
})