<template>
  <div id="app">
    <TodoHeader></TodoHeader>
    <!-- <TodoInput v-on:하위컴포넌트에서 발생시킨 이벤트 이름="현재 컴포넌트의 메소드 이름"></TodoInput> -->
    <TodoInput></TodoInput>
    <!--<TodoList v-bind:내려보낼 프롭스 속성 이름 = "현재 위치의 컴포넌트 데이터 속성"></TodoList> -->
    <TodoList></TodoList>
    <TodoFooter ></TodoFooter>
  </div>
</template>

<script>
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'

export default {
  data(){
    return{
      
    }
  },
  methods:{
  
  },
 
  components: {
    // 컴포넌트 태그명 : 컴포넌트 내용  현재 둘다 이름이 같은 상태
    // 'TodoHeader': TodoHeader, => TodoHeader 하나로 축약가능
    TodoHeader,
    TodoInput,
    TodoList,
    TodoFooter
  }  
}
</script>

<style>
body {
  text-align: center;
  background-color: #F6F6F8;
}
input {
  border-style: groove;
  width: 200px;
}
button {
  border-style: groove;
}
.shadow {
  box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03)
}
</style>