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
public class cat_municipios {
    
    public String result;
	
	public cat_municipios(){
		connectiondb con;
        con = new connectiondb();
		try{
			Statement query = con.getConnection().createStatement();
			ResultSet rs=query.executeQuery("SELECT * FROM igecem_municipio_a order by gid");
			
                        /*result="[";
			
			boolean flag=false;
			
			while(rs.next()){
				if(flag){
					result+=",{ \"Estado\" : \""+rs.getString(1)+"\", \"Clave\" : \""+rs.getString(2)+"\", \"Nombre\" : \""+rs.getString(5)+"\"}\n";
				}else{
					result+="{ \"Estado\" : \""+rs.getString(1)+"\", \"Clave\" : \""+rs.getString(2)+"\", \"Nombre\" : \""+rs.getString(5)+"\"}\n";
					flag=true;
				}
			}
			
			result+="]";]*/
                        
                        
                        
			result="{ \"Municipios\" : [";
			
			boolean flag=false;
			
			while(rs.next()){
				if(flag){
					result+=",{ \"ID\" : \""+rs.getString(1)+"\", \"Clave\" : \""+rs.getString(2)+"\", \"Perimetro\" : \""+rs.getString(3)+"\"}\n";
				}else{
					result+="{ \"ID\" : \""+rs.getString(1)+"\", \"Clave\" : \""+rs.getString(2)+"\", \"Perimetro\" : \""+rs.getString(3)+"\"}\n";
					flag=true;
				}
			}
			
			result+="]}";
                        
                        /*result="{ \"Municipios\" : { \"rows\": [";
			
			boolean flag=false;
			
			while(rs.next()){
				if(flag){
					result+=",{ \"Estado\" : \""+rs.getString(1)+"\", \"Clave\" : \""+rs.getString(2)+"\", \"Nombre\" : \""+rs.getString(5)+"\"}\n";
				}else{
					result+="{ \"Estado\" : \""+rs.getString(1)+"\", \"Clave\" : \""+rs.getString(2)+"\", \"Nombre\" : \""+rs.getString(5)+"\"}\n";
					flag=true;
				}
			}
			
			result+="]}}";*/
                        
                        
                        
			
			System.out.println(result);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
    
    
    
}
