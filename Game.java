package battleship;

import java.util.*;
import static battleship.Board.*;
import static battleship.Player.*;

public class Game {

    protected static void takeShot(Player player1, Player player2) {
        Scanner scanner = new Scanner(System.in);
        board(player2.getSecretBoard(), player2.getSecretSb());
        System.out.println("---------------------");
        board(player1.getVisibleBoard(), player1.getVisibleSb());
        System.out.printf("%s, it's your turn:\n", player1.name);
        boolean moveMade = false;
        while (!moveMade) {
            try {
                String input = scanner.nextLine();
                int x = stringToInt(input.substring(0, 1));
                int y = Integer.parseInt(input.substring(1)) - 1;
                if (player2.getVisibleBoard()[x][y].equals("O ")) {
                    player2.getSecretBoard()[x][y] = "X ";
                    player2.getVisibleBoard()[x][y] = "X ";
                    if (checkIfSank(player2) & player2.getShipsPosition().length() != 0) {
                        System.out.println("You sank a ship!");
                        moveMade = true;
                    } else if (player2.getShipsPosition().length() != 0) {
                        System.out.println("You hit a ship!");
                        moveMade = true;
                    }
                } else if (player2.getVisibleBoard()[x][y].equals("~ ")){
                    player2.getSecretBoard()[x][y] = "M ";
                    player2.getVisibleBoard()[x][y] = "M ";
                    System.out.println("You missed!");
                    moveMade = true;
                }
                moveMade = true;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException
                    | StringIndexOutOfBoundsException e) {
                System.out.println("Error! You entered the wrong coordinates!");
            }
        }
    }


    private static boolean checkIfSank(Player player2) {
        for (int j = 0; j < player2.getShipsPosition().length(); j += 5) {
            int shipDirection = player2.getShipsPosition().charAt(j) - 48;
            int shipNose = player2.getShipsPosition().charAt(j + 1) - 48;
            int shipAss = player2.getShipsPosition().charAt(j + 2) - 48;
            int shipSize = player2.getShipsPosition().charAt(j + 4) - 48;
            int countX = 0;
            while (shipAss <= shipNose) {
                if (player2.getShipsPosition().charAt(j + 3) == 'f') {
                    if (player2.getSecretBoard()[shipDirection][shipAss].equals("X ")) {
                        countX++;
                    }
                } else {
                    if (player2.getSecretBoard()[shipAss][shipDirection].equals("X ")) {
                        countX++;
                    }
                }
                if (countX == shipSize) {
                    player2.getShipsPosition().delete(j, j + 5);
                    return true;
                }
                shipAss++;
            }
        }
        return false;
    }
}