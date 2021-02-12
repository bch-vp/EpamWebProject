import Vue from 'vue'
import Vuex from 'vuex'
import Profile from 'vuejs/store/module/profile.js'

Vue.use(Vuex)

export const store = new Vuex.Store({
    modules: {
        Profile
    }
})