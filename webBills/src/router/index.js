import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/create_invoice',
    name: 'Create Invoice',

    component: () => import('../views/Create_Invoice.vue')
  },
  {
    path: '/invoice_list',
    name: 'Invoice List',

    component: () => import('../views/Invoice_List.vue')
  },
  {
    path: '/*',
    name: 'NotFound',
    component: Home
  },
]

const router = new VueRouter({
  routes
})

export default router
