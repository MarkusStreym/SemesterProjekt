package dk.helleberg;

public class Room {
    private int roomId;
    private int itemId;

    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

    private boolean hasItem;
    private String itemType;
    private Item item;
    private Player player;

    public Room () {
        this.itemId = assignId();
        this.roomId = assignId();
    }

    public boolean assignItem(int indexZero) {
        return this.hasItem = this.roomId <= Math.sqrt(indexZero);
    }

    private static int assignId() {
        return (int)(Math.random() * 101);
    }

    public void assignItemType() {
        if (itemId <= 50) {
            this.item = new Pollutants("░", 1, itemId);
            this.itemType = "Pollutants";
        } else if (itemId <= 80) {
            this.item = new Fish("▒", 1, itemId);
            this.itemType = "Fish";
        } else {
            this.item = new AirBubble("▓", 1, itemId);
            this.itemType = "AirBubble";
        }
    }

    public static Item createItem() {
        return new Pollutants("░", 1, 1);
    }

    public boolean isHasItem() {
        return this.hasItem;
    }

    public boolean isHasPlayer() {
        if (this.player != null) {
            return true;
        } else {
            return false;
        }
    }

    public Item getItem() {
        return this.item;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
