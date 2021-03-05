export default {
    state: {
        isAvatarExists: false,
        avatarUrl: ''
    },
    mutations: {
        set_isAvatarExists(state, data) {
            state.isAvatarExists = data
        },
        change_avatarUrl(state, data) {
            state.avatarUrl = data
        }
    }
}