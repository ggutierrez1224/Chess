package chess;
/**
 * Class to model the chess board cells
 * @author Gabriel Gutierrez and Lyle Filonuk
 *
 */
public class Cell
{
	/**
	 * ASCII symbol for the cell display
	 */
    String s;
    
    /**
     * color Enum for color of the cell
     */
    private Color color;
    
    /**
     * p The piece assigned to the cell
     */
    private Piece piece;
    
    /**
     * fileRank File rank of the cell
     */
    private String fileRank;
    
    /**
     * isEmpty Empty status of the cell
     */
    private boolean isEmpty;
    
    /**
     * Default constructor for Cell object
     * @param s ASCII symbol for the cell display
     * @param color Enum for color of the cell
     * @param p The piece assigned to the cell
     * @param fileRank File rank of the cell
     * @param isEmpty Empty status of the cell
     */
    public Cell (String s, Color color, Piece p, String fileRank, boolean isEmpty)
    {
        this.s = s;
        this.color = color;
        this.piece = p;
        this.fileRank = fileRank;
        this.isEmpty = isEmpty;
    }
    
    /**
     * Gets the ASCII representation of the cell
     * @return Returns either '#' or ' ' file rank
     */
    public String getS()
    {
        return s;
    }
    
    /**
     * Gets the file rank of the cell
     * @return Returns the file rank as a String
     */
    public String getFileRank()
    {
        return fileRank;
    }
    
    /**
     * Gets the empty status
     * @return Returns the empty status of the cell as a boolean
     */
    public boolean getIsEmpty()
    {
        return isEmpty;
    }
    
    /**
     * Gets the cell color
     * @return Returns the color enum
     */
    public Color getColor()
    {
        return this.color;
    }

    /**
     * returns piece object in Cell
     * @return, piece object in cell
     */
    public Piece getPieceInstance()
    {
        return piece;
    }
    /**
     * Gets the piece object from the cell
     * @return Returns the 
     */
    public String getPiece()
    {
        if(this.piece == null)
        {
            return null;
        }
        else
        {
            return this.piece.getName();
        }
    }
    
    /**
     * Assigns a piece to a cell
     * @param p Piece in the cell
     */
    public void setPiece(Piece p)
    {
        this.piece = p;
    }
    
    /**
     * Checks the empty status of the cell
     * @param isEmpty The empty status of the cell
     */
    public void setIsEmpty(boolean isEmpty)
    {
        this.isEmpty = isEmpty;
    }
}
