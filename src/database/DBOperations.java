package database;

import java.sql.*;
import java.util.*;
import util.ValidationUtil;

public class DBOperations extends DBConnection {

    @Override
    public boolean insert(int id, String name, String type, String email, String location, boolean is_active, int rating) {
        if (!ValidationUtil.isValidEmail(email)) {
            System.err.println("Insert Error: Invalid email format.");
            return false;
        }
        if (!ValidationUtil.isValidRating(rating)) {
            System.err.println("Insert Error: Rating must be between 1 and 5.");
            return false;
        }

        String query = "INSERT INTO vendors (vendor_id, vendor_name, vendor_type, vendor_email, vendor_location, is_active, vendor_rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, type);
            pst.setString(4, email);
            pst.setString(5, location);
            pst.setBoolean(6, is_active);
            pst.setInt(7, rating);
            pst.executeUpdate();
            System.out.println("Vendor Added");
            return true;
        } catch (SQLException sqle) {
            System.err.println("Insert Error: " + sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(int id, String name, String type, String email, String location, boolean is_active, int rating) {
        if (!ValidationUtil.isValidEmail(email)) {
            System.err.println("Update Error: Invalid email format.");
            return false;
        }
        if (!ValidationUtil.isValidRating(rating)) {
            System.err.println("Update Error: Rating must be between 1 and 5.");
            return false;
        }

        String query = "UPDATE vendors SET vendor_name=?, vendor_type=?, vendor_email=?, vendor_location=?, is_active=?, vendor_rating=? WHERE vendor_id=?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, type);
            pst.setString(3, email);
            pst.setString(4, location);
            pst.setBoolean(5, is_active);
            pst.setInt(6, rating);
            pst.setInt(7, id);
            pst.executeUpdate();
            System.out.println("Vendor Updated");
            return true;
        } catch (SQLException sqle) {
            System.err.println("Update Error: " + sqle.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM vendors WHERE vendor_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Vendor Deleted.");
            return true;
        } catch (SQLException sqle) {
            System.err.println("Delete Error: " + sqle.getMessage());
            return false;
        }
    }

    @Override
    public String[] select(int id) {
        String[] details = new String[7];
        String query = "SELECT * FROM vendors WHERE vendor_id = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                details[0] = rs.getString("vendor_id");
                details[1] = rs.getString("vendor_name");
                details[2] = rs.getString("vendor_type");
                details[3] = rs.getString("vendor_email");
                details[4] = rs.getString("vendor_location");
                details[5] = rs.getString("is_active");
                details[6] = rs.getString("vendor_rating");
                System.out.println("Found Vendor: id:" + details[0] + " Name: " + details[1]);
            }
        } catch (SQLException sqle) {
            System.err.println("Select Error: " + sqle.getMessage());
        }
        return details;
    }

    @Override
    public boolean selectAll() {
        String query = "SELECT * FROM vendors";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("vendor_id") + " | Name: " + rs.getString("vendor_name"));
            }
            return true;
        } catch (SQLException sqle) {
            System.err.println("SelectAll Error: " + sqle.getMessage());
            return false;
        }
    }
    
    @Override
    public String getUserRole(String username, String password) {
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("role"); 
            }
        } catch (SQLException sqle) {
            System.err.println("Role fetch error: " + sqle.getMessage());
        }
        return null; 
    }

    @Override
    public boolean userAuth(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Successfully logged in");
                return true;
            }
        } catch (SQLException sqle) {
            System.err.println("Login Error: " + sqle.getMessage());
        }
        return false;
    }

    @Override
    public List<Object[]> getTableData() {
        List<Object[]> data = new ArrayList<>();
        String query = "SELECT vendor_id, vendor_name, vendor_type, vendor_email, is_active FROM vendors";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                data.add(new Object[]{
                    rs.getInt("vendor_id"),
                    rs.getString("vendor_name"),
                    rs.getString("vendor_type"),
                    rs.getString("vendor_email"),
                    rs.getBoolean("is_active")
                });
            }
        } catch (SQLException sqle) {
            System.err.println("Table Data Error: " + sqle.getMessage());
        }
        return data;
    }
}