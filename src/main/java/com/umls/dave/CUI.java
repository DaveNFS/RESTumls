package com.umls.dave;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CUI {

	// member variables can be added as needed
	public String cui;
	public String definition;
	public String synonyms; 
	public String semanticTypes; 
	
	//TODO: implement the same for Lists (will require another class)
	
//  public ArrayList<String> definition;
//	public ArrayList<String> synonyms;
//	public ArrayList<String> semanticTypes;
	

	// NOTE: it's easier to build and return restful objects when they are strings.
	// NOTE: change the DELIMITER if needed 
	
	
	public static final String DELIMITER = "$"; 
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://mysql.chpc.utah.edu";
	public static final String USER_NAME = "umlsro";
	public static final String PASSWORD = "umls";
	

   public CUI()
   {
	   
   }
	
   public CUI(String cui)
   {
	   this.cui = cui; 
   }


	public void update()
	{
		// this methods just makes all the in memory data recent.
		// call this only if you have to get the most recent copy of the object 
		// use "specific calls" for faster response
		
		try
		{
			
			Class.forName(JDBC_DRIVER);
			Connection  conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT DEF FROM umls.MRDEF WHERE cui='" + cui + "';";
			ResultSet res = stmt.executeQuery(sql);
			
			while(res.next())
			 {
				 this.definition = res.getString("DEF");
			 }
			 
			stmt = conn.createStatement();
			sql = "SELECT STR FROM umls.MRCONSO WHERE cui='" + cui + "';";
			res = stmt.executeQuery(sql);
			
		    while(res.next())
			 {
				 this.synonyms = this.synonyms + DELIMITER + (res.getString("STR"));
			 }
		    
		    stmt = conn.createStatement();
			sql = "SELECT STY FROM umls.MRSTY WHERE cui='" + cui + "';";
			res = stmt.executeQuery(sql);
			
		    while(res.next())
			 {
				 this.semanticTypes = this.synonyms + DELIMITER + (res.getString("STY"));
			 }
		    
		    		    
		    // close response
		    res.close();
		    // close connection
		    conn.close();

		}catch(Exception e){e.printStackTrace();}
	}










}
