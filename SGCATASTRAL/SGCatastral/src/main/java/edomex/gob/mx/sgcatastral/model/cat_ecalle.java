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
public class cat_ecalle
        implements Serializable {

    private int id;
    private String calles;
    private String banda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalles() {
        return calles;
    }

    public void setCalles(String calles) {
        this.calles = calles;
    }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }
    
    

}
