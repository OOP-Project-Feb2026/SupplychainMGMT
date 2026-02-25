package database;

import java.sql.*;

public class DBOperations extends DBConnection {

    @Override
    public void insert(int id, String name, String type, String email, String location, boolean is_active, int rating) {
        // Validation Check
        if (!util.ValidationUtil.isValidRating(rating)) {
            System.err.println("Validation Failed: Rating must be between 1 and 5.");
        return; 
        }
    
        if (!util.ValidationUtil.isValidEmail(email)) {
            System.err.println("Validation Failed: Invalid email format.");
            return;
        }
        
        String query = "INSERT INTO vendors (vendor_id, vendor_name, vendor_type, vendor_email, vendor_location, is_active, vendor_rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, type);
            pst.setString(4, email);
            pst.setString(5, location);
            pst.setBoolean(6, is_active);
            pst.setDouble(7, rating);
            pst.executeUpdate();
            System.out.println("Vendor Added");
        } catch (SQLException sqle) { System.err.println("Insert Error: " + sqle.getMessage()); }
    }

    @Override
    public void update(int id, String name, String type, String email, String location, boolean is_active, int rating) {
        String query = "UPDATE vendors SET vendor_name=?, vendor_type=?, vendor_email=?, vendor_location=?, is_active=?, vendor_rating=? WHERE vendor_id=?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, type);
            pst.setString(3, email);
            pst.setString(4, location);
            pst.setBoolean(5, is_active);
            pst.setDouble(6, rating);
            pst.setInt(7, id);
            pst.executeUpdate();
            System.out.println("Vendor Updated");
        } catch (SQLException sqle) { System.err.println("Update Error: " + sqle.getMessage()); }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM vendors WHERE vendor_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Vendor Deleted.");
        } catch (SQLException sqle) { System.err.println("Delete Error: " + sqle.getMessage()); }
    }

    @Override
    public String[] select(int id) {
        String[] details = new String[7];
        String query = "SELECT * FROM vendors WHERE vendor_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                details[1] = rs.getString("vendor_id");
                details[2] = rs.getString("vendor_name");
                System.out.println("Found Vendor: " + "id:"+ details[1]+" Name: "+ details[2] );
            }
        } catch (SQLException sqle) { System.err.println("Select Error: " + sqle.getMessage()); }
        return details;
    }

    @Override
    public void selectAll() {
        String query = "SELECT * FROM vendors";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("vendor_id") + " | Name: " + rs.getString("vendor_name"));
            }
        } catch (SQLException sqle) { System.err.println("SelectAll Error: " + sqle.getMessage()); }
    }

    //check for user to log in
    @Override
    public boolean userAuth(String username, String password){
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try(PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Successfully logged in");
                return true; 
            }
        }catch(SQLException sqle){
            System.err.println("Login Error: " + sqle.getMessage());
        }
        return false;
    }
}