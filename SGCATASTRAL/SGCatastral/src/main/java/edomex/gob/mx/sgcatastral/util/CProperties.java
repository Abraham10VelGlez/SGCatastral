package edomex.gob.mx.sgcatastral.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;


public class CProperties {
        private Log log = LogFactory.getLog(CProperties.class);
	Properties prop = new Properties();
	String propiedad="";
	InputStream in ;
        
	public String getProperty(String key){
		try{
                    in = getClass().getClassLoader().getResourceAsStream("config.properties");				
                    prop.load(in);
                    propiedad=prop.getProperty(key);
		
                }catch(IOException ex){
                        log.error("Error al leer archivo de configuracion de propiedades");
                } finally {
                        if( in != null ) 
                        try { in.close(); }
                        catch( IOException e ) {
                            e.printStackTrace();				
                        }
                }
            
                return propiedad;				
	}
}
