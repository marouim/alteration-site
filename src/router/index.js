import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import QuiSommesNous from '../views/QuiSommesNous.vue'
import NosBieres from '../views/NosBieres.vue'
import Sinscrire from '../views/Sinscrire.vue'

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/about', name: 'About', component: About },
  { path: '/qui-sommes-nous', name: 'QuiSommesNous', component: QuiSommesNous },
  { path: '/nos-bieres', name: 'NosBieres', component: NosBieres },
  { path: '/sinscrire', name: 'Sinscrire', component: Sinscrire }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
