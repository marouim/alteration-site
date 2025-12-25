<template>
  <v-app>
    <v-app-bar app flat color="primary" dark>
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <v-toolbar-title class="font-weight-bold text-uppercase tracking d-flex align-center">
        <router-link to="/" class="toolbar-title-link d-flex align-center">
          <img src="/src/assets/Logo_Alteration_Wth.png" alt="Altération" class="toolbar-logo" />
          <span class="ml-2">Altération</span>
        </router-link>
      </v-toolbar-title>
      <v-spacer />
      <v-btn variant="text" to="/qui-sommes-nous">Qui sommes nous</v-btn>
      <v-btn variant="text" to="/points-de-vente">Points de vente</v-btn>
      <v-btn variant="text" to="/nos-bieres">Nos bières</v-btn>
      <v-btn variant="tonal" color="amber-accent" class="ml-3" rounded="xl" to="/sinscrire">Rejoindre</v-btn>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" app temporary>
      <v-list nav>
        <v-list-item>
          <v-list-item-title class="text-h6">Menu</v-list-item-title>
        </v-list-item>
        <v-divider />
        <v-list-item link :to="{ name: 'Home' }">Accueil</v-list-item>
        <v-list-item link :to="{ name: 'QuiSommesNous' }">Qui sommes nous</v-list-item>
        <v-list-item link :to="{ name: 'NosBieres' }">Nos bières</v-list-item>
        <v-list-item link :to="{ name: 'Sinscrire' }">S'inscrire</v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
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
import { ref } from 'vue'
const drawer = ref(false)
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
.v-application {
  background:
    radial-gradient(circle at 20% 20%, rgba(139, 30, 63, 0.14) 0, transparent 32%),
    radial-gradient(circle at 78% 10%, rgba(62, 167, 106, 0.12) 0, transparent 30%),
    linear-gradient(180deg, #0b0810 0%, #0d0a14 45%, #0b0810 100%);
  color: #ece6ee;
}
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
.footer { color: rgba(233,237,245,0.8); }
.tracking { letter-spacing: 0.18em; }
</style>
