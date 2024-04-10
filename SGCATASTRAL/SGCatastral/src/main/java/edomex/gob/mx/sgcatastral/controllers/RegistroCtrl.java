/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.apache.commons.codec.binary.Base64;
import edomex.gob.mx.sgcatastral.model.JData;
import edomex.gob.mx.sgcatastral.model.JResponse;
import edomex.gob.mx.sgcatastral.model.Registro;
import edomex.gob.mx.sgcatastral.model.Usuario;
import edomex.gob.mx.sgcatastral.model.PerfilUsuario;
import edomex.gob.mx.sgcatastral.helpers.UsuarioHelper;
import edomex.gob.mx.sgcatastral.services.UtilService;
import edomex.gob.mx.sgcatastral.services.EmailService;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 *
 * @author UAEM
 
@Controller
public class RegistroCtrl {
    
    @Autowired
    private UsuarioHelper usuarioHelper;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private EmailService emailService;
    
    @Secured({"ROLE_Administrador"})
    @RequestMapping({"admon/registrarUsuario.do"})
    public ModelAndView registro(HttpServletRequest request, HttpServletResponse response) {        
        ModelAndView mv = new ModelAndView("admon/registro"); 
        Registro registro = new Registro(); 
        mv.addObject("registro", registro);
        return mv;
    }
    
    @Secured({"ROLE_Administrador"})
    @RequestMapping(value={"admon/addRegistro.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public JResponse addRegistro(HttpServletRequest request, HttpServletResponse response, @RequestBody Registro registro)throws Exception {
        //Variable donde se guardará la respuesta del registro
        JResponse jres = new JResponse();
        //Verifica la existencia del usuario cuyo correo es ingresado
        Usuario usuario = this.usuarioHelper.getUsuarioByLogin(registro.getEmail());        
        if (usuario != null) {
            jres.setCodigo("500");
            jres.setMensaje(this.context.getMessage("Usuario.existe", null, null));
            return jres;
        }
        
        String pwd = registro.getPwd();
        registro.setPwd(UtilService.getMD5(registro.getPwd()));                    
        String resultado = this.usuarioHelper.saveUsuario(registro);
        
        try {
            if (!resultado.contains("Error")){                
                
                //usuario = this.usuarioHelper.getUsuarioByLogin(registro.getEmail());
                String data = usuario.getFecha().getTime() + "|" + registro.getEmail();
                byte[] bytesEncoded = Base64.encodeBase64(data.getBytes());
                System.out.println("url activacion: " + this.context.getMessage("URLBASE.activacion", null, null) + new String(bytesEncoded));
        
                ModelMap content = new ModelMap();
                content.put("nombre", registro.getNombreCompleto());
                content.put("login", registro.getEmail());
                content.put("pwd", pwd);
                content.put("email", this.context.getMessage("Email.Contacto", null, null));
                content.put("url", this.context.getMessage("URLBASE.activacion", null, null) + new String(bytesEncoded));
                try {
                    this.emailService.sendEmailFromTemplate(registro.getEmail(), this.context
                    .getMessage("Email.Contacto", null, null), this.context
                    .getMessage("Email.AsuntoBienvenida", null, null), content, this.context            
                    .getMessage("Email.TemplateBienvenida", null, null));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    jres.setCodigo("200");
                    String msgcorreo = this.context.getMessage("Email.Contacto", null, null);
                    Object[] params = { msgcorreo };
                    jres.setMensaje(resultado + ".<br>" + this.context.getMessage("Usuario.MsgCorreoErrorMail", params, null));
                    return jres;
                }
                jres.setCodigo("200");
                String msgcorreo = this.context.getMessage("Email.Contacto", null, null);
                Object[] params = { msgcorreo };
                jres.setMensaje(resultado + ".<br>" + this.context.getMessage("Usuario.MsgCorreo", params, null));
                
                
                //jres.setCodigo("200");
                //jres.setMensaje(resultado);
                
                
            } else {
                jres.setCodigo("500");
                jres.setMensaje(resultado);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            jres.setCodigo("500");
            jres.setMensaje("Error al registrar al usuario, vuelva a intentarlo por favor");
        }
        
        return jres;
    }
    
    
    
    @RequestMapping({"activacionCuenta.do"})
    public ModelAndView activacionCuenta(HttpServletRequest request, HttpServletResponse response) {
        
        ModelAndView mv = new ModelAndView("portal/activacion");
        String msgResponse = null;
        String data = request.getParameter("data");
        byte[] valueDecoded = Base64.decodeBase64(data.getBytes());
        data = new String(valueDecoded);
        System.out.println("Data: " + data);
        String[] urlparam = data.split("\\|");
        String login = urlparam[1];
        long loginTime = Long.parseLong(urlparam[0]);
        //Usuario usr = this.usuarioHelper.getUsuarioByLogin(login);  
        if (usr == null) {
            String[] param = { login, this.context.getMessage("Email.Contacto", null, null) };
            msgResponse = this.context.getMessage("Usuario.MsgActivacionError", param, null);
        } else if ((usr != null) && (usr.getActivo() == 2)) {
            String[] param = { usr.getNombreCompleto(), this.context.getMessage("Email.Contacto", null, null) };
            msgResponse = this.context.getMessage("Usuario.MsgActivacionBaja", param, null);
        } else if ((usr != null) && (usr.getActivo() == 1)) {
            mv = new ModelAndView("login");
        } else if ((usr != null) && (usr.getActivo() == 0) && (usr.getFecha().getTime() == loginTime)) {
            if (this.usuarioHelper.activaUsuarioByLogin(login)) {
                String[] param = { usr.getNombreCompleto() };
                msgResponse = this.context.getMessage("Usuario.MsgActivacionExito", param, null);
            } else {
                String[] param = { usr.getNombreCompleto(), this.context.getMessage("Email.Contacto", null, null) };
                msgResponse = this.context.getMessage("Usuario.MsgActivacionError", param, null);
            }
        } else if ((usr != null) && (usr.getActivo() == 0) && (usr.getFecha().getTime() != loginTime)) {
            String[] param = { usr.getNombreCompleto(), this.context.getMessage("Email.Contacto", null, null) };
            msgResponse = this.context.getMessage("Usuario.MsgActivacionError", param, null);
        }
    
        mv.addObject("mensaje", msgResponse);
        return mv;
        
    }
    
    
    
    @Secured({"ROLE_Administrador"})
    @RequestMapping({"admon/usuarios.do"})
    public ModelAndView usuarios(HttpServletRequest request, HttpServletResponse response) {
        
        ModelAndView mv = new ModelAndView("admon/usuarios");
        return mv;
        
    }
    
    @Secured({"ROLE_Administrador"})
    @RequestMapping({"admon/lstUsuarios.do"})
    @ResponseBody
    public JData obtenerUsuarios(HttpServletRequest request, HttpServletResponse response) {
           
        List<PerfilUsuario> lstUsuarios = this.usuarioHelper.getPerfilUsuarios();
        JData jdata = new JData();
        jdata.setData(lstUsuarios.toArray());
        return jdata;
        
    }        
    
    @Secured({"ROLE_Administrador"})
    @RequestMapping(value={"admon/usUsuario.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public JResponse updateEstatusUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
        JResponse jres = new JResponse();
 
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = this.usuarioHelper.getUsuarioByLogin(auth.getName());            
            
            String idUsuario = request.getParameter("idUsuario");
            String idAccion = request.getParameter("idAccion");
            long idEstatus = idAccion.equals("1") ? 1L : 2L;            
                                
            boolean flg = this.usuarioHelper.updateStatusUsuario(Long.parseLong(idUsuario), idEstatus);
    
            if (flg) {
                jres.setCodigo("200");
            } else {
                jres.setCodigo("500");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            jres.setCodigo("500");
        }
        
        return jres;
        
    }
    
    @Secured({"ROLE_Usuario", "ROLE_Administrador"})
    @RequestMapping({"portal/perfil.do"})
    public ModelAndView perfilUsuario(HttpServletRequest request, HttpServletResponse response) {
    
        ModelAndView mv = new ModelAndView("portal/perfil");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PerfilUsuario miperfil = this.usuarioHelper.getPerfilUsuarioByLogin(auth.getName());
        //Con éste método se agrega el resultado de la consulta a un path asociado a un formulario
        mv.addObject("perfil", miperfil);  
        
        return mv;
        
    }
    //Recuperar contraseña            
    @RequestMapping(value={"recuperarContrasenia.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody    
    public JResponse recuperarContrasenia(HttpServletRequest request, HttpServletResponse response) throws Exception {
            
        JResponse jres = new JResponse();        
        String usr = request.getParameter("j_username");
        String pwd = UtilService.getRandomPassword(6);                   
        String resultado = this.usuarioHelper.setPasswordUsuarioByLogin(usr, UtilService.getMD5(pwd));                                
        
        if (!resultado.contains("Error")){
            try {                                
                ModelMap content = new ModelMap();
                content.put("usuario", usr);
                content.put("pwd", pwd);
                this.emailService.sendEmailFromTemplate(usr, this.context
                .getMessage("Email.Contacto", null, null), this.context
                .getMessage("Email.AsuntoRecPwd", null, null), content, this.context                                    
                .getMessage("Email.TemplateRecPwd", null, null));
                        
                jres.setCodigo("200");
                String msgcorreo = usr;
                Object[] params = { msgcorreo };
                jres.setMensaje(this.context.getMessage("Usuario.MsgPwdRecuperada", params, null) + " " + this.context.getMessage("Email.Contacto", null, null));
            } catch (Exception ex) {
                ex.printStackTrace();
                jres.setCodigo("500");
                jres.setMensaje("Error al enviar la información, vuelva a intentarlo por favor");
            }
            
        } else {
            jres.setCodigo("500");
            jres.setMensaje("El usuario no está registrado o se encuentra desactivado");
        }
        
        return jres;
        
    }
    
    @Secured({"ROLE_Administrador"})
    @RequestMapping({"admon/perfil.do"})
    public ModelAndView perfilAdmon(HttpServletRequest request, HttpServletResponse response) {
        
        ModelAndView mv = new ModelAndView("portal/perfil");  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PerfilUsuario miperfil = this.usuarioHelper.getPerfilUsuarioByLogin(auth.getName());
        mv.addObject("perfil", miperfil);

        return mv;
        
    }
    
    @Secured({"ROLE_Usuario", "ROLE_Administrador"})
    @RequestMapping(value={"portal/editPerfil.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public JResponse editPerfil(HttpServletRequest request, HttpServletResponse response, @RequestBody PerfilUsuario perfil) throws Exception {
    
        JResponse jres = new JResponse();
        
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = this.usuarioHelper.getUsuarioByLogin(auth.getName());            
            if (usuario.getId().longValue() != perfil.getId().longValue()) {   
                jres.setCodigo("500");
                jres.setMensaje("El usuario no es válido");
                
                return jres;
            }
    
            String pwd = perfil.getPwd().trim();
            
            if ((pwd != null) && (!pwd.trim().equals(""))) {
                perfil.setPwd(UtilService.getMD5(pwd));
            } else {
                perfil.setPwd(null);
            }
    
            String resultado = this.usuarioHelper.updateUsuario(perfil);
            
            if (!resultado.contains("Error")) {
                
                if (perfil.getPwd() != null) {
                    
                    try {
                        ModelMap content = new ModelMap();
                        content.put("usuario", usuario.getUsuario());
                        content.put("pwd", pwd);
                        this.emailService.sendEmailFromTemplate(usuario.getUsuario(), this.context
                        .getMessage("Email.Contacto", null, null), this.context
                        .getMessage("Email.AsuntoActDatos", null, null), content, this.context                                    
                        .getMessage("Email.TemplateActDatos", null, null));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
  
                jres.setCodigo("200");
                jres.setMensaje("La información se ha actualizado.");
            } else {
                jres.setCodigo("500");
                jres.setMensaje(resultado);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            jres.setCodigo("500");
            jres.setMensaje("Error al actualizar la información, vuelva a intentarlo por favor");
        }

            return jres;
            
    }
    
    @Secured({"ROLE_Administrador"})
    @RequestMapping(value={"admon/editPerfil.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public JResponse editPerfilAdmon(HttpServletRequest request, HttpServletResponse response, @RequestBody PerfilUsuario perfil) throws Exception {
    
        JResponse jres = new JResponse();
        
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = this.usuarioHelper.getUsuarioByLogin(auth.getName());            
            if (usuario.getId().longValue() != perfil.getId().longValue()) {   
                jres.setCodigo("500");
                jres.setMensaje("El usuario no es válido");
                
                return jres;
            }
    
            String pwd = perfil.getPwd().trim();
            
            if ((pwd != null) && (!pwd.trim().equals(""))) {
                perfil.setPwd(UtilService.getMD5(pwd));
            } else {
                perfil.setPwd(null);
            }
    
            String resultado = this.usuarioHelper.updateUsuario(perfil);
            
            if (!resultado.contains("Error")) {
                
                if (perfil.getPwd() != null) {
                    
                    try {
                        ModelMap content = new ModelMap();
                        content.put("usuario", usuario.getUsuario());
                        content.put("pwd", pwd);
                        this.emailService.sendEmailFromTemplate(usuario.getUsuario(), this.context
                        .getMessage("Email.Contacto", null, null), this.context
                        .getMessage("Email.AsuntoActDatos", null, null), content, this.context                                    
                        .getMessage("Email.TemplateActDatos", null, null));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
  
                jres.setCodigo("200");
                jres.setMensaje("La información se ha actualizado.");
            } else {
                jres.setCodigo("500");
                jres.setMensaje(resultado);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            jres.setCodigo("500");
            jres.setMensaje("Error al actualizar la información, vuelva a intentarlo por favor");
        }

            return jres;
            
    }

}
    */

