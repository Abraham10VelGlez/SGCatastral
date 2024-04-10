/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Fernando
 */
public class municipio2 
        
        implements Serializable

{
    
    
  
/*    */ 
/*    */   private String cveigecem;
/*    */   private String nom_mun;
           private String delegacion;
/*    */   private BigDecimal lon;
/*    */   private BigDecimal lat;
/*    */   private String claveMunicipio;

    public String getCveigecem() {
        return cveigecem;
    }

    public void setCveigecem(String cveigecem) {
        this.cveigecem = cveigecem;
    }

    public String getNom_mun() {
        return nom_mun;
    }

    public void setNom_mun(String nom_mun) {
        this.nom_mun = nom_mun;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    
    
    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getClaveMunicipio() {
        return claveMunicipio;
    }

    public void setClaveMunicipio(String claveMunicipio) {
        this.claveMunicipio = claveMunicipio;
    }
    
}
