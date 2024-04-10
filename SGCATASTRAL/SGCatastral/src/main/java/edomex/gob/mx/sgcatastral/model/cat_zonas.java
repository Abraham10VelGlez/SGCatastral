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
public class cat_zonas
        implements Serializable {

    private int id;
    private String zona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

}
