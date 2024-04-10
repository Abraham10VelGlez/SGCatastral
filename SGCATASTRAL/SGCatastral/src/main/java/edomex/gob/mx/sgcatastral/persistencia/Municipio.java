/*    */ package edomex.gob.mx.sgcatastral.persistencia;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Municipio
/*    */   implements Serializable
/*    */ {
/*    */   private int id;
/*    */   private String municipio;
/*    */   private BigDecimal lon;
/*    */   private BigDecimal lat;
/*    */   private String claveMunicipio;
/*    */   
/*    */   public Municipio() {}
/*    */   
/*    */   public Municipio(int id, String municipio, BigDecimal lon, BigDecimal lat,  String claveMunicipio)
/*    */   {
/* 24 */     this.id = id;
/* 25 */     this.municipio = municipio;
/* 26 */     this.lon = lon;
/* 27 */     this.lat = lat;
/* 29 */     this.claveMunicipio = claveMunicipio;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 33 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 37 */     this.id = id;
/*    */   }
/*    */   
/* 40 */   public String getMunicipio() { return this.municipio; }
/*    */   
/*    */   public void setMunicipio(String municipio)
/*    */   {
/* 44 */     this.municipio = municipio;
/*    */   }
/*    */   
/* 47 */   public BigDecimal getLon() { return this.lon; }
/*    */   
/*    */   public void setLon(BigDecimal lon)
/*    */   {
/* 51 */     this.lon = lon;
/*    */   }
/*    */   
/* 54 */   public BigDecimal getLat() { return this.lat; }
/*    */   
/*    */   public void setLat(BigDecimal lat)
/*    */   {
/* 58 */     this.lat = lat;
/*    */   }
/*    */   

/*    */   
/* 68 */   public String getClaveMunicipio() { return this.claveMunicipio; }
/*    */   
/*    */   public void setClaveMunicipio(String claveMunicipio)
/*    */   {
/* 72 */     this.claveMunicipio = claveMunicipio;
/*    */   }
/*    */ }