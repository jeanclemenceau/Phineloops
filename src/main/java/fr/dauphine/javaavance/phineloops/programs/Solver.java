package fr.dauphine.javaavance.phineloops.programs;

import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class Solver {
	
	private static class Node {
		private Piece element;
		private int currentAllocation;
		private Node father;
		private Deque<Integer> possibleAllocations = new ArrayDeque<Integer>();
		
		public Node(Piece p, Node f) {
			element = p;
			currentAllocation = p.getOrientation();
			father = f;
			for(int i = currentAllocation-1 % p.getOrientationMax()+1; i <= currentAllocation; i = i-1 % p.getOrientationMax()+1)
				possibleAllocations.push(i);
		}
	}
	
	public static boolean solve(Grid g, Piece root) {
		boolean solved = false;
		List<Node> searchTree = new ArrayList<Node>();
		
		searchTree.add(new Node(root, null));
		
		
		return solved;
	}
}
