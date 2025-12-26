<template>
<v-app class="app-shell" :style="appBackgroundStyle">
    <v-app-bar app flat color="primary" dark>
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <v-toolbar-title class="font-weight-bold text-uppercase tracking d-flex align-center">
        <router-link to="/" class="toolbar-title-link d-flex align-center">
          <img src="/src/assets/Logo_Alteration_Wth.png" alt="Altération" class="toolbar-logo" />
          <span class="ml-2 d-none d-md-inline">Altération</span>
        </router-link>
      </v-toolbar-title>
      <v-spacer />
      <v-btn class="d-none d-md-inline-flex" variant="text" to="/qui-sommes-nous">Qui sommes nous</v-btn>
      <v-btn class="d-none d-md-inline-flex" variant="text" to="/points-de-vente">Points de vente</v-btn>
      <v-btn class="d-none d-md-inline-flex" variant="text" to="/nos-bieres">Nos bières</v-btn>
      <v-btn v-if="isAuthenticated" class="d-none d-md-inline-flex" variant="text" to="/mes-degustations">Mes dégustations</v-btn>
      <v-btn class="d-none d-md-inline-flex ml-3" variant="tonal" color="amber-accent" rounded="xl" to="/sinscrire">Rejoindre</v-btn>
      <router-link v-if="isAuthenticated" to="/profile" class="avatar-link d-none d-md-inline-flex">
        <v-avatar color="secondary" class="ml-4 avatar-chip d-flex align-center justify-center" size="40">
          <div class="d-flex flex-column align-center">
            <span class="font-weight-bold">{{ userInitial }}</span>
            <small class="avatar-level text-caption">{{ levelShort }}</small>
          </div>
        </v-avatar>
      </router-link>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" app temporary>
      <v-list nav>
        <v-list-item>
          <v-list-item-title class="text-h6">Menu</v-list-item-title>
        </v-list-item>
        <v-divider />
        <v-list-item link :to="{ name: 'Home' }">Accueil</v-list-item>
        <v-list-item link :to="{ name: 'PointsDeVente' }">Points de vente</v-list-item>
        <v-list-item link :to="{ name: 'QuiSommesNous' }">Qui sommes nous</v-list-item>
        <v-list-item link :to="{ name: 'NosBieres' }">Nos bières</v-list-item>
        <v-list-item v-if="isAuthenticated" link :to="{ name: 'MesDegustations' }">Mes dégustations</v-list-item>
        <v-list-item v-if="isAuthenticated" link :to="{ name: 'Profile' }">Mon profil</v-list-item>
        <v-list-item link :to="{ name: 'Sinscrire' }">S'inscrire</v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main class="app-main">
      <router-view />
    </v-main>

    <v-footer padless>
      <v-sheet class="pa-6 footer" width="100%" color="transparent" elevation="0">
        <div class="text-center text-body-2">
          © 2025 Altération — microbrasserie indépendante à fermentation créative.
        </div>
      </v-sheet>
    </v-footer>
  </v-app>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useUserStore } from './stores/userStore'
import heroBackground from './assets/Alteration_WebBanner_DarkBG_Left_1900x600.jpg'
const drawer = ref(false)
const { isAuthenticated, username, level } = useUserStore()
const userInitial = computed(() => (username.value ? username.value[0]?.toUpperCase() : ''))
const levelShort = computed(() => {
  if (!level.value) return ''
  const parts = level.value.split(' ')
  return parts.length > 1 ? parts[1] : level.value
})

const appBackgroundStyle = computed(() => ({
  background: `linear-gradient(180deg, rgba(11, 8, 16, 0.82) 0%, rgba(11, 8, 16, 0.86) 40%, rgba(11, 8, 16, 0.9) 100%), url(${heroBackground})`,
  backgroundSize: 'cover',
  backgroundPosition: 'center top',
  backgroundRepeat: 'no-repeat',
  backgroundAttachment: 'fixed'
}))
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Manrope:wght@400;600;700&display=swap');

:root {
  --v-primary-base: #8b1e3f;
}
body {
  margin: 0;
  font-family: 'Space Grotesk', 'Sora', 'Manrope', system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial;
}
.app-main,
.v-main,
.v-main__wrap {
  background: transparent !important;
}
.v-application { color: #ece6ee; }
.toolbar-title-link {
  color: inherit;
  text-decoration: none;
  display: inline-block;
}
.toolbar-logo {
  height: 36px;
  width: auto;
  object-fit: contain;
}
.avatar-link { text-decoration: none; }
.avatar-chip {
  background: rgba(255, 255, 255, 0.12);
  color: #f6ecf0;
}
.footer { color: rgba(233,237,245,0.8); }
.tracking { letter-spacing: 0.18em; }
</style>
