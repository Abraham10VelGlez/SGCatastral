package edomex.gob.mx.sgcatastral.model;

import java.util.Date;
import java.util.List;


public class Tramite {
    
    private int id;    
    private String cve_cat;
    private Date fcaptura;
    private String propietario;
    private String municipio;
    private String localidad;
    private String colonia;
    private String calle;
    private String cp;
    private String zona;
    private String manzana;
    private Number idpredio;
    private String zonaorigen;
    private String codcalle;
    private String tipopredio;
    private String numext;
    private String entrecalle;
    private String ycalle;
    private String regprop;
    private String regpropiedad;
    private Number supterrtot;
    private Number frente;
    private Number fondo;
    private Number areainscr;
    private Number supaprov;
    private Number supterrcom;
    private String geo;
    private Number lat;
    private Number lon;
    private String rfc;
    private String curp;
    private String domfis;
    
    private List<DoctoTramite> documentos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCve_cat() {
        return cve_cat;
    }

    public void setCve_cat(String cve_cat) {
        this.cve_cat = cve_cat;
    }

    public Date getFcaptura() {
        return fcaptura;
    }

    public void setFcaptura(Date fcaptura) {
        this.fcaptura = fcaptura;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public Number getIdpredio() {
        return idpredio;
    }

    public void setIdpredio(Number idpredio) {
        this.idpredio = idpredio;
    }

    public String getZonaorigen() {
        return zonaorigen;
    }

    public void setZonaorigen(String zonaorigen) {
        this.zonaorigen = zonaorigen;
    }

    public String getCodcalle() {
        return codcalle;
    }

    public void setCodcalle(String codcalle) {
        this.codcalle = codcalle;
    }

    public String getTipopredio() {
        return tipopredio;
    }

    public void setTipopredio(String tipopredio) {
        this.tipopredio = tipopredio;
    }

    public String getNumext() {
        return numext;
    }

    public void setNumext(String numext) {
        this.numext = numext;
    }

    public String getEntrecalle() {
        return entrecalle;
    }

    public void setEntrecalle(String entrecalle) {
        this.entrecalle = entrecalle;
    }

    public String getYcalle() {
        return ycalle;
    }

    public void setYcalle(String ycalle) {
        this.ycalle = ycalle;
    }

    public String getRegprop() {
        return regprop;
    }

    public void setRegprop(String regprop) {
        this.regprop = regprop;
    }

    public String getRegpropiedad() {
        return regpropiedad;
    }

    public void setRegpropiedad(String regpropiedad) {
        this.regpropiedad = regpropiedad;
    }

    public Number getSupterrtot() {
        return supterrtot;
    }

    public void setSupterrtot(Number supterrtot) {
        this.supterrtot = supterrtot;
    }

    public Number getFrente() {
        return frente;
    }

    public void setFrente(Number frente) {
        this.frente = frente;
    }

    public Number getFondo() {
        return fondo;
    }

    public void setFondo(Number fondo) {
        this.fondo = fondo;
    }

    public Number getAreainscr() {
        return areainscr;
    }

    public void setAreainscr(Number areainscr) {
        this.areainscr = areainscr;
    }

    public Number getSupaprov() {
        return supaprov;
    }

    public void setSupaprov(Number supaprov) {
        this.supaprov = supaprov;
    }

    public Number getSupterrcom() {
        return supterrcom;
    }

    public void setSupterrcom(Number supterrcom) {
        this.supterrcom = supterrcom;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public Number getLat() {
        return lat;
    }

    public void setLat(Number lat) {
        this.lat = lat;
    }

    public Number getLon() {
        return lon;
    }

    public void setLon(Number lon) {
        this.lon = lon;
    }

    public List<DoctoTramite> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DoctoTramite> documentos) {
        this.documentos = documentos;
    }            

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDomfis() {
        return domfis;
    }

    public void setDomfis(String domfis) {
        this.domfis = domfis;
    }
    
    
     
}
