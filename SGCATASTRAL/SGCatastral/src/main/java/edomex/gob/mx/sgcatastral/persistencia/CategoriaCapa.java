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
/*    */ public class CategoriaCapa
/*    */   implements Serializable
/*    */ {
/*    */   private short id;
/*    */   private String categoria;
/*    */   private int activo;
/* 17 */   private Set<Capa> capas = new HashSet(0);
/*    */   
/*    */ 
/*    */   public CategoriaCapa() {}
/*    */   
/*    */   public CategoriaCapa(short id, String categoria, int activo)
/*    */   {
/* 24 */     this.id = id;
/* 25 */     this.categoria = categoria;
/* 26 */     this.activo = activo;
/*    */   }
/*    */   
/* 29 */   public CategoriaCapa(short id, String categoria, int activo, Set<Capa> capas) { this.id = id;
/* 30 */     this.categoria = categoria;
/* 31 */     this.activo = activo;
/* 32 */     this.capas = capas;
/*    */   }
/*    */   
/*    */   public short getId() {
/* 36 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(short id) {
/* 40 */     this.id = id;
/*    */   }
/*    */   
/* 43 */   public String getCategoria() { return this.categoria; }
/*    */   
/*    */   public void setCategoria(String categoria)
/*    */   {
/* 47 */     this.categoria = categoria;
/*    */   }
/*    */   
/* 50 */   public int getActivo() { return this.activo; }
/*    */   
/*    */   public void setActivo(int activo)
/*    */   {
/* 54 */     this.activo = activo;
/*    */   }
/*    */   
/* 57 */   public Set<Capa> getCapas() { return this.capas; }
/*    */   
/*    */   public void setCapas(Set<Capa> capas)
/*    */   {
/* 61 */     this.capas = capas;
/*    */   }
/*    */ }