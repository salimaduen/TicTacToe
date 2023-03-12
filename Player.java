public class Player {
    // The symbol that represents this player (Either X or O)
    protected final String symbol;

    // Player name optional. By default, is player1 and player2
    private final String playerName;
    private int wins;
    private int losses;


    public Player(String symbol, String playerName) { // Constructor for Player object
        this.symbol = symbol;
        this.playerName = playerName;
        this.wins = 0;
        this.losses = 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void playerWon() {
        this.wins++;
    }

    public void playerLost() {
        this.losses++;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }
}
