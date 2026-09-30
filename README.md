# Video Service - TP GraphQL

Mini back-end Spring Boot qui expose une API GraphQL pour gérer des créateurs et leurs vidéos.

## Technologies
Spring Boot, Spring for GraphQL, Spring Data JPA, H2, Lombok, ModelMapper, WebSocket

## Lancer le projet
1. Cloner le dépôt
2. Lancer `VideoServiceApplication`
3. Ouvrir http://localhost:8090/graphiql

## Fonctionnalités
- **Queries** : `videoList`, `videoById`, `creatorList`, `creatorById`
- **Mutations** : `saveVideo`, `saveCreator`
- **Subscription** : `notifyVideoChange`