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
    getTodoItems(state){
      return state.todoItems;
    }
  },
  mutations:{
    addOneItem(state, todoItem) {
      const obj = {completed: false, item: todoItem};
      localStorage.setItem(todoItem, JSON.stringify(obj));
      state.todoItems.push(obj); 
    }
  }
});
