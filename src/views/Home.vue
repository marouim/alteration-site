<template>
  <v-container fluid class="py-10 px-5 px-md-12">
    <v-sheet class="hero pa-10 pa-md-14" rounded="xl" elevation="12">
      <div class="hero-gradient"></div>
      <div class="hero-grid">
        <div class="hero-copy">
          <div class="hero-brand">
            <img :src="logo" alt="Logo Altération" class="hero-logo" />
            <div>
              <div class="eyebrow">Microbrasserie Altération · Estrie</div>
              <h1>Bières artisanales infusées au moût de raisin.</h1>
            </div>
          </div>
          <p class="text-body-1 text-muted">
            Nous brassons des cuvées vineuses, nées du croisement entre fermentation brassicole et moût de raisins rouges et verts.
            Texture veloutée, acidité fraîche, couleur rubis scintillante : chaque lot est pensé comme une vendange en bouteille.
          </p>
          <div class="hero-actions">
            <v-btn color="primary" size="large" class="mr-4" rounded="xl" to="/nos-bieres">
              Découvrir la carte
            </v-btn>
            <v-btn variant="text" color="secondary" size="large" rounded="xl" to="/qui-sommes-nous">
              Rencontrer l'équipe
            </v-btn>
          </div>
          <div class="hero-tags mt-6">
            <v-chip color="secondary" class="mr-3" variant="flat" label>Moût de raisin</v-chip>
            <v-chip color="primary" class="mr-3" variant="tonal" label>Macération carbonique</v-chip>
            <v-chip color="info" variant="text" label>Élevage barrique</v-chip>
          </div>
        </div>
        <div class="hero-card">
          <v-card class="glass" elevation="8" rounded="xl">
            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-2">
                <span class="text-overline text-muted">Brassin du moment</span>
                <v-chip color="secondary" variant="flat" size="small" label>Vendange hybride</v-chip>
              </div>
              <h3 class="mb-2">Altération #27 — Assemblage Raisin Rubis</h3>
              <p class="text-muted">
                Moût de Grenache noir, fermentation mixte, macération lente sur lies. Tanins satinés, acidité de baies rouges, finale minérale.
              </p>
              <div class="d-flex mt-4">
                <div class="stat">
                  <span class="stat-label">ABV</span>
                  <span class="stat-value">6,1%</span>
                </div>
                <div class="stat">
                  <span class="stat-label">IBU</span>
                  <span class="stat-value">10</span>
                </div>
                <div class="stat">
                  <span class="stat-label">Édition</span>
                  <span class="stat-value">1100 btles</span>
                </div>
              </div>
            </v-card-text>
          </v-card>
        </div>
      </div>
    </v-sheet>

    <section class="mt-12">
      <div class="section-head d-flex align-center justify-space-between mb-6">
        <div>
          <p class="eyebrow">Sélections Altération</p>
          <h2>Nos cuvées signatures</h2>
          <p class="text-muted">Assemblages patients, houblonnages précis et infusion de moût pour une énergie vineuse.</p>
        </div>
        <v-btn variant="text" color="primary" to="/nos-bieres">Voir toute la carte →</v-btn>
      </div>
      <v-row dense>
        <v-col v-for="beer in beers" :key="beer.name" cols="12" md="4">
          <v-card class="beer-card" rounded="xl" elevation="8">
            <div class="beer-accent" :style="{ background: beer.accent }"></div>
            <v-card-text>
              <div class="d-flex align-center justify-space-between mb-1">
                <span class="text-overline text-muted">{{ beer.style }}</span>
                <v-chip color="primary" variant="text" density="compact">{{ beer.abv }}</v-chip>
              </div>
              <h3 class="mb-2">{{ beer.name }}</h3>
              <p class="text-muted mb-3">{{ beer.description }}</p>
              <div class="notes">
                <span class="note" v-for="note in beer.notes" :key="note">{{ note }}</span>
              </div>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </section>

    <section class="mt-14">
      <v-sheet class="process pa-8 pa-md-10" rounded="xl" elevation="10">
        <div class="section-head mb-6">
          <p class="eyebrow">Philosophie maison</p>
          <h2>Altérer, c'est mieux révéler</h2>
          <p class="text-muted">
            Du grain au raisin, chaque étape est ajustée en micro-détails pour délivrer des profils nets et vibrants.
          </p>
        </div>
        <v-row>
          <v-col v-for="step in steps" :key="step.title" cols="12" md="4">
            <div class="process-card">
              <div class="process-icon" :style="{ background: step.tint }">
                <span class="process-emoji">{{ step.emoji }}</span>
              </div>
              <h3 class="mb-2">{{ step.title }}</h3>
              <p class="text-muted">{{ step.text }}</p>
            </div>
          </v-col>
        </v-row>
      </v-sheet>
    </section>

    <section class="mt-14">
      <v-row align="center" class="visit" no-gutters>
        <v-col cols="12" md="7">
          <v-sheet class="pa-10 visit-card" elevation="8" rounded="xl">
            <p class="eyebrow">Taproom & visites</p>
            <h2 class="mb-3">Passez nous voir chez Altération</h2>
            <p class="text-muted mb-6">
              Baril roulant, playlists vinyles, cuvées au moût de raisin à la pression et micro-lots à emporter.
              Ouvert du jeudi au dimanche, réservations conseillées pour les groupes.
            </p>
            <div class="d-flex flex-wrap align-center">
              <v-chip color="secondary" variant="flat" class="mr-3 mb-3" label>Accords fromages</v-chip>
              <v-chip color="primary" variant="tonal" class="mr-3 mb-3" label>Visites de chai</v-chip>
              <v-chip color="info" variant="text" class="mr-3 mb-3" label>Line-up en rotation</v-chip>
            </div>
            <div class="mt-6">
              <v-btn color="primary" rounded="xl" class="mr-3" to="/sinscrire">Réserver un créneau</v-btn>
              <v-btn variant="text" color="secondary" rounded="xl" to="/qui-sommes-nous">Découvrir la brasserie</v-btn>
            </div>
          </v-sheet>
        </v-col>
        <v-col cols="12" md="5" class="visit-visual">
          <div class="visit-visual-inner">
            <div class="halo halo-1"></div>
            <div class="halo halo-2"></div>
            <div class="taproom-label">Altération</div>
            <div class="taproom-badge">Fermentation créative</div>
          </div>
        </v-col>
      </v-row>
    </section>
  </v-container>
</template>

<script setup>
import logo from '../assets/Logo_Alteration_Clr.png'

const beers = [
  {
    name: 'Assemblage Rubis',
    style: 'Ale hybride au moût de Grenache',
    abv: '6,1%',
    description: 'Macération pelliculaire, fruits rouges mûrs et finale saline pour rappeler la vendange.',
    notes: ['Raisin Grenache', 'Fermentation mixte', 'Lies fines'],
    accent: 'linear-gradient(135deg, #9c1b3f, #d8577f)'
  },
  {
    name: 'Verte Vigne',
    style: 'Saison au moût de Muscat',
    abv: '5,6%',
    description: 'Fleurs blanches, peau de citron vert, bouche vive portée par le grain et le raisin.',
    notes: ['Raisin Muscat', 'Fermentation lente', 'Houblons nobles'],
    accent: 'linear-gradient(135deg, #3ea76a, #9ae6b4)'
  },
  {
    name: 'Nocturne Pelliculaire',
    style: 'Stout vinifié en barriques',
    abv: '7,3%',
    description: 'Texture soyeuse, cacao profond et touche de cassis issue d\'une macération courte.',
    notes: ['Barriques chêne', 'Pelliculaire', 'Cacao'],
    accent: 'linear-gradient(135deg, #2a102d, #7c3a7c)'
  }
]

const steps = [
  {
    title: 'Vendange locale',
    text: 'Moût de raisins rouges et verts pressés doucement pour préserver les arômes primaires.',
    emoji: '🍇',
    tint: 'rgba(216, 87, 127, 0.14)'
  },
  {
    title: 'Fermentations vivantes',
    text: 'Cultures maison, pied de cuve et levures indigènes pour lier céréale et fruit.',
    emoji: '🧪',
    tint: 'rgba(62, 167, 106, 0.14)'
  },
  {
    title: 'Élevage patient',
    text: 'Repos en barriques et assemblages précis pour des profils veloutés et vibrants.',
    emoji: '🪵',
    tint: 'rgba(195, 213, 198, 0.16)'
  }
]
</script>

<style scoped>
.hero {
  position: relative;
  overflow: hidden;
  background: radial-gradient(circle at 15% 20%, rgba(216, 87, 127, 0.16), transparent 32%), radial-gradient(circle at 70% 18%, rgba(62, 167, 106, 0.14), transparent 32%), linear-gradient(120deg, #120912 0%, #0b0810 100%);
  border: 1px solid rgba(255, 255, 255, 0.06);
}
.hero-gradient {
  position: absolute;
  inset: 0;
  background: linear-gradient(180deg, rgba(139, 30, 63, 0.12), rgba(11, 8, 16, 0.88));
  pointer-events: none;
}
.hero-grid {
  position: relative;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 32px;
  align-items: center;
}
.hero-copy h1 {
  font-size: clamp(32px, 4vw, 46px);
  margin: 8px 0 14px;
}
.hero-brand {
  display: grid;
  grid-template-columns: auto 1fr;
  gap: 14px;
  align-items: center;
}
.hero-logo {
  width: min(240px, 42vw);
  height: min(240px, 42vw);
  object-fit: contain;
  filter: drop-shadow(0 10px 24px rgba(0, 0, 0, 0.3));
}
.eyebrow {
  font-size: 12px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(195, 213, 198, 0.9);
}
.hero-actions { display: flex; flex-wrap: wrap; align-items: center; }
.hero-tags .v-chip { backdrop-filter: blur(4px); }
.hero-card { position: relative; z-index: 1; }
.glass {
  background: rgba(18, 10, 18, 0.82);
  border: 1px solid rgba(255, 255, 255, 0.05);
  backdrop-filter: blur(10px);
}
.stat { margin-right: 28px; }
.stat:last-child { margin-right: 0; }
.stat-label { display: block; color: rgba(159, 178, 211, 0.8); font-size: 12px; }
.stat-value { font-weight: 700; font-size: 18px; }
.section-head h2 { margin: 4px 0; font-size: clamp(26px, 3vw, 36px); }
.beer-card {
  position: relative;
  background: #0f0a12;
  border: 1px solid rgba(255, 255, 255, 0.06);
  overflow: hidden;
}
.beer-accent {
  position: absolute;
  inset: 0 0 50% 0;
  opacity: 0.22;
}
.notes {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.note {
  background: rgba(255, 255, 255, 0.08);
  padding: 8px 12px;
  border-radius: 999px;
  font-size: 13px;
  color: rgba(246, 236, 240, 0.9);
}
.process {
  background: linear-gradient(135deg, rgba(216, 87, 127, 0.08), rgba(15, 10, 18, 0.94));
  border: 1px solid rgba(255, 255, 255, 0.05);
}
.process-card {
  padding: 20px;
  border-radius: 18px;
  background: rgba(18, 10, 18, 0.9);
  border: 1px solid rgba(255, 255, 255, 0.06);
  height: 100%;
}
.process-icon {
  width: 46px;
  height: 46px;
  border-radius: 12px;
  display: grid;
  place-items: center;
  margin-bottom: 14px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.25);
}
.process-emoji { font-size: 20px; }
.visit {
  gap: 24px;
}
.visit-card {
  background: rgba(16, 10, 18, 0.9);
  border: 1px solid rgba(255, 255, 255, 0.06);
}
.visit-visual {
  position: relative;
  min-height: 280px;
}
.visit-visual-inner {
  position: relative;
  height: 100%;
  border-radius: 20px;
  overflow: hidden;
  background: radial-gradient(circle at 60% 40%, rgba(216, 87, 127, 0.25), transparent 42%), radial-gradient(circle at 30% 70%, rgba(62, 167, 106, 0.2), transparent 36%), linear-gradient(145deg, #140914, #0b0810);
  border: 1px solid rgba(255, 255, 255, 0.08);
}
.halo {
  position: absolute;
  width: 220px;
  height: 220px;
  border-radius: 50%;
  filter: blur(60px);
}
.halo-1 { background: rgba(216, 87, 127, 0.48); top: -40px; left: -60px; }
.halo-2 { background: rgba(62, 167, 106, 0.38); bottom: -40px; right: -60px; }
.taproom-label {
  position: absolute;
  top: 26px;
  left: 26px;
  font-size: 18px;
  font-weight: 700;
  letter-spacing: 0.14em;
  text-transform: uppercase;
  color: #f6ecf0;
}
.taproom-badge {
  position: absolute;
  bottom: 24px;
  right: 24px;
  padding: 12px 16px;
  border-radius: 14px;
  background: rgba(17, 9, 18, 0.75);
  border: 1px solid rgba(255, 255, 255, 0.1);
  color: rgba(246, 236, 240, 0.92);
  font-weight: 600;
}
@media (max-width: 960px) {
  .hero { padding: 32px; }
  .hero-actions { gap: 12px; }
  .visit { flex-direction: column; }
}
</style>
