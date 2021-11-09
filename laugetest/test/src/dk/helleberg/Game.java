package dk.helleberg;

import dk.helleberg.Command.Command;
import dk.helleberg.Command.CommandWord;
import dk.helleberg.Command.Parser;

public class Game {
    private Parser parser;
    private Room[][] rooms = new Room[64][64];
    private Player player = new Player();
    private static boolean running = true;
    private Move movement = new Move(player, rooms);

    private void initMap() {

        // Draw init map
        for (int i = 0; i < this.rooms.length; i++) {
            for (int j = 0; j < this.rooms[i].length; j++) {
                this.rooms[i][j] = new Room();
            }
        }

        for (int i = 0; i < this.rooms.length; i++) {
            for (int j = 0; j < this.rooms[i].length; j++) {
                if (rooms[i][j].assignItem(i)) {
                    rooms[i][j].assignItemType();
                }
            }
        }

        // init player location
        this.rooms[player.getPosY()][player.getPosX()].setPlayer(player);
    }

    public void debug () {
        for (int i = 0; i <= 14; i++) {
            player.getInventory().addToInventory(rooms[0][0].createItem());
        }
    }

    public void initialize() {
        initMap();
        render();
        parser = new Parser();
    }

    private void render() {

        for (int i = 0; i < this.rooms.length; i++) {
            for (int j = 0; j < this.rooms[i].length; j++) {
                if (this.rooms[i][j].isHasPlayer()) {
                    System.out.print(this.player.getName());
                } else if (this.rooms[i][j].isHasItem()) {
                    System.out.print(this.rooms[i][j].getItem().getName());
                } else {
                    System.out.print("█");
                }
            }
            System.out.println();
        }
        // Show inventory - Maybe move to better place
        player.getInventory().showInventory();
    }

    private void update() {
        if (this.rooms[player.getPosY()][player.getPosX()].isHasItem()) {
            System.out.println("Do you want to pick up the item.");
            Command command = parser.getCommand();
            processCommand(command);
        }
    }

    public void run() {
        System.out.println("Welcome to Trashbusters!\n" +
                "Above, you see the map of Trashbusters, this wonderful world is filled with precious trash, \n" +
                "which you must collect and sell for currency to continue on your legendary adventure!\n" +
                "(And to save the world of course!) \n");
        printCommands();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            update();
        }

        System.out.println("\nGame Closed");
    }

    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        if (commandWord == CommandWord.HELP) {
            printCommands();
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.LEFT ||commandWord == CommandWord.UP||commandWord == CommandWord.RIGHT ||commandWord == CommandWord.DOWN) {
            if (Integer.parseInt(command.getSecondWord()) <= player.getMoveSpeed() + movement.getMovementBonus()) {
                movement.move(command);
                render();
            } else {
                System.out.println("Your can not move this fast.\nYou must upgrade your movement speed by "
                        + (Integer.parseInt(command.getSecondWord())-player.getMoveSpeed() + movement.getMovementBonus()));
            }
        }
        else if (commandWord == CommandWord.NO) {
            System.out.println("Item not picked up.");
            render();
        }
        else if (commandWord == CommandWord.YES) {
            removeItem(player);
            render();
            System.out.println("Item picked up.");
        }
        return wantToQuit;
    }
    private void printCommands() {
        System.out.print("""
                'Help': See commands and descriptions.
                'Quit': Close the game.
                'Move input': Direction space distance, etc. 'right 1' or 'down 1'.
                'Remove': Removes an item from inventory, etc. 'remove 2'
                [1] [2] [3] [4] [5]
                [6] [7] [8] [9] [10]
                [11][12][13][14][15]
                [16][17][18][19][20]
                """);
    }

    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }

    private void removeItem(Player player) {
        rooms[player.getPosY()][player.getPosX()].setHasItem(false);
        player.getInventory().addToInventory(rooms[player.getPosY()][player.getPosX()].getItem());
    }
}
