package chess;
/**
 * Class for rook piece
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Rook extends Piece
{	
	/**
	 * constructor for Rook object
	 * @param color color enum
	 * @param name name of Rook object
	 * @param fileRank starting file rank
	 * @param numMoves total number of moves
	 * @param isKilled killed status
	 */
    public Rook (Color color, String name, String fileRank, int numMoves, boolean isKilled)
    {
        super(color, name, fileRank, numMoves, isKilled);
    }
    
    /**
     * valid move test
     * @param p Piece object
     * @param curr current file rank
     * @param dest destination file rank
     */
    public boolean isValidMove(Cell[][] board, Piece p, String curr, String dest, Player current, Player enemy)
    {
    	int currFile = (int)curr.charAt(0) - 97;
        int currRank = Math.abs(8 - Character.getNumericValue(curr.charAt(1)));
        int destFile = (int)dest.charAt(0) - 97;
        int destRank = Math.abs(8 - Character.getNumericValue(dest.charAt(1)));

        // Like the bishop, the rook has 4 directions in which it can move
    	// However the directions are straight across ranks and columns rather than diagonal.
        // Check that it only moved across a column or rank.
        if ((currRank != destRank) && (currFile != destFile))
        {
        	return false;
        }
        // Check that the rook is not attempting to jump over other pieces.
        // Four directions to choose from.
        if ((destRank == currRank) && (destFile < currFile))
        {
        	for (int i = currFile - 1; i > destFile; i--)
        	{
        		if (!board[currRank][i].getIsEmpty())
        		{
        			return false;
        		}
        	}
        }
        else if ((destRank == currRank) && (destFile > currFile))
        {
        	for (int i = currFile + 1; i < destFile; i++)
        	{
        		if (!board[currRank][i].getIsEmpty())
        		{
        			return false;
        		}
        	}
        }
        else if ((currRank > destRank) && (destFile == currFile))
        {
        	for (int i = currRank - 1; i > destRank; i--)
        	{
        		if (!board[i][currFile].getIsEmpty())
        		{
        			return false;
        		}
        	}
        }
        else
        { // (destRank > currRank) && (destFile == currFile)
        	for (int i = currRank + 1; i < destRank; i++)
        	{
        		if (!board[i][currFile].getIsEmpty())
        		{
        			return false;
        		}
        	}
        }
        // And if all the tests are passed, then it is a valid move!
        return true;
    }
}
