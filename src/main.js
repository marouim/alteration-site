import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/styles.css'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const vuetify = createVuetify({
	components,
	directives,
  theme: {
    defaultTheme: 'alteration',
    themes: {
      alteration: {
        dark: true,
        colors: {
          primary: '#8b1e3f',
          secondary: '#3ea76a',
          surface: '#0f0a12',
          background: '#0b0810',
          'amber-accent': '#d8577f',
          info: '#c3d5c6'
        }
      }
    }
  }
})

createApp(App).use(router).use(vuetify).mount('#app')
