# AMT-Carfleet
## Introduction
**AMT-Carfleet** est une application Java de désérialisation de données JSON pour le projet [Carfleet](https://www.carfleet.ch/). 
Ce travail est réalisé dans le cadre d'un laboratoire du module AMT du Bachelor Informatique est Systèmes de Communication.

Les décisions prises le long du développement sont détaillées dans le wiki de ce dépôt.

Des exemples de données fournies par Carfleet sont disponible dans le dossier data.

## Tests
Les tests unitaires sont réalisés avec JUnit. Pour les exécuter, il suffit de lancer la commande suivante dans le dossier du projet:
```
mvn test
```
### Cas limites
Des tests spécifiques ont été réalisés pour tester que les fichiers json reçus soient correctement structurés. 
Nous avons donc prévu des tests unitaires pour nos classes driver et car, en testant plusieurs cas de json non conforme.
Nous contrôlons que les json reçu n'ont pas de d'objet manquant, que ce soit par exemple le Board, les Items, le champ Dataou encore
les clés-valeur.

