# 헬퍼함수 사용법

* 헬퍼를 사용하고자 하는 vue 파일에서 아래와 같이 해당 헬퍼를 로딩

* App.vue
```
import { mapState } from 'vuex'
improt { mapGetters } from 'vuex'
import { mapMutations } from 'vuex'
import { mapActions } form 'vuex'

export default{
   computed() { ...mapState(['num'], ...mapGetters(['countedNum']))},
   methods: {...mapMutations(['clickBtn']), ...mapActions(['asyncClickBtn'])}
}
```