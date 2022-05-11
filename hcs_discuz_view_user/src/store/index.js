import { createStore } from 'vuex'

export default createStore({
  state: {
    loginUserInfo: {
      userId: 10
    }
  },
  mutations: {
    initLoginUserInfo (state, userInfo){
      state.loginUserInfo = userInfo
    }
  },
  actions: {
  },
  modules: {
  }
})
