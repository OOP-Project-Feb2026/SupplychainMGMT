
package models;

public class Wholesaler extends Vendor {
    private String warehouseLocation;

    public Wholesaler(int id, String name, String email, String location, boolean isActive, int rating, String warehouseLocation) {
        super(id, name, email, location, isActive, rating);
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public void displaySpecificRoleInfo() {
        System.out.println("Warehouse situated at: " + warehouseLocation);
    }
}


