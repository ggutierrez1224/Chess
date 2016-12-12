package chess;
/**
 * Class for queen piece
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Queen extends Piece
{	
	/**
	 * constructor for Queen object
	 * @param color, color of piece
	 * @param name, name of piece displayed on board
	 * @param fileRank, current position of piece
	 * @param numMoves, number of moves piece has made
	 * @param isKilled, true if killed, false otherwise
	 */
    public Queen (Color color, String name, String fileRank, int numMoves, boolean isKilled)
    {
        super(color, name, fileRank, numMoves, isKilled);
    }
    
    /**
     * vaild move test
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
        
        // The queen has the combined moves of the rook and the bishop, 
        // i.e., the queen may move in any straight line, horizontal, vertical, or diagonal.

        //check for diagonal movement
        if (Math.abs(destRank - currRank) == Math.abs(destFile - currFile))
        {
            boolean upRight = currFile < destFile && currRank > destRank;
            boolean upLeft = currFile > destFile && currRank > destRank;
            boolean downRight = currFile < destFile && currRank < destRank;
            boolean downLeft = currFile > destFile && currRank < destRank;

            for (int i = 0; i < Math.abs(currFile - destFile); i++)
            {
                if (upRight)
                {
                    if (!board[currRank - (i + 1)][currFile + (i + 1)].getIsEmpty() &&
                            (board[currRank - (i + 1)][currFile + (i + 1)].getFileRank().equals(board[destRank][destFile].getFileRank())))
                    {
                        return true;
                    }
                    else if(board[currRank - (i + 1)][currFile + (i + 1)].getIsEmpty())
                    {
                        return true;
                    }
                    else
                    {
                       return false;
                    }
                }
                if (upLeft)
                {
                    if (!board[currRank - (i + 1)][currFile - (i + 1)].getIsEmpty() &&
                            (board[currRank - (i + 1)][currFile - (i + 1)].getFileRank().equals(board[destRank][destFile].getFileRank())))
                    {
                        return true;
                    }
                    else if(board[currRank - (i + 1)][currFile - (i + 1)].getIsEmpty())
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                if (downRight)
                {
                    if (!board[currRank + (i + 1)][currFile + (i + 1)].getIsEmpty() &&
                            (board[currRank + (i + 1)][currFile + (i + 1)].getFileRank().equals(board[destRank][destFile].getFileRank())))
                    {
                        return true;
                    }
                    else if(board[currRank + (i + 1)][currFile + (i + 1)].getIsEmpty())
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                if (downLeft)
                {
                    if (!board[currRank + (i + 1)][currFile - (i + 1)].getIsEmpty() &&
                            (board[currRank + (i + 1)][currFile - (i + 1)].getFileRank().equals(board[destRank][destFile].getFileRank())))
                    {
                        return true;
                    }
                    else if(board[currRank + (i + 1)][currFile - (i + 1)].getIsEmpty())
                    {
                        return  true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        //check for vertical or horizontal movements
        else
        {
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
        }
        // It passed all tests.
        return true;
    }
}
