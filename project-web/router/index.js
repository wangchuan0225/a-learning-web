import Vue from "vue";
import VueRouter from "vue-router";
import home from '../components/home.vue'
import login from '../components/login.vue'
import python from '../components/python.vue'
import ai from '../components/ai'
import java from '../components/java'
import google from '../components/google'
Vue.use(VueRouter)
const router=new VueRouter({
    routes:[
        {path: '/home',component: home,},
        {path: '/login',component: login},
        {path: '/python',component: python},
        {path: '/ai', component: ai},
        {path: '/java', component: java},
        {path: '/google', component: google}
    ]
})
export default router



