export const retailers = [
  {
    id: 'ferme-guyon',
    name: 'La ferme Guyon',
    address: '1001 Rue Patrick-Farrar',
    city: 'Chambly, QC',
    postalCode: 'J3L 6R2',
    phone: '(450) 658-1010',
    description: 'Marché fermier et épicerie fine avec section produits locaux.',
    lat: 45.4496,
    lng: -73.2963,
    mapPosition: { x: 58, y: 42 }
  },
  {
    id: 'marche-village',
    name: 'Épicerie Marché du Village',
    address: '12 Rue du Marché',
    city: 'Bromont, QC',
    postalCode: 'J2L 1K4',
    phone: '(450) 534-1212',
    description: 'Épicerie de quartier axée sur les produits du terroir et bières de micro.',
    lat: 45.3158,
    lng: -72.7201,
    mapPosition: { x: 46, y: 66 }
  },
  {
    id: 'iga-bromont',
    name: 'IGA Bromont',
    address: '19 Boul. de Bromont',
    city: 'Bromont, QC',
    postalCode: 'J2L 2J1',
    phone: '(450) 534-4636',
    description: 'IGA avec sélection de microbrasseries et produits locaux.',
    lat: 45.3177,
    lng: -72.6657,
    mapPosition: { x: 52, y: 64 }
  }
]

export function getRetailersByIds(ids = []) {
  return retailers.filter((retailer) => ids.includes(retailer.id))
}

export function getRetailerById(id) {
  return retailers.find((retailer) => retailer.id === id)
}
