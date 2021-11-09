package dk.helleberg;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> itemsList;
    private int inventoryLimit;

    public Inventory () {
        this.itemsList = new ArrayList<Item>();
        this.inventoryLimit = 5;
    }

    public void showInventory () {
        for (int i = 0; i < (this.inventoryLimit / 5); i++) {
            for (int j = i * 5; j < this.itemsList.size() && j < (i * 5) + 5; j++) {
                System.out.print("[" + this.itemsList.get(i).getName() + "]");
            }
            System.out.println();
        }
    }

    public void addToInventory (Item item) {
        if (this.itemsList.size() < this.inventoryLimit) {
            this.itemsList.add(item);
        } else {
            System.out.println("inventory is full.");
        }
    }

    public void removeFromInventory () {

    }
}
