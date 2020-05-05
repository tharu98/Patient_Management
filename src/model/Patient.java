package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Patient {

	private Connection connect() 
	{   
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/patient", "root", "");   
		  System.out.print("Successfully connected");
	  }   
	  catch (Exception e)   
	  {
		  e.printStackTrace();
	  } 
	 
	  return con;  
	  
	} 
	
	//Insert
	public String insertPatientDetails(String first_name, String last_name, String address, String email) 
	{   
		String output = ""; 
		
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for inserting."; 
	   } 
	 
	      String query = " insert into patient (`patientID`,`first_name`,`last_name`,`address`,`email`)"     
	    		  + " values (?, ?, ?, ?, ?)"; 
	 
	      java.sql.PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	      preparedStmt.setInt(1, 0);   
	      preparedStmt.setString(2, first_name);   
	      preparedStmt.setString(3, last_name);    
	      preparedStmt.setString(4, address);   
	      preparedStmt.setString(5, email); 
	 
	   
	      preparedStmt.execute();    
	      con.close(); 
	 
	      String newPatientDetails = readPatientDetails();    
	      output = "{\"status\":\"success\", \"data\": \"" +   newPatientDetails + "\"}";
	   
	  }catch (Exception e)   
	  	
	 	{    
		  output = "{\"status\":\"error\", \"data\":"
		  		+ "\"Error while inserting the patient details.\"}";    
		  System.err.println(e.getMessage());  
		} 
	 return output;  
	}
	
	//Read
	public String readPatientDetails()  
	{   
		String output = ""; 
	
		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
			
			output = "<table border=\'1\'><tr><th>First Name</th><th>Last Name</th><th>Address</th><th>Email</th> <th>Update</th><th>Remove</th></tr>"; 
	 

	     String query = "select * from patient";    
	     java.sql.Statement stmt = con.createStatement();    
	     ResultSet rs = stmt.executeQuery(query); 
	 
	     while (rs.next())    
	     {     
	    	 String patientID = Integer.toString(rs.getInt("patientID"));     
	    	 String first_name = rs.getString("first_name");     
	    	 String last_name = rs.getString("last_name");     
	    	 String address = rs.getString("address");     
	    	 String email = rs.getString("email"); 
	     
	 
	    	 //Add to html table
	    	 output += "<tr><td><input id='hidPatientIDUpdate' name='hidPatientIDUpdate' type='hidden' value='" 
	                  + patientID + "\'>" 
	                  + first_name + "</td>";    
	         output += "<td>" + last_name + "</td>";     
	         output += "<td>" + address + "</td>";     
	         output += "<td>" + email + "</td>"; 
	 
	         
	         //Button
	         output +=  "<td><input name='btnUpdate' type='button'  value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-patientID='" 
						+ patientID + "'>" + "</td></tr>";
	         
	         } 
	
	     con.close(); 
	 
	     output += "</table>";   
	     }   
		catch (Exception e)  
		{    
			output = "Error while reading the Details.";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	  } 
	
	//Update
	public String updatePatientDetails( String ID, String first_name, String last_name, String address, String email)  
	{   
		String output = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return "Error while connecting to the database for updating."; 
				} 
	 
	   String query = "UPDATE patient SET first_name=?,last_name=?,address=?,email=? WHERE patientID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setString(1, first_name);    
	   preparedStmt.setString(2, last_name);   
	   preparedStmt.setString(3, address);    
	   preparedStmt.setString(4, email);    
	   preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	   preparedStmt.execute();   
	   con.close(); 
	 
	   String newPatientDetails = readPatientDetails();    
	   output = "{\"status\":\"success\", \"data\": \"" + newPatientDetails + "\"}";  
	}   
		catch (Exception e)   
	{    
			output = "{\"status\":\"error\", \"data\": \"Error while updating the patient details.\"}";    
			System.err.println(e.getMessage());   
			
	} 
	 
	  return output;  
	  
	}
	
	//Delete
	public String deletePatientDetails(String patientID)  
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return "Error while connecting to the database for deleting."; 
	   	} 
	 
	   String query = "delete from patient where patientID=?"; 
	   java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	   preparedStmt.setInt(1, Integer.parseInt(patientID)); 
	 
	   preparedStmt.execute();    
	   con.close(); 
	 
	   String newPatientDetails = readPatientDetails();    
	   output = "{\"status\":\"success\", \"data\": \"" + newPatientDetails + "\"}";
	   
	  }   
	  catch (Exception e)   
	  {    
		  output = "{\"status\":\"error\", \"data\": \"Error while deleting.\"}";    
		  System.err.println(e.getMessage());  
	  } 
	 
	  return output;  }
	
	
}
