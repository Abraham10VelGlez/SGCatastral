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
public class Localidad2 

        implements Serializable
        
{
    
           private int id;
           private String cveigecem;
/*    */   private String nom_loc;
           private String cat_pol;
           private String cat_admin;
/*    */   private BigDecimal lon;
/*    */   private BigDecimal lat;
/*    */   private String claveMunicipio;
/*    */   private String claveLocalidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCveigecem() {
        return cveigecem;
    }

    public void setCveigecem(String cveigecem) {
        this.cveigecem = cveigecem;
    }

    public String getNom_loc() {
        return nom_loc;
    }

    public void setNom_loc(String nom_loc) {
        this.nom_loc = nom_loc;
    }

    public String getCat_pol() {
        return cat_pol;
    }

    public void setCat_pol(String cat_pol) {
        this.cat_pol = cat_pol;
    }

    public String getCat_admin() {
        return cat_admin;
    }

    public void setCat_admin(String cat_admin) {
        this.cat_admin = cat_admin;
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

    public String getClaveLocalidad() {
        return claveLocalidad;
    }

    public void setClaveLocalidad(String claveLocalidad) {
        this.claveLocalidad = claveLocalidad;
    }

   
    

   

    

    
    


}
