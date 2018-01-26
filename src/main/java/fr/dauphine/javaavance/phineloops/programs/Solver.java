package fr.dauphine.javaavance.phineloops.programs;

import java.util.List;
import java.util.Set;

import java.util.Iterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.model.Piece;
import fr.dauphine.javaavance.phineloops.model.PieceProperties;

public class Solver {


	private static class Element {
		private int x, y, num, orientation;


		public Element(int x, int y, int num, int orientation) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.orientation = orientation;
		}
	}


	public static boolean solve(Grid g, Piece root) {
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
				Iterator it = visited.iterator();
				while(it.hasNext()&& it.next()!=current)
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
