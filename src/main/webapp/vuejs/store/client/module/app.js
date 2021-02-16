export default {
    state: {
        isHome:false,
        isShoppingBasket:false,
        isProfile:false,

        categories: undefined,

    },
    mutations: {
        set_isProfile(state, data){
            state.isAvatarExists=data
        },
        set_isHome(state, data){
            state.isAvatarExists=data
        },

        show_home(state) {
            this.commit('off_allComponents')
            state.isHome = true
        },
        show_shoppingBasket(state) {
            this.commit('off_allComponents')
            state.isShoppingBasket = true
        },
        show_profile(state) {
            this.commit('off_allComponents')
            state.isProfile = true
        },

        off_allComponents(state) {
            state.isHome = false
            state.isShoppingBasket = false
            state.isProfile = false

        },

        set_categories(state, data){
            state.categories=data
        },
    }
}