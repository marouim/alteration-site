import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import QuiSommesNous from '../views/QuiSommesNous.vue'
import NosBieres from '../views/NosBieres.vue'
import Sinscrire from '../views/Sinscrire.vue'
import BeerDetail from '../views/BeerDetail.vue'
import Retailers from '../views/Retailers.vue'
import RetailerDetail from '../views/RetailerDetail.vue'
import MesDegustations from '../views/MesDegustations.vue'
import Profile from '../views/Profile.vue'

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/about', name: 'About', component: About },
  { path: '/qui-sommes-nous', name: 'QuiSommesNous', component: QuiSommesNous },
  { path: '/nos-bieres', name: 'NosBieres', component: NosBieres },
  { path: '/nos-bieres/:slug', name: 'BeerDetail', component: BeerDetail },
  { path: '/mes-degustations', name: 'MesDegustations', component: MesDegustations },
  { path: '/profile', name: 'Profile', component: Profile },
  { path: '/points-de-vente', name: 'PointsDeVente', component: Retailers },
  { path: '/points-de-vente/:id', name: 'RetailerDetail', component: RetailerDetail },
  { path: '/sinscrire', name: 'Sinscrire', component: Sinscrire }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
