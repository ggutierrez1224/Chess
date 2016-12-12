package chess;

/**
 * Class for the Bishop piece
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Bishop extends Piece {
    /**
     * Default constructor inherited from Piece class
     *
     * @param color    Enum for color of piece
     * @param name     Name of the piece
     * @param fileRank Current file rank
     * @param numMoves Total number of moves the piece has made
     * @param isKilled Killed status of piece
     */
    public Bishop(Color color, String name, String fileRank, int numMoves, boolean isKilled) {
        super(color, name, fileRank, numMoves, isKilled);
    }

    /**
     * Checks whether an inputed move is valid or not
     *
     * @param p    Piece for the move
     * @param curr Current file rank
     * @param dest Destination file rank
     */
    public boolean isValidMove(Cell[][] board, Piece p, String curr, String dest, Player current, Player enemy)
    {
        int currFile = (int) curr.charAt(0) - 97;
        int currRank = Math.abs(8 - Character.getNumericValue(curr.charAt(1)));
        int destFile = (int) dest.charAt(0) - 97;
        int destRank = Math.abs(8 - Character.getNumericValue(dest.charAt(1)));

        // Since the bishop can only move diagonally, this means that it can only move
        // to new array positions where the magnitude of the change from the start position 
        // to the end position are of the same magnitude for both dimensions.
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
        return false;
    }
}
