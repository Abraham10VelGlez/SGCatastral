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
public class cat_mzna
        implements Serializable {
    
    private int id;
    private String mun;
    private String zona;
    private String manz;
    private String cve_cat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMun() {
        return mun;
    }

    public void setMun(String mun) {
        this.mun = mun;
    }

        
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getManz() {
        return manz;
    }

    public void setManz(String manz) {
        this.manz = manz;
    }

    public String getCve_cat() {
        return cve_cat;
    }

    public void setCve_cat(String cve_cat) {
        this.cve_cat = cve_cat;
    }
    
    

}
