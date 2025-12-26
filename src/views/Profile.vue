<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="section-head mb-6">
      <p class="eyebrow">Espace membre</p>
      <h1 class="mb-2">Profil</h1>
      <p class="text-muted">Suivi local avant connexion au microservice.</p>
    </div>

    <div v-if="!isAuthenticated" class="text-muted">
      Connectez-vous pour voir vos points et dégustations.
    </div>

    <div v-else>
      <v-card rounded="xl" elevation="10" class="mb-6">
        <v-card-text class="d-flex align-center justify-space-between">
          <div>
            <h1 class="mb-2">{{ level }}</h1>
            <p class="text-overline text-muted mb-1">Mes points Altération</p>
            <h2 class="mb-1">{{ userPoints }} points</h2>
            <p class="text-body-2 text-muted mb-0">
              5 pts par note, 10 pts par commentaire. Stockage local provisoire.
            </p>
            <p class="text-body-2 text-muted mb-0">
              Scans : {{ scans }} (15 pts par scan)
            </p>
            <p class="text-body-2 mt-2">Niveau : {{ level }}</p>
          </div>
          <v-avatar color="secondary" size="48">
            <span class="font-weight-bold">{{ usernameInitial }}</span>
          </v-avatar>
        </v-card-text>
      </v-card>

      <v-card rounded="xl" elevation="8">
        <v-card-title>Résumé</v-card-title>
        <v-card-text>
          <p class="mb-1">Notes enregistrées : {{ ratedCount }}</p>
          <p class="mb-0">Commentaires : {{ commentCount }}</p>
        </v-card-text>
      </v-card>
    </div>
  </v-container>
</template>

<script setup>
import { computed } from 'vue'
import { useUserStore } from '../stores/userStore'

const userStore = useUserStore()
const isAuthenticated = userStore.isAuthenticated
const username = userStore.username
const usernameInitial = computed(() => (username.value ? username.value[0]?.toUpperCase() : ''))
const userRatings = computed(() => {
  if (!username.value) return {}
  return userStore.state.ratings[username.value] || {}
})
const ratedCount = computed(() => Object.values(userRatings.value).filter((r) => (r?.stars || 0) > 0).length)
const commentCount = computed(() => Object.values(userRatings.value).filter((r) => r?.comment && r.comment.trim()).length)
const userPoints = userStore.userPoints
const level = userStore.level
const scans = userStore.scans
</script>

<style scoped>
.section-head h1 { margin: 6px 0; }
.eyebrow {
  font-size: 12px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(195, 213, 198, 0.9);
}
</style>
