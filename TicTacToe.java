import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private String[] player;
    private boolean computer_on = false;

    public static void start() {

        boolean computer_on = true;
        String[] player = {"X", "O"};
        game(player, computer_on);

    }

    public static void start(String[] player) {

        boolean computer_on = true;
        game(player, computer_on);

    }

    public static void start(boolean computer_on) {

        String[] player = {"X", "O"};
        game(player, computer_on);

    }

    public static void start(String[] player, boolean computer_on) {

        game(player, computer_on);

    }

    private static void game(String[] player, boolean computer_on) {

        Scanner scn = new Scanner(System.in);
        int player_turn = 0;
        int pos = 0;
        String result = null;
        String restart = "yes";
        String[] board = setBoard();

        while (restart.contains("y")) {
            while (true) {
                if (player_turn == 0  || !computer_on) {
                    printBoard(board, player[player_turn], pos);
                    System.out.printf("%s turn (1-9): ", player[player_turn]);
                    pos = scn.nextInt() - 1;
                    System.out.println();
                }

                else { pos = computer(pos, board); }

                while (true) {
                    if (pos < 0 || pos > 9) {
                        System.out.print("Number is not 1-9. Try again: ");
                        pos = scn.nextInt() - 1;
                        System.out.println();
                    } else
                        break;
                }

                if (board[pos].equals("-")) {
                    board[pos] = player[player_turn];
                    break;
                } else {
                    System.out.println("\nPlace already taken. \n");
                }
            }

            result = checkWin(player[player_turn], board, result);

            if (result != null) {
                printBoard(board, player[player_turn], pos);
                if (result.equals("tie")) {
                    System.out.print("\nIt is a tie! Do you want to play again? (yes) (no): ");
                } else {
                    System.out.printf("\n%s won!\nDo you want to play again? (yes) (no): ", player[player_turn]);
                }

                restart = scn.next().toLowerCase();

                if (restart.contains("y")) {

                    System.out.println();
                    board = setBoard();
                    result = null;
                    player_turn = 0;
                    pos = 0;

                } else break;

            } else {
                player_turn = switchPlayer(player_turn);
            }
        }
    }

    private static void printBoard(String[] board, String player, int pos) {
        for (int i = 0; i < board.length; i++) {
                System.out.print(board[i] + "\t");
            if ((i == 2) || (i == 5) || (i == 8)) {
                System.out.println();
            }
        }
    }

    private static int computer(int pos, String[] board) {
        Random random = new Random();
        int comp_pos = 0;
        while (!board[comp_pos].equals("-")) {
            comp_pos = random.nextInt(9);
        }
        return comp_pos;
    }

    private static int switchPlayer(int player_turn) {
        if (player_turn == 0)
            return 1;
        else
            return 0;
    }

    private static String checkWin(String player, String[] board, String result) {
        if (board[0].equals(player) && board[1].equals(player) && board[2].equals(player)
        ||  board[3].equals(player) && board[4].equals(player) && board[5].equals(player)
        ||  board[6].equals(player) && board[7].equals(player) && board[8].equals(player)
        ||  board[0].equals(player) && board[3].equals(player) && board[6].equals(player)
        ||  board[1].equals(player) && board[4].equals(player) && board[7].equals(player)
        ||  board[2].equals(player) && board[5].equals(player) && board[8].equals(player)
        ||  board[0].equals(player) && board[4].equals(player) && board[8].equals(player)
        ||  board[2].equals(player) && board[4].equals(player) && board[6].equals(player)) {

            result = player;

            }

        result = isTie(result, board);
        return result;
    }

    private static String isTie(String result, String[] board) {
        int taken = 0;

        for (String s : board) {
            if (!s.equals("-")) {
                taken++;
            }
        }

        if (taken == 9) {
            result = "tie";
        }

        return result;
    }

    private static String[] setBoard() {
        return new String[]{
                "-", "-", "-",    // 1,2,3 - 4,5,6 - 7,8,9                                             / -1
                "-", "-", "-",   // 1,4,7 - 2,5,8 - 3,6,9     [WIN ROWS, COLUMNS, DIAGONALS]          / -1
                "-", "-", "-"   // 1,5,9 - 3,5,7                                                     / -1
        };
    }

    //    -  |  -  |  -
    //    --- ----- ---
    //    -  +  -  +  -
    //    --- ----- ---
    //    -  |  -  |  -
}
