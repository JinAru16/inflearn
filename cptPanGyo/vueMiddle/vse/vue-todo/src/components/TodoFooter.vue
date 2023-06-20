<template>
  <div class="clearAllContainer">
    <span class="clearAllBtn" v-on:click="clearTodo">Clear All</span>
    <span class="sortAllBtn" v-on:click="sortTodo">Sort all</span>
  </div>
</template>

<script>
import { mapMutations } from 'vuex';
export default {
  
  methods: {
    ...mapMutations({
    clearTodo : 'clearAllItems'
  }),
    sortTodo(){
      this.$store.commit('sortAllItems', (this.$store.state.todoItems))
    }
  }
}
</script>

<style scoped>
.clearAllContainer {
  width: 8.5rem;
  height: 50px;
  line-height: 50px;
  background-color: white;
  border-radius: 5px;
  margin: 0 auto;
}
.clearAllBtn, .sortAllBtn {
  color: #e20303;
  /* 추가 */
  display: block;
}
</style>