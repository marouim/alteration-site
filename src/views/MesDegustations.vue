<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="section-head mb-6">
      <p class="eyebrow">Mes dégustations</p>
      <h1>Bières notées ou commentées</h1>
      <p class="text-muted">Filtré sur vos appréciations locales.</p>
    </div>

    <div v-if="!ratedBeers.length" class="text-muted">
      Aucune dégustation enregistrée pour le moment. Notez une bière pour la voir apparaître ici.
    </div>

    <section v-else class="mb-10">
      <v-row dense>
        <v-col v-for="beer in ratedBeers" :key="beer.slug" cols="12" md="4">
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
                  <div class="text-body-2">
                    Votre note :
                    <span class="rating-indicator">{{ userStore.getRating(beer.slug)?.stars || '—' }}/5</span>
                  </div>
                  <div v-if="userStore.getRating(beer.slug)?.comment" class="text-muted text-body-2 mt-1">
                    “{{ userStore.getRating(beer.slug).comment }}”
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

    <section v-if="recommendedBeer" class="mt-12">
      <p class="eyebrow">Recommandée par le brasseur - basé sur votre expérience</p>
      <v-card
        class="feature-card"
        rounded="xl"
        elevation="12"
        link
        :to="{ name: 'BeerDetail', params: { slug: recommendedBeer.slug } }"
      >
        <v-card-text class="d-flex flex-column flex-md-row align-center">
          <div class="mr-md-6 mb-4 mb-md-0 feature-bottle">
            <BeerBottle :accent="recommendedBeer.accent" :title="recommendedBeer.name" :subtitle="recommendedBeer.style" />
          </div>
          <div class="flex-1">
            <p class="text-overline text-muted mb-1">À découvrir</p>
            <h3 class="mb-2">{{ recommendedBeer.name }}</h3>
            <p class="text-muted mb-3">{{ recommendedBeer.description }}</p>
            <div class="d-flex flex-wrap align-center mb-2">
              <v-chip color="primary" variant="flat" class="mr-2 mb-2" label>{{ recommendedBeer.style }}</v-chip>
              <v-chip color="secondary" variant="tonal" class="mr-2 mb-2" label>{{ recommendedBeer.abv }}</v-chip>
            </div>
            <div class="text-body-2 text-muted">Basé sur vos notes ≥ 4</div>
          </div>
        </v-card-text>
      </v-card>
    </section>

    <section v-if="isAuthenticated" class="mt-12">
      <p class="eyebrow">Scans/achats</p>
      <v-card class="feature-card" rounded="xl" elevation="12">
        <v-card-text class="d-flex flex-column flex-md-row align-center">
          <div class="flex-1">
            <h3 class="mb-2">Déclarer un achat</h3>
            <p class="text-muted mb-3">
              Activez la caméra et scannez un code-barres. La lecture est automatique et stoppe la caméra dès détection.
            </p>
            <div class="d-flex align-center gap-3 mb-3">
              <v-btn color="secondary" rounded="xl" @click="toggleScanner">
                {{ scanning ? 'Arrêter' : 'Scanner un code-barres' }}
              </v-btn>
              <span class="text-muted">Achats cumulés : {{ scans }}</span>
            </div>
            <div v-if="scanning" class="scanner">
              <video ref="videoEl" class="scanner-video" autoplay muted playsinline webkit-playsinline></video>
              <div class="scanner-overlay"></div>
            </div>
            <p class="text-body-2 mt-2" v-if="scanMessage">{{ scanMessage }}</p>
          </div>
        </v-card-text>
      </v-card>
    </section>
  </v-container>
</template>

<script setup>
import { computed, ref, onBeforeUnmount } from 'vue'
import { beers } from '../data/beers'
import BeerBottle from '../components/BeerBottle.vue'
import { useUserStore } from '../stores/userStore'

const userStore = useUserStore()
const activeUser = computed(() => userStore.state.user?.username || '')
const ratedBeers = computed(() => {
  if (!activeUser.value) return []
  const userRatings = userStore.state.ratings[activeUser.value] || {}
  return beers.filter((beer) => !!userRatings[beer.slug])
})

const recommendedBeer = computed(() => {
  if (!activeUser.value) return null
  const userRatings = userStore.state.ratings[activeUser.value] || {}
  const highRatings = Object.entries(userRatings)
    .filter(([, rating]) => (rating?.stars || 0) >= 4)
    .map(([slug]) => beers.find((b) => b.slug === slug))
    .filter(Boolean)

  if (!highRatings.length) return null

  const favoriteCategory = highRatings.reduce((acc, beer) => {
    acc[beer.category] = (acc[beer.category] || 0) + 1
    return acc
  }, {})

  const topCategory = Object.entries(favoriteCategory).sort((a, b) => b[1] - a[1])[0]?.[0]

  const alreadyRatedSlugs = new Set(Object.keys(userRatings))
  const candidates = beers.filter((beer) => beer.category === topCategory && !alreadyRatedSlugs.has(beer.slug))
  return candidates[0] || null
})

const isAuthenticated = userStore.isAuthenticated
const scans = userStore.scans

const scanning = ref(false)
const scanMessage = ref('')
const videoEl = ref(null)
let stream = null
let detector = null
let scanLoop = null
let jsQrLoaded = false

async function ensureJsQr() {
  if (jsQrLoaded) return true
  if (window.jsQR) {
    jsQrLoaded = true
    return true
  }
  return new Promise((resolve) => {
    const script = document.createElement('script')
    const base = import.meta.env.BASE_URL || '/'
    script.src = `${base.replace(/\/$/, '')}/jsqr.min.js`
    script.onload = () => {
      jsQrLoaded = !!window.jsQR
      resolve(true)
    }
    script.onerror = () => resolve(false)
    document.head.appendChild(script)
  })
}

async function toggleScanner() {
  if (scanning.value) {
    stopScanner()
    return
  }
  startScanner()
}

function stopScanner() {
  scanning.value = false
  if (scanLoop) {
    cancelAnimationFrame(scanLoop)
    scanLoop = null
  }
  if (stream) {
    stream.getTracks().forEach((t) => t.stop())
    stream = null
  }
}

async function startScanLoop() {
  if (!videoEl.value) return
  const canvas = document.createElement('canvas')
  const ctx = canvas.getContext('2d')

  const tick = async () => {
    if (!scanning.value || !videoEl.value) return
    const w = videoEl.value.videoWidth
    const h = videoEl.value.videoHeight
    if (!w || !h) {
      scanMessage.value = 'Initialisation caméra...'
      scanLoop = requestAnimationFrame(tick)
      return
    }
    try {
      if (detector) {
        const codes = await detector.detect(videoEl.value)
        if (codes.length) {
          const text = codes[0].rawValue || 'Achat'
          scanMessage.value = `Acheté ${text}`
          userStore.addScan()
          stopScanner()
          return
        }
      }
      if (window.jsQR) {
        canvas.width = w
        canvas.height = h
        ctx.drawImage(videoEl.value, 0, 0, w, h)
        const imageData = ctx.getImageData(0, 0, w, h)
        const qr = window.jsQR(imageData.data, w, h)
        if (qr && qr.data) {
          scanMessage.value = `Acheté ${qr.data}`
          userStore.addScan()
          stopScanner()
          return
        }
      }
    } catch (err) {
      console.error('Detect error', err)
      scanMessage.value = 'Scan en cours...'
    }
    scanLoop = requestAnimationFrame(tick)
  }
  scanLoop = requestAnimationFrame(tick)
}

async function startScanner() {
  scanMessage.value = 'Initialisation caméra...'
  detector = 'BarcodeDetector' in window ? new window.BarcodeDetector({ formats: ['qr_code', 'ean_13', 'code_128', 'ean_8'] }) : null
  if (!detector) {
    const ok = await ensureJsQr()
    if (!ok) {
      scanMessage.value = 'Scanner non supporté par ce navigateur.'
      return
    }
  }
  const constraintOptions = [
    {
      video: {
        facingMode: { ideal: 'environment' },
        width: { ideal: 1280 },
        height: { ideal: 720 }
      },
      audio: false
    },
    { video: true, audio: false }
  ]
  stream = null
  for (const c of constraintOptions) {
    try {
      stream = await navigator.mediaDevices.getUserMedia(c)
      if (stream) break
    } catch (err) {
      console.warn('Camera constraint failed', err)
    }
  }
  if (!stream) {
    scanMessage.value = 'Caméra refusée'
    return
  }
  const video = videoEl.value
  if (video) {
    video.setAttribute('playsinline', 'true')
    video.setAttribute('webkit-playsinline', 'true')
    video.setAttribute('autoplay', 'true')
    video.muted = true
    video.srcObject = stream
    video.onloadedmetadata = () => {
      scanning.value = true
      scanMessage.value = 'Scan en cours...'
      startScanLoop()
    }
    await video.play().catch(() => {
      scanMessage.value = 'Caméra refusée'
    })
  }
}

onBeforeUnmount(() => {
  stopScanner()
})
</script>

<style scoped>
.section-head h1 { margin: 6px 0; }
.eyebrow {
  font-size: 12px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(195, 213, 198, 0.9);
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
.note {
  background: rgba(255, 255, 255, 0.08);
  padding: 6px 10px;
  border-radius: 999px;
  font-size: 12px;
  color: rgba(246, 236, 240, 0.9);
}
.notes {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.rating-indicator {
  color: #d19c3a;
  font-weight: 700;
}
.bottle-row { gap: 8px; }
.feature-card {
  position: relative;
  background: linear-gradient(135deg, rgba(216, 87, 127, 0.1), rgba(62, 167, 106, 0.08));
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.feature-bottle {
  min-width: 120px;
}
.scanner {
  position: relative;
  width: 100%;
  max-width: 480px;
  aspect-ratio: 4/3;
  border-radius: 16px;
  overflow: hidden;
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.scanner-video {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.scanner-overlay {
  position: absolute;
  inset: 0;
  border: 2px solid rgba(216, 87, 127, 0.7);
  pointer-events: none;
  box-shadow: inset 0 0 0 2px rgba(62, 167, 106, 0.4);
}
</style>
