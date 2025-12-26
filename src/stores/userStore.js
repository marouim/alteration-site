import { computed, reactive, watch } from 'vue'

const state = reactive({
  user: null, // { username }
  ratings: {}, // { [username]: { [beerSlug]: { stars, comment } } }
  scans: {} // { [username]: numberOfScans }
})

// Load from localStorage if exists
const saved = localStorage.getItem('alteration-user-store')
if (saved) {
  try {
    const parsed = JSON.parse(saved)
    state.user = parsed.user || null
    state.ratings = parsed.ratings || {}
    state.scans = parsed.scans || {}
  } catch (err) {
    console.warn('Failed to parse user store', err)
  }
}

watch(
  () => state,
  () => {
    localStorage.setItem(
      'alteration-user-store',
      JSON.stringify({ user: state.user, ratings: state.ratings, scans: state.scans })
    )
  },
  { deep: true }
)

export function useUserStore() {
  const isAuthenticated = computed(() => !!state.user)
  const username = computed(() => state.user?.username || '')

  function computePoints(forUser) {
    if (!forUser) return 0
    const userRatings = state.ratings[forUser] || {}
    const ratingPoints = Object.values(userRatings).reduce((acc, rating) => {
      const hasStars = (rating?.stars || 0) > 0
      const hasComment = !!(rating?.comment && rating.comment.trim())
      return acc + (hasStars ? 5 : 0) + (hasComment ? 10 : 0)
    }, 0)
    const scanPoints = (state.scans[forUser] || 0) * 15
    return ratingPoints + scanPoints
  }

  function login(usernameValue) {
    state.user = { username: usernameValue }
    if (!state.ratings[usernameValue]) {
      state.ratings[usernameValue] = {}
    }
    if (state.scans[usernameValue] === undefined) {
      state.scans[usernameValue] = 0
    }
  }

  function logout() {
    state.user = null
  }

  function saveRating(beerSlug, stars, comment) {
    if (!state.user) return
    const userRatings = state.ratings[state.user.username] || {}
    userRatings[beerSlug] = { stars, comment }
    state.ratings[state.user.username] = userRatings
  }

  function addScan() {
    if (!state.user) return
    const current = state.scans[state.user.username] || 0
    state.scans[state.user.username] = current + 1
  }

  function getScanCount(forUser) {
    if (!forUser) return 0
    return state.scans[forUser] || 0
  }

  function levelFor(points) {
    if (points >= 201) return 'Niveau Privilège'
    if (points >= 101) return 'Niveau Élite'
    if (points >= 51) return 'Niveau Expérimenté'
    return 'Niveau Initié'
  }

  function getRating(beerSlug) {
    if (!state.user) return null
    return state.ratings[state.user.username]?.[beerSlug] || null
  }

  return {
    state,
    isAuthenticated,
    username,
    userPoints: computed(() => computePoints(state.user?.username)),
    level: computed(() => levelFor(computePoints(state.user?.username))),
    scans: computed(() => getScanCount(state.user?.username)),
    login,
    logout,
    saveRating,
    addScan,
    getScanCount,
    getRating,
    computePoints,
    levelFor
  }
}
