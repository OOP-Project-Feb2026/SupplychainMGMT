
package models;

public class Manufacturer extends Vendor {
    private int factoryCapacity;

    public Manufacturer(int id, String name, String email, String location, boolean isActive, int rating, int factoryCapacity) {
        super(id, name, email, location, isActive, rating);
        this.factoryCapacity = factoryCapacity;
    }

    @Override
    public void displaySpecificRoleInfo() {
        System.out.println("Manufacturing Capacity: " + factoryCapacity + " units/day");
    }

}
