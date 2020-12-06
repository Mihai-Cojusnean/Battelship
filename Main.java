package battleship;

import java.util.Scanner;

import static battleship.Board.*;
import static battleship.Game.takeShot;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        System.out.println("Player 1, place your ships on the game field");
        placeShip(player1);
        passMove();
        System.out.println("Player 2, place your ships to the game field");
        placeShip(player2);

        while (player1.getShipsPosition().length() != 0 && player2.getShipsPosition().length() != 0) {
            passMove();
            takeShot(player1, player2);
            passMove();
            takeShot(player2, player1);
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private static void passMove() {
        System.out.println("Press Enter and pass the move to another player");
        new Scanner(System.in).nextLine();
    }
}