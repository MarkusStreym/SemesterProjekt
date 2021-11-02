package dk.helleberg;

public class Fish extends Item {
    private int dangerLevel;

    public Fish (String name, int dangerLevel, int itemId) {
        setId(itemId);
        setName(name);
        this.dangerLevel = dangerLevel;
    }


    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
