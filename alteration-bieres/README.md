# alteration-bieres

Microservice Quarkus qui expose les données de bières et points de vente.

## Endpoints
- `GET /beers` : liste des bières
- `GET /beers/{slug}` : détail d’une bière
- `GET /retailers` : liste des points de vente
- `GET /retailers/{id}` : détail d’un point de vente

## Développement local
```bash
mvn quarkus:dev
```

## Build (S2I OpenShift)
Le répertoire `.s2i/environment` configure un build Maven (`package -DskipTests`).

Exemple (S2I ubi8/openjdk-17) :
```bash
s2i build . registry.access.redhat.com/ubi8/openjdk-17 alteration-bieres:latest
```
