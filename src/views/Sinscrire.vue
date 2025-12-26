<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="section-head mb-6">
      <p class="eyebrow">Espace membre</p>
      <h1 class="mb-2">Connexion / Création locale</h1>
      <p class="text-muted">
        Authentification locale provisoire. Les données seront branchées à un microservice plus tard.
      </p>
    </div>

    <v-card class="mb-8" rounded="xl" elevation="10">
      <v-card-text>
        <v-form @submit.prevent="onSubmit" class="d-flex flex-column gap-4">
          <v-text-field
            v-model="form.username"
            label="Nom d'usager"
            required
            prepend-inner-icon="mdi-account"
          />
          <v-text-field
            v-model="form.password"
            label="Mot de passe"
            required
            type="password"
            prepend-inner-icon="mdi-lock"
          />
          <div class="d-flex align-center gap-4">
            <v-btn color="primary" type="submit" rounded="xl">Se connecter</v-btn>
            <span v-if="isAuthenticated" class="text-success">Connecté comme {{ username }}</span>
          </div>
        </v-form>
      </v-card-text>
    </v-card>

    <v-card v-if="isAuthenticated" rounded="xl" elevation="10">
      <v-card-title>Mes appréciations</v-card-title>
      <v-card-text>
        <p class="text-muted mb-4">
          Vos notes et commentaires sont stockés localement et visibles lors de vos prochaines connexions.
        </p>
        <p class="text-body-2">Allez sur une fiche bière pour noter en étoiles et laisser un commentaire.</p>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script setup>
import { reactive } from 'vue'
import { useUserStore } from '../stores/userStore'

const { login, isAuthenticated, username } = useUserStore()

const form = reactive({
  username: '',
  password: ''
})

function onSubmit() {
  if (!form.username || !form.password) return
  login(form.username.trim())
}
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
