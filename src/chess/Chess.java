package chess;

/**
 * Abstract class for chess pieces.
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */

import java.util.Scanner;

/**
 * The main application for the chess game
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Chess
{	
	/**
	 * Main app
	 * @param args
	 */
    public static void main (String[] args)
    {
    	boolean gameOver = false;
        boolean badInput = true;
        boolean whiteIsInCheck = false;
        boolean blackIsInCheck = false;
    	
        Player whitePlayer = new Player(Color.WHITE);
        Player blackPlayer = new Player(Color.BLACK);

        Board.getInstance().init(whitePlayer, blackPlayer);
        
        // Scanner object to read input from the user.
        Scanner sc = new Scanner(System.in);
        
        // The idea is to count the total number of turns in the game. White will go on odd turns. Black on even turns.
        int turns = 1;
        String input = null;
        while(!gameOver)
        {
        	// Reprint the board.
        	Board.getInstance().printBoard();
        	
        	// White goes on odd turns.
        	if (turns % 2 == 1)
        	{
        		badInput = true;
                while(badInput)
                {
                    System.out.print("White's move: ");
                    input = sc.nextLine();// Read in line.
                    System.out.println();
                    
                    // Check that the input is valid.
                    badInput = checkInput(input, whitePlayer, blackPlayer);
                }
                int currFile = (int)input.toLowerCase().charAt(0) - 97;
                int currRank = Math.abs(8 - Character.getNumericValue(input.toLowerCase().charAt(1)));
                int destFile = (int)input.toLowerCase().charAt(3) - 97;
                int destRank = Math.abs(8 - Character.getNumericValue(input.toLowerCase().charAt(4)));

                //call move method to move piece to destination
                Board.getInstance().getCells()[currRank][currFile].getPieceInstance().move(Board.getInstance().getCells(), input.toLowerCase(), whitePlayer);

                //increment player moves made
                whitePlayer.incrementNumMoves();

                //check if moving piece is pawn that wasn't specified a promotion
                if(Board.getInstance().getCells()[destRank][destFile].getPieceInstance() instanceof Pawn && destRank == 0)
                {
                    ((Pawn) Board.getInstance().getCells()[destRank][destFile].getPieceInstance()).promote(Board.getInstance().getCells()[destRank][destFile].getPieceInstance(), "q", Board.getInstance().getCells(), whitePlayer);
                }
        	}
        	else
            {
        		badInput = true;
                while(badInput)
                {
                    System.out.print("Black's move: ");
                    input = sc.nextLine();// Read in line.
                    System.out.println();

                    // Check that the input is valid.
                    badInput = checkInput(input, blackPlayer, whitePlayer);
                }
                int currFile = (int)input.toLowerCase().charAt(0) - 97;
                int currRank = Math.abs(8 - Character.getNumericValue(input.toLowerCase().charAt(1)));
                int destFile = (int)input.toLowerCase().charAt(3) - 97;
                int destRank = Math.abs(8 - Character.getNumericValue(input.toLowerCase().charAt(4)));

                //call move method to move piece to destination
                Board.getInstance().getCells()[currRank][currFile].getPieceInstance().move(Board.getInstance().getCells(), input.toLowerCase(), blackPlayer);

                //increment player moves made
                blackPlayer.incrementNumMoves();

                //check if moving piece is pawn that wasn't specified a promotion
                if(Board.getInstance().getCells()[destRank][destFile].getPieceInstance() instanceof Pawn && destRank == 7)
                {
                    ((Pawn) Board.getInstance().getCells()[destRank][destFile].getPieceInstance()).promote(Board.getInstance().getCells()[destRank][destFile].getPieceInstance(), "q", Board.getInstance().getCells(), blackPlayer);
                }
        	}
        	turns++; // Increment the turns count.
        }
        // Have to close the scanner object.
        sc.close();
    }

	/** function to check user input for any errors
	 *
	 * @param input, user's input
	 * @param player, current player
     * @param enemy, opposing player
	 * @return boolean value, true if good input, false if wrong input
	 */
	public static boolean checkInput(String input, Player player, Player enemy)
    {
        //check for empty input
        if(input.isEmpty())
        {
            System.out.println("No move entered, please enter move: ");
            return true;
        }

    	// First, let's parse the input into either 1, 2, or 3 elements.
    	String[] inputs = input.toLowerCase().split(" ");

        //if only one element in inputs, check if player is resigning
        if(inputs.length == 1 && inputs[0].equalsIgnoreCase("resign"))
        {
            System.out.println(player.getColor() + " resigns!");
            endGame(player, 1);
        }
        else if(inputs.length == 1)
        {
            System.out.println("Missing input!");
            return true;
        }
        //make sure input is of format 'fileRank fileRank'
        if(inputs[0].trim().length() != 2 || inputs[1].trim().length() != 2)
        {
            System.out.println("Input must be in the format: fileRank filRank");
            return true;
        }

        //cast file and rank to int and store in appropriate variable
        int currFile = (int)inputs[0].charAt(0) - 97;
        int currRank = Math.abs(8 - Character.getNumericValue(inputs[0].charAt(1)));
        int destFile = (int)inputs[1].charAt(0) - 97;
        int destRank = Math.abs(8 - Character.getNumericValue(inputs[1].charAt(1)));

        //check if current cell is in board
        if(currFile > 7 || currFile < 0 || currRank > 7 || currRank < 0 )
        {
            System.out.println("Current is outside of board!");
            return true;
        }
        //check if there is a piece in starting cell
    	else if (Board.getInstance().getCells()[currRank][currFile].getIsEmpty())
    	{
            System.out.println("No piece found at: " + inputs[0]);
            return true;
        }
        //check if move will go outside board
        else if(!Board.getInstance().getCells()[currRank][currFile].getPieceInstance().isWithinBoard(inputs[1]))
        {
            System.out.println("Destination is outside of board!");
            return true;
        }
        else if(inputs.length == 3)
        {
            if(inputs[2].equalsIgnoreCase("draw?"))
            {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter 'draw' to accept: ");
                String ans = sc.next();
                if(ans.trim().equalsIgnoreCase("draw"))
                {
                    endGame(player, 2);
                    return false;
                }
                else
                {
                     return checkInput(inputs[0] + " " + inputs[1], player, enemy);
                }
            }
            else if (Board.getInstance().getCells()[currRank][currFile].getPieceInstance() instanceof Pawn && (destRank == 0 || destRank == 7))
            {
                if(!checkInput(inputs[0] + " " + inputs[1], player, enemy))
                {
                    ((Pawn) Board.getInstance().getCells()[currRank][currFile].getPieceInstance()).promote(Board.getInstance().getCells()[currRank][currFile].getPieceInstance(), inputs[2], Board.getInstance().getCells(), player);
                    return false;
                }
                else
                {
                    System.out.println("Invalid move");
                    return true;
                }
            }
            else
            {
                System.out.println("Pawn can't be promoted yet!");
                return true;
            }
        }
    	// Now that there is a piece in the starting cell,
    	// check if the player is trying to move the other player's piece.
    	else if (Board.getInstance().getCells()[currRank][currFile].getPieceInstance().getColor() != player.getColor())
    	{
    		System.out.println("Starting cell does not contain " + player.getColor() + "'s piece.");
    		return true;
    	}
    	//check if player is killing own piece by seeing whether the destination file's piece's color is the same as the the moving piece's
    	else if (!Board.getInstance().getCells()[destRank][destFile].getIsEmpty() && (Board.getInstance().getCells()[destRank][destFile].getPieceInstance().getColor() == player.getColor()))
        {
            System.out.println("You can't kill your own piece !");
            return true;
    	}
    	//return value of piece's validMove method is false, then move is not valid
    	else if (!player.getPieceAt(inputs[0]).isValidMove(Board.getInstance().getCells(), player.getPieceAt(inputs[0]), inputs[0], inputs[1], player, enemy))
        {
            System.out.println("Invalid move for piece at: " + inputs[0]);
            return true;
        }
        if(player.getPieces()[8].check(Board.getInstance().getCells(), player, enemy))
        {
            System.out.println("King is in check!");
            return true;
        }
        else
        {
            player.getPieceAt(inputs[0]).incrementMoves();
            return false;
        }
    }

    /**
     * ends game and announces winning player
     * @param player, current player
     * @param won, 0 if player is the winner, 1 if not the winner, 2 if draw
     */
    public static void endGame(Player player, int won)
    {
        if(won == 0)
        {
            if(player.getColor() == Color.WHITE)
            {
                System.out.println(Color.WHITE + " wins!");
            }
            else
            {
                System.out.println(Color.BLACK + " wins!");
            }
            System.exit(0);
        }
        else if(won == 1)
        {
            if(player.getColor() == Color.WHITE)
            {
                System.out.println(Color.BLACK + " wins!");
            }
            else
            {
                System.out.println(Color.WHITE + " wins!");
            }
            System.exit(0);
        }
        else
        {
            System.out.println("Both players draw");
            System.exit(0);
        }

    }
}
