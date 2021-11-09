package dk.helleberg;

import dk.helleberg.Command.Command;
import dk.helleberg.Command.CommandWord;

public class Move {
    private Player player;
    private Room[][] rooms;
    private int movementBonus;

    Move(Player player, Room[][] rooms) {
        this.movementBonus = 0;
        this.player = player;
        this.rooms = rooms;
    }

    public int getMovementBonus() {
        return movementBonus;
    }

    public void move(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go how long?");
            return;
        }
        CommandWord commandWord = command.getCommandWord();
        this.rooms[player.getPosY()][player.getPosX()].setPlayer(null);
        try {
            int moveAmount = Integer.parseInt(command.getSecondWord());
            // Can't be less than 0
            if ((commandWord == CommandWord.UP) && (player.getPosY() - moveAmount) >= 0) {
                player.setPosY(player.getPosY() - moveAmount);
            }
            // Can't be less than 0
            else if ((commandWord == CommandWord.LEFT) && (player.getPosX() - moveAmount) >= 0) {
                player.setPosX(player.getPosX() - moveAmount);
            }
            // Can't be greater than rooms array length (etc. 64)
            else if ((commandWord == CommandWord.DOWN) && (player.getPosY() + moveAmount) <= rooms.length) {
                player.setPosY(player.getPosY() + moveAmount);
            }
            // Can't be greater than rooms array length (etc. 64)
            else if ((commandWord == CommandWord.RIGHT) && (player.getPosX() + moveAmount) <= rooms[0].length) {
                player.setPosX(player.getPosX() + moveAmount);
            } else {
                System.out.println("Out of bounds...");
            }
        } catch (Exception e)
        {
            System.out.println("Write a valid integer");
        }
        this.rooms[player.getPosY()][player.getPosX()].setPlayer(player);
    }
}
