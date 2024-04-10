/*    */ package edomex.gob.mx.sgcatastral.persistencia;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Localidad
/*    */   implements Serializable
/*    */ {
/*    */   private int id;
/*    */   private String localidad;
/*    */   private BigDecimal lon;
/*    */   private BigDecimal lat;
/*    */   private String claveMunicipio;
/*    */   private String claveLocalidad;
/*    */   
/*    */   public Localidad() {}
/*    */   
/*    */   public Localidad(int id, String localidad, BigDecimal lon, BigDecimal lat, String claveMunicipio, String claveLocalidad)
/*    */   {
/* 25 */     this.id = id;
/* 26 */     this.localidad = localidad;
/* 27 */     this.lon = lon;
/* 28 */     this.lat = lat;
/* 30 */     this.claveMunicipio = claveMunicipio;
/* 31 */     this.claveLocalidad = claveLocalidad;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 35 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 39 */     this.id = id;
/*    */   }
/*    */   
/* 42 */   public String getLocalidad() { return this.localidad; }
/*    */   
/*    */   public void setLocalidad(String localidad)
/*    */   {
/* 46 */     this.localidad = localidad;
/*    */   }
/*    */   
/* 49 */   public BigDecimal getLon() { return this.lon; }
/*    */   
/*    */   public void setLon(BigDecimal lon)
/*    */   {
/* 53 */     this.lon = lon;
/*    */   }
/*    */   
/* 56 */   public BigDecimal getLat() { return this.lat; }
/*    */   
/*    */   public void setLat(BigDecimal lat)
/*    */   {
/* 60 */     this.lat = lat;
/*    */   }
/*    */   
/* 70 */   public String getClaveMunicipio() { return this.claveMunicipio; }
/*    */   
/*    */   public void setClaveMunicipio(String claveMunicipio)
/*    */   {
/* 74 */     this.claveMunicipio = claveMunicipio;
/*    */   }
/*    */   
/* 77 */   public String getClaveLocalidad() { return this.claveLocalidad; }
/*    */   
/*    */   public void setClaveLocalidad(String claveLocalidad)
/*    */   {
/* 81 */     this.claveLocalidad = claveLocalidad;
/*    */   }
/*    */ }