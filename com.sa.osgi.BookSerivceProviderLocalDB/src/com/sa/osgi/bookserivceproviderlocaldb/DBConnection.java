package com.sa.osgi.bookserivceproviderlocaldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	
	public static Connection ConnectToDB() {
       
		Connection conn = null;
        
        try {

        	Class.forName("org.sqlite.JDBC");
                       
            String url = "jdbc:sqlite:C:/Sqlite/dbs/BookStoreDB.db";
            conn = DriverManager.getConnection(url);
            
            //System.out.println("Connection to SQLite has been established.");
            
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        
        return conn;
    }
}
