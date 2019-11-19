import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Vuex from 'vuex'
import axios from 'axios';

import Login from '@/components/Login';
import Home from '@/components/Home';
import Products from '@/components/Products';
import ShoppingBag from '@/components/ShoppingBag';
import storeConfig from './store';

Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(Vuex)
let store = new Vuex.Store(storeConfig)
const routes = [
  { path: '/login', component: Login },
  { path: '/', component: Login },
  { path: '/home', component: Home },
  { path: '/products', component: Products },
  { path: '/shopping-bag', component: ShoppingBag }
]
const router = new VueRouter({
  routes
})

Vue.prototype.$formatPrice = function(price) {
  return price.toFixed(2).replace('.', ',');
};

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
