<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="section-head mb-6">
      <p class="eyebrow">Carte Altération</p>
      <h1>Nos bières au moût de raisin</h1>
      <p class="text-muted">Bière du moment, signatures et cuvées de passage. Ajoutez au panier pour préparer votre commande.</p>
    </div>

    <v-row dense class="mb-8">
      <v-col cols="12" md="8">
        <v-card class="feature-card" rounded="xl" elevation="12">
          <v-card-text class="d-flex flex-column flex-md-row align-center">
            <div class="feature-accent mr-md-6 mb-4 mb-md-0"></div>
            <div class="flex-1">
              <p class="text-overline text-muted mb-1">Bière du moment</p>
              <h3 class="mb-2">{{ momentBeer.name }}</h3>
              <p class="text-muted mb-3">{{ momentBeer.description }}</p>
              <div class="d-flex flex-wrap align-center mb-4">
                <v-chip color="primary" variant="flat" class="mr-2 mb-2" label>{{ momentBeer.style }}</v-chip>
                <v-chip color="secondary" variant="tonal" class="mr-2 mb-2" label>{{ momentBeer.abv }}</v-chip>
                <v-chip color="info" variant="text" class="mr-2 mb-2" label>{{ momentBeer.batch }}</v-chip>
              </div>
              <div class="d-flex align-center justify-space-between">
                <div class="price">{{ formatPrice(momentBeer.price) }}</div>
                <v-btn color="primary" rounded="xl" @click="addToCart(momentBeer)">Ajouter au panier</v-btn>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" md="4">
        <v-card class="cart-card" rounded="xl" elevation="12">
          <v-card-title class="d-flex align-center justify-space-between">
            <span>Panier</span>
            <v-chip color="secondary" variant="flat" label>{{ totalItems }} article<span v-if="totalItems > 1">s</span></v-chip>
          </v-card-title>
          <v-divider />
          <v-card-text>
            <div v-if="!cartItems.length" class="text-muted">Votre panier est vide.</div>
            <div v-else class="cart-list">
              <div v-for="item in cartItems" :key="item.id" class="cart-item">
                <div>
                  <p class="cart-name">{{ item.beer.name }}</p>
                  <p class="text-muted text-body-2">{{ item.beer.style }}</p>
                </div>
                <div class="cart-actions">
                  <v-btn icon size="small" variant="text" @click="changeQty(item.id, -1)">
                    <v-icon icon="mdi-minus" />
                  </v-btn>
                  <span class="qty">{{ item.qty }}</span>
                  <v-btn icon size="small" variant="text" @click="changeQty(item.id, 1)">
                    <v-icon icon="mdi-plus" />
                  </v-btn>
                </div>
                <div class="cart-price">{{ formatPrice(item.beer.price * item.qty) }}</div>
              </div>
            </div>
          </v-card-text>
          <v-divider />
          <v-card-text class="d-flex align-center justify-space-between">
            <span class="font-weight-medium">Total</span>
            <span class="price">{{ formatPrice(totalPrice) }}</span>
          </v-card-text>
          <v-card-actions>
            <v-btn block color="secondary" rounded="xl" :disabled="!cartItems.length">Passer la commande</v-btn>
          </v-card-actions>
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
        <v-col v-for="beer in signatureBeers" :key="beer.name" cols="12" md="4">
          <v-card class="beer-card" rounded="xl" elevation="10">
            <div class="beer-accent" :style="{ background: beer.accent }"></div>
            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-1">
                <span class="text-overline text-muted">{{ beer.style }}</span>
                <v-chip color="primary" variant="text" density="comfortable">{{ beer.abv }}</v-chip>
              </div>
              <h3 class="mb-1">{{ beer.name }}</h3>
              <p class="text-muted mb-3">{{ beer.description }}</p>
              <div class="notes mb-3">
                <span class="note" v-for="note in beer.notes" :key="note">{{ note }}</span>
              </div>
              <div class="d-flex align-center justify-space-between">
                <span class="price">{{ formatPrice(beer.price) }}</span>
                <v-btn color="primary" variant="tonal" rounded="lg" size="small" @click="addToCart(beer)">Ajouter</v-btn>
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
        <v-col v-for="beer in otherBeers" :key="beer.name" cols="12" md="3">
          <v-card class="beer-card compact" rounded="xl" elevation="8">
            <div class="beer-accent subtle" :style="{ background: beer.accent }"></div>
            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-1">
                <span class="text-overline text-muted">{{ beer.style }}</span>
                <v-chip color="secondary" variant="text" density="comfortable">{{ beer.abv }}</v-chip>
              </div>
              <h4 class="mb-1">{{ beer.name }}</h4>
              <p class="text-muted mb-3">{{ beer.description }}</p>
              <div class="d-flex align-center justify-space-between">
                <span class="price">{{ formatPrice(beer.price) }}</span>
                <v-btn color="secondary" variant="outlined" rounded="lg" size="small" @click="addToCart(beer)">Ajouter</v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </section>
  </v-container>
</template>

<script setup>
import { computed, ref } from 'vue'

const momentBeer = {
  name: 'Assemblage Rubis 27',
  style: 'Ale hybride · Moût de Grenache',
  abv: '6,1%',
  batch: 'Lot 1100 btles',
  description: 'Macération pelliculaire, fruits rouges mûrs, finale saline qui rappelle la vendange.',
  price: 7.5
}

const signatureBeers = [
  {
    name: 'Verte Vigne',
    style: 'Saison au moût de Muscat',
    abv: '5,6%',
    description: 'Fleurs blanches, peau de citron vert, bouche vive portée par le grain et le raisin.',
    notes: ['Muscat', 'Fermentation lente', 'Houblons nobles'],
    price: 6.8,
    accent: 'linear-gradient(135deg, #3ea76a, #9ae6b4)'
  },
  {
    name: 'Nocturne Pelliculaire',
    style: 'Stout vinifié',
    abv: '7,3%',
    description: 'Texture soyeuse, cacao profond et touche de cassis issue d’une macération courte.',
    notes: ['Barriques chêne', 'Pelliculaire', 'Cacao'],
    price: 7.9,
    accent: 'linear-gradient(135deg, #2a102d, #7c3a7c)'
  },
  {
    name: 'Assemblage Rubis',
    style: 'Ale hybride au moût de Grenache',
    abv: '6,1%',
    description: 'Macération pelliculaire, fruits rouges mûrs et finale saline pour rappeler la vendange.',
    notes: ['Grenache', 'Fermentation mixte', 'Lies fines'],
    price: 7.5,
    accent: 'linear-gradient(135deg, #9c1b3f, #d8577f)'
  }
]

const otherBeers = [
  {
    name: 'Ambrée Vendange Tardive',
    style: 'Amber au moût de Pinot Gris',
    abv: '6,4%',
    description: 'Notes d’abricot sec, miel clair et légère tension minérale.',
    price: 6.5,
    accent: 'linear-gradient(135deg, #f0a35a, #d8577f)'
  },
  {
    name: 'Cuvée Florale',
    style: 'Blanche au moût de Riesling',
    abv: '4,9%',
    description: 'Zeste de citron, fleurs blanches, bulle fine et sèche.',
    price: 6.2,
    accent: 'linear-gradient(135deg, #8fb3ff, #b6e0fe)'
  },
  {
    name: 'Sureau en Barrique',
    style: 'Saison vieillie',
    abv: '6,0%',
    description: 'Fleurs de sureau, vin jaune et finale épicée.',
    price: 6.9,
    accent: 'linear-gradient(135deg, #d6c2ff, #8b5cf6)'
  },
  {
    name: 'Collab Presse Directe',
    style: 'Gueuze au moût de Gamay',
    abv: '5,7%',
    description: 'Acidité vineuse, groseille et pointe brettée élégante.',
    price: 7.1,
    accent: 'linear-gradient(135deg, #f472b6, #d946ef)'
  }
]

const cart = ref({})

const cartItems = computed(() =>
  Object.entries(cart.value).map(([id, entry]) => ({
    id,
    ...entry
  }))
)

const totalItems = computed(() =>
  cartItems.value.reduce((acc, item) => acc + item.qty, 0)
)

const totalPrice = computed(() =>
  cartItems.value.reduce((acc, item) => acc + item.beer.price * item.qty, 0)
)

function addToCart(beer) {
  const id = beer.name
  const existing = cart.value[id]
  const qty = existing ? existing.qty + 1 : 1
  cart.value[id] = { beer, qty }
}

function changeQty(id, delta) {
  const entry = cart.value[id]
  if (!entry) return
  const next = entry.qty + delta
  if (next <= 0) {
    const { [id]: _, ...rest } = cart.value
    cart.value = rest
  } else {
    cart.value[id] = { ...entry, qty: next }
  }
}

function formatPrice(value) {
  return `${value.toFixed(2)} $`
}
</script>

<style scoped>
.section-head h1 { margin: 6px 0; }
.feature-card {
  position: relative;
  background: linear-gradient(135deg, rgba(216, 87, 127, 0.1), rgba(62, 167, 106, 0.08));
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.feature-accent {
  width: 120px;
  height: 120px;
  border-radius: 18px;
  background: radial-gradient(circle at 30% 30%, rgba(216, 87, 127, 0.4), transparent 55%), radial-gradient(circle at 70% 70%, rgba(62, 167, 106, 0.35), transparent 55%), #0f0a12;
  border: 1px solid rgba(255, 255, 255, 0.06);
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.35);
}
.price {
  font-weight: 700;
  letter-spacing: 0.02em;
  color: #f6ecf0;
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
.cart-card {
  background: rgba(16, 10, 18, 0.94);
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.cart-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.cart-item {
  display: grid;
  grid-template-columns: 1fr auto auto;
  gap: 12px;
  align-items: center;
}
.cart-name { margin: 0; font-weight: 600; }
.cart-actions {
  display: inline-flex;
  align-items: center;
  gap: 8px;
}
.qty { min-width: 20px; text-align: center; }
.eyebrow {
  font-size: 12px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(195, 213, 198, 0.9);
}
@media (max-width: 960px) {
  .cart-item { grid-template-columns: 1fr auto; grid-template-rows: auto auto; }
  .cart-price { justify-self: end; }
}
</style>
