package chess;
/**
 * Class for pawn piece
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Pawn extends Piece
{
	/**
	 * constructor for a Pawn object
	 * @param color color enum
	 * @param name name of piece
	 * @param fileRank starting file rank
	 * @param numMoves total number of moves made on
	 * @param isKilled killed status
	 */
    public Pawn (Color color, String name, String fileRank, int numMoves, boolean isKilled)
    {
        super(color, name, fileRank, numMoves, isKilled);
    }
    
    /**
     * valid move function
     * @param board, game board
     * @param p Piece object
     * @param curr current file rank
     * @param dest destination file rank
     * @return true if move is valid, false if invalid move
     */
    public boolean isValidMove(Cell[][] board, Piece p, String curr, String dest, Player current, Player enemy)
    {
        int currFile = (int)curr.charAt(0) - 97;
        int currRank = Math.abs(8 - Character.getNumericValue(curr.charAt(1)));
        int destFile = (int)dest.charAt(0) - 97;
        int destRank = Math.abs(8 - Character.getNumericValue(dest.charAt(1)));

        if(p.getColor() == Color.WHITE)
        {
            //if pawn is moving one cell forward, return true if destination cell is empty
            if(currFile == destFile && destRank == currRank - 1)
            {
                return board[destRank][destFile].getIsEmpty();
            }
            //if pawn is moving one diagonal space forward, return true if there is enemy piece in destination cell
            else if ((destFile == currFile + 1 || destFile == currFile - 1) && destRank == currRank - 1)
            {
                if(!board[destRank][destFile].getIsEmpty())
                {
                    return true;
                }
                //check for en passant, check if piece is going diagonal to an empty square and a pawn next current
                else if (board[destRank][destFile].getIsEmpty() && (!board[currRank][currFile + 1].getIsEmpty() || !board[currRank][currFile -1].getIsEmpty()) && currRank == 3)
                {
                    if(current.getNumMovesMade() == enemy.getDoubleStepTurn())
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            //pawn can move forward two steps, only if it is its first move
            //and the two cells in front of it are be empty.
            else if(currFile == destFile && destRank == currRank - 2)
            {
                //white pawn start rank is 2
                if(currRank == 6 && board[destRank + 1][destFile].getIsEmpty() && board[destRank][destFile].getIsEmpty())
                {
                    current.setDoubleStepTurn(current.getNumMovesMade() + 1);
                    return true;
                }
            }
            //no other possible valid moves, return false
            return false;
        }
        //piece is black
        else
        {
            //if pawn is moving one cell forward, return true if destination cell is empty
            if(currFile == destFile && destRank == currRank + 1)
            {
                return board[destRank][destFile].getIsEmpty();
            }
            //if pawn is moving one diagonal space forward, return true if there is enemy piece in destination cell
            else if ((destFile == currFile + 1 || destFile == currFile - 1) && destRank == currRank + 1)
            {
                if(!board[destRank][destFile].getIsEmpty())
                {
                    return true;
                }
                //check for en passant, check if piece is going diagonal to an empty square and a pawn next current
                else if (board[destRank][destFile].getIsEmpty() && (!board[currRank][currFile + 1].getIsEmpty() || !board[currRank][currFile -1].getIsEmpty()) && currRank == 4)
                {
                    if(current.getNumMovesMade() == enemy.getNumMovesMade() - 1)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            //pawn can move forward two steps, only if it is its first move
            //and the two cells in front of it are be empty.
            else if(currFile == destFile && destRank == currRank + 2)
            {
                //black pawn start rank is 7
                if (currRank == 1 && board[destRank - 1][destFile].getIsEmpty() && board[destRank][destFile].getIsEmpty())
                {
                    current.setDoubleStepTurn(current.getNumMovesMade() + 1);
                    return true;
                }
            }
            //no other possible valid moves, return false
            return false;
        }
    }
    
    /**
     * promotion of a pawn
     * @param p, Pawn object
     * @param piece, name of Pawn object
     * @param b, game board
     * @param player, owner of pawn object being promoted
     */
    public void promote(Piece p, String piece, Cell[][] b, Player player)
    {
        String fr = p.getFileRank();
        int file = (int)fr.charAt(0) - 97;
        int rank = Math.abs(8 - Character.getNumericValue(fr.charAt(1)));

        Piece newPiece;
        String name;
        Color c = player.getColor();
        if(c == Color.WHITE)
        {
            name = "w";
        }
        else
        {
            name = "b";
        }

        //figure out with piece to promote to, then create and store instance in its cell on the board
        if(piece.charAt(0) == 'r')
        {
            newPiece = new Rook(p.getColor(), name + "R", p.getFileRank(), p.getNumberMoves(), false);
            b[rank][file].setPiece(newPiece);
        }
        else if(piece.charAt(0) == 'n')
        {
            newPiece = new Knight(p.getColor(), name + "N", p.getFileRank(), p.getNumberMoves(), false);
            b[rank][file].setPiece(newPiece);
        }
        else if(piece.charAt(0) == 'b')
        {
            newPiece = new Bishop(p.getColor(), name + "B", p.getFileRank(), p.getNumberMoves(), false);
            b[rank][file].setPiece(newPiece);
        }
        else
        {
            newPiece = new Queen(p.getColor(), name + "Q", p.getFileRank(), p.getNumberMoves(), false);
            b[rank][file].setPiece(newPiece);
        }
        //replace pawn for promoted piece in player's piece array
        for(int i = 0; i < player.getPieces().length; i++)
        {
            if(player.getPieces()[i].getFileRank().equals(fr))
            {
                player.getPieces()[i] = newPiece;
            }
        }
    }
}
