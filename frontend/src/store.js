import Vue from 'vue';
export default {
  state: {
    user: {
      'login': '',
      'id': '',
      'name': ''
    },
    isLogin: false,
    jwtToken: '',
    shoppingBag: []
  },
  getters: {
    bagItems: state => {
      return state.shoppingBag;
    }
  },
  mutations: {
    addToCart(state, item) {
      Vue.set(item, 'quantity', 1);
      state.shoppingBag.push(item);
    }
    // login(state, loginInfo) {
    // state.user = loginInfo.user
    // state.isLogin = loginInfo.isLogin
    // state.jwtToken = localStorage.getItem('jwtToken')
    // }
  },
  actions: {
    addToCart({ commit }, data) {
      commit('addToCart', data);
    }
  }
}
