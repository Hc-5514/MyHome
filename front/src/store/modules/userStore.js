const userStore = {
    namespaced: true,
    state: {
        is_login: false,
        user_id: "",
    },
    getters: {
        get_is_login: (state) => {
            return state.is_login
        },
        get_user_id: (state) => {
            return state.user_id
        }
    },
    mutations: {
        set_is_login(state, is_login) {
            state.is_login = is_login
        },
        set_user_id(state, id) {
            state.user_id = id
        }
    },
    actions: {
        login(context, id) {
            context.commit('set_is_login', true)
            context.commit('set_user_id', id)
        },
        logout(context) {
            context.commit('set_is_login', false)
        }
    },
};

export default userStore;