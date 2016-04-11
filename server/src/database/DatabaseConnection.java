package database;

import java.sql.*;

public class DatabaseConnection {

		
	  public static void main (String args[]){
	    
	    try {
	      String driver = "com.mysql.jdbc.Driver";
	      Class.forName(driver);
	      String url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7114844";
	      String user = "sql7114844";
	      String pass = "iTPjBncAf7";
	      Connection con = DriverManager.getConnection(url, user, pass);
	      Statement cmd = con.createStatement();
	      String query = "select * from Prova ;";
	      ResultSet res = cmd.executeQuery(query);
	     while (res.next()) {
	        System.out.print(res.getString("ID")+" ");
	        System.out.println(res.getString("Nome"));
	      }
	      res.close(); // chiudere le risorse DB è obbligatorio
	      cmd.close();
	      con.close();
	    }

	    catch (SQLException e){
	      e.printStackTrace();
	    }

	    catch (ClassNotFoundException e){
	      e.printStackTrace();
	    }
	  }
	}
