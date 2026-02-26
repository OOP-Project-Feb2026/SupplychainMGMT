package database;

import java.sql.*;

public abstract class DBConnection {
    protected String url = "jdbc:postgresql://aws-1-eu-central-1.pooler.supabase.com:6543/postgres?sslmode=require"; //sslmode=require is essential for cloud
    //switched to shared pooler because of post 5432 block shared works better for ipv4  networks
    protected String username = "postgres.tqiprmxpkdodjzgxyiyo";
    protected String password = "ObjectOriented_2026"; 
    public Connection con;
    
    public DBConnection(){
            //Step 1: Load Driver
            try{
                Class.forName("org.postgresql.Driver");
                System.out.println("Driver loading sucessful");
            }catch(ClassNotFoundException cnfe){
                System.out.println("Driver Loading Failed"+ cnfe.getMessage());
            }
            //Step 2: Establish connection
            try{
                con = DriverManager.getConnection(url,username,password);
                System.out.println("Connection sucessful");
            }catch(SQLException sqle){
                System.out.println("Conncetion failed: "+ sqle.getMessage());
            }  
    }
    
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed successfully.");
            }
        } catch (SQLException sqle) {
            System.err.println("Error while closing connection: " + sqle.getMessage());
        }
    }

    // Abstract methods as per assignment requirements
    public abstract void insert(int id, String name, String type, String email, String location, boolean status, int rating);
    public abstract void update(int id, String name, String type, String email, String location, boolean status, int rating);
    public abstract void delete(int id);
    public abstract String[] select(int id);
    public abstract void selectAll();
    public abstract boolean userAuth(String username, String password);
}

