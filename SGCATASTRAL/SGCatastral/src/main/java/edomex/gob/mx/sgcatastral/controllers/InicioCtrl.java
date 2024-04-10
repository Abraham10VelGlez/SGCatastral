package edomex.gob.mx.sgcatastral.controllers;

import edomex.gob.mx.sgcatastral.helpers.TramitesHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import edomex.gob.mx.sgcatastral.helpers.UsuarioHelper;
import edomex.gob.mx.sgcatastral.model.DoctoTramite;
import edomex.gob.mx.sgcatastral.model.FDocumento;
import edomex.gob.mx.sgcatastral.model.Tramite;
import edomex.gob.mx.sgcatastral.util.CProperties;
import java.io.File;
import edomex.gob.mx.sgcatastral.model.Usuario;  //no esta esta con el ing 
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class InicioCtrl {
    
    @Autowired
    private UsuarioHelper usuarioHelper;

    @Autowired
    private TramitesHelper tramitesHelper; 

//Métodos del sitio Wingstop
    @RequestMapping({"login.do"})
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "login";
    }                   
    
    @RequestMapping({"loggedout.do"})
    public String Logged(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        
        return "login";
        
    }
    
    @RequestMapping({"bienvenido.do"})
    public String bienvenido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "home";
        
    }
    
    @RequestMapping({"inicio.do"})
    public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response) {
    
        ModelAndView mv = new ModelAndView("inicio");     
        return mv;
                
    }
    
    
    
    @RequestMapping({"consulta.do"})
    public ModelAndView consulta(HttpServletRequest request, HttpServletResponse response) {
    
        ModelAndView mv = new ModelAndView("consulta");
        return mv;
                
    }
        
    @RequestMapping(value="ctramites.do", method = RequestMethod.POST )
    @ResponseBody    
    public List<Tramite> tramites(HttpServletRequest request, HttpServletResponse response) throws Exception {
            
        String finicial = request.getParameter("finicial");
        String ffinal = request.getParameter("ffinal");        
        List<Tramite> tramites = new ArrayList();        
        tramites = tramitesHelper.getTramites(finicial, ffinal);
        return tramites;
        
    }
    
   
    @RequestMapping(value="tramite.do", method = RequestMethod.POST )
    @ResponseBody    
    public Tramite tramite(HttpServletRequest request, HttpServletResponse response) throws Exception {
            
        int id = Integer.parseInt(request.getParameter("id"));   
        Tramite tramite = tramitesHelper.getTramite(id);        
        List<DoctoTramite> dctos = tramitesHelper.getDoctosTramites(tramite.getCve_cat());
        tramite.setDocumentos(dctos);        
        return tramite;        
    }
    
    @RequestMapping({"password.do"})
    public ModelAndView password(HttpServletRequest request, HttpServletResponse response) {
    
        ModelAndView mv = new ModelAndView("password");     
        return mv;
                
    }
    
    @RequestMapping(value="descargaDocumento.do", method = RequestMethod.GET )
    @ResponseBody
    public void descargaDocumento(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CProperties prop = new CProperties();                
        int idDcto = Integer.parseInt(request.getParameter("id"));                
        DoctoTramite docto = tramitesHelper.getDoctoTramite(idDcto);                
        OutputStream out = response.getOutputStream();                
        File archivo= new File(prop.getProperty("DIRECTORIO_DCTOS") + "/" + docto.getCve_cat() + "/" + docto.getRuta());
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        response.setContentLengthLong(archivo.getAbsoluteFile().length());
        response.setContentType(mimeTypesMap.getContentType(archivo));
        response.setHeader( "Content-Disposition", "attachment;filename=" + archivo.getName() );
        FileUtils.copyFile(archivo, out);
    }
    
    
    @RequestMapping(value="cargarDocumento.do", method = RequestMethod.POST)
    @ResponseBody
    public String cargarDocumento(@ModelAttribute("FDocumento") FDocumento fDocumento, 
            HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        
        CommonsMultipartFile docto = fDocumento.getDocumento();
        
        if(docto == null || docto.getSize() == 0) { 
            return "Error, el archivo esta vacío.";
        } 
        
        int id_doc = Integer.parseInt(request.getParameter("id_doc"));
        String cve_cat = request.getParameter("cve_cat");
        
        DoctoTramite antDocto = tramitesHelper.getDoctoTramite(id_doc, cve_cat);
                
        Date fecha = new Date();
        CProperties prop = new CProperties();                        
        String nomArchivo = fecha.getTime() + "_" + docto.getOriginalFilename();
        String directorioBase = prop.getProperty("DIRECTORIO_DCTOS") + "/" + request.getParameter("cve_cat");
        File dirRepositorio = new File(directorioBase);
        dirRepositorio.mkdirs();       
        String rutaArchivo = dirRepositorio + "/" + nomArchivo;
        File archivo = new File(rutaArchivo);
        docto.transferTo(archivo);
        int rc=0;
        if(antDocto != null) {
            File tmp = new File(directorioBase + "/" + antDocto.getRuta());
            tmp.delete();
            rc = tramitesHelper.updateDoctoTramite(id_doc, cve_cat, nomArchivo);            
        } else {
            rc = tramitesHelper.saveDoctoTramite(id_doc, cve_cat, nomArchivo);
        }
        
        return rc > 0 ?  "El archivo se ha guardado" : "Error al guardar el archivo";
    }
    
    @RequestMapping({"header.do"})
    public ModelAndView header(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        ModelAndView mv = new ModelAndView("header");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        /*Usuario usr = this.usuarioHelper.getUsuarioByLogin(auth.getName());

        if (usr != null) {
           mv.addObject("nomUsuario", usr.getNombreCompleto());
        }
        */
        return mv;
        
    }

    @RequestMapping({"footer.do"})
    public String footer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        return "footer";
        
    }

    @RequestMapping({"acercade.do"})
    public String acercade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "acercade";
        
    }
    
     @RequestMapping({"catalogos.do"})
    public String catalogos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogos";
        
    }
    
     @RequestMapping({"catalogo_mun.do"})
    public String catalogo_mun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_mun";
        
    }
    
     @RequestMapping({"catalogo_loc.do"})
    public String catalogo_loc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_loc";
        
    }
    
      @RequestMapping({"cat_municipios.do"})
    public String cat_municipios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "cat_municipios";
        
    }
    
     
    
      @RequestMapping({"catalogo_zonas.do"})
    public String catalogo_zonas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_zonas";
        
    }
    
        @RequestMapping({"catalogo_mzna.do"})
    public String catalogo_mzna(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_mzna";
        
    }
    
      @RequestMapping({"catalogo_predio.do"})
    public String catalogo_predio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_predio";
        
    }
    
       @RequestMapping({"catalogo_col.do"})
    public String catalogo_col(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_col";
        
    }
    
      @RequestMapping({"catalogo_ah.do"})
    public String catalogo_ah(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_ah";
        
    }
    
      @RequestMapping({"catalogo_ecalle.do"})
    public String catalogo_ecalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "catalogo_ecalle";
        
    }
    
    @RequestMapping({"inc_padron_predios.do"})
    public String inc_padron_predios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "inc_padron_predios";
        
    }
    
    
    
        @RequestMapping({"marcoTeorico.do"})
    public String marcoTeorico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "marcoTeorico";
        
    }
    
            @RequestMapping({"metodologia.do"})
    public String metodologia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "metodologia";
        
    }
    
                @RequestMapping({"temas.do"})
    public String temas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return "temas";
        
    }
    
    
}
