/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author UAEM
 */
public class PuntosUsuario {
    //Parámetros de la clase
    private Number idPunto;
    private Number idUsr;
    //Atributos de la clase
    private Number id;    
    private Number plng;
    private Number plat;  
    private String desc_corta;
    private String desc_larga;
    private String titulo;
    private String contenido;
    private String reset;
    private String submit;    
    private CommonsMultipartFile imgRecurso1;      

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Number idPunto) {
        this.idPunto = idPunto;
    }

    public Number getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(Number idUsr) {
        this.idUsr = idUsr;
    }

    public Number getPlng() {
        return plng;
    }

    public void setPlng(Number plng) {
        this.plng = plng;
    }

    public Number getPlat() {
        return plat;
    }

    public void setPlat(Number plat) {
        this.plat = plat;
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

    public CommonsMultipartFile getImgRecurso1() {
        return imgRecurso1;
    }

    public void setImgRecurso1(CommonsMultipartFile imgRecurso1) {
        this.imgRecurso1 = imgRecurso1;
    } 

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
