package fr.dauphine.javaavance.phineloops.programs;

import java.util.List;
import java.util.Random;
import java.util.Iterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;

public class Solver {

	private static class Element {
		private int x, y, orientation;

		public Element(int x, int y, int num, int orientation) {
			this.x = x;
			this.y = y;
			this.orientation = orientation;
		}
	}

	/**
	 * Method that solves the grid. It starts the algorithm by an odd piece of the
	 * grid and checks the other pieces from the left to the right
	 * @param  Grid g             the grid to solve
	 * @return      true if the grid is solved
	 */
	public static boolean solveOdd(Grid g) {
		Piece root;
		Random rx = new Random();
		Random ry = new Random();
		root = g.getPieces()[rx.nextInt(g.getWidth()+1)][ry.nextInt(g.getHeight()+1)];
		Deque<Element> stack = new ArrayDeque<Element>();
		List<Piece> visited = new ArrayList<>();
		Piece current;
		Piece toStack;
		Element tmp;

		for(int i = root.getOrientationMax(); i>=0; i--) {
			stack.push(new Element(root.getX(), root.getY(), root.getNum(), (root.getOrientation()+i)%(root.getOrientationMax()+1)));
		}

		while(!stack.isEmpty()) {
			tmp = stack.pop();

			current = g.getPieces()[tmp.x][tmp.y];
			current.setOrientation(tmp.orientation);

			if(visited.contains(current)){
				Iterator<Piece> it = visited.iterator();
				while(it.hasNext()&& it.next()!=current);
				it.remove();
				while(it.hasNext()){
					it.next();
					it.remove();
				}
			}

			if(Checker.check(g)) return true;

			if(g.isAllowedOrientation(current, visited)){
				if(!visited.contains(current)) visited.add(current);
				toStack = getNext(g, current);
				for(int i = toStack.getOrientationMax(); i>=0; i--) {
					stack.push(new Element(toStack.getX(), toStack.getY(), toStack.getNum(), (toStack.getOrientation()+i)%(toStack.getOrientationMax()+1)));
				}
			}
		}

		return false;
	}

	public static Piece getNext(Grid g, Piece p) {
		int x = p.getX(), y = p.getY();
		if(p.getX()==g.getWidth()-1) {
			x = 0;
			if(p.getY()==g.getHeight()-1) y = 0;
			else y++;
		}
		else x++;
		return g.getPieces()[x][y];
	}

	public static boolean solve2(Grid g){
		while(!Checker.check(g))
			g.shuffle();
		return true;
	}

}
