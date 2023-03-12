public class Board {

    // Board will be a 2D array of Square objects
    private final Square[][] board;

    public Board() {
        // Constructor initializes board 3x3
        board = new Square[3][3];

        // Method for creating board
        this.createBoard();
    }

    /**
     *  Method for inserting symbols into the board
     * @param row - Desired row
     * @param column - Desired column
     * @param player - Player inserting symbol
     * @return - Returns whether it is possible to insert or not (True | False)
     */
    public boolean insert(int row, int column, Player player) {
        if (! (row >= 0 && row <= 2) || !(column >= 0 && column <= 2)) {
            System.out.println("Not a valid position. Try again!");
            return false;
        }
        if (! board[row][column].getIsOccupied()) {
            board[row][column].setOccupied();
            board[row][column].setSymbol(player.getSymbol(), player);
            return true;
        }

        System.out.println("Position already occupied by " + board[row][column].getPlayer().getPlayerName());
        return false;
    }

    /**
     *  Method for printing the board
     */
    public void printBoard() {
        int row = 0;
        System.out.println("\t  0   1   2");
        for (int i = 0; i < 7; ++i) {
            if (i % 2 == 0) {
                System.out.println("\t-------------");
            } else {
                //System.out.println("|   |   |   |");
                System.out.println(row + "\t|" + board[row][0] + "|" + board[row][1] + "|" + board[row][2] + "|" );
                row++;
            }
        }

    }

    /**
     * Creates a board by filling every space with a Square object
     */
    public void createBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                this.board[i][j] = new Square();
            }
        }
    }

    public Square[][] getBoard() {
        return this.board;
    }

}
