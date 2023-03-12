import java.util.Scanner;

public class TicTacToe {
    // Board object
    private Board board;

    // Player objects
    private final Player player1;
    private  Player player2;

    // Winner of the current game
    private Player winner;

    // Keeps track of the players' turns.
    private Player currentTurn;

    private Bot ai;

    // Default constructor. Start game with default names (player1, player2)
    public TicTacToe() {
        player1 = new Player("X", "Player 1");
        player2 = new Player("O", "Player 2");
        this.currentTurn = player1;
    }


    /**
     * Second constructor in case the default names need to be overridden (player1 and player2)
     * @param name1 - Player 1's name
     * @param name2 - Player 2's name
     */
    public TicTacToe(String name1, String name2) {
        this.board = new Board();
        this.player1 = new Player("X", name1);
        this.player2 = new Player("O", name2);
        this.winner = null;
        this.currentTurn = player1;
    }

    public void playGame(Boolean AI) {
        // New board is created
        this.board = new Board();
        if (AI) {
            this.ai = new Bot(board, player1);
            player2 = this.ai;
        }
        // Game logic
        gameLogic(true);
    }



    /**
     * Method for playing the game
     */
    public void playGame() {
        // New board is created
        this.board = new Board();
        // Game logic
        gameLogic();
    }

    /**
     *  Method for printing game stats.
     *  Players' wins and losses.
     */
    public void gameStats() {
        System.out.println(player1.getPlayerName() + ":");
        System.out.println("Wins: " + player1.getWins());
        System.out.println("Losses: " + player1.getLosses() + "\n");

        System.out.println(player2.getPlayerName() + ":");
        System.out.println("Wins: " + player2.getWins());
        System.out.println("Losses: " + player2.getLosses() + "\n");
    }

    /**
     * Method asking player to insert/place a symbol on the board
     */
    public void makeInsertion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("It is currently " + currentTurn.getPlayerName() + "'s turn. (" + currentTurn.getSymbol() + ")");

        while (true) {
            System.out.println("Enter a row (0, 1, 2):");
            int row = sc.nextInt();

            System.out.println("Enter a column (0, 1, 2):");
            int column = sc.nextInt();

            if (board.insert(row, column, currentTurn)) {
                break;
            }
        }

    }

    /**
     * Alternate the players' turn
     */
    public void alternatePlayers() {
        if (currentTurn == player1) {
            currentTurn = player2;
        }

        else if (currentTurn == player2) {
            currentTurn = player1;
        }
    }

    /**
     * Main game logic
     */
    public void gameLogic() {
        // Starts turns at 0
        int turns = 0;
        // While loop - If there is a winner it stops.
        // If the max. possible turns have been achieved, it also stops,
        while (this.winner == null && turns < 9) {
            board.printBoard();
            makeInsertion();
            winCondition();
            this.alternatePlayers();
            turns++;
        }

        board.printBoard();

        if (this.winner == null) {
            System.out.println("Game was a tie. Try again!");
        }

        else {
            System.out.println("Congratulations " + this.winner.getPlayerName() + "! You've won.");
            this.winner.playerWon();

            if (this.winner == player1) {
                player2.playerLost();
            } else {
                player1.playerLost();
            }

            this.winner = null;
        }
        currentTurn = player1;
    }


    public void gameLogic(boolean AI) {
        board.printBoard();
        // Starts turns at 0
        int turns = 0;
        // While loop - If there is a winner it stops.
        // If the max. possible turns have been achieved, it also stops,
        while (this.winner == null && turns < 9) {

            if (currentTurn == player2) {
                this.ai.analyzeBoard();
                board.printBoard();
            } else {
                makeInsertion();
            }
            winCondition();
            alternatePlayers();
            turns++;
        }

        board.printBoard();

        if (this.winner == null) {
            System.out.println("Game was a tie. Try again!");
        }

        else {
            System.out.println("Congratulations " + this.winner.getPlayerName() + "! You've won.");
            this.winner.playerWon();

            if (this.winner == player1) {
                player2.playerLost();
            } else {
                player1.playerLost();
            }

            this.winner = null;

        }
        currentTurn = player1;
    }

    /**
     * Method for checking any winning conditions on columns
     */
    public void checkColumns() {
        String columnSymbol = null;
        Player columnPlayer = null;

        for (int i = 0; i < board.getBoard().length; ++i) {
            for (int j = 0; j < board.getBoard()[i].length; ++j) {

                if (columnSymbol == null) {
                    columnSymbol = board.getBoard()[j][i].getSymbol();
                    columnPlayer = board.getBoard()[j][i].getPlayer();
                }


                if (! columnSymbol.equals(board.getBoard()[j][i].getSymbol()) || columnSymbol.equals("")) {
                    columnSymbol = null;
                    columnPlayer = null;
                    break;
                }

            }

            if ( columnSymbol != null) {
                this.winner = columnPlayer;
            }
        }
    }

    /**
     * Method for checking winning conditions diagonally
     */
    public void checkDiagonal() {
        Player player = board.getBoard()[0][0].getPlayer();
        if (player == board.getBoard()[1][1].getPlayer() && player == board.getBoard()[2][2].getPlayer()) {
            this.winner = player;
        }

        player = board.getBoard()[0][2].getPlayer();
        if (player == board.getBoard()[1][1].getPlayer() && player == board.getBoard()[2][0].getPlayer()) {
            this.winner = player;
        }
    }

    /**
     * Method for checking winning conditions on any row
     */
    public void checkRows() {
        String rowSymbol = null;
        Player rowPlayer = null;

        for (int i = 0; i < board.getBoard().length; ++i) {

            for (int j = 0; j < board.getBoard()[i].length; ++j) {

                if (rowSymbol == null) {
                    rowSymbol = board.getBoard()[i][j].getSymbol();
                    rowPlayer = board.getBoard()[i][j].getPlayer();
                }

                if (! rowSymbol.equals(board.getBoard()[i][j].getSymbol()) || rowSymbol.equals("")) {
                    rowSymbol = null;
                    rowPlayer = null;
                    break;
                }

            }

            if ( rowSymbol != null) {
                this.winner = rowPlayer;
            }
        }
    }

    /**
     * Method calls all three verification to see if there's any winning condition
     */
    public void winCondition() {
        checkDiagonal();
        checkRows();
        checkColumns();
    }
}
