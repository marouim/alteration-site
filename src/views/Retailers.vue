<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="section-head mb-6 d-flex align-center justify-space-between">
      <div>
        <p class="eyebrow">Points de vente</p>
        <h1 class="mb-2">Où trouver nos cuvées</h1>
        <p class="text-muted">Sélection actuelle de détaillants — prête à être connectée à un flux dynamique.</p>
      </div>
    </div>

    <v-row dense class="mb-8">
      <v-col cols="12" md="7">
        <div class="map-card">
          <div class="map-bg"></div>
          <div class="map-overlay">
            <div
              v-for="retailer in retailers"
              :key="retailer.id"
              class="map-pin"
              :style="{ left: `${retailer.mapPosition.x}%`, top: `${retailer.mapPosition.y}%` }"
              @click="goTo(retailer.id)"
            >
              <span class="dot"></span>
              <span class="pulse"></span>
              <span class="pin-label">{{ retailer.name }}</span>
            </div>
          </div>
        </div>
      </v-col>
      <v-col cols="12" md="5">
        <v-card
          v-for="retailer in retailers"
          :key="retailer.id"
          class="mb-4"
          rounded="xl"
          elevation="8"
          link
          :to="{ name: 'RetailerDetail', params: { id: retailer.id } }"
        >
          <v-card-text class="d-flex align-center justify-space-between">
            <div>
              <p class="text-overline text-muted mb-1">Point de vente</p>
              <h3 class="mb-1">{{ retailer.name }}</h3>
              <p class="text-muted mb-0">{{ retailer.address }}, {{ retailer.city }}</p>
            </div>
            <v-btn icon variant="text" color="secondary">
              <v-icon icon="mdi-chevron-right" />
            </v-btn>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { retailers } from '../data/retailers'
import { useRouter } from 'vue-router'

const router = useRouter()

function goTo(id) {
  router.push({ name: 'RetailerDetail', params: { id } })
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
  height: 420px;
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
  cursor: pointer;
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
@keyframes pulse {
  0% { transform: scale(0.9); opacity: 0.6; }
  70% { transform: scale(1.25); opacity: 0; }
  100% { transform: scale(0.9); opacity: 0; }
}
</style>
