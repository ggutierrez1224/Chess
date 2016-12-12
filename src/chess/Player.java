package chess;
/**
 * Class for a player
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Player
{
	/**
	 * color enum
	 */
    private Color color;
    
    /**
     * an array of the 16 pieces belonging to the player
     */
    private Piece[] pieces = new Piece[16];

    /**
     * tracks number of moves player has made during the game
     */
    private int numMovesMade;

    private int doubleStepTurn = 0;
    
    /**
     * constructor for a Player
     * @param color enum color
     */
    public Player(Color color)
    {
        this.color = color;
        this.numMovesMade = 0;

        //initialize player's pieces
        if(color == Color.WHITE)
        {
            int file = 97;
            String rank = "2";
            for(int i = 0; i < 8; i++)
            {
                //init pawn
                this.pieces[i] = new Pawn(color, "wp", new Character((char)file).toString() + rank, 0, false);
                file++;
            }
            //init king
            this.pieces[8] = new King(color, "wK", "e1", 0, false);
            //init queen
            this.pieces[9] = new Queen(color, "wQ", "d1", 0, false);
            //init rook
            this.pieces[10] = new Rook(color, "wR", "a1", 0, false);
            this.pieces[11] = new Rook(color, "wR", "h1", 0, false);
            //init bishop
            this.pieces[12] = new Bishop(color, "wB", "c1", 0, false);
            this.pieces[13] = new Bishop(color, "wB", "f1", 0, false);
            //init knight
            this.pieces[14] = new Knight(color, "wN", "b1", 0, false);
            this.pieces[15] = new Knight(color, "wN", "g1", 0, false);
        }
        else
        {
            int file = 97;
            String rank = "7";
            for(int i = 0; i < 8; i++)
            {
                //init pawn
                this.pieces[i] = new Pawn(color, "bp", new Character((char)file).toString() + rank, 0, false);
                file++;
            }
            //init king
            this.pieces[8] = new King(color, "bK", "e8", 0, false);
            //init queen
            this.pieces[9] = new Queen(color, "bQ", "d8", 0, false);
            //init rook
            this.pieces[10] = new Rook(color, "bR", "a8", 0, false);
            this.pieces[11] = new Rook(color, "bR", "h8", 0, false);
            //init bishop
            this.pieces[12] = new Bishop(color, "bB", "c8", 0, false);
            this.pieces[13] = new Bishop(color, "bB", "f8", 0, false);
            //init knight
            this.pieces[14] = new Knight(color, "bN", "b8", 0, false);
            this.pieces[15] = new Knight(color, "bN", "g8", 0, false);
        }

    }
    
    /**
     * Get the player's piece at specified fileRank
     * @return array of pieces
     */
    public Piece[] getPieces()
    {
        return pieces;
    }
    /**
     * Get the player's piece that matches requested fileRank
     * @param fileRank, position of piece being searched
     * @return piece matching fileRank parameter
     */
    public Piece getPieceAt(String fileRank)
    {
        //search pieces array for matching fileRank
        for(int i = 0; i < pieces.length; i++)
        {
            if(pieces[i].getFileRank().equals(fileRank))
            {
                //return index of matching fileRank
                return pieces[i];
            }
        }
        //piece was not found
        return null;
    }

    /**
     * changes fileRank of a moved piece
     * @param curr, current fileRank of piece
     * @param dest, new fileRank of piece
     */
    public void setPieceAt(String curr, String dest)
    {
        for(int i = 0; i < pieces.length; i++)
        {
            if(pieces[i].getFileRank().equals(curr))
            {
                //return index of matching fileRank
                pieces[i].setFileRank(dest);
            }
        }
    }
    /**
     * Get the player's color
     * @return color enum of player
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * increments number of moves made during game
     */
    public void incrementNumMoves()
    {
        numMovesMade++;
    }

    /**
     * returns numMovesMade variable
     * @return number of moves player has made during game
     */
    public int getNumMovesMade()
    {
        return numMovesMade;
    }

    /**
     * @param turn, most recent turn a pawn moved two spaces forward
     */
    public void setDoubleStepTurn(int turn)
    {
        this.doubleStepTurn = turn;
    }

    /**
     * @return most recent turn a pawn moved two spaces forward
     */
    public int getDoubleStepTurn()
    {
        return doubleStepTurn;
    }
}
