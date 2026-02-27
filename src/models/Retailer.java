package models;


public class Retailer extends Vendor {
    private int storeNumber;

    public Retailer(int id, String name, String email, String location,String isActive, int rating, int storeNumber) {
        super(id, name, email, location,isActive, rating);
        this.storeNumber = storeNumber;
    }

    @Override
    public void displaySpecificRoleInfo() {
        System.out.println("Retail Store ID: " + storeNumber);
    }
} 

