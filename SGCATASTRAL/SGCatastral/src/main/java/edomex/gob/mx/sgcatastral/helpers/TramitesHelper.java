package edomex.gob.mx.sgcatastral.helpers;

import edomex.gob.mx.sgcatastral.model.DoctoTramite;
import edomex.gob.mx.sgcatastral.model.Tramite;
import edomex.gob.mx.sgcatastral.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;


public class TramitesHelper {
    
    
    public List<Tramite> getTramites(String finicial, String ffinal) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Tramite> tramites = new ArrayList();
        
        try {                        
            String sql = "select predios.id, predios.cve_cat,predios.fcaptura,propiedades.pmnprop as propietario from predios inner join propiedades on propiedades.cv_cat = predios.cve_cat where predios.fcaptura between ' " + finicial + "' and '" + ffinal + "'";
            
            Query query = session.createSQLQuery(sql);                                    
            
            tramites = query.setResultTransformer(new AliasToBeanResultTransformer(Tramite.class)).list();
            tx.commit();           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tramites;
        
    }
    
    
    
    public Tramite getTramite(int id) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
                
        try {                        
            String sql =    " select predios.id, predios.cve_cat,predios.fcaptura, \n" +
                            "      propiedades.pmnprop as propietario,\n" +
                            "      propiedades.curp,\n" +
                            "      propiedades.rfc,\n" +
                            "      propiedades.domfis,\n" +
                            "       predios.entcalle as entrecalle,\n" +
                            "       predios.ycalle as ycalle,\n" +
                            "       predios.calle,\n" +
                            "       predios.codpost as cp,\n" +
                            "       municipios.nom_mun as municipio,\n" +
                            "       localidades.nom_loc as localidad,\n" +
                            "       predios.zona,\n" +
                            "       predios.manzana,\n" +
                            "       predios.id_predio as idpredio,\n" +
                            "       predios.zonaorigen as zonaorigen,\n" +
                            "       predios.codcalle,\n" +
                            "       ejes.calles as calle,\n" +
                            "       predios.tipopredio as tipopredio,\n" +
                            "       predios.numext as numext,\n" +
                            "       predios.regprop as regprop,\n" +
                            "       regimenpropiedad.descripcion as regpropiedad,\n" +
                            "       predios.supterrtot,\n" +
                            "       predios.fondo,\n" +
                            "       predios.frente,\n" +
                            "       predios.supaprov,\n" +
                            "       predios.areainscr,\n" +
                            "       predios.supterrcom,\n" +
                            "       predios.colonia,\n" +
                            "       ST_AsGeoJSON(ST_TRANSFORM(predios.geom,4326)) as geo, \n" +
                            "       ST_X(ST_Centroid(ST_TRANSFORM(predios.geom,4326))) as lon, \n" +
                            "       ST_Y(ST_Centroid(ST_TRANSFORM(predios.geom,4326))) as lat \n" +
                            "   from predios\n" +
                            "       left join propiedades on propiedades.cv_cat = predios.cve_cat\n" +
                            "       left join municipios on municipios.cveigecem = predios.municipio\n" +
                            "       left join localidades on localidades.cveigecem = predios.localidad\n" +
                            "       left join ejes on ejes.id = CAST(coalesce( predios.codcalle, '0') AS integer)\n" +
                            "       left join regimenpropiedad on regimenpropiedad.id = CAST(coalesce( predios.regprop , '0') AS integer) \n" +
                            "    where predios.id = " + id;
            
            Query query = session.createSQLQuery(sql);
            Tramite tramite = (Tramite)query.setResultTransformer(new AliasToBeanResultTransformer(Tramite.class)).uniqueResult();
            tx.commit();   
            return tramite;
            
        } catch (Exception e) {
            e.printStackTrace();            
        }

        return null;        
    }
    
    
     public List<DoctoTramite> getDoctosTramites() {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<DoctoTramite> dtramites = new ArrayList();
        
        try {                        
            String sql = "select * from documentos order by id";
            
            Query query = session.createSQLQuery(sql);                                    
            
            dtramites = query.setResultTransformer(new AliasToBeanResultTransformer(DoctoTramite.class)).list();
            tx.commit();           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtramites;
        
    }
    
    
    public List<DoctoTramite> getDoctosTramites(String cve_cat) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<DoctoTramite> dtramites = new ArrayList();
        
        try {                        
            String sql = "select tramites_doctos.id, tramites_doctos.id_doc, tramites_doctos.ruta, documentos.documento\n" +
                         " from tramites_doctos\n" +
                         "      inner join documentos on documentos.id = tramites_doctos.id_doc\n" +
                         "   where cve_cat = '" + cve_cat + "' order by id";
            
            Query query = session.createSQLQuery(sql);                                    
            
            dtramites = query.setResultTransformer(new AliasToBeanResultTransformer(DoctoTramite.class)).list();
            tx.commit();           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtramites;
        
    }
    
    
    
    public DoctoTramite getDoctoTramite(int id) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {                        
            String sql = "select tramites_doctos.id, tramites_doctos.cve_cat,tramites_doctos.ruta\n" +
                         " from tramites_doctos\n" +
                         "   where id="+ id;
            
            Query query = session.createSQLQuery(sql);                                    
            
            DoctoTramite dtramite = (DoctoTramite)query.setResultTransformer(new AliasToBeanResultTransformer(DoctoTramite.class)).uniqueResult();
            tx.commit();           
            return dtramite;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
        
    }
    
    
    public DoctoTramite getDoctoTramite(int id_doc, String cve_cat) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {                        
            String sql = "select tramites_doctos.id, tramites_doctos.cve_cat,tramites_doctos.ruta\n" +
                         " from tramites_doctos\n" +
                         "   where id_doc="+ id_doc + " and cve_cat='" + cve_cat + "'";
            
            Query query = session.createSQLQuery(sql);                                    
            
            DoctoTramite dtramite = (DoctoTramite)query.setResultTransformer(new AliasToBeanResultTransformer(DoctoTramite.class)).uniqueResult();
            tx.commit();           
            return dtramite;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
        
    }
    
    
    
    
    public int saveDoctoTramite(int id_doc, String cve_cat, String documento) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        int rc = 0;
        
        try {                                    
            String sql = "insert into tramites_doctos(id_doc,cve_cat,ruta) values (" + id_doc + ",'" + cve_cat + "','" + documento + "')";            
            Query query = session.createSQLQuery(sql);                                                
            rc = query.executeUpdate();
            tx.commit();           
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rc;
        
    }
    
    public int updateDoctoTramite(int id_doc, String cve_cat, String documento) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        int rc = 0;
        
        try {                                    
            String sql = "update tramites_doctos set ruta='" + documento + "' where id_doc=" + id_doc +" and cve_cat='" + cve_cat + "'";            
            Query query = session.createSQLQuery(sql);                                                
            rc = query.executeUpdate();
            tx.commit();           
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rc;
        
    }
}
