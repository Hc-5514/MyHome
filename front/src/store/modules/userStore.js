const userStore = {
    namespaced: true,
    state: {
        is_login: false,
    },
    getters: {
        get_is_login: (state) => {
            return state.is_login
        },
    },
    mutations: {
        set_is_login(state, is_login) {
            state.is_login = is_login
        },
    },
    actions: {
        login(context) {
            context.commit('set_is_login', true)
        },
        logout(context) {
            context.commit('set_is_login', false)
        }
    },
};

export default userStore;