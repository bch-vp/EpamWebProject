import Vue from 'vue'
import Notification from 'vuejs/notification/notification.vue'
import vuetify from 'vuejs/plugin/vuetify' // path to vuetify export

new Vue({
    el: '#app',
    render: h => h(Notification),
    vuetify
})