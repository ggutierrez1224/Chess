package chess;

/**
 * Abstract class for chess pieces.
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
import java.util.ArrayList;

public abstract class Piece
{
	/**
	 * enum for color
	 */
    Color color;
    
    /**
     * identity of the piece
     */
    private String name;
    
    /**
     * current file rank
     */
    private String fileRank;
    
    /**
     * total number of moves the piece has made
     */
    private int numberMoves;
    
    /**
     * killed status of the piece
     */
    private boolean isKilled;
    
    /**
     * a record of all moves made on the piece by the player
     */
    private ArrayList<String> moveRecords;

    /**
     * constructor for the Piece object
     * @param color enum for color
     * @param name identifier for piece
     * @param fileRank current file rank
     * @param numberMoves total number of moves made on the piece
     * @param isKilled killed status
     */
    public Piece(Color color, String name, String fileRank, int numberMoves, boolean isKilled)
    {
        this.color = color;
        this.name = name;
        this.fileRank = fileRank;
        this.numberMoves = 0;
        this.isKilled = isKilled;
        moveRecords = new ArrayList<String>();
    }
    
    /**
     * Abstract method for a valid move by the piece.
     * Valid moves vary based on the type of piece.
     * @param board, game board
     * @param p the piece
     * @param curr the current file rank
     * @param dest the destination file rank
     * @param current, player currently making move
     * @param enemy, opposing player
     * @return true if move is valid, false if invalid move
     */
    abstract boolean isValidMove(Cell[][] board, Piece p, String curr, String dest, Player current, Player enemy);

    /**
     * removes piece instance from cell where a piece is moving to
     * @param board, game board
     * @param file, column of board
     * @param rank, row of board
     */
    public void kill(Cell[][] board, int file, int rank)
    {
        board[rank][file].getPieceInstance().setIsKilled(true);
        board[rank][file].setIsEmpty(true);
        board[rank][file].setPiece(null);
    }

    /**
     * determines if destination of piece is within the board
     * @param destFileRank, destinatin cell of the piece
     * @return true if withing board, false if out of bounds
     */
    public boolean isWithinBoard(String destFileRank)
    {
        int file = (int)destFileRank.charAt(0) - 97;
        int rank = Math.abs(8 - Character.getNumericValue(destFileRank.charAt(1)));

        if(file > 7 || file < 0 || rank > 7 || rank < 0)
        {
            return false;
        }
        else
        {
            return true;

        }
    }
    
    /**
     * move piece to indicated cell, and remove the instance in the original cell
     * @param board, game board
     * @param m, player's move
     * @param player, player making move
     */
    public void move(Cell[][] board, String m, Player player)
    {
        String[] moves  = m.split(" ");

        int currFile = (int)moves[0].charAt(0) - 97;
        int currRank = Math.abs(8 - Character.getNumericValue(moves[0].charAt(1)));
        int destFile = (int)moves[1].charAt(0) - 97;
        int destRank = Math.abs(8 - Character.getNumericValue(moves[1].charAt(1)));

        //if destination cell is not empty, kill piece
        if(!board[destRank][destFile].getIsEmpty())
        {
            kill(board, destFile, destRank);
        }
        //check for en passant kill
        else if(board[currRank][currFile].getPieceInstance() instanceof Pawn && currFile != 7 && !board[currRank][currFile + 1].getIsEmpty() && (currFile == 3 || currFile ==4))
        {
            kill(board, currFile + 1, currRank);
        }
        else if(board[currRank][currFile].getPieceInstance() instanceof Pawn && currFile != 0 && !board[currRank][currFile - 1].getIsEmpty() && (currFile == 3 || currFile ==4))
        {
            kill(board, currFile - 1, currRank);
        }

        //put piece instance into destination cell
        board[destRank][destFile].setPiece(board[currRank][currFile].getPieceInstance());
        //set piece to null in original cell
        board[currRank][currFile].setPiece(null);
        //set original cell to empty
        board[currRank][currFile].setIsEmpty(true);
        //set original cell to empty
        board[destRank][destFile].setIsEmpty(false);
        //update piece fileRank in player class
        player.setPieceAt(moves[0], moves[1]);
    }

    public boolean check(Cell[][] board, Player current, Player enemy)
    {
        Piece[] currPieces = current.getPieces();
        Piece[] enemyPieces = enemy.getPieces();
        String fr;
        //get fileRank of current player's king
        fr = currPieces[8].getFileRank();

        String enemyfr;
        //check if current king is threatened
        for(int i = 0;i < enemyPieces.length; i++)
        {
            enemyfr = enemyPieces[i].getFileRank();
            //If any of the opponent pieces has valid move to king, then king is in check
            if(enemyPieces[i].isValidMove(board, enemyPieces[i], enemyfr, fr, current, enemy))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the kings move will cause him to be in check
     * @param board, game board
     * @param dest, kings move
     * @param current, player king belongs to
     * @param enemy, other player
     * @return true if king will be in check after move, false otherwise
     */
    public boolean willKingBeInCheck(Cell[][] board, String dest,Player current, Player enemy )
    {
        Piece[] enemyPieces = enemy.getPieces();

        String enemyfr;
        //check if current king is threatened
        for(int i = 0;i < enemyPieces.length; i++)
        {
            enemyfr = enemyPieces[i].getFileRank();
            //If any of the opponent pieces has valid move to king, then king is in check
            if(enemyPieces[i].isValidMove(board, enemyPieces[i], enemyfr, dest, current, enemy))
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkMate(Cell[][] board, Color curr, Player current, Player enemy)
    {
        return false;
    }
    /**
     * Get the name of the piece
     * @return identifier for Piece object
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the file rank
     * @return the current file rank
     */
    public String getFileRank()
    {
        // this is to get the file rank

        return fileRank;
    }
    
    /**
     * Get the color
     * @return the color enum
     */
    public Color getColor()
    {
        //this is to get the color

        return color;
    }
    
    /**
     * Get the moves record
     * @return the record of all moves made by the piece
     */
    public ArrayList<String> getMoveRecords()
    {
        return moveRecords;
    }
    
    /**
     * Get the killed status
     * @return the killed status of the piece
     */
    public boolean getIsKilled()
    {
        return isKilled;
    }
    
    /**
     * Get the number of moves
     * @return the total number of moves made by the piece
     */
    public int getNumberMoves()
    {
        return numberMoves;
    }
    
    /**
     * Create a name for the piece
     * @param s the unique identifier for the piece
     */
    public void setPiece(String s)
    {
        this.name = s;
    }
    
    /**
     * Set the file rank
     * @param fr the file rank of the piece
     */
    public void setFileRank(String fr)
    {
        this.fileRank = fr;
    }
    
    /**
     * Set the killed status
     * @param flag the killed status for the piece
     */
    public void setIsKilled(boolean flag)
    {
        this.isKilled = flag;
    }


    public void incrementMoves()
    {
        numberMoves++;
    }
}
