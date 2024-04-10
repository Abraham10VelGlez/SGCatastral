/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.DAO;

import java.sql.*;

/**
 *
 * @author Fernando
 */
public class connectiondb {
    
    private Connection connection = null;
	
	public connectiondb(){
		try{
			//obtenemos el driver de para postgres
	        Class.forName("org.postgresql.Driver");
	        //obtenemos la conexi�n
	        connection = DriverManager.getConnection("jdbc:postgresql://192.168.15.44:5432/ccg","postgres","geografia");
	        
	        if(connection != null){
	        	System.out.println("Connection Ok!");
	        }
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	public void closeConnection(){
		connection=null;
	}
    
}
