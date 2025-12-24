<template>
  <v-container class="py-10 px-5 px-md-12">
    <div class="section-head mb-6">
      <p class="eyebrow">Équipe</p>
      <h1>Qui sommes nous</h1>
      <p class="text-muted">Deux artisans derrière Altération, à l'intersection du grain et du raisin.</p>
    </div>

    <v-row dense>
      <v-col cols="12" md="6">
        <v-card class="profile-card" elevation="10" rounded="xl">
          <v-card-text>
            <p class="text-overline text-muted mb-2">Maître brasseur</p>
            <h3 class="mb-1">Guillaume Hébert</h3>
            <p class="text-muted">Orchestration des fermentations mixtes, sélection des céréales et des moûts de raisins rouges et verts.</p>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="6">
        <v-card class="profile-card" elevation="10" rounded="xl">
          <v-card-text>
            <p class="text-overline text-muted mb-2">Co-propriétaire</p>
            <h3 class="mb-1">Louis-Phillipe Rivest</h3>
            <p class="text-muted">Direction des assemblages, expérience en salle et liens avec les vignerons partenaires.</p>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.section-head h1 {
  margin: 6px 0;
}
.profile-card {
  background: rgba(16, 10, 18, 0.88);
  border: 1px solid rgba(255, 255, 255, 0.06);
}
.eyebrow {
  font-size: 12px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(195, 213, 198, 0.9);
}
</style>
