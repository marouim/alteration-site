<template>
  <div class="overlay" @click.self="close">
    <aside class="sidebar">
      <button class="close" @click="close" aria-label="Fermer">✕</button>
      <nav>
        <ul>
          <li><router-link to="/qui-sommes-nous" @click.native="close">Qui sommes nous</router-link></li>
          <li><router-link to="/nos-bieres" @click.native="close">Nos bières</router-link></li>
          <li><router-link to="/sinscrire" @click.native="close">S'inscrire</router-link></li>
        </ul>
      </nav>
    </aside>
  </div>
</template>

<script setup>
import { defineEmits } from 'vue'
const emit = defineEmits(['close'])
function close() { emit('close') }
</script>

<style scoped>
.overlay {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.4);
  display: flex;
  z-index: 30;
}
.sidebar {
  width: 260px;
  background: #fff;
  height: 100%;
  padding: 1rem;
  box-shadow: 2px 0 8px rgba(0,0,0,0.12);
}
.close {
  background: transparent;
  border: none;
  font-size: 1.2rem;
  float: right;
}
nav ul { list-style: none; padding: 1rem 0; margin: 0 }
nav li { margin: 0.6rem 0 }
nav a { color: #1f2937; text-decoration: none }
</style>
