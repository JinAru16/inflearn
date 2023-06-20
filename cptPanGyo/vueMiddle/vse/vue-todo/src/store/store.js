import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);
const storage = {
  fetch() {
    const arr = [];
    if (localStorage.length > 0) {
      for (let i = 0; i < localStorage.length; i++) {
        if (localStorage.key(i) !== 'loglevel:webpack-dev-server') {
          arr.push(JSON.parse(localStorage.getItem(localStorage.key(i))));
        }
      }
    }
    return arr;
  },
};

export const store = new Vuex.Store({
  state:{
    headerText: 'Todo it from Store Using Vuex',
    todoItems: storage.fetch()
  },
  getters:{
    storedTodoItems(state){
      return state.todoItems;
    }
  },
  mutations:{
    /*
    state를 mutations를 통해 변경하는 이유
    1. 여러개의 컴포넌트에서 state를 변경하는 경우 어느 컴포넌트에서 해당 state를 변경했는지
    추적하기가 어렵다.
    2. 특정 시점에서 어떤 컴포넌트가 state를 접근하여 변경한건지 확인하기 어렵다
    3. 따라서, 뷰의 반응성을 거스르지 않게 명시적으로 상태변화를 수행, 반응성, 디버깅, 테스팅 혜택
    */
    addOneItem(state, todoItem) {
      const obj = {completed: false, item: todoItem};
      localStorage.setItem(todoItem, JSON.stringify(obj));
      state.todoItems.push(obj); 
    },
    removeOneItem(state, payload){
                            // payload : TodoList.vue에서 (todoitem, index)
       localStorage.removeItem(payload.todoItem.item);
       state.todoItems.splice(payload.index, 1);
    },
    toggleOneItem(state, payload){

      state.todoItems[payload.index].completed = !state.todoItems[payload.index].completed;
      localStorage.removeItem(payload.todoItem.item);
      localStorage.setItem(payload.todoItem.item, JSON.stringify(payload.todoItem));
      
    },
    clearAllItems(state){
      localStorage.clear();
      state.todoItems = []
    },
    sortAllItems() {
      console.log("sort is working");
    }
  }
});
