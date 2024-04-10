/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author UAEM
 */
public class MisPuntos {
    
    private Number idUsr;
    private Number folio;
    private Number x;
    private Number y;  
    private String icono;
    private String desc_corta;
    private String desc_larga;
    private Date fecha;
    private String reset;
    private String submit;            

    public Number getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(Number idUsr) {
        this.idUsr = idUsr;
    }

    public Number getFolio() {
        return folio;
    }

    public void setFolio(Number folio) {
        this.folio = folio;
    }

    public Number getX() {
        return x;
    }

    public void setX(Number x) {
        this.x = x;
    }

    public Number getY() {
        return y;
    }

    public void setY(Number y) {
        this.y = y;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }        
    
    public String getFecha() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(this.fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDesc_corta() {
        return desc_corta;
    }

    public void setDesc_corta(String desc_corta) {
        this.desc_corta = desc_corta;
    }

    public String getDesc_larga() {
        return desc_larga;
    }

    public void setDesc_larga(String desc_larga) {
        this.desc_larga = desc_larga;
    }
    
}

