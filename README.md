# Algorithme KNN

## Membres du groupe

Randy De Wancker  
Théo Canoen  
Florine Defossez  
Léopold Varlamoff  

## Prérequis

- Maven

## Lancer le projet

Pour lancer le projet avec les dernières modifications, vous pouvez lancer le projet depuis la branche [master](https://gitlab.univ-lille.fr/sae302/2022/equipe-I1).

Ensuite, utiliser la commande :

```bash
mvn clean javafx:run
```

pour pouvoir lancer le projet.

## Voir les résultats de tests

Toujours sur la branche master :

```bash
mvn clean test
```

pour lancer les tests.  
Les tests de robustesses prennent du temps, c'est normal, car nous utilisons la [validation croisée d'un contre tous](https://fr.wikipedia.org/wiki/Validation_crois%C3%A9e#Techniques_de_validation)
, ou "leave-one-out cross-validation".