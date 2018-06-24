Dans java build path il faut ajouter "ressources" au sources.
Si besoin de modifier le classPath pour log4j les deux seuls librairies nécaissaires sont log4j-api2.11.0.jar et log4j-core-2.11.0.jar

Configuration et démarage du jeux :

En premier ce sont les arguments qui sont tester

args 0 -> le nombre de case 
args 1 -> le nombre d'essai
args 2 -> le nombre de chiffre utilisable en mode MasterMind celui doit être compris entre 4 et 9 sans quoi il sera considéré non valide
args 3 -> true pour le mode debug tout autre entrée sera considéré comme un false.

Si un ou plusieur argument sont invalide le jeux essayera de démarer en utilisant le fichier properties pour se configurer.

Au cas ou le fichier serait vide voici les parametre a renseigner :
nbCase=

nbEssai=

nbMaster=

debug=

 Si le fichier properties ne permet pas non plus de configurer le jeux correctement ( vide ou donnée non valide),
le jeux démarera en utilisant des valeurs par défaut :
nbCase sera 4
nbEssai 5
nbMaster 9
et debug sera false.

