package com.umls.dave;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;





@Path("/cui")
public class CUIService {

    @GET
    @Path("/def/{id}")
    @Produces("text/plain")
    public String getDefinition(@PathParam("id") String cui_id)
    {
    	try
		{
			String output = null; 
			Class.forName(CUI.JDBC_DRIVER);
			Connection  conn = DriverManager.getConnection(CUI.DB_URL, CUI.USER_NAME, CUI.PASSWORD);
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT DEF FROM umls.MRDEF WHERE cui='" + cui_id + "';";
			ResultSet res = stmt.executeQuery(sql);
			
		    while(res.next())
			 {
				 output = output + CUI.DELIMITER + res.getString("DEF");
			 }
			 
		    // close response
		    res.close();
		    // close connection
		    conn.close();	 
		    return output; 
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "an error has occured";
		}
    }
    
    
    
    @GET
    @Path("/synonyms/{id}")
    @Produces("text/plain")
    public String getSynonyms(@PathParam("id") String cui_id)
    {
    	try
		{
			String output = null; 
			Class.forName(CUI.JDBC_DRIVER);
			Connection  conn = DriverManager.getConnection(CUI.DB_URL, CUI.USER_NAME, CUI.PASSWORD);
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT STR FROM umls.MRCONSO WHERE cui='" + cui_id + "';";
			ResultSet res = stmt.executeQuery(sql);
			
		    while(res.next())
			 {
				 output = output + CUI.DELIMITER + (res.getString("STR"));
			 }
			 
		    // close response
		    res.close();
		    // close connection
		    conn.close();	 
		    return output; 
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "an error has occured";
		}
    }
    
    
    @GET
    @Path("/semantics/{id}")
    @Produces("text/plain")
    public String getSemanticTypes(@PathParam("id") String cui_id)
    {
    	try
		{
			String output = null; 
			Class.forName(CUI.JDBC_DRIVER);
			Connection  conn = DriverManager.getConnection(CUI.DB_URL, CUI.USER_NAME, CUI.PASSWORD);
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT STY FROM umls.MRSTY WHERE cui='" + cui_id + "';";
			ResultSet res = stmt.executeQuery(sql);
			
		    while(res.next())
			 {
				 output = output + CUI.DELIMITER + (res.getString("STY"));
			 }
			 
		    // close response
		    res.close();
		    // close connection
		    conn.close();	 
		    return output; 
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "an error has occured";
		}
    }
    
    
    
    
    
    
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response get(@PathParam("id") String cui_id)
    {
    	CUI obj = new CUI(cui_id);
    	obj.update();
    	
        return Response.ok(obj).build();
    }
    
    
    
    
    
    
    
    
    
    
    
}
