/*    */ package edomex.gob.mx.sgcatastral.persistencia;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PCargaError
/*    */   implements Serializable
/*    */ {
/*    */   private long id;
/*    */   private String idProceso;
/*    */   private String descripcion;
/*    */   private Long nreg;
/*    */   
/*    */   public PCargaError() {}
/*    */   
/*    */   public PCargaError(long id, String idProceso, String descripcion)
/*    */   {
/* 22 */     this.id = id;
/* 23 */     this.idProceso = idProceso;
/* 24 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/* 27 */   public PCargaError(long id, String idProceso, String descripcion, Long nreg) { this.id = id;
/* 28 */     this.idProceso = idProceso;
/* 29 */     this.descripcion = descripcion;
/* 30 */     this.nreg = nreg;
/*    */   }
/*    */   
/*    */   public long getId() {
/* 34 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(long id) {
/* 38 */     this.id = id;
/*    */   }
/*    */   
/* 41 */   public String getIdProceso() { return this.idProceso; }
/*    */   
/*    */   public void setIdProceso(String idProceso)
/*    */   {
/* 45 */     this.idProceso = idProceso;
/*    */   }
/*    */   
/* 48 */   public String getDescripcion() { return this.descripcion; }
/*    */   
/*    */   public void setDescripcion(String descripcion)
/*    */   {
/* 52 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/* 55 */   public Long getNreg() { return this.nreg; }
/*    */   
/*    */   public void setNreg(Long nreg)
/*    */   {
/* 59 */     this.nreg = nreg;
/*    */   }
/*    */ }