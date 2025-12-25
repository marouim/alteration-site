<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="section-head mb-6">
      <p class="eyebrow">Carte Altération</p>
      <h1>Nos bières au moût de raisin</h1>
      <p class="text-muted">Bière du moment, signatures et cuvées de passage.</p>
    </div>

    <v-row dense class="mb-8">
      <v-col cols="12">
        <v-card
          v-if="momentBeer"
          class="feature-card"
          rounded="xl"
          elevation="12"
          link
          :to="{ name: 'BeerDetail', params: { slug: momentBeer.slug } }"
        >
          <v-card-text class="d-flex flex-column flex-md-row align-center">
            <div class="mr-md-6 mb-4 mb-md-0 feature-bottle">
              <BeerBottle :accent="momentBeer.accent" :title="momentBeer.name" :subtitle="momentBeer.style" />
            </div>
            <div class="flex-1">
              <p class="text-overline text-muted mb-1">Bière du moment</p>
              <h3 class="mb-2">{{ momentBeer.name }}</h3>
              <p class="text-muted mb-3">{{ momentBeer.description }}</p>
              <div class="d-flex flex-wrap align-center mb-4">
                <v-chip color="primary" variant="flat" class="mr-2 mb-2" label>{{ momentBeer.style }}</v-chip>
                <v-chip color="secondary" variant="tonal" class="mr-2 mb-2" label>{{ momentBeer.abv }}</v-chip>
                <v-chip v-if="momentBeer.batch" color="info" variant="text" class="mr-2 mb-2" label>{{ momentBeer.batch }}</v-chip>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <section class="mb-10">
      <div class="d-flex align-center justify-space-between mb-4">
        <div>
          <p class="eyebrow">Bières signatures</p>
          <h2>Assemblages emblématiques</h2>
        </div>
      </div>
      <v-row dense>
        <v-col v-for="beer in signatureBeers" :key="beer.slug" cols="12" md="4">
          <v-card
            class="beer-card"
            rounded="xl"
            elevation="10"
            link
            :to="{ name: 'BeerDetail', params: { slug: beer.slug } }"
          >
            <div class="beer-accent" :style="{ background: beer.accent }"></div>
            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-1">
                <span class="text-overline text-muted">{{ beer.style }}</span>
                <v-chip color="primary" variant="text" density="comfortable">{{ beer.abv }}</v-chip>
              </div>
              <div class="d-flex align-start justify-space-between bottle-row">
                <div class="flex-1 mr-3">
                  <h3 class="mb-1">{{ beer.name }}</h3>
                  <p class="text-muted mb-3">{{ beer.description }}</p>
              <div class="notes mb-3" v-if="beer.notes?.length">
                <span class="note" v-for="note in beer.notes" :key="note">{{ note }}</span>
              </div>
            </div>
            <BeerBottle class="d-none d-md-block" :accent="beer.accent" :title="beer.name" :subtitle="beer.style" />
          </div>
          <div class="d-flex align-center justify-space-between">
            <span class="text-muted text-body-2">Fiche détaillée</span>
            <v-btn color="primary" variant="text" rounded="lg" size="small">Voir</v-btn>
          </div>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</section>

    <section>
      <div class="d-flex align-center justify-space-between mb-4">
        <div>
          <p class="eyebrow">Autres bières</p>
          <h2>Editions limitées & collabs</h2>
        </div>
      </div>
      <v-row dense>
        <v-col v-for="beer in otherBeers" :key="beer.slug" cols="12" md="3">
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
              <div class="d-flex align-start justify-space-between bottle-row">
                <div class="flex-1 mr-3">
                  <h4 class="mb-1">{{ beer.name }}</h4>
                  <p class="text-muted mb-3">{{ beer.description }}</p>
                </div>
                <BeerBottle class="d-none d-md-block" :accent="beer.accent" :title="beer.name" :subtitle="beer.style" />
              </div>
              <div class="d-flex align-center justify-space-between">
                <span class="text-muted text-body-2">Fiche détaillée</span>
                <v-btn color="secondary" variant="text" rounded="lg" size="small">Voir</v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </section>

    <section class="mt-12">
      <div class="d-flex align-center justify-space-between mb-4">
        <div>
          <p class="eyebrow">Points de vente</p>
          <h2>Où trouver nos cuvées</h2>
          <p class="text-muted">Liste centralisée, prête à devenir dynamique si des points s’ajoutent.</p>
        </div>
      </div>
      <v-row dense>
        <v-col v-for="retailer in retailers" :key="retailer.id" cols="12" md="4">
          <v-card class="beer-card compact" rounded="xl" elevation="8">
            <div class="beer-accent subtle" :style="{ background: 'linear-gradient(135deg, #8b1e3f, #3ea76a)' }"></div>
            <v-card-text>
              <h4 class="mb-2">{{ retailer.name }}</h4>
              <p class="text-muted mb-0">Stock variable selon arrivages — bientôt connecté à notre suivi en temps réel.</p>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </section>
  </v-container>
</template>

<script setup>
import { beers, beersByCategory } from '../data/beers'
import BeerBottle from '../components/BeerBottle.vue'
import { retailers } from '../data/retailers'

const momentBeer = beers.find((beer) => beer.category === 'moment') || null
const signatureBeers = beersByCategory('signature')
const otherBeers = beersByCategory('limited')
</script>

<style scoped>
.section-head h1 { margin: 6px 0; }
.feature-card {
  position: relative;
  background: linear-gradient(135deg, rgba(216, 87, 127, 0.1), rgba(62, 167, 106, 0.08));
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.feature-bottle {
  min-width: 120px;
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
.eyebrow {
  font-size: 12px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(195, 213, 198, 0.9);
}
.bottle-row { gap: 8px; }
</style>
