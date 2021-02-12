export default {
    state: {
        isAvatarExists:false,
        avatarUrl: "ajax?command=load_profile_image&" + Date.now()
    },
    // getters: {
    //
    // },
    // actions: {
    //
    // },
    mutations: {
        set_isAvatarExists(state, data){
            state.isAvatarExists=data
        },
        change_avatarUrl(state, data){
            state.avatarUrl = data
        }
    }
}