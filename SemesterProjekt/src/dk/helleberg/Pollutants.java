package dk.helleberg;

public class Pollutants extends Item {
     private int toxicity;

     public Pollutants (String name, int toxicity, int itemId) {
          setId(itemId);
          setName(name);
          this.toxicity = toxicity;
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
