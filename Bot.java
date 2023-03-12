import java.util.Random;
import java.util.ArrayList;
public class Bot extends Player {
    private final Random random = new Random();
    final private Board board;
    final private Player opponent;

    public Bot(Board board, Player opponent) {
        super("O", "AI");
        this.board = board;
        this.opponent = opponent;
    }

    public void makeMove() {


        while(true) {
            int row = random.nextInt(0,3);
            int column = random.nextInt(0,3);

            if (board.insert(row,column, this)) {
                break;
            }
        }
    }

    public void analyzeBoard() {
        if (! (checkColumns() || checkRows())) {
            makeMove();
        }
    }

    public boolean checkRows() {
        ArrayList<Square> row = new ArrayList<>();

        for (int i = 0; i < board.getBoard().length; ++i) {
            int counter = 0;
            int self = 0;
            for (int j = 0; j < board.getBoard().length; ++j) {
                row.add(board.getBoard()[i][j]);
                if (row.get(j).getPlayer() == opponent) {
                    counter++;
                } else if (row.get(j).getPlayer() == this) {
                    self++;
                }
            }

            if (counter == 2 && self != 1) {
                if (random.nextBoolean()) {
                    counterPlay(row).setSymbol(this.symbol, this).setOccupied();
                    return true;
                }
            }
            row.clear();
        }
        return false;
    }


    public boolean checkColumns() {
        ArrayList<Square> column = new ArrayList<>();

        for (int i = 0; i < board.getBoard().length; ++i) {
            int counter = 0;
            int self = 0;
            for (int j = 0; j < board.getBoard().length; ++j) {
                column.add(board.getBoard()[j][i]);
                if (column.get(j).getPlayer() == opponent) {
                    counter++;
                } else if (column.get(j).getPlayer() == this) {
                    self++;
                }
            }

            if (counter == 2 && self != 1) {
                if (random.nextBoolean()) {
                    counterPlay(column).setSymbol(this.symbol, this).setOccupied();
                    return true;
                }
            }
            column.clear();
        }
        return false;
    }

    public Square counterPlay(ArrayList<Square> row) {
        Square counter = null;
        for (Square s : row) {
            if (s.getPlayer() == null) {
                counter = s;
                break;
            }
        }
        return counter;
    }
}
