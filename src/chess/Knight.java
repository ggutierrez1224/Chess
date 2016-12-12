package chess;
/**
 * Class for knight piece
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Knight extends Piece
{
	/**
	 * 
	 * @param color, color of piece
	 * @param name, name of piece displayed on board
	 * @param fileRank, position on board
	 * @param numMoves, how many moves the piece has made
	 * @param isKilled, whether piece is killed or not
	 */
    public Knight (Color color, String name, String fileRank, int numMoves, boolean isKilled)
    {
        super(color, name, fileRank, numMoves, isKilled);
    }

	/**
	 *
	 * @param board, game board
	 * @param p the piece
	 * @param curr the current file rank
	 * @param dest the destination file rank
	 * @param current, player currently making move
	 * @param enemy, opposing player
	 * @return true if move is valid, false otherwise
	 */
    public boolean isValidMove(Cell[][] board, Piece p, String curr, String dest, Player current, Player enemy)
    {
    	int currFile = (int)curr.charAt(0) - 97;
        int currRank = Math.abs(8 - Character.getNumericValue(curr.charAt(1)));
        int destFile = (int)dest.charAt(0) - 97;
        int destRank = Math.abs(8 - Character.getNumericValue(dest.charAt(1)));

    	// The knight can jump over pieces. So we don't have to check for pieces along the path.
    	// There are 8 places the knight can jump to
    	// Check that the destination rank and file are one of those places.
    	
    	if ((destRank == currRank + 2) && (destFile == currFile - 1))
    	{
    		return true;
    	}
    	else if ((destRank == currRank + 2) && (destFile == currFile + 1))
    	{
    		return true;
    	}
    	else if ((destRank == currRank + 1) && (destFile == currFile - 2))
    	{
    		return true;
    	}
    	else if ((destRank == currRank - 1) && (destFile == currFile - 2))
    	{
    		return true;
    	}
    	else if ((destRank == currRank - 2) && (destFile == currFile + 1))
    	{
    		return true;
    	}
    	else if ((destRank == currRank - 2) && (destFile == currFile - 1))
    	{
    		return true;
    	}
    	else if ((destRank == currRank + 1) && (destFile == currFile  + 2))
    	{
    		return true;
    	}
    	else if ((destRank == currRank - 1) && (destFile == currFile + 2))
    	{
    		return true;
    	}
    	return false;
    }
}
