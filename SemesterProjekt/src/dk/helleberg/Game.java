package dk.helleberg;

public class Game {
    private Room[][] rooms = new Room[64][64];
    private Player player = new Player();
    private static boolean running = true;
    private GameController gameController = new GameController(player, rooms);

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

    public void initialize() {
        initMap();
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
            System.out.println("");
        }
    }

    public void run() {
        while (running) {
            render();
            gameController.moveInput();
        }
        System.out.println("\nGame Closed");
    }

    public static void setRunning(boolean run) {
        running = run;
    }

    public static boolean getRunning() {
        return running;
    }
}
