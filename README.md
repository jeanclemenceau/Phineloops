# Phineloops project

## Betty BISMUTH & Jean CLEMENCEAU

### M1 MIAGE Université Paris-Dauphine - Advanced Java Course

This project was made using Java programming language and Apache Maven software project management. 
You must have installed on your device a JRE (Jave Runtime Environment) and Maven in order to run the following commands.

All the commands take place at the root of the project.

* To compile:

```mvn compile```

* To create a java archive (.jar):
    
```mvn package```

* To execute:

1. With graphical user interface:

```java -jar target/phineloops-1.0-jar-with-dependencies. jar --visualize ```

   *--visualize* can be remplaced by *-v* : this non mandatory option can also be used with generation and solving commands.

2. Without graphical user interface:

     * To generate a grid 
     
      ```java -jar target/phineloops-1.0-jar-with-dependencies. jar --generate wxh --output file [--nbcc, -x nb] [--visualize, -v]```
    
      or

      ```java -jar target/phineloops-1.0-jar-with-dependencies. jar -g wxh -o file [--nbcc, -x nb] [--visualize, -v]```

    with *w* the grid width, *h* the grid height and *file* the output file name. *nb* the number of connected components if option *--nbcc* is specified.


    * To verify a grid 
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies. jar --check file```
     
    or
   
   ```java -jar target/phineloops-1.0-jar-with-dependencies. jar -c file```
   
    with *file* the name of the file containing the grid to be tested

    * To solve a grid
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies. jar --solve file --output filesolved [--choice, -ch choice] [--visualize, -v]```
    
      or

    ```java -jar target/phineloops-1.0-jar-with-dependencies. jar -s file -o filesolved [--choice, -ch choice] [--visualize, -v]```
    
     with *file* the name of the file containing the grid to solve and *filesolved* the name of the file that will store the resolved grid and *choice* a non mandatory option (*--choice*) which represents the choice of the solving method (0 or 1) 0 for solving using random piece choice and 1 for solving using fixed pieces first, by default the choice is 1.

________________________________________________________________________________________________________________________________________

Ce projet a été fait en Java et avec le programme de gestion de logiciel Maven (Apache)
Il est nécessaire d'avoir un JRE et Maven d'installés sur son appareil pour exécuter les commandes suivantes.

Toutes les commandes suivantes se font à la racine du projet.

* Pour compiler:

```mvn compile```

* Pour créer une archive java:

```mvn package```

* Pour exécuter:

1. Avec interface graphique:

```java -jar target/phineloops-1.0-jar-with-dependencies.jar --visualize```

*--visualize* peut être remplacer par *-v* : ce paramètre non obligatoire peut également être utilisé avec les commandes de génération et de résolution.

2. Sans interface graphique:

    * Pour générer une grille 
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar --generate wxh --output file [--nbcc, -x nb] [--visualize, -v]```
    
      ou
      
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar -g wxh -o file [--nbcc, -x nb] [--visualize, -v]```
    
   avec *w* la largeur de la grille, *h* la hauteur de la grille et *file* le nom du fichier de sortie, *nb* le nombre de composantes connexes si le paramètre optionnel *--nbcc* est spécifié.


    * Pour vérifier une grille 
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar --check file```
    
      ou
      
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar -c file```
    
      avec *file* le nom du fichier contenant la grille à tester

    * Pour résoudre une grille
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar --solve file --output filesolved [--choice, -ch choice] [--visualize, -v]```
    
   ou
   
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar -s file -o filesolved [--choice, -ch choice] [--visualize, -v]```
    
   avec *file* le nom du fichier contenant la grille à résoudre et *filesolved* le nom du fichier qui stockera la grille résolue et *choice* une valeur optionnelle qui représente le choix de la méthode de résolution (0 ou 1) 0 pour résoudre en utilisant le choix aléatoire de pièce et 1 pour résoudre en prenant d'abord les pièces fixées, par défaut le choix est 1.
