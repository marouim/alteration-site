<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="mb-4">
      <v-btn variant="text" color="secondary" prepend-icon="mdi-arrow-left" @click="goBack">
        Retour aux bières
      </v-btn>
    </div>

    <v-alert
      v-if="lowInventoryWarning"
      type="warning"
      variant="tonal"
      color="amber-accent"
      border="start"
      class="mb-4"
    >
      Faites vites, l'inventaire est presque qu'épuisé
    </v-alert>

    <div v-if="beer">
      <div class="section-head mb-6">
        <p class="eyebrow">Fiche bière</p>
        <h1 class="mb-2">{{ beer.name }}</h1>
        <p class="text-muted mb-3">{{ beer.description }}</p>
        <div class="d-flex flex-wrap align-center gap-2 chips-row">
          <v-chip color="primary" variant="flat" class="mr-2 mb-2" label>{{ beer.style }}</v-chip>
          <v-chip color="secondary" variant="tonal" class="mr-2 mb-2" label>{{ beer.abv }}</v-chip>
          <v-chip v-if="beer.batch" color="info" variant="text" class="mr-2 mb-2" label>{{ beer.batch }}</v-chip>
          <v-chip
            v-if="inventoryText"
            :color="lowInventoryWarning ? 'amber-accent' : 'secondary'"
            variant="flat"
            class="mr-2 mb-2"
            label
          >
            {{ inventoryText }} restant / brassé
          </v-chip>
        </div>
      </div>

      <v-card class="detail-card mb-8" rounded="xl" elevation="12" :style="{ '--accent': beer.accent }">
        <v-card-text class="d-flex flex-column flex-md-row align-start">
          <div class="detail-accent mr-md-6 mb-4 mb-md-0">
            <BeerBottle :accent="beer.accent" :title="beer.name" :subtitle="beer.style" />
          </div>
          <div class="flex-1">
            <p class="text-overline text-muted mb-1">{{ beer.category === 'signature' ? 'Signature' : 'Edition limitée' }}</p>
            <h2 class="mb-3">Profil aromatique</h2>
            <p class="text-muted text-body-1 mb-4">
              {{ beer.description }} Nous avons travaillé cette cuvée pour conserver le fil du raisin tout en laissant la fermentation s’exprimer.
            </p>
            <div class="notes" v-if="beer.notes?.length">
              <span class="note" v-for="note in beer.notes" :key="note">{{ note }}</span>
            </div>
            <div class="mt-4">
              <p class="text-overline text-muted mb-2">Votre appréciation</p>
              <div class="rating-stars" role="radiogroup" aria-label="Note sur 5">
                <button
                  v-for="star in 5"
                  :key="star"
                  type="button"
                  class="rating-star"
                  :class="{ filled: ratingStars >= star }"
                  :aria-checked="ratingStars === star"
                  :aria-label="`${star} étoiles`"
                  @click="setRating(star)"
                >
                  ★
                </button>
              </div>
              <div v-if="ratingStars" class="text-body-2 mt-1">
                Votre note : {{ ratingStars }}/5
              </div>

              <div v-if="isAuthenticated">
                <v-textarea
                  v-model="ratingComment"
                  auto-grow
                  rows="2"
                  label="Commentaire"
                  placeholder="Notez vos impressions pour la prochaine dégustation."
                  class="mt-3"
                />
              </div>
              <div v-else class="mt-2 text-muted text-body-2">
                Connectez-vous pour enregistrer votre note et commentaire.
              </div>
            </div>
          </div>
        </v-card-text>
      </v-card>

      <v-sheet class="pairing-block mb-8" rounded="xl" elevation="10">
        <div class="section-head mb-4">
          <p class="eyebrow">Accords mets & fromages</p>
          <h2 class="mb-2">Ce qui sublime {{ beer.name }}</h2>
          <p class="text-muted">Suggestions pour amplifier les arômes et textures de cette cuvée.</p>
        </div>
        <v-row>
          <v-col cols="12" md="6">
            <div class="pairing-card">
              <div class="pairing-icon" :style="{ background: 'rgba(216, 87, 127, 0.16)' }">🥘</div>
              <div>
                <p class="text-overline text-muted mb-1">Mets</p>
                <h3 class="mb-1">{{ pairings.dishes.title }}</h3>
                <p class="text-muted mb-0">{{ pairings.dishes.text }}</p>
              </div>
            </div>
          </v-col>
          <v-col cols="12" md="6">
            <div class="pairing-card">
              <div class="pairing-icon" :style="{ background: 'rgba(62, 167, 106, 0.16)' }">🧀</div>
              <div>
                <p class="text-overline text-muted mb-1">Fromages</p>
                <h3 class="mb-1">{{ pairings.cheeses.title }}</h3>
                <p class="text-muted mb-0">{{ pairings.cheeses.text }}</p>
              </div>
            </div>
          </v-col>
        </v-row>
      </v-sheet>

      <v-row dense class="mb-8">
        <v-col cols="12" md="4">
          <v-sheet class="info-block" rounded="xl" elevation="8">
            <p class="text-overline text-muted mb-2">Houblons</p>
            <div class="d-flex flex-wrap gap-2">
              <v-chip v-for="hop in beer.hops" :key="hop" color="secondary" variant="flat" label size="small">
                {{ hop }}
              </v-chip>
            </div>
          </v-sheet>
        </v-col>
        <v-col cols="12" md="4">
          <v-sheet class="info-block" rounded="xl" elevation="8">
            <p class="text-overline text-muted mb-2">Levure</p>
            <p class="text-body-1">{{ beer.yeast }}</p>
          </v-sheet>
        </v-col>
        <v-col cols="12" md="4">
          <v-sheet class="info-block" rounded="xl" elevation="8">
            <p class="text-overline text-muted mb-2">Technique</p>
            <p class="text-body-1">{{ beer.technique }}</p>
          </v-sheet>
        </v-col>
      </v-row>

      <div v-if="relatedBeers.length" class="mb-2">
        <p class="eyebrow">Autres cuvées</p>
        <h3>Dans la même veine</h3>
      </div>
      <v-row v-if="relatedBeers.length" dense>
        <v-col v-for="item in relatedBeers" :key="item.slug" cols="12" md="4">
          <v-card
            class="beer-card compact"
            rounded="xl"
            elevation="8"
            link
            :to="{ name: 'BeerDetail', params: { slug: item.slug } }"
          >
            <div class="beer-accent subtle" :style="{ background: item.accent }"></div>
            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-1">
                <span class="text-overline text-muted">{{ item.style }}</span>
                <v-chip color="secondary" variant="text" density="comfortable">{{ item.abv }}</v-chip>
              </div>
              <h4 class="mb-1">{{ item.name }}</h4>
              <p class="text-muted mb-3">{{ item.description }}</p>
              <div class="d-flex align-center justify-space-between">
                <span class="text-muted text-body-2">Fiche détaillée</span>
                <v-btn color="secondary" variant="outlined" rounded="lg" size="small">Voir</v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>

      <v-card v-if="beerRetailers.length" class="info-block mt-6" rounded="xl" elevation="8">
        <v-card-title>Points de vente</v-card-title>
        <v-card-text>
          <div class="d-flex flex-wrap gap-2">
            <v-chip
              v-for="retailer in beerRetailers"
              :key="retailer.id"
              color="secondary"
              variant="flat"
              label
              link
              :to="{ name: 'RetailerDetail', params: { id: retailer.id } }"
            >
              {{ retailer.name }}
            </v-chip>
          </div>
        </v-card-text>
      </v-card>
    </div>

    <div v-else class="text-center py-12">
      <p class="text-h5 mb-3">Cette bière est introuvable.</p>
      <p class="text-muted mb-6">Elle n’est plus disponible ou le lien est incorrect.</p>
      <v-btn color="primary" rounded="xl" to="/nos-bieres">Retour à la carte</v-btn>
    </div>
  </v-container>
</template>

<script setup>
import { computed, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { beers, getBeerBySlug, beersByCategory } from '../data/beers'
import BeerBottle from '../components/BeerBottle.vue'
import { getRetailersByIds } from '../data/retailers'
import { useUserStore } from '../stores/userStore'

const route = useRoute()
const router = useRouter()
const userStore = useUserStore()

const slug = computed(() => route.params.slug?.toString?.() || '')
const beer = computed(() => getBeerBySlug(slug.value))

const inventoryText = computed(() => {
  if (!beer.value || !beer.value.initialInventory || beer.value.remainingInventory === undefined) return null
  return `${beer.value.remainingInventory}/${beer.value.initialInventory}`
})

const lowInventoryWarning = computed(() => {
  if (!beer.value || !beer.value.initialInventory || beer.value.remainingInventory === undefined) return false
  if (beer.value.initialInventory <= 0) return false
  return beer.value.remainingInventory / beer.value.initialInventory < 0.15
})

const relatedBeers = computed(() => {
  if (!beer.value) return []
  return beersByCategory(beer.value.category)
    .filter((item) => item.slug !== beer.value.slug)
    .slice(0, 3)
})

const beerRetailers = computed(() => {
  if (!beer.value?.availableAt) return []
  return getRetailersByIds(beer.value.availableAt)
})

const isAuthenticated = userStore.isAuthenticated
const userRating = computed(() => (beer.value ? userStore.getRating(beer.value.slug) : null))
const tempRating = ref(0)
const ratingStars = computed({
  get: () => (isAuthenticated.value ? userRating.value?.stars || 0 : tempRating.value),
  set: (val) => {
    if (!beer.value) return
    if (isAuthenticated.value) {
      userStore.saveRating(beer.value.slug, val, userRating.value?.comment || '')
    } else {
      tempRating.value = val
    }
  }
})
const ratingComment = computed({
  get: () => userRating.value?.comment || '',
  set: (val) => {
    if (beer.value) userStore.saveRating(beer.value.slug, ratingStars.value, val)
  }
})

function goBack() {
  router.push({ name: 'NosBieres' })
}

function setRating(star) {
  ratingStars.value = star
}

const pairings = computed(() => ({
  dishes: {
    title: beer.value?.pairings?.dishes?.title || 'Cuisine vive & iodée',
    text:
      beer.value?.pairings?.dishes?.text ||
      "Crudo de poisson blanc, ceviche aux agrumes, légumes grillés au zaatar ou volaille fermière rôtie."
  },
  cheeses: {
    title: beer.value?.pairings?.cheeses?.title || 'Fromages à pâte molle & croûte fleurie',
    text:
      beer.value?.pairings?.cheeses?.text ||
      "Brie fermier, camembert au lait cru, saint-félicien affiné, tomme légèrement lavée pour jouer l'umami."
  }
}))
</script>

<style scoped>
.section-head h1 { margin: 6px 0; }
.eyebrow {
  font-size: 12px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(195, 213, 198, 0.9);
}
.detail-card {
  position: relative;
  background: linear-gradient(135deg, rgba(255, 255, 255, 0.04), rgba(255, 255, 255, 0.02));
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.detail-accent {
  width: 160px;
  min-width: 160px;
}
.notes {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.note {
  background: rgba(255, 255, 255, 0.08);
  padding: 6px 10px;
  border-radius: 999px;
  font-size: 12px;
  color: rgba(246, 236, 240, 0.9);
}
.pairing-block {
  background: rgba(11, 8, 16, 0.9);
  border: 1px solid rgba(255, 255, 255, 0.06);
}
.pairing-card {
  display: grid;
  grid-template-columns: auto 1fr;
  gap: 12px;
  padding: 16px;
  border-radius: 14px;
  background: rgba(255, 255, 255, 0.02);
  border: 1px solid rgba(255, 255, 255, 0.05);
  height: 100%;
}
.pairing-icon {
  width: 40px;
  height: 40px;
  border-radius: 12px;
  display: grid;
  place-items: center;
  font-size: 18px;
}
.info-block {
  background: rgba(16, 10, 18, 0.94);
  border: 1px solid rgba(255, 255, 255, 0.08);
  min-height: 150px;
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
.rating-star {
  font-size: 28px;
  color: #7c7c85;
  cursor: pointer;
  background: transparent;
  border: none;
  padding: 2px;
  line-height: 1;
}
.rating-star.filled {
  color: #d19c3a;
}
.chips-row { gap: 10px; }
</style>
