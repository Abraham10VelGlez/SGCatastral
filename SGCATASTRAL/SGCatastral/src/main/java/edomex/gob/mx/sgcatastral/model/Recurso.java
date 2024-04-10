/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.model;

/**
 *
 * @author UAEM
 */
public class Recurso {
    
    private Number id;
    private Number idTipoRecurso;
    private Number idRecurso;
    private String titulo;
    private String contenido;
    private String carga;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Number idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public Number getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Number idRecurso) {
        this.idRecurso = idRecurso;
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

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

}
