/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.helpers;
/**
 *
 * @author UAEM
 */
import edomex.gob.mx.sgcatastral.model.AE;
import edomex.gob.mx.sgcatastral.model.Usuario;
import edomex.gob.mx.sgcatastral.model.Registro;
import edomex.gob.mx.sgcatastral.model.PerfilUsuario;
import edomex.gob.mx.sgcatastral.model.PuntosUsuario;
import edomex.gob.mx.sgcatastral.model.MisPuntos;
import edomex.gob.mx.sgcatastral.model.Recurso;
import edomex.gob.mx.sgcatastral.model.Competidor;
import edomex.gob.mx.sgcatastral.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;

public class UsuarioHelper {
    /*
    public Usuario getUsuarioByLogin(String usr) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Usuario usuario = null;
        try {
            String sql = "Select * from dbo.getuser(?)";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, usr);
            usuario = (Usuario)query.setResultTransformer(new AliasToBeanResultTransformer(Usuario.class)).uniqueResult();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return usuario;
        
    }
    */
    public String saveUsuario(Registro registro) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String resultado = null;
        
        try {
            String sql = "EXEC saveUsuario @login=:login, @pwd=:pwd, @nombre=:nombre, @apaterno=:apaterno, @amaterno=:amaterno, @telefono=:telefono, @idperfil=:idperfil, @activo=:activo";
            Query query = session.createSQLQuery(sql);
            query.setParameter("login", registro.getEmail());
            query.setParameter("pwd", registro.getPwd());
            query.setParameter("nombre", registro.getNombre());
            query.setParameter("apaterno", registro.getApaterno());
            query.setParameter("amaterno", registro.getAmaterno());
            query.setParameter("telefono", registro.getTelefono());            
            query.setParameter("idperfil", Integer.valueOf(2));
            query.setParameter("activo", Integer.valueOf(0));
    
            System.out.println(query.toString());            
            resultado = (String)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar usuario, intente más tarde";
        }

        return resultado;
        
    }
    
    public boolean activaUsuarioByLogin(String usr) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "EXEC activateUsuario @login=:login";
            Query query = session.createSQLQuery(sql);
            query.setParameter("login", usr);
            List<Object> lst = query.list();
            tx.commit();
            return !lst.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
        
    }
    
    public PerfilUsuario getPerfilUsuarioByLogin(String usr) {
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        PerfilUsuario usuario = null;
            try {
                String sql = "EXEC getPerfilUsuario @login=:login";
                Query query = session.createSQLQuery(sql);
                query.setParameter("login", usr);
                usuario = (PerfilUsuario)query.setResultTransformer(new AliasToBeanResultTransformer(PerfilUsuario.class)).uniqueResult();
                tx.commit();                
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        return usuario;
            
    }            
    
    public String setPasswordUsuarioByLogin(String usr, String pwd) {
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String resultado = null;
            try {
                String sql = "EXEC updatePassword @login=:login, @pwd=:pwd ";
                Query query = session.createSQLQuery(sql);
                query.setParameter("login", usr);
                query.setParameter("pwd", pwd);
                
                System.out.println(query.toString());
                resultado = (String)query.uniqueResult();
                tx.commit();                
            } catch (Exception e) {
                e.printStackTrace();
                return "Error al generar contraseña, intente más tarde";
            }

        return resultado;
            
    }
    
    public List<PerfilUsuario> getPerfilUsuarios() {
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<PerfilUsuario> lstPerfiles = new ArrayList();     
        
        try {
            String sql = "EXEC getPerfilesUsuario";
            Query query = session.createSQLQuery(sql);
            lstPerfiles = query.setResultTransformer(new AliasToBeanResultTransformer(PerfilUsuario.class)).list();
            tx.commit();            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lstPerfiles;

    }
    
    public String updateUsuario(PerfilUsuario perfil) {
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        String resultado = null;
        
        try {
            String sql = "EXEC updateUsuario @id=:id, @pwd=:pwd, @nombre=:nombre, @apaterno=:apaterno, @amaterno=:amaterno, @telefono=:telefono";
            Query query = session.createSQLQuery(sql);
            query.setParameter("id", perfil.getId());
            query.setParameter("pwd", perfil.getPwd());
            query.setParameter("nombre", perfil.getNombre());
            query.setParameter("apaterno", perfil.getApaterno());
            query.setParameter("amaterno", perfil.getAmaterno());
            query.setParameter("telefono", perfil.getTelefono());                        
            
            System.out.println(query.toString());
            resultado = (String)query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar usuario, intente más tarde";
        }

        return resultado;
        
    }
    
    public boolean updateStatusUsuario(long idUsuario, long idEstatus) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
     
        try {
            String sql = "EXEC updateStatusUsuario @idUsr=:idUsr, @idEstatus=:idEstatus";
            Query query = session.createSQLQuery(sql);
            query.setParameter("idUsr", Long.valueOf(idUsuario));
            query.setParameter("idEstatus", Long.valueOf(idEstatus)); 
            List<Object> res = query.list();
            tx.commit();    
            return !res.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
        
    }    
    
    public String savePuntos(PuntosUsuario puntos, List<Recurso> recursos) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        StringBuilder resultado = new StringBuilder();
        
        try {   
            
            for (Recurso recurso : recursos) {
                
                if (recurso.getCarga().contains("error")) {
                    resultado.append("Error al guardar la imagen:").append(recurso.getTitulo());
                } else {
                    try {
                        String sql = "EXEC savePuntos @idUsr=:idUsr, @x=:x, @y=:y, @desc_corta=:desc_corta, @desc_larga=:desc_larga, @titulo=:titulo, @contenido=:contenido, @idTipoRecurso=:idTipoRecurso, @fecha=:fecha";
                        Query query = session.createSQLQuery(sql);
                        query.setParameter("idUsr", puntos.getIdUsr());
                        query.setParameter("x", puntos.getPlng());            
                        query.setParameter("y", puntos.getPlat());
                        query.setParameter("desc_corta", puntos.getDesc_corta());
                        query.setParameter("desc_larga", puntos.getDesc_larga());
                        query.setParameter("titulo", recurso.getTitulo());
                        query.setParameter("contenido", recurso.getContenido());
                        query.setParameter("idTipoRecurso", recurso.getIdTipoRecurso());
                        query.setParameter("fecha", new Date());

                        System.out.println(query.toString());            
                        String res = (String)query.uniqueResult();
                        if (res.contains("Error")) {
                            resultado.append("El punto ").append(res).append(", imagen: ").append(recurso.getTitulo());
                        } else {
                            resultado.append("El punto con el icono ").append(recurso.getTitulo()).append(" fue guardado");
                        }                                                
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        resultado.append("Error al guardar el punto con el icono").append(recurso.getTitulo());
                    } 
                }                                
            }
            
            tx.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            resultado.append("<br>* Error al guardar el punto");
        }

        return resultado.toString();
        
    }
        
    public List<MisPuntos> getPuntosUsuario(Number idUsr) {
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<MisPuntos> lstMisPuntos = new ArrayList();     
        
        try {
            String sql = "EXEC getPuntosUsuario @idUsr=:idUsr";
            Query query = session.createSQLQuery(sql);
            query.setParameter("idUsr", idUsr);
            lstMisPuntos = query.setResultTransformer(new AliasToBeanResultTransformer(MisPuntos.class)).list();
            tx.commit();            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lstMisPuntos;

    }
    
    public PuntosUsuario getPuntoById(long idPunto, long idUsr) {
    
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        PuntosUsuario puntos = null;
        
        try {
        
            String sql = "EXEC getPuntoUsuario @idPunto=:idPunto, @idUsr=:idUsr";
            Query query = session.createSQLQuery(sql);
            query.setParameter("idPunto", Long.valueOf(idPunto));
            query.setParameter("idUsr", Long.valueOf(idUsr));
                        
            puntos = (PuntosUsuario)query.setResultTransformer(new AliasToBeanResultTransformer(PuntosUsuario.class)).uniqueResult();
            tx.commit();                 
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return puntos;
        
    }             
    
    public String updatePuntosImagen(PuntosUsuario puntos, List<Recurso> recursos) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        StringBuilder resultado = new StringBuilder();
        
        try {   
            
            for (Recurso recurso : recursos) {
                
                if (recurso.getCarga().contains("error")) {
                    resultado.append("Error al guardar la imagen:").append(recurso.getTitulo());
                } else {
                    try {
                        String sql = "EXEC updatePuntosImagen @id=:id, @idUsr=:idUsr, @x=:x, @y=:y, @desc_corta=:desc_corta, @desc_larga=:desc_larga, @titulo=:titulo, @contenido=:contenido, @idTipoRecurso=:idTipoRecurso, @fecha=:fecha";
                        Query query = session.createSQLQuery(sql);
                        query.setParameter("id", puntos.getId());
                        query.setParameter("idUsr", puntos.getIdUsr());
                        query.setParameter("x", puntos.getPlng());            
                        query.setParameter("y", puntos.getPlat());
                        query.setParameter("desc_corta", puntos.getDesc_corta());
                        query.setParameter("desc_larga", puntos.getDesc_larga());
                        query.setParameter("titulo", recurso.getTitulo());
                        query.setParameter("contenido", recurso.getContenido());
                        query.setParameter("idTipoRecurso", recurso.getIdTipoRecurso());
                        query.setParameter("fecha", new Date());

                        System.out.println(query.toString());            
                        String res = (String)query.uniqueResult();
                        if (res.contains("Error")) {
                            resultado.append("El punto ").append(res).append(", imagen: ").append(recurso.getTitulo());
                        } else {
                            resultado.append("El punto con el icono ").append(recurso.getTitulo()).append(" fue guardado");
                        }                                                
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        resultado.append("Error al guardar el punto con el icono").append(recurso.getTitulo());
                    } 
                }                                
            }
            
            tx.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            resultado.append("<br>* Error al guardar el punto");
        }

        return resultado.toString();
        
    }
    
    public String updatePuntos(PuntosUsuario puntos) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        StringBuilder resultado = new StringBuilder();
        
        try {   
            try {
                String sql = "EXEC updatePuntos @id=:id, @idUsr=:idUsr, @x=:x, @y=:y, @desc_corta=:desc_corta, @desc_larga=:desc_larga, @fecha=:fecha";
                Query query = session.createSQLQuery(sql);
                query.setParameter("id", puntos.getId());
                query.setParameter("idUsr", puntos.getIdUsr());
                query.setParameter("x", puntos.getPlng());            
                query.setParameter("y", puntos.getPlat());
                query.setParameter("desc_corta", puntos.getDesc_corta());
                query.setParameter("desc_larga", puntos.getDesc_larga());                                
                query.setParameter("fecha", new Date());

                System.out.println(query.toString());            
                String res = (String)query.uniqueResult();
                if (res.contains("Error")) {
                    resultado.append("No fue posible guardar loc cambios");
                } else {
                    resultado.append("El punto fue actualizado");
                }                                                
            } catch (Exception ex) {
                ex.printStackTrace();
                resultado.append("Error al actualizar el punto");
            }                        
            
            tx.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            resultado.append("<br>* Error al actualizar el punto");
        }

        return resultado.toString();
        
    }
    
    public boolean deletePuntoUsuario(long idUsuario, long idPunto) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
     
        try {
            String sql = "EXEC deletePunto @idUsr=:idUsr, @idPunto=:idPunto";
            Query query = session.createSQLQuery(sql);
            query.setParameter("idUsr", Long.valueOf(idUsuario));
            query.setParameter("idPunto", Long.valueOf(idPunto)); 
            List<Object> res = query.list();
            tx.commit();    
            return res.size() == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
        
    }                       
    
    
    /*Extractor de capas*/    
    //AE
    public List<AE> getExtractedAE(String shape, float buff_distance, int tipo_geom) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<AE> lstAE = new ArrayList();                                
        
        try {                        
            String sql = "EXEC extraer_ae @geom=:geom, @buff_distance=:buff_distance, @tipo=:tipo";            
            Query query = session.createSQLQuery(sql);
                        
            query.setParameter("geom", shape);
            query.setParameter("buff_distance", buff_distance);
            query.setParameter("tipo", tipo_geom);                
            
            lstAE = query.setResultTransformer(new AliasToBeanResultTransformer(AE.class)).list();                        
            tx.commit();           
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lstAE;
        
    }    

    
    //Las Alitas
    public List<Competidor> getExtractedAlitas(String shape, float buff_distance, int tipo_geom) {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        List<Competidor> lstAlitas = new ArrayList();                                
        
        try {                        
            String sql = "EXEC extraer_alitas @geom=:geom, @buff_distance=:buff_distance, @tipo=:tipo";            
            Query query = session.createSQLQuery(sql);
                        
            query.setParameter("geom", shape);
            query.setParameter("buff_distance", buff_distance);
            query.setParameter("tipo", tipo_geom);                
            
            lstAlitas = query.setResultTransformer(new AliasToBeanResultTransformer(Competidor.class)).list();                        
            tx.commit();           
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return lstAlitas;
        
    }    
    
}
