//7.6 Jigsaw: implement an N*N jigsaw puzzle. Design the data structures and explain an algorithm to solve the puzzle. You can assume that you have a fitsWith method which, when passed to puzzle edges, return true if the two edges belong together.

public enum Orientation {
	LEFT, TOP, RIGHT, BOTTOM;
	public Orientation getOpposite() {
		switch(this) {
			case LEFT: return RIGHT;
			case RIGHT: return LEFT;
			case TOP: return BOTTOM;
			case BOTTOM: return TOP;
			default: return null;
		}
	}
}

public enum Shape {
	INNER, OUTER, FLAT;
	public Shape getOpposite() {
		switch(this) {
			case INNER: return OUTER;
			case OUTER: return INNER;
			default: return null;
		}
	}
}

public class Puzzle {
	private LinkedList<Piece> pieces; //remaining pieces to put away
	private Piece[][] solution;
	private int size;

	public Puzzle(int size, LinkedList<Piece> pieces) {...}
	private void setEdgeInSolution(LinkedList<Piece> pieces, Edge edge, int row, int column, Orientation orientation) {
		Piece piece = edge.getParentPiece();
		piece.setEdgeAsOrientation(edge, orientation);
		pieces.remove(piece);
		solution[row][column] = piece;
	}


	private boolean fitNextEdge(LinkedList<Piece> piecesToSearch, int row, int col) {
		if (row == 0 && column == 0) {
			Piece p = piecesToSearch.remove();
			orientTopLeftCorner(p);
			solution[0][0] = p;
		}
		else {
			Piece pieceToMatch = column == 0 ? solution[row - 1][0] : solution[row][column - 1];
			Orientation orientationToMatch = column == 0 ? Orientation.BOTTOM : Orientation.RIGHT;
			Edge edgeToMatch = pieceToMatch.getEdgeWithOrientation(orientationToMatch);

			Edge edge = getMatchingEdge(edgeToMatch, piecesToSearch);
			if (edge == null) return false; //can't solve

			Orientation orientation = orientationToMatch.getOpposite();
			setEdgeInSolution(piecesToSearch, edge, row, column, orientation);
		}
		return true;
	}

	public boolean solve() {
		LinkedList<Piece> cornerPieces = new LinkedList<Piece>();
		LinkedList<Piece> borderPieces = new LinkedList<Piece>();
		LinkedList<Piece> insidePieces = new LinkedList<Piece>();

		groupPieces(cornerPieces, borderPieces, insidePieces);

		solution = new Piece[size][size];
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				LinkedList<Piece> piecesToSearch = getPieceListToSearch(cornerPieces, borderPieces, insidePieces, row, column);
				if (!fitNextEdge(piecesToSearch, row, column)) return false;
			}
		}
	}
	return true;
}

public class Piece {
	private HashMap<Orientation, Edge> edges = new HashMap<Orientation, Edge>();
	public Piece(Edge[] edgeList) {...}
	public void rotateEdgesBy(int numberRotations) {...}
	public boolean isCorner() {...}
	public boolean isBorder() {...}
}

public clas Edge {
	private Shape shape;
	private Piece parentPiece;
	public Edge(Shape shape) {...}
	public boolean fitsWith(Edge edge) {...}
}

