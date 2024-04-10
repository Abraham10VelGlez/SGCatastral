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
public class cat_colonias
        implements Serializable {

    private int gid;
    private String nomcol;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getNomcol() {
        return nomcol;
    }

    public void setNomcol(String nomcol) {
        this.nomcol = nomcol;
    }


    
    
}
