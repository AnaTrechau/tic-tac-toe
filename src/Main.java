import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        boolean victory;
        String firstPlayer = "";
        Scanner scanner = new Scanner(System.in);


        outputBoard(board);

        while (!(firstPlayer.equals("0") || firstPlayer.equals("X"))) {
            System.out.println("Who starts? Choose (0 or X)");
            firstPlayer = scanner.nextLine();
        }


        while(true) {
            System.out.println("Choose a position (1 to 9):");

            String input = scanner.nextLine();
            int convertedInput;

            try {
                convertedInput = Integer.parseInt(input);
                char convertedPlayerPiece = firstPlayer.charAt(0);

                placePieceOnBoard(board, convertedInput, convertedPlayerPiece);

                victory = wasVictory(board, convertedPlayerPiece);

                if(victory) {
                    break;
                }
                firstPlayer = changePlayer(firstPlayer);


            }
            catch (Exception e) {
                System.out.println("Position should be a number.");
            }
        }
        System.out.println("Player " + firstPlayer + " won!");
    }

    public static void outputBoard(char[][] array) {
        for (char[] tile : array) {
            System.out.println(treatArray(tile));
        }
        System.out.println();
    }

    public static String treatArray(char[] array) {
        return Arrays.toString(array)
                .replace('[', ' ')
                .replace(',', '|')
                .replace(']', '|');
    }

    public static void placePieceOnBoard(char[][] board, int position, char piece) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (position <= 3 && position == row + 1) {
                    board[column][row] = piece;
                    break;
                }
                if (position > 3  && position <= 6 && position == row + 4) {
                    board[column + 1][row] = piece;
                    break;
                }
                if (position > 6  && position == row + 7) {
                    board[column + 2][row] = piece;
                    break;
                }
            }
        }
        System.out.println();
        outputBoard(board);
    }

    public static String changePlayer(String player) {
        switch (player) {
            case "X" -> {
                return "0";
            }
            case "0" -> {
                return "X";
            }
            default -> {
                return player;
            }
        }
    }

    public static boolean wasVictory(char[][] board, char piece) {

        boolean diagonalWin1 = board[0][0] == piece && board[1][1] == piece && board[2][2] == piece;
        boolean diagonalWin2 = board[0][2] == piece && board[1][1] == piece && board[2][0] == piece;

        return wasRowVictory(board, piece) || wasColumnVictory(board, piece) ||diagonalWin1 || diagonalWin2;
    }

    public static boolean wasRowVictory(char[][] board, char piece) {
        for (char[] row : board) {
            int countItem = 0;
            for (char item : row) {
                if (item == piece) {
                    countItem++;
                }
            }
            if (countItem == row.length) {
                return true;
            }
        }
        return false;
    }

    public static boolean wasColumnVictory(char[][] board, char piece){
        for (int row = 0; row < board.length; row++) {
            char[] columnResult = new char[3];
            for (int column = 0; column < board.length; column++) {
                columnResult[column] = board[column][row];
            }

            int countItemColumn = 0;
            for (char item : columnResult) {
                if (item == piece) {
                    countItemColumn++;
                }
            }
            if (countItemColumn == board.length) {
                return true;
            }
        }
        return false;
    }
}
