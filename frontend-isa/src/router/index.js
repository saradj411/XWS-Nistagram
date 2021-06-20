import Vue from 'vue'
import VueRouter from 'vue-router'
import Welcome from '../components/Welcome.vue'
import Login from '../components/Login.vue'
import Registration from '../components/Registration.vue'
import UserProfile from '../components/UserProfile.vue'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'


import moment from 'moment'




Vue.use(VueRouter)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.filter('formatDate', function(value) {
  if (value) {
      return moment(String(value)).format('DD/MM/YYYY')
  }
});
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

const routes = [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome
    },
      
      {
        path: '/login',
        name: 'Login',
        component: Login
      }
      ,
      {
        path: '/registration',
        name: 'Registration',
        component: Registration
      }
      ,
      {
        path: '/profile',
        name: 'UserProfile',
        component: UserProfile
      }



      

      
      


    ]
    
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
    
  })
  
  export default router
