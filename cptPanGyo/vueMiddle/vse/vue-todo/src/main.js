import Vue from 'vue'
import App from './App.vue'
import { store } from './store/store.js';

new Vue({
  el: '#app',
  store, //'store' : store 이렇게 써야하는걸 축약한거임.
  render: h => h(App)
  
})
