/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.model;

import java.util.Date;

/**
 *
 * @author Roberto
 */
public class PredioInsertodos {

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the zona
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }

    /**
     * @return the manzana
     */
    public String getManzana() {
        return manzana;
    }

    /**
     * @param manzana the manzana to set
     */
    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    /**
     * @return the predio
     */
    public String getPredio() {
        return predio;
    }

    /**
     * @param predio the predio to set
     */
    public void setPredio(String predio) {
        this.predio = predio;
    }

    /**
     * @return the numext
     */
    public String getNumext() {
        return numext;
    }

    /**
     * @param numext the numext to set
     */
    public void setNumext(String numext) {
        this.numext = numext;
    }  
    /**
     * @return the fcaptura
     */
    public Date getFcaptura() {
        return fcaptura;
    }

    /**
     * @param fcaptura the fcaptura to set
     */
    public void setFcaptura(Date fcaptura) {
        this.fcaptura = fcaptura;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the idusuario
     */
    public int getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    /**
     * @return the id_predio
     */
    public int getId_predio() {
        return id_predio;
    }

    /**
     * @param id_predio the id_predio to set
     */
    public void setId_predio(int id_predio) {
        this.id_predio = id_predio;
    }
      
 private String municipio  ;
 private String zona  ;
 private String manzana  ;
 private String predio  ;
 private String numext  ; 
 private Date fcaptura  ;
 private String estatus  ;
 private int idusuario  ;
 private int id_predio ;
}
