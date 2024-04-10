/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.services;

import edomex.gob.mx.sgcatastral.model.AE;
import edomex.gob.mx.sgcatastral.model.Competidor;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.web.multipart.MultipartFile;
import edomex.gob.mx.sgcatastral.model.Recurso;
import java.io.FileOutputStream;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 *
 * @author UAEM
 */
public class UtilService {
    
    public static String getMD5(String data) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes(Charset.forName("UTF-8")));
            result = String.format(Locale.ROOT, "%032x", new Object[] { new BigInteger(1, md.digest()) });
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e); 
        }
        
        return result;
    }
    
    public static void saveImagen(List<Recurso> recursos, MultipartFile img, String pathRepo, long idUsr) {
    
        Recurso recurso = new Recurso();
        
        try {
        
            if ((img == null) || (img.getSize() == 0L)) return ;
                        
            recurso.setIdTipoRecurso(Integer.valueOf(1));
            recurso.setTitulo(img.getOriginalFilename());
            Date fecha = new Date();
            String fileName = fecha.getTime() + img.getOriginalFilename();
            File repoDirectory = new File(pathRepo + idUsr);
            repoDirectory.mkdirs();
            File repoFile = new File(pathRepo + idUsr + "/" + fileName);
            img.transferTo(repoFile);
            recurso.setContenido(idUsr + "/" + fileName);
            recurso.setCarga("exito");
                
        } catch (Exception ex) {
            ex.printStackTrace();
            recurso.setCarga("error");
        }

        recursos.add(recurso);
        
    }
    
    public static void delImagen(String pathRepo, String fileName) {    
    
        try {
            
            File repoFile = new File(pathRepo + fileName);            
            repoFile.delete();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
         
    public static void createExcel(List<AE>lstAE, List<Competidor>lstAlitas) throws Exception {
                    
        /*La ruta donde se creará el archivo*/                
        String rutaArchivo = "C:/REPOSITORIO_MAGIC/resultados_extraccion.xls";
        /*Se crea el objeto de tipo File con la ruta del archivo*/
        File archivoXLS = new File(rutaArchivo);
        /*Si el archivo existe se elimina*/
        if(archivoXLS.exists()) archivoXLS.delete();
        /*Se crea el archivo*/
        archivoXLS.createNewFile();
        
        /*Se crea el libro de excel usando el objeto de tipo Workbook*/
        Workbook libro = new HSSFWorkbook();
        /*Se inicializa el flujo de datos con el archivo xls*/
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        
        /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
        int f = 0;
                
        if(!lstAE.isEmpty()){
            
            Sheet hoja = libro.createSheet("Unidades Económicas");

            f = 0;         
            
            for (AE ae : lstAE) {                                                                                                                                                   
                Row fila = hoja.createRow(f);                                   
                Cell celda = fila.createCell(0);                                                
                celda.setCellValue(ae.getC1());                    
                celda = fila.createCell(1);                                                
                celda.setCellValue(ae.getC2());                    
                celda = fila.createCell(2);                                                
                celda.setCellValue(ae.getC3());                    
                celda = fila.createCell(3);                                                
                celda.setCellValue(ae.getC4());                    
                celda = fila.createCell(4);                                                
                celda.setCellValue(ae.getC5());                    
                celda = fila.createCell(5);                                                
                celda.setCellValue(ae.getC6());                    
                celda = fila.createCell(6);                                                
                celda.setCellValue(ae.getC7());                    
                celda = fila.createCell(7);                                                
                celda.setCellValue(ae.getC8());                    
                celda = fila.createCell(8);                                                
                celda.setCellValue(ae.getC9());                    
                celda = fila.createCell(9);                                                
                celda.setCellValue(ae.getC10());                    
                celda = fila.createCell(10);                                                
                celda.setCellValue(ae.getC11());                    
                celda = fila.createCell(11);                                                
                celda.setCellValue(ae.getC12());                    
                celda = fila.createCell(12);                                                
                celda.setCellValue(ae.getC13());                    
                celda = fila.createCell(13);                                                
                celda.setCellValue(ae.getC14());                    
                celda = fila.createCell(14);                                                
                celda.setCellValue(ae.getC15());                    
                celda = fila.createCell(15);                                                
                celda.setCellValue(ae.getC16());                    
                celda = fila.createCell(16);                                                
                celda.setCellValue(ae.getC17());  
                celda = fila.createCell(17);                                                
                celda.setCellValue(ae.getC18());                    
                celda = fila.createCell(18);                                                
                celda.setCellValue(ae.getC19());                    
                celda = fila.createCell(19);                                                
                celda.setCellValue(ae.getC20());                    
                celda = fila.createCell(20);                                                
                celda.setCellValue(ae.getC21());                    
                celda = fila.createCell(21);                                                
                celda.setCellValue(ae.getC23());                    
                celda = fila.createCell(22);                                                
                celda.setCellValue(ae.getC24());                    
                celda = fila.createCell(23);                                                
                celda.setCellValue(ae.getC25());                    
                celda = fila.createCell(24);                                                
                celda.setCellValue(ae.getC26());                    
                celda = fila.createCell(25);                                                
                celda.setCellValue(ae.getC27());                    
                celda = fila.createCell(26);                                                
                celda.setCellValue(ae.getC28());                    
                celda = fila.createCell(27);                                                
                celda.setCellValue(ae.getC29());                    
                celda = fila.createCell(28);                                                
                celda.setCellValue(ae.getC30());                    
                celda = fila.createCell(29);                                                
                celda.setCellValue(ae.getC31());                    
                celda = fila.createCell(30);                                                
                celda.setCellValue(ae.getC32());                    
                celda = fila.createCell(31);                                                
                celda.setCellValue(ae.getC33());                    
                celda = fila.createCell(32);                                                
                celda.setCellValue(ae.getC34());  
                celda = fila.createCell(33);                                                
                celda.setCellValue(ae.getC35());                    
                celda = fila.createCell(34);                                                
                celda.setCellValue(ae.getC36());                    
                celda = fila.createCell(35);                                                
                celda.setCellValue(ae.getC37());                    
                celda = fila.createCell(36);                                                
                celda.setCellValue(ae.getC38());                    
                celda = fila.createCell(37);                                                
                celda.setCellValue(ae.getC39());                    
                celda = fila.createCell(38);                                                
                celda.setCellValue(ae.getC40());                    
                celda = fila.createCell(39);                                                
                celda.setCellValue(ae.getC41());                    
                celda = fila.createCell(40);                                                
                celda.setCellValue(ae.getC42());                    
                celda = fila.createCell(41);                                                
                celda.setCellValue(ae.getC43());                    
                celda = fila.createCell(42);                                                
                celda.setCellValue(ae.getC44());                    
                celda = fila.createCell(43);                                                
                celda.setCellValue(ae.getC45());                    
                celda = fila.createCell(44);                                                
                celda.setCellValue(ae.getC46());                    
                celda = fila.createCell(45);                                                
                celda.setCellValue(ae.getC47());                    
                celda = fila.createCell(46);                                                
                celda.setCellValue(ae.getC48());                    
                celda = fila.createCell(47);                                                
                celda.setCellValue(ae.getC49());                    
                celda = fila.createCell(48);                                                
                celda.setCellValue(ae.getC50()); 
                celda = fila.createCell(49);                                                
                celda.setCellValue(ae.getC51());                    
                celda = fila.createCell(50);                                                
                celda.setCellValue(ae.getC52());                    
                celda = fila.createCell(51);                                                
                celda.setCellValue(ae.getC53());                    
                celda = fila.createCell(52);                                                
                celda.setCellValue(ae.getC54());                    
                celda = fila.createCell(53);                                                
                celda.setCellValue(ae.getC55());                    
                celda = fila.createCell(54);                                                
                celda.setCellValue(ae.getC56());                    
                celda = fila.createCell(55);                                                
                celda.setCellValue(ae.getC57());                    
                celda = fila.createCell(56);                                                
                celda.setCellValue(ae.getC58());                    
                celda = fila.createCell(57);                                                
                celda.setCellValue(ae.getC59());                    
                celda = fila.createCell(58);                                                
                celda.setCellValue(ae.getC60());                    
                celda = fila.createCell(59);                                                
                celda.setCellValue(ae.getC61());                    
                celda = fila.createCell(60);                                                
                celda.setCellValue(ae.getC62());                    
                celda = fila.createCell(61);                                                
                celda.setCellValue(ae.getC63());                    
                celda = fila.createCell(62);                                                
                celda.setCellValue(ae.getC64());                    
                celda = fila.createCell(63);                                                
                celda.setCellValue(ae.getC65());                    
                celda = fila.createCell(64);                                                
                celda.setCellValue(ae.getC66());  
                celda = fila.createCell(65);                                                
                celda.setCellValue(ae.getC67());                    
                celda = fila.createCell(66);                                                
                celda.setCellValue(ae.getC68());                    
                celda = fila.createCell(67);                                                
                celda.setCellValue(ae.getC69());                    
                celda = fila.createCell(68);                                                
                celda.setCellValue(ae.getC70());                    
                celda = fila.createCell(69);                                                
                celda.setCellValue(ae.getC71());                    
                celda = fila.createCell(70);                                                
                celda.setCellValue(ae.getC71());                    
                celda = fila.createCell(71);                                                
                celda.setCellValue(ae.getC72());                    
                celda = fila.createCell(72);                                                
                celda.setCellValue(ae.getC73());                                    
                                             
                f++;        
            }
        }
        
        if(!lstAlitas.isEmpty()){
        
            Sheet hoja = libro.createSheet("Tektec");                

            f = 0;

            for (Competidor competidor : lstAlitas) {                                                                                                                                                   
                Row fila = hoja.createRow(f);                   
                Cell celda = fila.createCell(0);                                                
                celda.setCellValue(competidor.getC1());                    
                celda = fila.createCell(1);                                                
                celda.setCellValue(competidor.getC2());                    
                celda = fila.createCell(2);                                                
                celda.setCellValue(competidor.getC3());                    
                celda = fila.createCell(3);                                                
                celda.setCellValue(competidor.getC4());                    
                celda = fila.createCell(4);                                                
                celda.setCellValue(competidor.getC5());                                    
                f++;        
            }
        }
        
        /*Escribimos en el libro*/
        libro.write(archivo);
        
        /*Cerramos el flujo de datos*/
        archivo.close();        
        
    }        
    
    //Generador de contraseñas
    public static String getRandomPassword (int longitud){
        String password = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
            char c = (char)r.nextInt(255);
            if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
                password += c;
                i ++;
            }
        }
        return password;
    }
    
}
