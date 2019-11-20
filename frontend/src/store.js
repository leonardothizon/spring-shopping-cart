import Vue from 'vue';
export default {
  state: {
    user: {},
    token: '',
    shoppingBag: []
  },
  getters: {
    bagItems: state => {
      return state.shoppingBag;
    },
    user: state => {
      return state.user;
    },
    getToken: state => {
      return state.token;
    }
  },
  mutations: {
    setUser(state, { user, token }) {
      state.user = user;
      state.token = token;
    },
    logout(state) {
      state.user = {};
      state.token = undefined;
    },
    addToCart(state, item) {
      Vue.set(item, 'quantity', 1);
      state.shoppingBag.push(item);
    },
    removeFromCart(state, itemId) {
      let itemIndex = state.shoppingBag.findIndex(i => i.id === itemId);
      state.shoppingBag.splice(itemIndex, 1);
    },
    cleanShoppingBag(state) {
      state.shoppingBag = [];
    }
  },
  actions: {
    setUser({ commit }, { user, token }) {
      commit('setUser', { user, token });
    },
    logout({ commit }) {
      commit('logout');
    },
    addToCart({ commit, state }, item) {
      let exists = state.shoppingBag.findIndex(i => i.id === item.id);
      if (exists > -1) {
        alert('Item já adicionado ao carrinho.');
        return;
      }
      commit('addToCart', item);
    },
    removeFromCart({ commit }, data) {
      commit('removeFromCart', data);
    },
    cleanShoppingBag({ commit }) {
      commit('cleanShoppingBag');
    }
  }
}
