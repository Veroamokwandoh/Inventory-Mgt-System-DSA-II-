package com.example.inventorymanagement;
import java.sql.*;

public class Database {
    public static Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");
            return connect;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}