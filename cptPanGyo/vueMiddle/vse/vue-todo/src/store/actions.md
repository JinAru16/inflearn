## actions?
비동기 async await 같은거임.

## actions 예제코드1

* store.js
```
mutations: {
  addCounter(state) {
    sate.counter++
  },
},
actions: {
  delayedAddCounter(context) {
    setTimeout(() => context.commit('addCounter), 2000);
  }
}
```

* App.vue
```
methods: {
  incrementCounter() {
    this.$store.dispatch('deylayedAddCounter');
  }
}
```
* 순서 : App.vue의 incrementCounter() -> store.js의  delayedAddCounter(context) -> addCounter(state). 

## actions 예제코드 2

* store.js
```
mutations:{
  setData(state, fetchData) {
    state.product = fetchData;
  }
},
actions: {
  fetchPrdoductData(conetxt){
    return axios.get('https://test/products/1')
                .then(response => context.commit('setData', response));
  }
}
```

* App.vue
methods: {
  getProduct(){
    this.$store.dispatch('fetchProductData');
  }
}

