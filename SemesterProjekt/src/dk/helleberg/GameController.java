package dk.helleberg;
import java.util.Scanner;

public class GameController {
    private Scanner input = new Scanner(System.in);
    private Player player;
    private Room[][] rooms;
    private int movementBonus;

    GameController(Player player, Room[][] rooms) {
        this.movementBonus = 0;
        this.player = player;
        this.rooms = rooms;
    }

    public void moveInput () {
        try {
            System.out.print("\nEnter direction and distance (Move input): ");
            String inputMove = input.nextLine().toLowerCase();
            String[] move = inputMove.split(" ", 2);
            boolean isMenuCommand = checkState(inputMove);
            if (Game.getRunning()) {
                try {
                    int moveAmountInt = Integer.parseInt(move[1]);

                    if (moveAmountInt <= player.getMoveSpeed()) {
                        move(move[0], moveAmountInt);
                    } else {
                        System.out.println("Input greater than current movement speed of " + (player.getMoveSpeed() + movementBonus) + ".");
                        moveInput();
                    }
                } catch (Exception e) {
                    if (!isMenuCommand){
                        System.out.println("Invalid input - Move input is to be written in type 'String' and 'int', separated by SPACE. (Error: " + e + ")");
                        System.out.println("Type 'Help' to see commands and descriptions.");
                    }
                    moveInput();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input - Must be of type 'String' (Error: " + e + ")");
            System.out.println("Type 'Help' to see commands and descriptions.");
            // Check if game is closed
            if (Game.getRunning()) {
                moveInput();
            }
        }
    }

    private void move(String moveDirection, int moveAmount) {
        this.rooms[player.getPosY()][player.getPosX()].setPlayer(null);
        // Can't be less than 0
        if (moveDirection.equals("up") && (player.getPosY() - moveAmount) >= 0) {
            player.setPosY(player.getPosY() - moveAmount);
        }
        // Can't be less than 0
        else if (moveDirection.equals("left") && (player.getPosX() - moveAmount) >= 0) {
            player.setPosX(player.getPosX() - moveAmount);
        }
        // Can't be greater than rooms array length (etc. 64)
        else if (moveDirection.equals("down") && (player.getPosY() + moveAmount) <= rooms.length) {
            player.setPosY(player.getPosY() + moveAmount);
        }
        // Can't be greater than rooms array length (etc. 64)
        else if (moveDirection.equals("right") && (player.getPosX() + moveAmount) <= rooms[0].length) {
            player.setPosX(player.getPosX() + moveAmount);
        } else {
            System.out.println("Out of bounds...");
            moveInput();
        }
        this.rooms[player.getPosY()][player.getPosX()].setPlayer(player);
    }

    private boolean checkState(String input) {
        // Close program
        if (input.equals("escape") || input.equals("esc")) {
            Game.setRunning(false);
            return true;
        }
        // Print commands
        else if (input.equals("help")) {
            printCommands();
            return true;
        }
        return false;
    }

    private void printCommands() {
        System.out.print("""
                'Help': See commands and descriptions.
                'Escape or ESC': Close the game.
                'Move input': Direction space distance, etc. 'right 1' or 'down 1'.
                """);
    }
}


// moveInput version 0.0.1
/*
try {
    System.out.print("Enter direction: ");
    String moveDirection = input.next().toLowerCase();
    input.nextLine();
    checkState(moveDirection);
    if (Game.getRunning()) {
        System.out.print("Enter move amount: ");
        String moveAmount = input.next().toLowerCase();
        checkState(moveAmount);
        if (Game.getRunning()) {
            try {
                int moveAmountInt = Integer.parseInt(moveAmount);

                if (Integer.parseInt(moveAmount) <= player.getMoveSpeed()) {
                    move(moveDirection, moveAmountInt);
                } else {
                    System.out.println("Input greater than current movement speed of " + (player.getMoveSpeed() + movementBonus));
                    input.nextLine();
                    moveInput();
                }
            } catch (Exception e) {
                // Erase direction input
                if (moveAmount.equals("cancel")) {
                    moveInput();
                } else {
                    System.out.println("Invalid input - Must be of type 'int' (Error: " + e + ")");
                    input.nextLine();
                }
            }
        }
    }
} catch (Exception e) {
    System.out.println("Invalid input - Must be of type 'String' (Error: " + e + ")");
    input.nextLine();
    // Check if game is closed
    if (Game.getRunning()) {
        moveInput();
    }
}
*/