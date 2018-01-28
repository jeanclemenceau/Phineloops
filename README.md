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

```java -jar target/phineloops-1.0-jar-with-dependencies. jar```


2. Without graphical user interface:

     * To generate a grid 
     
      ```java -jar target/phineloops-1.0-jar-with-dependencies. jar --generate wxh --output file```
    
      or

      ```java -jar target/phineloops-1.0-jar-with-dependencies. jar -g wxh -o file```

    with *w* the grid width, *h* the grid height and *file* the output file name


    * To verify a grid 
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies. jar --check file```
     
    or
   
   ```java -jar target/phineloops-1.0-jar-with-dependencies. jar -c file```
   
    with *file* the name of the file containing the grid to be tested

    * To solve a grid
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies. jar --solve file --output filesolved```
    
      or

    ```java -jar target/phineloops-1.0-jar-with-dependencies. jar -s file -o filesolved```
    
     with *file* the name of the file containing the grid to solve and *filesolved* the name of the file that will store the resolved grid

________________________________________________________________________________________________________________________________________

Ce projet a été fait en Java et avec le programme de gestion de logiciel Maven (Apache)
Il est nécessaire d'avoir un JRE et Maven d'installer sur son appareil pour exécuter les commandes suivantes.

Toutes les commandes suivantes se font à la racine du projet.

* Pour compiler:

```mvn compile```

* Pour créer une archive java:

```mvn package```

* Pour exécuter:

1. Avec interface graphique:

```java -jar target/phineloops-1.0-jar-with-dependencies.jar```

2. Sans interface graphique:

    * Pour générer une grille 
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar --generate wxh --output file```
    
      ou
      
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar -g wxh -o file```
    
   avec *w* la largeur de la grille, *h* la hauteur de la grille et *file* le nom du fichier de sortie


    * Pour vérifier une grille 
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar --check file```
    
      ou
      
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar -c file```
    
      avec *file* le nom du fichier contenant la grille à tester

    * Pour résoudre une grille
    
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar --solve file --output filesolved```
    
   ou
   
    ```java -jar target/phineloops-1.0-jar-with-dependencies.jar -s file -o filesolved```
    
   avec *file* le nom du fichier contenant la grille à résoudre et *filesolved* le nom du fichier qui stockera la grille résolue
