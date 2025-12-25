package com.alteration;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DataStore {

  private final List<Retailer> retailers = List.of(
    new Retailer("ferme-guyon", "La ferme Guyon", "1001 Rue Patrick-Farrar", "Chambly, QC", "J3L 6R2", "(450) 658-1010", "Marché fermier et épicerie fine avec section produits locaux.", 45.4496, -73.2963),
    new Retailer("marche-village", "Épicerie Marché du Village", "12 Rue du Marché", "Bromont, QC", "J2L 1K4", "(450) 534-1212", "Épicerie de quartier axée sur les produits du terroir et bières de micro.", 45.3158, -72.7201),
    new Retailer("iga-bromont", "IGA Bromont", "19 Boul. de Bromont", "Bromont, QC", "J2L 2J1", "(450) 534-4636", "IGA avec sélection de microbrasseries et produits locaux.", 45.3177, -72.6657)
  );

  private final List<Beer> beers = List.of(
    new Beer(
      "assemblage-rubis-27",
      "Assemblage Rubis 27",
      "moment",
      "Ale hybride · Moût de Grenache",
      "6,1%",
      "Lot 1100 btles",
      "Macération pelliculaire, fruits rouges mûrs, finale saline qui rappelle la vendange.",
      List.of("Grenache", "Fermentation mixte", "Lies fines"),
      7.5,
      "linear-gradient(135deg, #d8577f, #3ea76a)",
      1200,
      320,
      List.of("Barbe Rouge", "Mosaic Cryo", "Strisselspalt"),
      "Culture maison mixte (levure ale + levures indigènes du moût)",
      "Macération pelliculaire sur Grenache, fermentation inox à basse température puis refermentation bouteille.",
      List.of("ferme-guyon", "marche-village", "iga-bromont")
    ),
    new Beer(
      "verte-vigne",
      "Verte Vigne",
      "signature",
      "Saison au moût de Muscat",
      "5,6%",
      null,
      "Fleurs blanches, peau de citron vert, bouche vive portée par le grain et le raisin.",
      List.of("Muscat", "Fermentation lente", "Houblons nobles"),
      6.8,
      "linear-gradient(135deg, #3ea76a, #9ae6b4)",
      600,
      180,
      List.of("Hallertau Mittelfrüh", "Saaz", "Nelson Sauvin"),
      "Souche saison belge + flore indigène du moût de Muscat",
      "Fermentation mixte en cuve inox, élevage sur lies fines puis assemblage avec moût frais.",
      List.of("ferme-guyon", "marche-village")
    ),
    new Beer(
      "nocturne-pelliculaire",
      "Nocturne Pelliculaire",
      "signature",
      "Stout vinifié",
      "7,3%",
      null,
      "Texture soyeuse, cacao profond et touche de cassis issue d’une macération courte.",
      List.of("Barriques chêne", "Pelliculaire", "Cacao"),
      7.9,
      "linear-gradient(135deg, #2a102d, #7c3a7c)",
      500,
      140,
      List.of("East Kent Goldings", "Fuggle"),
      "Levure anglaise + Brettanomyces dosée pour garder la gourmandise",
      "Brassage haute densité, macération pelliculaire courte puis passage en barriques de chêne toast léger.",
      List.of("iga-bromont", "ferme-guyon")
    ),
    new Beer(
      "assemblage-rubis",
      "Assemblage Rubis",
      "signature",
      "Ale hybride au moût de Grenache",
      "6,1%",
      null,
      "Macération pelliculaire, fruits rouges mûrs et finale saline pour rappeler la vendange.",
      List.of("Grenache", "Fermentation mixte", "Lies fines"),
      7.5,
      "linear-gradient(135deg, #9c1b3f, #d8577f)",
      800,
      260,
      List.of("Strisselspalt", "Barbe Rouge"),
      "Culture maison mixte issue de nos cuves d’assemblage",
      "Macération 36h sur marc de Grenache, fermentation inox puis élevage court en barrique neutre.",
      List.of("marche-village", "iga-bromont")
    ),
    new Beer(
      "ambree-vendange-tardive",
      "Ambrée Vendange Tardive",
      "limited",
      "Amber au moût de Pinot Gris",
      "6,4%",
      null,
      "Notes d’abricot sec, miel clair et légère tension minérale.",
      List.of(),
      6.5,
      "linear-gradient(135deg, #f0a35a, #d8577f)",
      300,
      96,
      List.of("Tettnang", "Loral"),
      "Levure neutre de fermentation haute pour laisser parler le moût",
      "Presse directe sur marc de Pinot Gris, fermentation lente en inox et garde à froid.",
      List.of("ferme-guyon")
    ),
    new Beer(
      "cuvee-florale",
      "Cuvée Florale",
      "limited",
      "Blanche au moût de Riesling",
      "4,9%",
      null,
      "Zeste de citron, fleurs blanches, bulle fine et sèche.",
      List.of(),
      6.2,
      "linear-gradient(135deg, #8fb3ff, #b6e0fe)",
      420,
      210,
      List.of("Hallertau Blanc", "Mandarina Bavaria"),
      "Levure de blé + flore de Riesling pour une aromatique florale",
      "Fermentation mixte à basse température, repos sur lies puis refermentation bouteille.",
      List.of("marche-village", "iga-bromont")
    ),
    new Beer(
      "sureau-en-barrique",
      "Sureau en Barrique",
      "limited",
      "Saison vieillie",
      "6,0%",
      null,
      "Fleurs de sureau, vin jaune et finale épicée.",
      List.of(),
      6.9,
      "linear-gradient(135deg, #d6c2ff, #8b5cf6)",
      260,
      15,
      List.of("Styrian Goldings", "Saaz"),
      "Levure farmhouse + Brett léger pour la complexité",
      "Saison élevée 5 mois en barriques avec infusions successives de fleurs de sureau.",
      List.of("iga-bromont")
    ),
    new Beer(
      "collab-presse-directe",
      "Collab Presse Directe",
      "limited",
      "Gueuze au moût de Gamay",
      "5,7%",
      null,
      "Acidité vineuse, groseille et pointe brettée élégante.",
      List.of(),
      7.1,
      "linear-gradient(135deg, #f472b6, #d946ef)",
      340,
      112,
      List.of("Hersbrucker vieilli", "Triskel"),
      "Culture de gueuze + levures indigènes du Gamay pressé",
      "Presse directe du Gamay, fermentation spontanée partielle puis assemblage en cuve pour garder la fraîcheur.",
      List.of("ferme-guyon", "iga-bromont")
    )
  );

  public List<Beer> getBeers() {
    return beers;
  }

  public Optional<Beer> getBeerBySlug(String slug) {
    return beers.stream().filter(b -> b.slug().equals(slug)).findFirst();
  }

  public List<Retailer> getRetailers() {
    return retailers;
  }

  public Optional<Retailer> getRetailerById(String id) {
    return retailers.stream().filter(r -> r.id().equals(id)).findFirst();
  }
}
