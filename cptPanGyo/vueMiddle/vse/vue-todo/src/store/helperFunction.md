# 헬퍼함수 사용법

* 헬퍼를 사용하고자 하는 vue 파일에서 아래와 같이 해당 헬퍼를 로딩

* App.vue
```
import { mapState } from 'vuex'
import { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
import { mapActions } from 'vuex'

export default{
   computed() { ...mapState(['num'], ...mapGetters(['countedNum']))},
   methods: {...mapMutations(['clickBtn']), ...mapActions(['asyncClickBtn'])}
}
```

## mapState
* Vuex에 선언한 state속성을 뷰 컴포넌트에 더 쉽게 연결해주는 헬퍼

```
//App.vue
import { mapState } from 'vuex'
computed(){
  ...mapState(['num'])
}

//store.js
state: {
  num: 10
}

<!-- <p>{{ this.$store.state.num }} </p>-->
    <p>{{ this.num }}</p>
```

## mapGetters
* Vuex에 선언한 getters 속성을 뷰 컴포넌트에 더 쉽게 연결해주는 헬퍼

```
//App.vue
import { mapGetters } from 'vuex'
computed() {...mapGetters(['reverseMessage']) }

//store.js
getters: {
  reverseMessage(state){
    return state.msg.split('').reverse().join('');
  }
}

<!-- <p>{{ this.$store.getters.reverseMessage }} </p>-->
    <p>{{ this.reverseMessage }}</p>
```
