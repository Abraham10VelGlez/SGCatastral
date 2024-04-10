/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.model;

/**
 *
 * @author Fernando
 */
public class Cat_mun {
    
    private Number cve_estado;
    private Number cve_mun;    
    private String nombre;
    
      public Number getCve_estado() {
        return cve_estado;
    }

    public void setCve_estado(Number cve_estado) {
        this.cve_estado = cve_estado;
    }
    
      public Number getCve_mun() {
        return cve_mun;
    }

    public void setCve_mun(Number cve_mun) {
        this.cve_mun = cve_mun;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  
    
    
}
