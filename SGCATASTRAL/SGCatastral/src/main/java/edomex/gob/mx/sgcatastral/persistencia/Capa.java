/*    */ package edomex.gob.mx.sgcatastral.persistencia;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Capa
/*    */   implements Serializable
/*    */ {
/*    */   private int id;
/*    */   private CategoriaCapa categoriaCapa;
/*    */   private String anio;
/*    */   private String descripcion;
/*    */   private String wms;
/*    */   private String infownd;
/*    */   private int activo;       
           private int orden; 
/*    */   
/*    */   public Capa() {}
/*    */   
/*    */   public Capa(int id, CategoriaCapa categoriaCapa,String anio, String descripcion, String wms, int activo, int orden)
/*    */   {
/* 25 */     this.id = id;
/* 26 */     this.categoriaCapa = categoriaCapa;
/* 27 */    this.anio = anio;
/* 28 */     this.descripcion = descripcion;
/* 29 */     this.wms = wms;
/* 30 */     this.activo = activo;
/* 30 */     this.orden = orden;
/*    */   }
/*    */   
/* 33 */   public Capa(int id, CategoriaCapa categoriaCapa,String anio, String descripcion, String wms, String infownd, int activo, int orden) { this.id = id;
/* 34 */     this.categoriaCapa = categoriaCapa;
/* 35 */     this.anio = anio;
/* 36 */     this.descripcion = descripcion;
/* 37 */     this.wms = wms;
/* 38 */     this.infownd = infownd;
/* 39 */     this.activo = activo;
/* 30 */     this.orden = orden;
/*    */   }
/*    */   
/*    */   
/*    */      public int getId() { return id; }
/*    */   
/*    */  

/*    */
/*    */
    public void setId(int id) {
        this.id = id;
    }

/*    */
/* 50 */
    public CategoriaCapa getCategoriaCapa() {
        return this.categoriaCapa;
    }
 public void setCategoriaCapa(CategoriaCapa categoriaCapa)
/*    */   {
/* 54 */     this.categoriaCapa = categoriaCapa;
/*    */   }
/*    */   
/*    */   
/* 64 */   public String getDescripcion() { return this.descripcion; }
/*    */   
/*    */   public void setDescripcion(String descripcion)
/*    */   {
/* 68 */     this.descripcion = descripcion;
/*    */   }


/*    */   
/* 71 */

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
 public String getWms() { return this.wms; }
/*    */   
/*    */   public void setWms(String wms)
/*    */   {
/* 75 */     this.wms = wms;
/*    */   }
/*    */   
/* 78 */   public String getInfownd() { return this.infownd; }
/*    */   
/*    */   public void setInfownd(String infownd)
/*    */   {
/* 82 */     this.infownd = infownd;
/*    */   }
/*    */   
/* 85 */   public int getActivo() { 
                return this.activo; 
            }
/*    */   
/*    */   public void setActivo(int activo)
/*    */   {
/* 89 */     this.activo = activo;
/*    */   }



/*    */

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
 }