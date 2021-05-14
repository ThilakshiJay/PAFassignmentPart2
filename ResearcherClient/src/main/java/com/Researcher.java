package com;

import java.sql.*;


public class Researcher {
	
	public Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 
	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher?serverTimezone=UTC", "root", "");
	 
	//For testing
	 System.out.print("Successfully connected"); 
	 
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	//insert to database
	public String insertItem(String researcherName, String researcherNIC, String researcherAddress, String researcherEmail, String researcherPhoneNo)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into resercher(`Name`,`NIC`,`Address`,`Email`,`PhoneNo`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, researcherName);
	 preparedStmt.setString(2, researcherNIC);
	 preparedStmt.setString(3, researcherAddress);
	 preparedStmt.setString(4, researcherEmail);
	 preparedStmt.setString(5, researcherPhoneNo);

	
	 
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newResearcher = readItems();
	 output =  "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}"; 
	 }
	 catch (Exception e)
	 {
	 output ="{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	public String readItems()
	 {
	 String output = "";
	 try
	 {
		 
	 Connection con = connect();
	 
	 if (con == null)
	 {
	 return "Error while connecting to the database for readingggg."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>researcherID</th><th> researcherName </th><th> researcherNIC </th><th> researcherAddress </th><th> researcherEmail </th><th> researcherPhoneNo </th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from resercher";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 //database Strings
		 String researcherID = Integer.toString(rs.getInt("ID"));
		 String researcherName = rs.getString("Name");
		 String researcherNIC = rs.getString("NIC");
		 String researcherAddress = rs.getString("Address");
		 String researcherEmail = rs.getString("Email");
		 String researcherPhoneNo = rs.getString("PhoneNo");		

		
	 // Add into the html table
		 output += "<tr><td><input id='hidResearcherIDUpdate' name='hidResearcherIDUpdate' type='hidden' value='" + researcherID + "'>"
				 + researcherName + "</td>";

		// output += "<td>" + researcherName + "</td>";
		 output += "<td>" + researcherNIC + "</td>";
		 output += "<td>" + researcherAddress + "</td>";
		 output += "<td>" + researcherEmail + "</td>";
		 output += "<td>" + researcherPhoneNo + "</td>";

		 
	 // buttons	
		 
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btnUpdate btn btn-secondary\"  data-researcherid=\"" + researcherID +"\" ></td>"
	 + "<td><input name=\"btnRemove\" type=\"button\" value=\"Remove\"class=\" btnRemove btn btn-danger\" data-researcherid=\"" + researcherID +"\" ></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	

	public String updateItem(String researcherID,  String researcherName, String researcherNIC, String researcherAddress, String researcherEmail, String researcherPhoneNo)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE resercher SET Name=?,NIC=?,Address=?,Email=?,PhoneNo=?WHERE ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, researcherName);
	 preparedStmt.setString(2, researcherNIC);
	 preparedStmt.setString(3, researcherEmail);
	 preparedStmt.setString(4, researcherAddress);
	 preparedStmt.setString(5, researcherPhoneNo);
	 preparedStmt.setInt(6,Integer.parseInt(researcherID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newResearcher = readItems();
	 output =  "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}"; 
	 }
	 catch (Exception e)
	 {
	 output =  "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String deleteItem(String researcherID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from resercher where ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(researcherID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 
	 String newResearcher = readItems();
	 output =  "{\"status\":\"success\", \"data\": \"" + newResearcher + "\"}";
	 
	 }
	 catch (Exception e)
	 {
	 output =  "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	
	

}
