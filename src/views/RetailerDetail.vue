<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="mb-4">
      <v-btn variant="text" color="secondary" prepend-icon="mdi-arrow-left" @click="goBack">
        Retour aux points de vente
      </v-btn>
    </div>

    <div v-if="retailer">
      <div class="section-head mb-6">
        <p class="eyebrow">Point de vente</p>
        <h1 class="mb-2">{{ retailer.name }}</h1>
        <p class="text-muted mb-3">{{ retailer.description }}</p>
      </div>

      <v-row dense class="mb-6">
        <v-col cols="12" md="7">
          <v-card class="map-card" rounded="xl" elevation="10">
            <div class="map-bg"></div>
            <div class="map-overlay">
              <div
                class="map-pin"
                :style="{ left: `${retailer.mapPosition.x}%`, top: `${retailer.mapPosition.y}%` }"
              >
                <span class="dot"></span>
                <span class="pulse"></span>
                <span class="pin-label">{{ retailer.name }}</span>
              </div>
            </div>
          </v-card>
        </v-col>
        <v-col cols="12" md="5">
          <v-card class="info-block" rounded="xl" elevation="10">
            <v-card-title>Coordonnées</v-card-title>
            <v-card-text>
              <p class="mb-1 font-weight-medium">{{ retailer.address }}</p>
              <p class="mb-3 text-muted">{{ retailer.city }} {{ retailer.postalCode }}</p>
              <p class="mb-1">Téléphone : {{ retailer.phone }}</p>
              <p class="text-muted mb-0">Disponibilités sujettes aux arrivages.</p>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>

      <div v-if="retailerBeers.length" class="mb-3">
        <p class="eyebrow">Cuvées disponibles ici</p>
      </div>
      <v-row v-if="retailerBeers.length" dense>
        <v-col v-for="beer in retailerBeers" :key="beer.slug" cols="12" md="4">
          <v-card
            class="beer-card compact"
            rounded="xl"
            elevation="8"
            link
            :to="{ name: 'BeerDetail', params: { slug: beer.slug } }"
          >
            <div class="beer-accent subtle" :style="{ background: beer.accent }"></div>
            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-1">
                <span class="text-overline text-muted">{{ beer.style }}</span>
                <v-chip color="secondary" variant="text" density="comfortable">{{ beer.abv }}</v-chip>
              </div>
              <h4 class="mb-1">{{ beer.name }}</h4>
              <p class="text-muted mb-3">{{ beer.description }}</p>
              <div class="d-flex align-center justify-space-between">
                <span class="text-muted text-body-2">Voir la fiche</span>
                <v-btn color="secondary" variant="outlined" rounded="lg" size="small">Ouvrir</v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </div>

    <div v-else class="text-center py-12">
      <p class="text-h5 mb-3">Point de vente introuvable.</p>
      <p class="text-muted mb-6">Le lien est incorrect ou le point n’est plus actif.</p>
      <v-btn color="primary" rounded="xl" to="/points-de-vente">Retour à la liste</v-btn>
    </div>
  </v-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { retailers, getRetailerById } from '../data/retailers'
import { beers } from '../data/beers'

const route = useRoute()
const router = useRouter()

const retailerId = computed(() => route.params.id?.toString?.() || '')
const retailer = computed(() => getRetailerById(retailerId.value))

const retailerBeers = computed(() =>
  beers.filter((beer) => beer.availableAt?.includes(retailerId.value))
)

function goBack() {
  router.push({ name: 'Retailers' })
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
.map-card {
  position: relative;
  height: 320px;
  border-radius: 24px;
  overflow: hidden;
  border: 1px solid rgba(255, 255, 255, 0.08);
  background: #0f0a12;
}
.map-bg {
  position: absolute;
  inset: 0;
  background:
    radial-gradient(circle at 35% 40%, rgba(216, 87, 127, 0.22), transparent 40%),
    radial-gradient(circle at 70% 65%, rgba(62, 167, 106, 0.2), transparent 36%),
    linear-gradient(135deg, #0b0810, #0f0a12);
  opacity: 0.9;
}
.map-overlay {
  position: absolute;
  inset: 0;
}
.map-pin {
  position: absolute;
  transform: translate(-50%, -50%);
}
.map-pin .dot {
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: #d8577f;
  border: 2px solid #f6ecf0;
  display: block;
  position: relative;
  z-index: 2;
}
.map-pin .pulse {
  position: absolute;
  inset: -10px;
  border-radius: 50%;
  background: rgba(216, 87, 127, 0.2);
  animation: pulse 2s infinite;
  z-index: 1;
}
.pin-label {
  margin-top: 6px;
  display: inline-block;
  padding: 6px 10px;
  border-radius: 999px;
  background: rgba(15, 10, 18, 0.9);
  border: 1px solid rgba(255, 255, 255, 0.08);
  color: #f6ecf0;
  font-size: 12px;
}
.info-block {
  background: rgba(16, 10, 18, 0.94);
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.beer-card {
  position: relative;
  background: #0f0a12;
  border: 1px solid rgba(255, 255, 255, 0.06);
  overflow: hidden;
}
.beer-card.compact { min-height: 100%; }
.beer-accent {
  position: absolute;
  inset: 0 0 55% 0;
  opacity: 0.18;
}
.beer-accent.subtle { opacity: 0.14; inset: 0 0 60% 0; }
.bottle-row { gap: 8px; }
</style>
