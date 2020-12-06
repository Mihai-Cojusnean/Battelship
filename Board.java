package battleship;

import java.util.Scanner;
import static battleship.Player.*;

public class Board {

    static int stringToInt(String s) {
        String val = "ABCDEFGHIJ";
        return val.indexOf(s.charAt(0));
    }

    private static boolean checkDistance(int[] xy, String[][] visibleBoard) {
        int row1 = Math.min(xy[0], xy[2]);
        int row2 = Math.max(xy[0], xy[2]);
        int col1 = Math.min(xy[1], xy[3]);
        int col2 = Math.max(xy[1], xy[3]);

        int i = row1 == 0 ? 0 : row1 - 1;
        for (; i <= row2 + 1 & i < 10; i++) {
            int j = col1 == 0 ? 0 : col1 - 1;
            for (; j <= col2 & j < 10; j++) {
                if (!(visibleBoard[i][j].equals("~ "))) {
                    return false;
                }
            }
        }
        return true;
    }

    protected static void placeShip(Player player) {

        clearBoard(player.getVisibleBoard());
        board(player.getVisibleBoard(), player.getVisibleSb());
        clearBoard(player.getSecretBoard());

        Scanner scanner = new Scanner(System.in);
        int[] xy = new int[4];
        int shipAss;   /* i like big buts and cannot lie */
        int shipNose ;
        int shipDirection;
        int cellsNeeded = 4;  /* with 0 */
        int difference;
        int[] shipSize = {2, 3, 3, 4, 5};
        String[] ships = {"Destroyer (2 cells)", "Cruiser (3 cells)", "Submarine (3 cells)", "Battleship (4 cells)",
                "Aircraft Carrier (5 cells)"};
        while (cellsNeeded >= 0) {
            try {
                System.out.printf("Enter the coordinates of the %s:\n", ships[cellsNeeded]);
                String[] input = scanner.nextLine().split(" ");
                xy[0] = stringToInt(input[0].substring(0, 1));
                xy[1] = Integer.parseInt(input[0].substring(1)) - 1;
                xy[2] = stringToInt(input[1].substring(0, 1));
                xy[3] = Integer.parseInt(input[1].substring(1)) - 1;
                boolean possible = checkDistance(xy, player.getVisibleBoard());
                if (xy[1] != xy[3] && xy[0] != xy[2]) {
                    throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
                }
                if (!possible) {
                    throw new IllegalArgumentException("Error! You placed it too close to another one. Try again:");
                }
                if (xy[0] != xy[2]) {
                    shipDirection = xy[1];
                    shipNose = Math.max(xy[0], xy[2]);
                    shipAss = Math.min(xy[0], xy[2]);
                } else {
                    shipDirection = xy[0];
                    shipNose = Math.max(xy[1], xy[3]);
                    shipAss = Math.min(xy[1], xy[3]);
                }
                difference = shipNose - shipAss + 1;
                if (difference != shipSize[cellsNeeded]) {
                    throw new IllegalArgumentException("Error! Wrong length of the " +
                            ships[cellsNeeded].replaceAll(" \\(\\b.+", "") + ". Try again:");
                }
                player.getShipsPosition().append(shipDirection).append(shipNose).append(shipAss)
                        .append(xy[0] == xy[2] ? 'f' : 't').append(difference);
                while (shipAss <= shipNose) {
                    if (xy[0] != xy[2]) {
                        player.getVisibleBoard()[shipAss][shipDirection] = "O ";
                    } else {
                        player.getVisibleBoard()[shipDirection][shipAss] = "O ";
                    }
                    shipAss++;
                }
                cellsNeeded--;
                board(player.getVisibleBoard(), player.getVisibleSb());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input! Please Enter Coordinates Correctly.");
            }
        }
    }
}