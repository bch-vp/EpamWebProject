export default {
    state: {
        isHome:false,
        isShoppingCart:false,
        isProfile:false,

        categories: [],
        products: [],
        shoppingCart:[]

    },
    getters: {
        products: state => {
            return state.products
        }
    },
    mutations: {
        // set_isProfile(state, data){
        //     state.isAvatarExists=data
        // },
        // set_isHome(state, data){
        //     state.isAvatarExists=data
        // },

        show_home(state) {
            this.commit('off_allComponents')
            state.isHome = true
        },
        show_shoppingCart(state) {
            this.commit('off_allComponents')
            state.isShoppingCart = true
        },
        show_profile(state) {
            this.commit('off_allComponents')
            state.isProfile = true
        },

        off_allComponents(state) {
            state.isHome = false
            state.isShoppingCart = false
            state.isProfile = false
        },

        set_categories(state, data){
            state.categories=data
        },

        set_products(state, data){
            state.products=data
        },
        add_productToProducts(state, data){
            state.products.push(data)
        },
        remove_productToProducts(state, data){
            state.products.splice(state.products.indexOf(data), 1)
        },

        set_shoppingCart(state, data){
            state.shoppingCart=data
        },
        add_productToShoppingCart(state, data){
            state.shoppingCart.push(data)
        },
        remove_productToShoppingCart(state, data){
            state.shoppingCart.splice(state.shoppingCart.indexOf(data), 1)
        },
    }
}