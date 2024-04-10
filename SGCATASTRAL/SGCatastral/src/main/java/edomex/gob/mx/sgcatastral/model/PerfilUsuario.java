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
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilUsuario {
    
    private Number id;
    private Number idDatos;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String telefono;    
    private String pwd;
    private Number idPerfil;
    private String perfil;
    private Number idEstatus;
    private String situacion;
    private String login;
    private String nombreCompleto;
    private Date fecha;
    private String claveSituacion;    
    private String reset;
    private String submit;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getIdDatos() {
        return idDatos;
    }

    public void setIdDatos(Number idDatos) {
        this.idDatos = idDatos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Number getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Number idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Number getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Number idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apaterno;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFecha() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(this.fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getClaveSituacion() {
        return this.id + "-" + this.idEstatus;
    }

    public void setClaveSituacion(String claveSituacion) {
        this.claveSituacion = claveSituacion;
    }    

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }
    
    
    
}
