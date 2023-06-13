
import { createStore } from 'vuex';

const store = createStore({
    state () {
        return {
            user: {
                id: '',
                username: '',
                password: '',
                email: '',
                type: '',
            }
        }
    },
    mutations: {
        changeUser (user, update) {
            user = update
        }
    }
})

export default store