package dk.helleberg;

public class AirBubble extends Item {
    private int airAmount;

    public AirBubble (String name, int airAmount, int itemId) {
        setId(itemId);
        setName(name);
        this.airAmount = airAmount;
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
