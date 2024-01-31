<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <br>
    <router-link v-if="showLink" to="/login" @click="hideLink">click it to login or logout</router-link>
    <br>
    <router-view></router-view>
  </div>
</template>

<!--页面主页加上链接跳转到登录界面-->
<script>
import ai from './components/ai.vue'
export default {
  name: 'App',
components:{
ai
},
  data() {
  return {
    showLink: true
  }
},
methods: {
  hideLink() {
    this.showLink = false;
  }
}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
   background:linear-gradient(#D0c8b6,#cfc4b1)
}
*{
 
}
</style>
