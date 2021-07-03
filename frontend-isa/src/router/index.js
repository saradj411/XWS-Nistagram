import Vue from 'vue'
import VueRouter from 'vue-router'
import Welcome from '../components/Welcome.vue'
import Login from '../components/Login.vue'
import Registration from '../components/Registration.vue'
import UpdateAccount from '../components/UpdateAccount.vue'
import UserProfile from '../components/UserProfile.vue'
import AddPost from '../components/AddPost.vue'

import Proba from '../components/Proba.vue'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VerificationRequest from '../components/VerificationRequest.vue'
import AdminProfile from '../components/AdminProfile.vue'
import AllRequest from '../components/AllRequest.vue'
import AnotherUserProfile from '../components/AnotherUserProfile'
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
        path: '/UpdateAccount',
        name: 'UpdateAccount',
        component: UpdateAccount
      },
      {
          path: '/profile',
        name: 'UserProfile',
        component: UserProfile
      },
      {
          path: '/AddPost',
        name: 'AddPost',
        component: AddPost
      },
      {
          path: '/Proba',
        name: 'Proba',
        component: Proba
      }
      ,
      {
        path: '/VerificationRequest',
        name: 'VerificationRequest',
        component: VerificationRequest
      },
      {
        path: '/AdminProfile',
        name: 'AdminProfile',
        component: AdminProfile
      },
      {
        path: '/AllRequest',
        name: 'AllRequest',
        component: AllRequest
      }
      ,
      {
        path: '/AnotherUserProfile/:username',
        name: 'AnotherUserProfile',
        component: AnotherUserProfile
      }


      
      

      
      


    ]
    
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
    
  })
  
  export default router
