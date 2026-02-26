package dbtests;

import database.DBOperations;

public class OperationTest {
    public static void main(String[] args) {
        DBOperations dbc = new DBOperations();

        dbc.insert(2233, "Edith", "Wholesaler", "edith@gmail.com", "Nairobi", true, 4);
        
        dbc.insert(2266, "June", "Retailer", "june@gmail.com", "Nairobi", true, 5);
        
        dbc.insert(2234, "Edith", "Manufacturer", "edith@outlook.com", "Nairobi", true, 3);

        dbc.select(2233);

        dbc.selectAll();

        dbc.update(2233, "Edith", "Retailer", "agai.ed@outlook.com", "Kisumu", true, 5);

        dbc.delete(2233);
        
        dbc.userAuth("admin","ObjectOriented_2026");

        dbc.closeConnection();
    }
}
