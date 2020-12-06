package battleship;

public class Player {

    String name;

    private final String[][] visibleBoard = new String[10][10];
    private final String[][] secretBoard = new String[10][10];
    private final StringBuilder visibleSb = new StringBuilder();
    private final StringBuilder secretSb = new StringBuilder();
    private final StringBuilder shipsPosition = new StringBuilder();

    public Player(String name) {
        this.name = name;
    }

    public static void clearBoard(String[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = "~ ";
            }
        }
    }

    public static void board(String[][] board, StringBuilder sb) {
        sb.setLength(0);
        char letter = 'A';
        sb.append("  1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i < 10; i++) {
            sb.append(letter).append(" ");
            for (int j = 0; j < 10; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
            letter++;
        }
        System.out.print(sb);
    }

    public String[][] getVisibleBoard() {
        return visibleBoard;
    }

    public String[][] getSecretBoard() {
        return secretBoard;
    }

    public StringBuilder getVisibleSb() {
        return visibleSb;
    }

    public StringBuilder getSecretSb() {
        return secretSb;
    }

    public StringBuilder getShipsPosition() {
        return shipsPosition;
    }
}