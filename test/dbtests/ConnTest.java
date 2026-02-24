package dbtests;

import database.*;
        
public class ConnTest {
    public static void main(String[ ] args){
        System.out.println("--- testing supabase conn ---");
        
        // 1. Initialize the connection
        DBConnection db = new DBOperations();
        
        // 2. Check if connection was successful
        if (db.con != null) {
            System.out.println("connnected!");
            // 3. Close the connection when done
            db.closeConnection();
        } else {
            System.out.println("failed to connect");
        }
        
        System.out.println("--- end of test ---");
    }
}
