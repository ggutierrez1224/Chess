package chess;
/**
 * Class for the chess board
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Board
{
	/**
	 * Field for a singleton instance of Board
	 */
	private static final Board board = new Board();
	
	/**
	 * The tiles in the board
	 * A 2d array of Cell objects
	 */
    private Cell[][] boardCells = new Cell[8][8];
        
    /**
     * Constructor for the board
     * It creates Cell objects and assigns them to the board array
     * It is private so as to prevent any other class from instantiating
     */
    
    private Board()
    {
    	// This is ASCII character for 'a'.
        int file = 97; 
        // ASCII character value for '1'.
        int rank = 49;
        
        int setCellColor = 0; // For alternating the cell color. White corresponds to even, black to odd.
        
        // Iterate over the rows, then the columns. Start from rank 1, initialize the row, move to rank 2, etc.
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {	
            	// The first cell is white. From then on it alternates.
                if(setCellColor%2 == 0)
                {
                    this.boardCells[i][j] = new Cell("  ", Color.WHITE, null, new Character((char)file).toString() + new Character((char)rank).toString(), true);
                }
                else
                {
                    this.boardCells[i][j] = new Cell("##", Color.BLACK, null,new Character((char)file).toString() + new Character((char)rank).toString(), true);
                }
                file++;
                setCellColor++;
            }
            file = 97;
            setCellColor++;
            rank++;
        }
    }
    
    /**
     * Return the board singleton instance
     * @return instance of Board class
     */
    public static Board getInstance() {
    	return board;
    }
    
    /**
     * Gets the 2d array of board cells
     * @return a 2d array of Cells
     */
    public Cell[][] getCells() {
    	return boardCells;
    }
    
    /**
     * Initializes and assigns the pieces
     * for each player
     * @param w The white player
     * @param b The black player
     */
    public void init (Player w, Player b)
    {
        Piece[] whitePieces = w.getPieces();
        Piece[] blackPieces = b.getPieces();
        String fr;
        int file;
        int rank;

        for(int i = 0; i < whitePieces.length; i++)
        {
            fr = whitePieces[i].getFileRank();
            file = ((int)fr.charAt(0)) - 97;
            rank = Math.abs(8 - Character.getNumericValue(fr.charAt(1)));
            this.boardCells[rank][file].setPiece(whitePieces[i]);
            this.boardCells[rank][file].setIsEmpty(false);
        }

        for(int i = 0; i < blackPieces.length; i++)
        {
            fr = blackPieces[i].getFileRank();
            file = ((int)fr.charAt(0)) - 97;
            rank = Math.abs(8 - Character.getNumericValue(fr.charAt(1)));
            this.boardCells[rank][file].setPiece(blackPieces[i]);
            this.boardCells[rank][file].setIsEmpty(false);
        }
    }
    
    /**
     * Displays the board to the screen with ASCII characters
     */
    public void printBoard()
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(boardCells[i][j].getPiece() == null)
                {
                    System.out.print(boardCells[i][j].s + " ");
                }
                else
                {
                    System.out.print(boardCells[i][j].getPiece() + " ");
                }
            }
            // Print the rank numbers on the right side of the cells.
            System.out.print(8 - i);
            System.out.println();
        }
        // Finally, print out the file letters below the cells.
        for (int file = 97; file <= 104; file++) {
        	System.out.print(" " + (char)file + " ");
        }
        System.out.println("\n");
    }
}
