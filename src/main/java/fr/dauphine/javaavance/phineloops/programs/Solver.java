package fr.dauphine.javaavance.phineloops.programs;

import java.util.List;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;
import fr.dauphine.javaavance.phineloops.model.PieceProperties;

public class Solver {


	private static class Node {
		private Piece element;
		private int orientation;

		public Node(Piece p, Node f) {
			element = p;

			 for(int i = p.getOrientationMax(); i>=0; i--){
			 	possibleAllocations.push((p.getOrientation()+i)%(p.getOrientationMax()+1));
			 }
		}
	}

	public static boolean solve2(Grid g){
		while(!Checker.check(g))
			g.shuffle();
		return true;
	}

	public static boolean solve(Grid g, Piece root) {
		Node current = new Node(root,null);
		Node tmp;
		List<Node> searchTree = new ArrayList<Node>();
		List<Piece> allPieces = new ArrayList<Piece>();
		Piece[] neighbours;
		searchTree.add(current);


		// construct a list of all the pieces in the grid
		for(Piece[] subTab : g.getPieces())
			for(Piece p : subTab)
				allPieces.add(p);

		while(!allPieces.isEmpty()){
			System.out.println("Je rentre dans le 1er while");
			while(!current.possibleAllocations.isEmpty()){
				System.out.println("Le current est "+ current.element.toString());
				System.out.println("Il y a "+allPieces.size()+" elements dans allPieces");
				if(current.element.getFixed()){
					current.element.setFixed(false);
					while(!current.possibleAllocations.isEmpty()){
						if(!g.isAllowedOrientation(current.element.getX(), current.element.getY(), current.element) || !connectionsWithFatherAreOK(current)){
							current.element.pivot();
							current.possibleAllocations.pop();
							break;
						}
					}
				}
				else{
					if(!g.isAllowedOrientation(current.element.getX(), current.element.getY(), current.element)){
						System.out.println("Il y a un pb Allowed");
						current.possibleAllocations.pop();
						System.out.println("Il reste "+current.possibleAllocations.size()+ " orientations possibles");
						current.element.pivot();
					}
					else if(!connectionsWithFatherAreOK(current)){
						System.out.println("Il y a un pb Father");
						current.possibleAllocations.pop();
						System.out.println("Il reste "+current.possibleAllocations.size()+ " orientations possibles");
						current.element.pivot();
					}
					else{
						System.out.println("on continue");
						current.element.setFixed(true);
						allPieces.remove(current.element);
						if(Checker.check(g))
							return true;
						else
							System.out.println("cherchons les voisins");
							neighbours = g.getPieceNeighbours(current.element);
							tmp = current;
							for(Piece n : neighbours){
								if(n!=null && !n.getFixed()){
									current = new Node(n,tmp);
									searchTree.add(current);
									break;
								}
							}
					}
				}
			}

			if(!current.element.equals(root)){
				System.out.println("hello");
				current = current.father;
			}
			else{
				System.out.println("coucou, le current est " + current.element.toString());
				return false;
			}
		}
		System.out.println("salut");
		return false;
	}

	//PB ACTUEL : isAllowedOrientation ne donne pas un bon resultat, quand je fais
	//s'afficher les pieces possibles, certaines sont oubliées
	//re tester avec testg qui est une grille 2x2

	//prendre une piece et lui choisir une affectation (on commence par l'orientation actuelle)
	//vérifier 1) que cette orientation est autorisée par rapport à la config de la grille
	//					sinon : pivoter / si toutes les positions essayées : revenir en arriere et si c'est root :false
	//				2) qu'elle permet le bon nb de connexions avec son pere
	//					sinon : pivoter / si toutes les positions essayées : revenir en arriere
	//				3) si la grille dans la position actuelle est solved : true
	//				si pas 3 mais oui 1 et 2 : prendre la liste des voisins non encore traités
	//					si plus de voisins : true
	//					recuperer le premier : recommencer


	public static boolean connectionsWithFatherAreOK(Node n){
		// n is the root and has no father
		if(n.father == null)
			return true;
		else {
			Piece father = n.father.element;
			Piece son = n.element;
			int xf = father.getX();
			int yf = father.getY();
			int xs = son.getX();
			int ys = son.getY();

			//father and son are on the same column
			if(xf == xs){
				// father on son's top
				if(yf<ys)
					return PieceProperties.getLinksOnCardinalPoints(father.getNum(),father.getOrientation())[2] == PieceProperties.getLinksOnCardinalPoints(son.getNum(),son.getOrientation())[0];
				// father on son's bottom
				else
					return PieceProperties.getLinksOnCardinalPoints(father.getNum(),father.getOrientation())[0] == PieceProperties.getLinksOnCardinalPoints(son.getNum(),son.getOrientation())[2];
			}
			//father and son are on the same line
			else{
				// father on son's left
				if(xf<xs)
					return PieceProperties.getLinksOnCardinalPoints(father.getNum(),father.getOrientation())[1] == PieceProperties.getLinksOnCardinalPoints(son.getNum(),son.getOrientation())[3];
				// father on son's bottom
				else
					return PieceProperties.getLinksOnCardinalPoints(father.getNum(),father.getOrientation())[3] == PieceProperties.getLinksOnCardinalPoints(son.getNum(),son.getOrientation())[1];
			}
		}
	}

}
