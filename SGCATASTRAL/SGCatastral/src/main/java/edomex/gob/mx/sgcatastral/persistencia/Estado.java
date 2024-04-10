/*    */ package edomex.gob.mx.sgcatastral.persistencia;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Estado
/*    */   implements Serializable
/*    */ {
/*    */   private int id;
/*    */   private String estado;
/*    */   private BigDecimal lon;
/*    */   private BigDecimal lat;
/*    */   private String clave;
/*    */   
/*    */   public Estado() {}
/*    */   
/*    */   public Estado(int id, String estado, BigDecimal lon, BigDecimal lat, String clave)
/*    */   {
/* 23 */     this.id = id;
/* 24 */     this.estado = estado;
/* 25 */     this.lon = lon;
/* 26 */     this.lat = lat;
/* 27 */     this.clave = clave;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 31 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(int id) {
/* 35 */     this.id = id;
/*    */   }
/*    */   
/* 38 */   public String getEstado() { return this.estado; }
/*    */   
/*    */   public void setEstado(String estado)
/*    */   {
/* 42 */     this.estado = estado;
/*    */   }
/*    */   
/* 45 */   public BigDecimal getLon() { return this.lon; }
/*    */   
/*    */   public void setLon(BigDecimal lon)
/*    */   {
/* 49 */     this.lon = lon;
/*    */   }
/*    */   
/* 52 */   public BigDecimal getLat() { return this.lat; }
/*    */   
/*    */   public void setLat(BigDecimal lat)
/*    */   {
/* 56 */     this.lat = lat;
/*    */   }
/*    */   
/* 59 */   public String getClave() { return this.clave; }
/*    */   
/*    */   public void setClave(String clave)
/*    */   {
/* 63 */     this.clave = clave;
/*    */   }
/*    */ }