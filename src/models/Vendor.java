
package models;


public abstract class Vendor {
    protected int id;
    protected String name;
    protected String email;
    protected String location;
    protected String isActive;
    protected int rating;

    public Vendor(int id, String name, String email, String location,String isActive, int rating) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.isActive = isActive;
        this.rating = rating;
    }

    // Abstract method to be implemented by children
    public abstract void displaySpecificRoleInfo();

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
}
