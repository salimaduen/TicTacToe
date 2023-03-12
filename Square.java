public class Square {
    // Variable in charge of the square's symbol (X or O)
    private String symbol;

    // Player variable keeps track of which variable has this square
    private Player player;

    // Boolean to verify whether it is already occupied or not
    private boolean isOccupied;

    public Square() { // Default constructor
        this.symbol = "";
        this.player = null;
        this.isOccupied = false;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Square setSymbol(String symbol, Player player) {
        this.symbol = symbol;
        this.player = player;
        return this;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setOccupied() {
        this.isOccupied = true;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    @Override
    public String toString() {
        // If symbol is an empty string
        if (this.symbol.equals("")) {
            return "   ";
        }
        return " " + this.symbol + " ";
    }
}
