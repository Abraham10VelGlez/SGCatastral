/*    */ package edomex.gob.mx.sgcatastral.persistencia;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TipoCapa
/*    */   implements Serializable
/*    */ {
/*    */   private short id;
/*    */   private String descripcion;
/* 16 */   private Set<Capa> capas = new HashSet(0);
/*    */   
/*    */ 
/*    */   public TipoCapa() {}
/*    */   
/*    */   public TipoCapa(short id, String descripcion)
/*    */   {
/* 23 */     this.id = id;
/* 24 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/* 27 */   public TipoCapa(short id, String descripcion, Set<Capa> capas) { this.id = id;
/* 28 */     this.descripcion = descripcion;
/* 29 */     this.capas = capas;
/*    */   }
/*    */   
/*    */   public short getId() {
/* 33 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(short id) {
/* 37 */     this.id = id;
/*    */   }
/*    */   
/* 40 */   public String getDescripcion() { return this.descripcion; }
/*    */   
/*    */   public void setDescripcion(String descripcion)
/*    */   {
/* 44 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/* 47 */   public Set<Capa> getCapas() { return this.capas; }
/*    */   
/*    */   public void setCapas(Set<Capa> capas)
/*    */   {
/* 51 */     this.capas = capas;
/*    */   }
/*    */ }