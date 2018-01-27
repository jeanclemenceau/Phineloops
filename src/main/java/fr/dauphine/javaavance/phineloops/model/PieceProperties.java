package fr.dauphine.javaavance.phineloops.model;

/***
 * Stores constant properties of pieces and allow retrieving some of them in a static context (unicode, cardinal points of connections) with keys arguments
 * Identifiers are the numbers of the pieces preceded by a bottom dash.
 */
public enum PieceProperties {
	_0(0),
	_1(3),
	_2(1),
	_3(3),
	_4(0),
	_5(3);

	/**
	 * The number of the maximum orientation a piece can have
	 */
	private final int orientationMax;

	/**
	 * Constructor
	 * Initialize the two properties (orientationMax and nbConnections)
	 * @param orientationMax 
	 * @param nbConnections
	 */
	PieceProperties(int orientationMax) {
		this.orientationMax = orientationMax;
	}

	/**
	 * Get the value of orientationMax property
	 * @return the value of orientationMax property
	 */
	public int getOrientationMax() {
		return orientationMax;
	}

	/**
	 * 
	 * @param num the piece number
	 * @param orientation the orientation for the piece number provided
	 * @return the string which is the Java source code for unicode scheme corresponding of the association of given parameters
	 */
	public static String getUnicode(int num, int orientation) {
		switch (num) {
		case 0:
			return " ";
		case 1:
			if(orientation == 0) return "\u2579";
			if(orientation == 1) return "\u257A";
			if(orientation == 2) return "\u257B";
			if(orientation == 3) return "\u2578";
		case 2:
			if(orientation == 0) return "\u2503";
			if(orientation == 1) return "\u2501";
		case 3:
			if(orientation == 0) return "\u253B";
			if(orientation == 1) return "\u2523";
			if(orientation == 2) return "\u2533";
			if(orientation == 3) return "\u252B";
		case 4:
			return "\u254B";
		case 5:
			if(orientation == 0) return "\u2517";
			if(orientation == 1) return "\u250F";
			if(orientation == 2) return "\u2513";
			if(orientation == 3) return "\u251B";
		default:
			return "\u000F";
		}
	}
	
	/**
	 * Get the image path corresponding of a pair piece number and piece orientation
	 * @param num a piece number
	 * @param orientation a piece orientation
	 * @return a string which is the path to the image file corresponding of parameters
	 */
	public static String getImage(int num, int orientation) {
		switch (num) {
		case 0:
			return " ";
		case 1:
			if(orientation == 0) return "/images/1-0.png";
			if(orientation == 1) return "/images/1-1.png";
			if(orientation == 2) return "/images/1-2.png";
			if(orientation == 3) return "/images/1-3.png";
		case 2:
			if(orientation == 0) return "/images/2-0.png";
			if(orientation == 1) return "/images/2-1.png";
		case 3:
			if(orientation == 0) return "/images/3-0.png";
			if(orientation == 1) return "/images/3-1.png";
			if(orientation == 2) return "/images/3-2.png";
			if(orientation == 3) return "/images/3-3.png";
		case 4:
			return "/images/4-0.png";
		case 5:
			if(orientation == 0) return "/images/5-0.png";
			if(orientation == 1) return "/images/5-1.png";
			if(orientation == 2) return "/images/5-2.png";
			if(orientation == 3) return "/images/5-3.png";
		default:
			return "\u000F";
		}
	}

	/***
	 * Fetch connections available for each cardinal point for a piece depending of its orientation
	 * @param num
	 * @param orientation
	 * @return an array of integers which contains number of connections for a cardinal point.
	 * Index 0 is for the North, index 1 is for the East, index 2 is for the South, index 3 is for the West
	 */
	public static int[] getLinksOnCardinalPoints(int num, int orientation) {
		int[] links = new int[4];
		switch(num) {
		case 0:
			break;
		case 1:
			if(orientation == 0) links[0] = 1;
			if(orientation == 1) links[1] = 1;
			if(orientation == 2) links[2] = 1;
			if(orientation == 3) links[3] = 1;
			break;
		case 2:
			if(orientation == 0) {links[0] = 1; links[2] = 1;}
			if(orientation == 1) {links[1] = 1; links[3] = 1;}
			break;
		case 3:
			if(orientation == 0) {links[0] = 1; links[1] = 1; links[3] = 1;}
			if(orientation == 1) {links[0] = 1; links[1] = 1; links[2] = 1;}
			if(orientation == 2) {links[1] = 1; links[2] = 1; links[3] = 1;}
			if(orientation == 3) {links[0] = 1; links[2] = 1; links[3] = 1;}
			break;
		case 4:
			{links[0] = 1; links[1] = 1; links[2] = 1; links[3] = 1;}
			break;
		case 5:
			if(orientation == 0) {links[0] = 1; links[1] = 1;}
			if(orientation == 1) {links[1] = 1; links[2] = 1;}
			if(orientation == 2) {links[2] = 1; links[3] = 1;}
			if(orientation == 3) {links[3] = 1; links[0] = 1;}
			break;
		default:
		}
		return links;
	}

	/**
	 * Get identifier for a piece number in order to retrieve the orientation max corresponding
	 * @param num
	 * @return
	 */
	public static PieceProperties getIdentifier(int num) {
		switch(num) {
		case 0: return _0;
		case 1: return _1;
		case 2: return _2;
		case 3: return _3;
		case 4: return _4;
		case 5: return _5;
		default: return null;
		}
	}

	/**
	 * Retrieves the piece number max of the project
	 * @return
	 */
	public static int getNumMax() {
		return 5;
	}

}
