import java.util.Scanner;
public class Main {
    static TicTacToe game = new TicTacToe();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int input;
        do {
            System.out.println("Welcome to Tic Tac Toe!");
            System.out.println("1. Start 2-player game");
            System.out.println("2. Play against AI");
            System.out.println("3. Game stats");
            System.out.println("4. Quit");
            input = sc.nextInt();
            options(input);
        } while(input != 4);


    }

    public static void options(int input) {
        if (input == 1) {
            game.playGame();
        }

        if (input == 2) {
            game.playGame(true);
        }

        if (input == 3) {
            game.gameStats();
        }
    }
}