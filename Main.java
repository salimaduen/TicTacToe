import java.util.Scanner;
public class Main {
    static TicTacToe game = new TicTacToe();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int input;
        do {
            System.out.println("Welcome to Tic Tac Toe!");
            System.out.println("1. Start game");
            System.out.println("2. Game stats");
            System.out.println("3. Quit");
            input = sc.nextInt();
            options(input);
        } while(input != 3);


    }

    public static void options(int input) {
        if (input == 1) {
            game.playGame(true);
        }

        if (input == 2) {
            game.gameStats();
        }
    }
}