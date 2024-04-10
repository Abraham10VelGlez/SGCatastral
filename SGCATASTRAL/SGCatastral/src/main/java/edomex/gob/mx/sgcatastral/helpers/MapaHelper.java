/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.helpers;

import edomex.gob.mx.sgcatastral.model.CapaArea;
import edomex.gob.mx.sgcatastral.model.CapaBusca;
import edomex.gob.mx.sgcatastral.model.CapaEspacio;
import edomex.gob.mx.sgcatastral.model.Descripcion;
import edomex.gob.mx.sgcatastral.model.Fuente;
import edomex.gob.mx.sgcatastral.model.Grafica;
import edomex.gob.mx.sgcatastral.model.Graficam;
import edomex.gob.mx.sgcatastral.model.Tabular;
import edomex.gob.mx.sgcatastral.model.Cat_mun;
import edomex.gob.mx.sgcatastral.model.DestinodelPredio;
import edomex.gob.mx.sgcatastral.model.Destinopredio;
import edomex.gob.mx.sgcatastral.model.Localidad2;
import edomex.gob.mx.sgcatastral.model.Localidadessopcioness;
import edomex.gob.mx.sgcatastral.model.PredioInserto;
import edomex.gob.mx.sgcatastral.model.PredioInsertodos;
import edomex.gob.mx.sgcatastral.model.ProcedimientoCVT;
import edomex.gob.mx.sgcatastral.model.ProcedimientoFA;
import edomex.gob.mx.sgcatastral.model.ProcedimientoFI;
import edomex.gob.mx.sgcatastral.model.ProcedimientoFO;
import edomex.gob.mx.sgcatastral.model.ProcedimientoVUS;
import edomex.gob.mx.sgcatastral.model.Procedimientocalles;
import edomex.gob.mx.sgcatastral.model.Procedimientocol;
import edomex.gob.mx.sgcatastral.model.Procedimientoentrcalless;
import edomex.gob.mx.sgcatastral.model.Procedimientoinsertopropietario;
import edomex.gob.mx.sgcatastral.model.Procedimientopredio;
import edomex.gob.mx.sgcatastral.model.RegimenPropiedad;
import edomex.gob.mx.sgcatastral.model.Usoespecifo;
import edomex.gob.mx.sgcatastral.model.Vialidadestip;
import edomex.gob.mx.sgcatastral.model.municipio2;
import edomex.gob.mx.sgcatastral.model.cat_zonas;
import edomex.gob.mx.sgcatastral.model.cat_mzna;
import edomex.gob.mx.sgcatastral.model.cat_predios;
import edomex.gob.mx.sgcatastral.model.cat_colonias;
import edomex.gob.mx.sgcatastral.model.cat_ah;
import edomex.gob.mx.sgcatastral.model.cat_ecalle;
import edomex.gob.mx.sgcatastral.persistencia.Capa;
import edomex.gob.mx.sgcatastral.persistencia.Estado;
import edomex.gob.mx.sgcatastral.util.HibernateUtil;
import edomex.gob.mx.sgcatastral.persistencia.Localidad;
import edomex.gob.mx.sgcatastral.persistencia.Municipio;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

public class MapaHelper {

    private String campo;
    private ArrayList tabla;
    private ArrayList grafi;
    private ArrayList grafim;

    protected final Log logger = LogFactory.getLog(getClass());

    public List<CapaArea> getCapas() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<CapaArea> capas = new ArrayList();

        try {
            String sql = "Select * from obtenerCapas()";
            Query query = session.createSQLQuery(sql);

            capas = query.setResultTransformer(new AliasToBeanResultTransformer(CapaArea.class)).list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return capas;

    }

    public Capa getCapaById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Capa capa = null;
        try {
            Criteria criteria = session.createCriteria(Capa.class)
                    .add(Restrictions.eq("activo", Integer.valueOf(1)))
                    .add(Restrictions.eq("id", Integer.valueOf(id)));

            capa = (Capa) criteria.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            this.logger.error(e);
            e.printStackTrace();
        }

        return capa;
    }

    public List<Estado> getEstados() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Estado> estados = new ArrayList();
        try {
            Criteria criteria = session.createCriteria(Estado.class)
                    .addOrder(Order.asc("estado"));
            estados = criteria.list();
            tx.commit();
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return estados;
    }

    public List<Descripcion> getDes(short id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Descripcion> des = new ArrayList();

        try {
            String sql = "Select * from objetivo(?)";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, id);
            des = query.setResultTransformer(new AliasToBeanResultTransformer(Descripcion.class)).list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return des;

    }

    public List<Tabular> getTabu(short id) {
        // campo = "ph";
        tabla = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Tabular> tab = new ArrayList();

        try {
            String campo = "Select * from campo(?)";
            Query queryc = session.createSQLQuery(campo);
            queryc.setParameter(0, id);
            campo = (String) queryc.uniqueResult();
            if (campo != null) {
                //tx.commit();  
                String shape = "Select * from shape(?)";
                Query querys = session.createSQLQuery(shape);
                querys.setParameter(0, id);
                shape = (String) querys.uniqueResult();
                String sql = "Select nom_mun as municipio," + campo + " as campo from " + shape + " order by municipio";
                Query query = session.createSQLQuery(sql);
                tab = query.setResultTransformer(new AliasToBeanResultTransformer(Tabular.class)).list();
                tx.commit();
            } else {
                return tabla;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return tab;

    }

    public List<Fuente> getFuen(short id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Fuente> fue = new ArrayList();

        try {
            String sql = "Select * from fuente(?)";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, id);
            fue = query.setResultTransformer(new AliasToBeanResultTransformer(Fuente.class)).list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return fue;

    }

    public List<municipio2> getMunicipiosByCveEstado() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<municipio2> mpios = new ArrayList();
        try {
            String sql = "Select cveigecem, nom_mun, delegacion, lon, lat from public.municipios order by nom_mun";
            Query query = session.createSQLQuery(sql);

            mpios = query.setResultTransformer(new AliasToBeanResultTransformer(municipio2.class)).list();
            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return mpios;
    }

    public List<Localidad2> getLocalidadesByCveMpio(String cveMpio) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Localidad2> localidades = new ArrayList();
        try {

            String sql = "Select id, cveigecem, nom_loc, cat_pol, cat_admin, lon, lat from public.localidades order by id";
            Query query = session.createSQLQuery(sql);
            localidades = query.setResultTransformer(new AliasToBeanResultTransformer(Localidad2.class)).list();

            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return localidades;
    }

    public List<cat_zonas> getZonas() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<cat_zonas> zona = new ArrayList();
        try {
            String sql = "Select id, zona from public.zonas order by id";
            Query query = session.createSQLQuery(sql);

            zona = query.setResultTransformer(new AliasToBeanResultTransformer(cat_zonas.class)).list();
            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return zona;
    }

    public List<cat_mzna> getManzanas() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<cat_mzna> mzna = new ArrayList();
        try {
            String sql = "Select id, mun, zona, manz, cve_cat from public.manzanas order by id";
            Query query = session.createSQLQuery(sql);

            mzna = query.setResultTransformer(new AliasToBeanResultTransformer(cat_mzna.class)).list();
            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return mzna;
    }

    public List<cat_predios> getPredios() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<cat_predios> predio = new ArrayList();
        try {
            String sql = "Select id, cve_cat, municipio, zona, manzana, lote from public.predios order by id";
            Query query = session.createSQLQuery(sql);

            predio = query.setResultTransformer(new AliasToBeanResultTransformer(cat_predios.class)).list();
            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return predio;
    }

    public List<cat_colonias> getColonias() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<cat_colonias> colonias = new ArrayList();
        try {
            String sql = "Select gid, nomcol from public.colonias order by gid";
            Query query = session.createSQLQuery(sql);

            colonias = query.setResultTransformer(new AliasToBeanResultTransformer(cat_colonias.class)).list();
            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return colonias;
    }
    
     public List<cat_ah> getAreash() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<cat_ah> areas_h = new ArrayList();
        try {
            String sql = "Select id, identifi, clasifi from public.areas order by id";
            Query query = session.createSQLQuery(sql);

            areas_h = query.setResultTransformer(new AliasToBeanResultTransformer(cat_ah.class)).list();
            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return areas_h;
    }

     
     public List<cat_ecalle> getEcalle() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<cat_ecalle> ecalle = new ArrayList();
        try {
            String sql = "Select id, calles, banda from public.ejes order by id";
            Query query = session.createSQLQuery(sql);

            ecalle = query.setResultTransformer(new AliasToBeanResultTransformer(cat_ecalle.class)).list();
            tx.commit();

        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }

        return ecalle;
    }
     
    public List<CapaEspacio> getEspacios() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<CapaEspacio> capaes = new ArrayList();

        try {
            String sql = "Select * from obtenerespacios()";
            Query query = session.createSQLQuery(sql);

            capaes = query.setResultTransformer(new AliasToBeanResultTransformer(CapaEspacio.class)).list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return capaes;

    }

    public List<Grafica> getGraf(short id) {
        grafi = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Grafica> graf = new ArrayList();

        try {
            String campog = "Select * from campo(?)";
            Query querygc = session.createSQLQuery(campog);
            querygc.setParameter(0, id);
            campog = (String) querygc.uniqueResult();
            if (campog != null) {
                String shapeg = "Select * from shape(?)";
                Query querysg = session.createSQLQuery(shapeg);
                querysg.setParameter(0, id);
                shapeg = (String) querysg.uniqueResult();
                String sql = "Select nom_mun as municipio," + campog + " as campo from " + shapeg + " order by campo desc limit 10";
                Query query = session.createSQLQuery(sql);
                graf = query.setResultTransformer(new AliasToBeanResultTransformer(Grafica.class)).list();
                tx.commit();
            } else {
                return grafi;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return graf;

    }

    public List<Graficam> getGrafm(short id) {
        grafim = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<Graficam> grafm = new ArrayList();

        try {
            String campog = "Select * from campo(?)";
            Query querygc = session.createSQLQuery(campog);
            querygc.setParameter(0, id);
            campog = (String) querygc.uniqueResult();
            if (campog != null) {
                String shapeg = "Select * from shape(?)";
                Query querysg = session.createSQLQuery(shapeg);
                querysg.setParameter(0, id);
                shapeg = (String) querysg.uniqueResult();
                String sql = "Select nom_mun as municipio," + campog + " as campo from " + shapeg + " order by campo asc limit 10";
                Query query = session.createSQLQuery(sql);
                grafm = query.setResultTransformer(new AliasToBeanResultTransformer(Graficam.class)).list();
                tx.commit();
            } else {
                return grafim;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return grafm;

    }

    public String Visitasc() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String resultado = null;

        try {
            //resultado = "Select visitas()";
            resultado = "INSERT INTO visitas(fecha) VALUES (CURRENT_DATE)";
            Query query = session.createSQLQuery(resultado);
            //System.out.println(query.toString());  
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public Number Impvisc() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String resultado = null;
        Number res = 0;

        try {
            //resultado = "Select visitas()";
            resultado = "select count(*) from visitas";
            Query query = session.createSQLQuery(resultado);
            //System.out.println(query.toString());  
            //query.executeUpdate();
            res = (Number) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public String contCapas(Short id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String resultado = null;
        try {
            resultado = "UPDATE cat_capasgc SET visto=visto + 1 WHERE id_capa=" + id;
            Query query = session.createSQLQuery(resultado);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public List<CapaBusca> getCapab(String nombre) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        List<CapaBusca> capasb = new ArrayList();

        try {
            String sql = "Select id_capa as id, nom_cap as descripcion, wms from cat_capasgc where TRANSLATE(nom_cap,'ÁÉÍÓÚáéíóú','AEIOUaeiou') ilike translate('%" + nombre + "%','ÁÉÍÓÚáéíóú','AEIOUaeiou') and capa_activa=1 ORDER BY visto desc,anio desc limit 15";
            Query query = session.createSQLQuery(sql);

            capasb = query.setResultTransformer(new AliasToBeanResultTransformer(CapaBusca.class)).list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return capasb;

    }
    
    public List<RegimenPropiedad> getcamporp(String idd) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<RegimenPropiedad> objetopried = new ArrayList();
        try{
             String procedimiento1 = "select * from regimenpdd("+idd+")";
             Query query = session.createSQLQuery(procedimiento1);                                                
             objetopried = query.setResultTransformer(new AliasToBeanResultTransformer(RegimenPropiedad.class)).list();                        
            tx.commit();           
        }catch(Exception reg){
            reg.printStackTrace();
        }
        
        return objetopried;
    }
  
   public List<Vialidadestip> gettipoviall(String sspoit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Vialidadestip> objetovvial = new ArrayList();
        try{
             String procedimiento3 = "select * from tiposvialidad('"+sspoit+"')";
             Query query = session.createSQLQuery(procedimiento3);                                                
             objetovvial = query.setResultTransformer(new AliasToBeanResultTransformer(Vialidadestip.class)).list();                        
            tx.commit();           
        }catch(Exception tipvldd){
            tipvldd.printStackTrace();
        }
        
        return objetovvial;
    }

   public List<PredioInserto> getinsertandoa(String polyg){
       Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<PredioInserto> oriiginaj = new ArrayList();
        try{
             String procedimientoorigen = "select * from funcionvalidacioncuatro('MULTIPOLYGON((("+polyg+")))');";
             Query query = session.createSQLQuery(procedimientoorigen);                                                
             oriiginaj = query.setResultTransformer(new AliasToBeanResultTransformer(PredioInserto.class)).list();                        
            tx.commit();           
        }catch(Exception iii){
            iii.printStackTrace();
        }
        
        return oriiginaj;
   }

   public List<Procedimientopredio> getclavecatcampos(String numclav){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Procedimientopredio> procalvpred = new ArrayList();
        try{
             String procedimientocalculoclavecat = "select * from clavefuncion("+numclav+");";
             Query query = session.createSQLQuery(procedimientocalculoclavecat);                                                
             procalvpred = query.setResultTransformer(new AliasToBeanResultTransformer(Procedimientopredio.class)).list();                        
            tx.commit();           
        }catch(Exception iii){
            iii.printStackTrace();
        }
        
        return procalvpred;
   }
   
   public List<Localidadessopcioness> getopcioneslocalidadesradio(String val){
       Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Localidadessopcioness> tiposopceiiones = new ArrayList();
        try{
             String procedimientocalculolocalidadposibles = "select * from clacatprocedmtlocalids("+val+");";
             Query query = session.createSQLQuery(procedimientocalculolocalidadposibles);                                                
             tiposopceiiones = query.setResultTransformer(new AliasToBeanResultTransformer(Localidadessopcioness.class)).list();                        
            tx.commit();           
        }catch(Exception qq){
            qq.printStackTrace();
        }
        
        return tiposopceiiones;
   }

   public List<Procedimientocalles> getposiblecallles(String identity){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Procedimientocalles> posstreets = new ArrayList();
        try{
             String procedimientocalculocallesposibles = "select * from callessobrepredio("+identity+");";
             Query query = session.createSQLQuery(procedimientocalculocallesposibles);                                                
             posstreets = query.setResultTransformer(new AliasToBeanResultTransformer(Procedimientocalles.class)).list();                        
            tx.commit();           
        }catch(Exception c){
           c.printStackTrace();
        }
        
        return posstreets;
       
   }
   
   public List<Procedimientoentrcalless> getposibleentrecallles(String identityclv,String nomclks ){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Procedimientoentrcalless> entrrecaes = new ArrayList();
        try{
             String procedimientocalculoentrecalles = "select * from entcallepredio("+identityclv+",'"+nomclks+"');";
             Query query = session.createSQLQuery(procedimientocalculoentrecalles);                                                
             entrrecaes = query.setResultTransformer(new AliasToBeanResultTransformer(Procedimientoentrcalless.class)).list();                        
            tx.commit();           
        }catch(Exception c){
           c.printStackTrace();
        }
        
        return entrrecaes;
       
   }
   
    public List<Procedimientoentrcalless> getposibleykcallles(String identityclv,String nomclks ){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Procedimientoentrcalless> yentrrecaes = new ArrayList();
        try{
             String procedimientocalculoykcalles = "select * from entcallepredio("+identityclv+",'"+nomclks+"');";
             Query query = session.createSQLQuery(procedimientocalculoykcalles);                                                
             yentrrecaes = query.setResultTransformer(new AliasToBeanResultTransformer(Procedimientoentrcalless.class)).list();                        
            tx.commit();           
        }catch(Exception cy){
           cy.printStackTrace();
        }
        
        return yentrrecaes;
       
   }
    
    public List<Procedimientocol> getcalculodecolonia(String clveprd){
         Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Procedimientocol> calcoloonia = new ArrayList();
        try{
             String procedimientocalculocolo = "select * from coloniapredio("+clveprd+");";
             Query query = session.createSQLQuery(procedimientocalculocolo);                                                
             calcoloonia = query.setResultTransformer(new AliasToBeanResultTransformer(Procedimientocol.class)).list();                        
            tx.commit();           
        }catch(Exception coly){
           coly.printStackTrace();
        }
        
        return calcoloonia;
    }
   
   public List<PredioInsertodos> getinsertoa(String a, String b, String c, String d, String e, String fg, String h, String j, String k, String l, String m, String n, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, String z, String ax, String bx, String cx, String dx, String ex,String fx,String fomrapredopodsa){
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<PredioInsertodos> objetoinsertado = new ArrayList();
        try{
             String procedimientoinsertdevolucion = "select * from ainserto('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+fg+"','"+h+"','"+j+"','"+m+"',"+n+",'"+p+"','"+q+"','"+r+"','"+s+"',"+u+","+v+","+w+","+x+","+y+","+z+","+bx+","+cx+",'"+dx+"','"+ex+"',"+fx+",'"+fomrapredopodsa+"');";
             //"SELECT ainserto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
             Query query = session.createSQLQuery(procedimientoinsertdevolucion);
           /*  query.setParameter(0, a);
             query.setParameter(1, b);
             query.setParameter(2, c);
             query.setParameter(3, d);
             query.setParameter(4, e);
             query.setParameter(5, fg);
             query.setParameter(6, h);
             query.setParameter(7, j);
             query.setParameter(8, m);
             query.setParameter(9, n);
             query.setParameter(10, p);
             query.setParameter(11, q);
             query.setParameter(12, r);
             query.setParameter(13, s);
             query.setParameter(14, u);
             query.setParameter(15, v);
             query.setParameter(16, w);
             query.setParameter(17, x);
             query.setParameter(18, y);
             query.setParameter(19, z);
             query.setParameter(20, bx);
             query.setParameter(21, cx);
             query.setParameter(22, dx);
             query.setParameter(23, ex);
             query.setParameter(24, fx);  */
             
             //String proces0 = "select * from ainserto('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+fg+"','"+h+"','"+j+"','"+m+"',"+n+",'"+p+"','"+q+"','"+r+"','"+s+"',"+u+","+v+","+w+","+x+","+y+","+z+","+bx+","+cx+",'"+dx+"','"+ex+"',"+fx+");";
             
             
             objetoinsertado = query.setResultTransformer(new AliasToBeanResultTransformer(PredioInsertodos.class)).list();                        
             tx.commit();           
        }catch(Exception delv){
            delv.printStackTrace();            
            
        }
        
        return objetoinsertado; 
        
    }
   
    public List<DestinodelPredio> getlistacombodesprd(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<DestinodelPredio> objetodestinodelpredio = new ArrayList();
        try{
             String procedimiento7 = "select * from destinodelprediofuncion();";
             Query query = session.createSQLQuery(procedimiento7);
             objetodestinodelpredio = query.setResultTransformer(new AliasToBeanResultTransformer(DestinodelPredio.class)).list();                        
             tx.commit();           
        }catch(Exception comb){
            comb.printStackTrace();            
            
        }        
        return objetodestinodelpredio;
    }
    
    public List<Destinopredio> gettiposusos (String tipp){


        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Destinopredio> objetodeopcion = new ArrayList();
        try{
             String procedimiento5 = "select * from destinousosfuncion('"+tipp+"')";
             Query query = session.createSQLQuery(procedimiento5);
             objetodeopcion = query.setResultTransformer(new AliasToBeanResultTransformer(Destinopredio.class)).list();                        
             tx.commit();           
        }catch(Exception opcion){
            opcion.printStackTrace();            
            
        }
        
        return objetodeopcion;
    }
    
    public  List<Usoespecifo> getcombofindescripuso(String valuso){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Usoespecifo> espefikuSo = new ArrayList();
        try{
             String procedimiento8 = "select * from usoespecifinfuncion('"+valuso+"');";
             Query query = session.createSQLQuery(procedimiento8);
             espefikuSo = query.setResultTransformer(new AliasToBeanResultTransformer(Usoespecifo.class)).list();                        
             tx.commit();           
        }catch(Exception comb){
            comb.printStackTrace();            
            
        }        
        return espefikuSo;
    }
    
    public List<Procedimientoinsertopropietario> getinsertopropietarioo(String a, String b, String c, String d, String e, String f,String g, String h,String i, String j, String k, String l, String m, String n,String o, String p, String q, String r, String s, String tu, String z){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Procedimientoinsertopropietario> inserpropietario = new ArrayList();
        try{
             String procedimiento9 = "select * from ainsertodos('"+a+"', '"+b+"','"+c+"', '"+d+"', '"+e+"', '"+f+"','"+p+"', '"+r+"','"+j+"', '"+h+"', '"+i+"', '"+n+"', '"+l+"', '"+o+"','12/12/2017',"+z+",'"+tu+"','"+k+"');";
             Query query = session.createSQLQuery(procedimiento9);
             inserpropietario = query.setResultTransformer(new AliasToBeanResultTransformer(Procedimientoinsertopropietario.class)).list();                        
             tx.commit();           
        }catch(Exception insererror){
            insererror.printStackTrace();            
            
        }        
        return inserpropietario;
    }
    
    public List<ProcedimientoFO> getresultadofo(String rr){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<ProcedimientoFO> factorfonfooodo = new ArrayList();
        try{
             String procedimiento10 = "select * from ffo("+rr+");";
             Query query = session.createSQLQuery(procedimiento10);
             factorfonfooodo = query.setResultTransformer(new AliasToBeanResultTransformer(ProcedimientoFO.class)).list();                        
             tx.commit();           
        }catch(Exception ffo){
            ffo.printStackTrace();            
            
        }        
        return factorfonfooodo;
        
    }
    
    public List<ProcedimientoFA> getresultadofa(String rrr){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<ProcedimientoFA> factorAre4 = new ArrayList();
        try{
             String procedimiento11 = "select * from fa("+rrr+");";
             Query query = session.createSQLQuery(procedimiento11);
             factorAre4 = query.setResultTransformer(new AliasToBeanResultTransformer(ProcedimientoFA.class)).list();                        
             tx.commit();           
        }catch(Exception fa){
            fa.printStackTrace();            
            
        }        
        return factorAre4;
        
    }
    
     public List<ProcedimientoVUS> getresultadovus(String aaaa){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<ProcedimientoVUS> factorvuss = new ArrayList();
        try{
             String procedimiento12 = "select * from vus("+aaaa+");";
             Query query = session.createSQLQuery(procedimiento12);
             factorvuss = query.setResultTransformer(new AliasToBeanResultTransformer(ProcedimientoVUS.class)).list();                        
             tx.commit();           
        }catch(Exception vuss){
            vuss.printStackTrace();            
            
        }        
        return factorvuss;
        
    }
     
       public List<ProcedimientoFI> getresultadofii(String aavgaa){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<ProcedimientoFI> factorfig = new ArrayList();
        try{
             String procedimiento15 = "select * from fi("+aavgaa+");";
             Query query = session.createSQLQuery(procedimiento15);
             factorfig = query.setResultTransformer(new AliasToBeanResultTransformer(ProcedimientoFI.class)).list();                        
             tx.commit();           
        }catch(Exception fii){
            fii.printStackTrace();            
            
        }        
        return factorfig;
        
    }
       
       public List<ProcedimientoCVT> getresultadoCCVVTT(String CVTTA){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<ProcedimientoCVT> CVTRAL = new ArrayList();
        try{
             String procedimiento16 = "select * from cvt("+CVTTA+");";
             Query query = session.createSQLQuery(procedimiento16);
             CVTRAL = query.setResultTransformer(new AliasToBeanResultTransformer(ProcedimientoCVT.class)).list();                        
             tx.commit();           
        }catch(Exception fii){
            fii.printStackTrace();            
            
        }        
        return CVTRAL;
        
    }
    
}
