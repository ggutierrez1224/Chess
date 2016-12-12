package chess;

/**
 * Class for king piece
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class King extends Piece
{
	/**
	 * True if the king is in check, false otherwise.
	 */
	boolean inCheck = false;
	
	/**
	 * Default constructor for king
	 * @param color color enum
	 * @param name piece indentity
	 * @param fileRank current file rank of piece
	 * @param numMoves total number of moves by piece in game
	 * @param isKilled killed status of piece
	 */
    public King (Color color, String name, String fileRank, int numMoves, boolean isKilled)
    {
        super(color, name, fileRank, numMoves, isKilled);
    }
    
    /**
     * This method scans all directions from the king for an attacking piece.
     * @param currFile, array index of current file
     * @param currRank, array index of current rank
     * @param board, game board
     * @return
     */

    /**
     * checks if input move is valid
     * @param board, game board
     * @param p, king object
     * @param curr, the current file rank
     * @param dest, the destination file rank
     * @return true if valid move, false if not
     */
    public boolean isValidMove(Cell[][] board, Piece p, String curr, String dest, Player current, Player enemy)
    {
        int currFile = (int)curr.charAt(0) - 97;
        int currRank = Math.abs(8 - Character.getNumericValue(curr.charAt(1)));
        int destFile = (int)dest.charAt(0) - 97;
        int destRank = Math.abs(8 - Character.getNumericValue(dest.charAt(1)));

        //check for castling move, make sure king is not in check and king has not moved
        if(p.getColor() == Color.WHITE && p.getNumberMoves() == 0 && (dest.equals("g1") || dest.equals("c1")) && !p.check(board, current, enemy))
        {
            //make sure no pieces between king and rook
            if((!board[currRank][currFile + 1].getIsEmpty() || !board[currRank][currFile + 2].getIsEmpty()) && (!board[currRank][currFile - 1].getIsEmpty() || !board[currRank][currFile - 2].getIsEmpty() || !board[currRank][currFile - 3].getIsEmpty()))
            {
                return false;
            }
            //make sure king isn't in check before, during, or after castling move. Make sure rook is in original spot
            else if(dest.equals("g1"))
            {
                if(p.willKingBeInCheck(board, "f1", current, enemy) || p.willKingBeInCheck(board, "g1", current, enemy))
                {
                    return false;
                }
                else if(current.getPieces()[11].getNumberMoves() != 0)
                {
                    return false;
                }
                else
                {
                    current.getPieces()[11].move(board, "h1 f1", current);
                    return true;
                }
            }
            else if(dest.equals("c1"))
            {
                if(p.willKingBeInCheck(board, "d1", current, enemy) || p.willKingBeInCheck(board, "c1", current, enemy))
                {
                    return false;
                }
                else if(current.getPieces()[10].getNumberMoves() != 0)
                {
                    return false;
                }
                else
                {
                    current.getPieces()[10].move(board, "a1 d1", current);
                    return true;
                }
            }
            return false;
        }
        //now castling on black side
        else if(p.getColor() == Color.BLACK && p.getNumberMoves() == 0 && (dest.equals("g8") || dest.equals("c8")) && !p.check(board, current, enemy))
        {
            //make sure no pieces between king and rook
            if((!board[currRank][currFile + 1].getIsEmpty() || !board[currRank][currFile + 2].getIsEmpty()) && (!board[currRank][currFile - 1].getIsEmpty() || !board[currRank][currFile - 2].getIsEmpty() || !board[currRank][currFile - 3].getIsEmpty()))
            {
                return false;
            }
            //make sure king isn't in check before, during, or after castling move. Make sure rook is in original spot
            else if(dest.equals("g8"))
            {
                if(p.willKingBeInCheck(board, "f8", current, enemy) || p.willKingBeInCheck(board, "g8", current, enemy))
                {
                    return false;
                }
                else if(current.getPieces()[11].getNumberMoves() != 0)
                {
                    return false;
                }
                else
                {
                    current.getPieces()[11].move(board, "h8 f8", current);
                    return true;
                }
            }
            else if(dest.equals("c8"))
            {
                if(p.willKingBeInCheck(board, "d8", current, enemy) || p.willKingBeInCheck(board, "c8", current, enemy))
                {
                    return false;
                }
                else if(current.getPieces()[10].getNumberMoves() != 0)
                {
                    return false;
                }
                else
                {
                    current.getPieces()[10].move(board, "a8 d8", current);
                    return true;
                }
            }
            return false;
        }
        //king can move like a queen, but can only move one cell per turn
        if (Math.abs(currFile - destFile) > 1 || Math.abs(currRank - destRank) > 1)
        {
            return false;
        }
        //check for diagonal movement
        boolean ret = false;
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
                        ret = true;
                    }
                    else if(board[currRank - (i + 1)][currFile + (i + 1)].getIsEmpty())
                    {
                        ret = true;
                    }
                    else
                    {
                        ret = false;
                    }
                }
                if (upLeft)
                {
                    if (!board[currRank - (i + 1)][currFile - (i + 1)].getIsEmpty() &&
                            (board[currRank - (i + 1)][currFile - (i + 1)].getFileRank().equals(board[destRank][destFile].getFileRank())))
                    {
                        ret = true;
                    }
                    else if(board[currRank - (i + 1)][currFile - (i + 1)].getIsEmpty())
                    {
                        ret = true;
                    }
                    else
                    {
                        ret = false;
                    }
                }
                if (downRight)
                {
                    if (!board[currRank + (i + 1)][currFile + (i + 1)].getIsEmpty() &&
                            (board[currRank + (i + 1)][currFile + (i + 1)].getFileRank().equals(board[destRank][destFile].getFileRank())))
                    {
                        ret = true;
                    }
                    else if(board[currRank + (i + 1)][currFile + (i + 1)].getIsEmpty())
                    {
                        ret = true;
                    }
                    else
                    {
                        ret = false;
                    }
                }
                if (downLeft)
                {
                    if (!board[currRank + (i + 1)][currFile - (i + 1)].getIsEmpty() &&
                            (board[currRank + (i + 1)][currFile - (i + 1)].getFileRank().equals(board[destRank][destFile].getFileRank())))
                    {
                        ret = true;
                    }
                    else if(board[currRank + (i + 1)][currFile - (i + 1)].getIsEmpty())
                    {
                        ret = true;
                    }
                    else
                    {
                        ret = false;
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
        if(p.willKingBeInCheck(board, dest, current, enemy))
        {
            System.out.println("Move will put King in check!");
            ret = false;
        }
        // It passed all tests.
        return ret;
    }
}