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
```
methods: {
  getProduct(){
    this.$store.dispatch('fetchProductData');
  }
}
```

## 왜 비동기 처리 로직은 actions에 선언해야 할까?
* 1. 언제 어느 컴포넌트에서 해당 state를 호출하고, 변경했는지 확인하기가 어려움
* 2. state값의 변화를 추적하기 어렵기 때문에 mutations 속성에는 동기 처리 로직만 넣어야 한다.


