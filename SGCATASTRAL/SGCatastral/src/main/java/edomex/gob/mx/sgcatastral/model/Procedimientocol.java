/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.model;

/**
 *
 * @author Roberto
 */
public class Procedimientocol {

    /**
     * @return the clavecol
     */
    public int getClavecol() {
        return clavecol;
    }

    /**
     * @param clavecol the clavecol to set
     */
    public void setClavecol(int clavecol) {
        this.clavecol = clavecol;
    }

    /**
     * @return the banda
     */
    public String getBanda() {
        return banda;
    }

    /**
     * @param banda the banda to set
     */
    public void setBanda(String banda) {
        this.banda = banda;
    }
    private int clavecol;
    private String   banda;
}
