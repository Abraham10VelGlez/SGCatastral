/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edomex.gob.mx.sgcatastral.controllers;

import edomex.gob.mx.sgcatastral.persistencia.Capa;
import edomex.gob.mx.sgcatastral.persistencia.Estado;
import edomex.gob.mx.sgcatastral.persistencia.Localidad;
import edomex.gob.mx.sgcatastral.persistencia.Municipio;
import edomex.gob.mx.sgcatastral.helpers.MapaHelper;
import edomex.gob.mx.sgcatastral.helpers.TramitesHelper;
import edomex.gob.mx.sgcatastral.util.CProperties;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import edomex.gob.mx.sgcatastral.model.JResponse;
import edomex.gob.mx.sgcatastral.model.Usuario;
import edomex.gob.mx.sgcatastral.model.PuntosUsuario;
import edomex.gob.mx.sgcatastral.model.Recurso;
import edomex.gob.mx.sgcatastral.helpers.UsuarioHelper;
import edomex.gob.mx.sgcatastral.model.AE;
import edomex.gob.mx.sgcatastral.model.CapaArea;
import edomex.gob.mx.sgcatastral.model.CapaBusca;
import edomex.gob.mx.sgcatastral.model.CapaEspacio;
import edomex.gob.mx.sgcatastral.model.JData;
import edomex.gob.mx.sgcatastral.model.MisPuntos;
import edomex.gob.mx.sgcatastral.model.Competidor;
import edomex.gob.mx.sgcatastral.model.Descripcion;
import edomex.gob.mx.sgcatastral.model.Fuente;
import edomex.gob.mx.sgcatastral.model.Cat_mun;
import edomex.gob.mx.sgcatastral.model.DestinodelPredio;
import edomex.gob.mx.sgcatastral.model.Destinopredio;
import edomex.gob.mx.sgcatastral.model.DoctoTramite;
import edomex.gob.mx.sgcatastral.model.Grafica;
import edomex.gob.mx.sgcatastral.model.Graficam;
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
import edomex.gob.mx.sgcatastral.model.Tabular;
import edomex.gob.mx.sgcatastral.model.Usoespecifo;
import edomex.gob.mx.sgcatastral.model.Vialidadestip;
import edomex.gob.mx.sgcatastral.model.municipio2;
import edomex.gob.mx.sgcatastral.model.cat_zonas;
import edomex.gob.mx.sgcatastral.model.cat_mzna;
import edomex.gob.mx.sgcatastral.model.cat_predios;
import edomex.gob.mx.sgcatastral.model.cat_colonias;
import edomex.gob.mx.sgcatastral.model.cat_ah;
import edomex.gob.mx.sgcatastral.model.cat_ecalle;
import edomex.gob.mx.sgcatastral.services.UtilService;
import java.io.File;
import java.io.OutputStream;
import java.util.StringTokenizer;
import javax.activation.MimetypesFileTypeMap;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;

@Controller
public class MapaCtrl {

    @Autowired
    private TramitesHelper tramitesHelper;

    @RequestMapping("portal/mapa.do")
    public ModelAndView mapa(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("portal/mapa");
        JSONObject jInitMap = new JSONObject();
        jInitMap.accumulate("lat", request.getParameter("lat"));
        jInitMap.accumulate("lng", request.getParameter("lng"));
        jInitMap.accumulate("zoom", request.getParameter("zoom"));
        jInitMap.accumulate("idCapa", request.getParameter("idCapa"));
        jInitMap.accumulate("aliasMapa", request.getParameter("aliasMapa"));
        jInitMap.accumulate("filtro", request.getParameter("filtro"));

        mv.addObject("initMap", jInitMap.toString());

        PuntosUsuario puntos = new PuntosUsuario();
        mv.addObject("puntos", puntos);

        return mv;
    }

    @RequestMapping("portal/visorAtlas.do")
    public ModelAndView visorAtlas(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("portal/visorAtlas");
        JSONObject jInitMap = new JSONObject();
        jInitMap.accumulate("lat", request.getParameter("lat"));
        jInitMap.accumulate("lng", request.getParameter("lng"));
        jInitMap.accumulate("zoom", request.getParameter("zoom"));
        jInitMap.accumulate("idCapa", request.getParameter("idCapa"));
        jInitMap.accumulate("aliasMapa", request.getParameter("aliasMapa"));
        jInitMap.accumulate("filtro", request.getParameter("filtro"));

        mv.addObject("initMap", jInitMap.toString());

        PuntosUsuario puntos = new PuntosUsuario();
        mv.addObject("puntos", puntos);

        return mv;
    }

    @RequestMapping("map_sgc.do")
    public ModelAndView map_sgc(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("map_sgc");
        JSONObject jInitMap = new JSONObject();
        jInitMap.accumulate("lat", request.getParameter("lat"));
        jInitMap.accumulate("lng", request.getParameter("lng"));
        jInitMap.accumulate("zoom", request.getParameter("zoom"));
        jInitMap.accumulate("idCapa", request.getParameter("idCapa"));
        jInitMap.accumulate("aliasMapa", request.getParameter("aliasMapa"));
        jInitMap.accumulate("filtro", request.getParameter("filtro"));

        mv.addObject("initMap", jInitMap.toString());

        PuntosUsuario puntos = new PuntosUsuario();
        mv.addObject("puntos", puntos);

        List<DoctoTramite> documentos = tramitesHelper.getDoctosTramites();
        mv.addObject("documentos", documentos);

        return mv;
    }

    @RequestMapping("obtenerCapasSgc.do")
    @ResponseBody
    public String obtenerCapasSgc(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jCapas = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<CapaArea> capas = new ArrayList();

        capas = mapaHelper.getCapas();

        for (CapaArea capa : capas) {
            JSONObject jCapa = new JSONObject();
            jCapa.accumulate("id", capa.getId());
            jCapa.accumulate("capa", capa.getDescripcion());
            jCapa.accumulate("alias", capa.getWms());
            jCapa.accumulate("infownd", capa.getInfownd());
            jCapa.accumulate("idCategoria", capa.getIdcategoria());
            jCapa.accumulate("Categoria", capa.getCategoria());
            jCapas.add(jCapa);
        }

        json.accumulate("capas", jCapas);

        return json.toString();
    }

    @RequestMapping("portal/obtenerCapas.do")
    @ResponseBody
    public String obtenerCapas(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jCapas = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<CapaArea> capas = new ArrayList();

        capas = mapaHelper.getCapas();

        for (CapaArea capa : capas) {
            JSONObject jCapa = new JSONObject();
            jCapa.accumulate("id", capa.getId());
            jCapa.accumulate("capa", capa.getDescripcion());
            jCapa.accumulate("alias", capa.getWms());
            jCapa.accumulate("infownd", capa.getInfownd());
            jCapa.accumulate("idCategoria", capa.getIdcategoria());
            jCapa.accumulate("Categoria", capa.getCategoria());
            jCapas.add(jCapa);
        }

        json.accumulate("capas", jCapas);

        return json.toString();
    }

    @RequestMapping(value = "/getMapInfo.do")
    public void getMapInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        CProperties prop = new CProperties();

        try {
            StringBuilder furl = new StringBuilder()
                    .append("http://").append(prop.getProperty("GEOSERVER_IP")).append(":").append(prop.getProperty("GEOSERVER.PUERTO"))
                    .append("/").append(prop.getProperty("GEOSERVER.PATH")).append("/").append(prop.getProperty("GEOSERVER.INSTANCIA"))
                    .append("/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetFeatureInfo&SRS=EPSG%3A900913&WIDTH=256&HEIGHT=256")
                    .append("&LAYERS=").append(request.getParameter("LAYERS"))
                    .append("&bbox=").append(request.getParameter("bbox"))
                    .append("&INFO_FORMAT=").append(request.getParameter("INFO_FORMAT"))
                    .append("&FEATURE_COUNT=").append(request.getParameter("FEATURE_COUNT"))
                    .append("&QUERY_LAYERS=").append(request.getParameter("QUERY_LAYERS"))
                    .append("&x=").append(request.getParameter("x"))
                    .append("&y=").append(request.getParameter("y"))
                    .append("&buffer=").append(request.getParameter("buffer"));
            if (request.getParameter("propertyName") != null && !request.getParameter("propertyName").trim().equals("")) {
                furl.append("&propertyName=").append(request.getParameter("propertyName"));
            }
            furl.append("&cql_filter=").append(request.getParameter("cql_filter"));

            DefaultHttpClient httpclient = new DefaultHttpClient();
            try {
                httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(prop.getProperty("GEOSERVER.USER"), prop.getProperty("GEOSERVER.PWD")));

                System.out.println(furl.toString());
                HttpGet httpget = new HttpGet(furl.toString());
                HttpResponse res = httpclient.execute(httpget);
                HttpEntity entity = res.getEntity();

                if (entity != null) {
                    response.setHeader("Access-Control-Allow-Origin", "*");
                    out.println(EntityUtils.toString(entity));
                }

            } catch (Exception Ex) {
                Ex.printStackTrace();
            } finally {
                httpclient.getConnectionManager().shutdown();
            }
        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "getMap.do")
    public void obtenerMapa(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PrintWriter out = response.getWriter();
        response.setContentType("image/png");
        try {
            CProperties prop = new CProperties();
            StringBuilder furl = new StringBuilder()
                    .append("http://").append(prop.getProperty("GEOSERVER_IP")).append(":").append(prop.getProperty("GEOSERVER.PUERTO"))
                    .append("/").append(prop.getProperty("GEOSERVER.PATH")).append("/").append(prop.getProperty("GEOSERVER.INSTANCIA"))
                    .append("/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&SRS=EPSG%3A900913&WIDTH=256&HEIGHT=256&FORMAT=image%2Fpng&transparent=true&tiled=true")
                    .append("&LAYERS=").append(request.getParameter("LAYERS"))
                    .append("&bbox=").append(request.getParameter("bbox"))
                    .append("&tilesorigin=").append(request.getParameter("x")).append(",").append(request.getParameter("y"))
                    .append("&cql_filter=").append(URLEncoder.encode(request.getParameter("cql_filter")))
                    .append("&styles=").append(request.getParameter("styles"));

            DefaultHttpClient httpclient = new DefaultHttpClient();

            try {

                httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(prop.getProperty("GEOSERVER.USER"), prop.getProperty("GEOSERVER.PWD")));

                HttpGet httpget = new HttpGet(furl.toString());
                HttpResponse res = httpclient.execute(httpget);
                HttpEntity entity = res.getEntity();

                if (entity != null) {
                    response.setHeader("Access-Control-Allow-Origin", "*");
                    out.println(EntityUtils.toString(entity));
                }

            } catch (Exception Ex) {
                Ex.printStackTrace();

            } finally {
                httpclient.getConnectionManager().shutdown();
            }

        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "obtenerLeyenda.do")
    public void obtenerLeyenda(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PrintWriter out = response.getWriter();
        response.setContentType("image/png");
        String idcapa = request.getParameter("id");
        MapaHelper mapaHelper = new MapaHelper();
        try {
            Capa capa = mapaHelper.getCapaById(Short.parseShort(idcapa));
            CProperties prop = new CProperties();
            StringBuilder furl = new StringBuilder()
                    .append("http://").append(prop.getProperty("GEOSERVER_IP")).append(":").append(prop.getProperty("GEOSERVER.PUERTO"))
                    .append("/").append(prop.getProperty("GEOSERVER.PATH")).append("/").append(prop.getProperty("GEOSERVER.INSTANCIA"))
                    .append("/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetLegendGraphic&FORMAT=image%2Fpng&transparent=true")
                    .append("&LAYER=SGCatastral:").append(capa.getWms());

            DefaultHttpClient httpclient = new DefaultHttpClient();

            try {
                httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(prop.getProperty("GEOSERVER.USER"), prop.getProperty("GEOSERVER.PWD")));
                HttpGet httpget = new HttpGet(furl.toString());
                HttpResponse res = httpclient.execute(httpget);
                HttpEntity entity = res.getEntity();
                if (entity != null) {
                    response.setHeader("Access-Control-Allow-Origin", "*");
                    out.println(EntityUtils.toString(entity));
                }
            } catch (Exception Ex) {
            } finally {
                httpclient.getConnectionManager().shutdown();
            }
        } finally {
            out.close();
        }
    }

    @RequestMapping("portal/descripcion.do")
    @ResponseBody
    public String descripcion(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jDess = new JSONArray();
        String id = request.getParameter("id");
        MapaHelper mapaHelper = new MapaHelper();
        List<Descripcion> dess = new ArrayList();

        dess = mapaHelper.getDes(Short.parseShort(id));

        for (Descripcion des : dess) {
            JSONObject jDes = new JSONObject();
            jDes.accumulate("descripcion", des.getObjetivo());
            jDess.add(jDes);
        }

        json.accumulate("dess", jDess);

        return json.toString();
    }

    @RequestMapping("portal/tabular.do")
    @ResponseBody
    public String tabular(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jTabs = new JSONArray();
        String id = request.getParameter("id");
        MapaHelper mapaHelper = new MapaHelper();
        List<Tabular> tabus = new ArrayList();

        tabus = mapaHelper.getTabu(Short.parseShort(id));

        for (Tabular tabu : tabus) {
            JSONObject jTab = new JSONObject();
            jTab.accumulate("municipio", tabu.getMunicipio());
            jTab.accumulate("campo", tabu.getCampo());
            jTabs.add(jTab);
        }

        json.accumulate("tabus", jTabs);

        return json.toString();
    }

    @RequestMapping("portal/fuente.do")
    @ResponseBody
    public String fuente(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jFuen = new JSONArray();
        String id = request.getParameter("id");
        MapaHelper mapaHelper = new MapaHelper();
        List<Fuente> fuen = new ArrayList();

        fuen = mapaHelper.getFuen(Short.parseShort(id));

        for (Fuente fue : fuen) {
            JSONObject jFue = new JSONObject();
            jFue.accumulate("fuente", fue.getFuente());
            jFuen.add(jFue);
        }

        json.accumulate("fuen", jFuen);

        return json.toString();
    }

    @RequestMapping("obtenerMunicipios.do")
    @ResponseBody
    public String obtenerMunicipios(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jMpios = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<municipio2> municipios = mapaHelper.getMunicipiosByCveEstado();

        for (municipio2 municipio : municipios) {
            JSONObject jMpio = new JSONObject();
            jMpio.accumulate("clave", municipio.getCveigecem() + "|" + municipio.getLat() + "|" + municipio.getLon());
            jMpio.accumulate("clave_igecem", municipio.getCveigecem());
            jMpio.accumulate("municipio", municipio.getNom_mun());
            jMpio.accumulate("delegacion", municipio.getDelegacion());
            jMpios.add(jMpio);
        }

        json.accumulate("municipios", jMpios);

        return json.toString();
    }

    @RequestMapping("obtenerLocalidades.do")
    @ResponseBody
    public String obtenerLocalidades(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jLocalidades = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<Localidad2> localidades = mapaHelper.getLocalidadesByCveMpio(request.getParameter("cveMpio"));

        for (Localidad2 localidad : localidades) {
            JSONObject jLoc = new JSONObject();
            jLoc.accumulate("clave", localidad.getId() + "|" + localidad.getLat() + "|" + localidad.getLon());
            jLoc.accumulate("localidad", localidad.getNom_loc());
            jLoc.accumulate("cat_pol", localidad.getCat_pol());
            jLoc.accumulate("cat_admin", localidad.getCat_admin());
            jLocalidades.add(jLoc);
        }

        json.accumulate("localidades", jLocalidades);

        return json.toString();
    }

    @RequestMapping("obtener_zonas.do")
    @ResponseBody
    public String obtener_zonas(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jZonas = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<cat_zonas> zonas = mapaHelper.getZonas();

        for (cat_zonas zona : zonas) {
            JSONObject jZona = new JSONObject();
            jZona.accumulate("id", zona.getId());
            jZona.accumulate("zona", zona.getZona());

            jZonas.add(jZona);
        }

        json.accumulate("zonas", jZonas);

        return json.toString();
    }

    @RequestMapping("obtener_manzanas.do")
    @ResponseBody
    public String obtener_manzanas(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jMzna = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<cat_mzna> mznas = mapaHelper.getManzanas();

        for (cat_mzna mzna : mznas) {
            JSONObject jMznas = new JSONObject();
            jMznas.accumulate("id", mzna.getId());
            jMznas.accumulate("municipio", mzna.getMun());
            jMznas.accumulate("zona", mzna.getZona());
            jMznas.accumulate("manzana", mzna.getManz());
            jMznas.accumulate("cve_cat", mzna.getCve_cat());

            jMzna.add(jMznas);
        }

        json.accumulate("manzanas", jMzna);

        return json.toString();
    }

    @RequestMapping("obtener_predios.do")
    @ResponseBody
    public String obtener_predios(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jPredio = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<cat_predios> predios = mapaHelper.getPredios();

        for (cat_predios predio : predios) {
            JSONObject jPredios = new JSONObject();
            jPredios.accumulate("id", predio.getId());
            jPredios.accumulate("municipio", predio.getMunicipio());
            jPredios.accumulate("zona", predio.getZona());
            jPredios.accumulate("manzana", predio.getManzana());
            jPredios.accumulate("lote", predio.getLote());
            jPredios.accumulate("cve_cat", predio.getCve_cat());

            jPredio.add(jPredios);
        }

        json.accumulate("predios", jPredio);

        return json.toString();
    }

    @RequestMapping("obtener_colonias.do")
    @ResponseBody
    public String obtener_colonias(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jColonia = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<cat_colonias> colonias = mapaHelper.getColonias();

        for (cat_colonias colonia : colonias) {
            JSONObject jColonias = new JSONObject();
            jColonias.accumulate("id", colonia.getGid());
            jColonias.accumulate("colonia", colonia.getNomcol());

            jColonia.add(jColonias);
        }

        json.accumulate("colonias", jColonia);

        return json.toString();
    }

    @RequestMapping("obtener_areash.do")
    @ResponseBody
    public String obtener_areash(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jAreash = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<cat_ah> areas = mapaHelper.getAreash();

        for (cat_ah areash : areas) {
            JSONObject jAreas = new JSONObject();
            jAreas.accumulate("id", areash.getId());
            jAreas.accumulate("identificador", areash.getIdentifi());
            jAreas.accumulate("clasificacion", areash.getClasifi());

            jAreash.add(jAreas);
        }

        json.accumulate("areash", jAreash);

        return json.toString();
    }

    @RequestMapping("obtener_ecalle.do")
    @ResponseBody
    public String obtener_ecalle(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jEcalle = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<cat_ecalle> ecalles = mapaHelper.getEcalle();

        for (cat_ecalle ecalle : ecalles) {
            JSONObject jEcalles = new JSONObject();
            jEcalles.accumulate("id", ecalle.getId());
            jEcalles.accumulate("calle", ecalle.getCalles());
            jEcalles.accumulate("banda", ecalle.getBanda());

            jEcalle.add(jEcalles);
        }

        json.accumulate("ecalles", jEcalle);

        return json.toString();
    }

    @RequestMapping("obtenerEspacios.do")
    @ResponseBody
    public String obtenerEspacios(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jCapaes = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<CapaEspacio> capaes = new ArrayList();

        capaes = mapaHelper.getEspacios();

        for (CapaEspacio capae : capaes) {
            JSONObject jCapae = new JSONObject();
            jCapae.accumulate("id", capae.getId());
            jCapae.accumulate("capa", capae.getDescripcion());
            jCapae.accumulate("alias", capae.getWms());
            jCapae.accumulate("infownd", capae.getInfownd());
            jCapae.accumulate("idCategoria", capae.getIdcategoria());
            jCapae.accumulate("Categoria", capae.getCategoria());
            jCapae.accumulate("Espacio", capae.getEspacio());
            jCapaes.add(jCapae);
        }

        json.accumulate("capas", jCapaes);

        return json.toString();
    }

    @RequestMapping("portal/grafica.do")
    @ResponseBody
    public String grafica(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jGrafs = new JSONArray();
        String id = request.getParameter("id");
        MapaHelper mapaHelper = new MapaHelper();
        List<Grafica> grafs = new ArrayList();

        grafs = mapaHelper.getGraf(Short.parseShort(id));

        for (Grafica graf : grafs) {
            JSONObject jGra = new JSONObject();
            jGra.accumulate("municipio", graf.getMunicipio());
            jGra.accumulate("campo", graf.getCampo());
            jGrafs.add(jGra);
        }

        json.accumulate("grafs", jGrafs);

        return json.toString();
    }

    @RequestMapping("portal/graficam.do")
    @ResponseBody
    public String graficam(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jGrafms = new JSONArray();
        String id = request.getParameter("id");
        MapaHelper mapaHelper = new MapaHelper();
        List<Graficam> grafms = new ArrayList();

        grafms = mapaHelper.getGrafm(Short.parseShort(id));

        for (Graficam grafm : grafms) {
            JSONObject jGram = new JSONObject();
            jGram.accumulate("municipio", grafm.getMunicipio());
            jGram.accumulate("campo", grafm.getCampo());
            jGrafms.add(jGram);
        }

        json.accumulate("grafms", jGrafms);

        return json.toString();
    }

    @RequestMapping("portal/Visitas.do")
    @ResponseBody
    public JResponse Visitas(HttpServletRequest request, HttpServletResponse response) {

        JResponse jres = new JResponse();
        MapaHelper mapaHelper = new MapaHelper();
        String resultado = mapaHelper.Visitasc();

        return jres;
    }

    @RequestMapping("portal/Impvis.do")
    @ResponseBody
    public Number Impvis(HttpServletRequest request, HttpServletResponse response) {

        MapaHelper mapaHelper = new MapaHelper();
        Number resultado = mapaHelper.Impvisc();

        return resultado;
    }

    @RequestMapping("contarCapas.do")
    @ResponseBody
    public JResponse contarCapas(HttpServletRequest request, HttpServletResponse response) {

        JResponse jres = new JResponse();
        MapaHelper mapaHelper = new MapaHelper();
        String id = request.getParameter("id");
        String resultado = mapaHelper.contCapas(Short.parseShort(id));

        return jres;
    }

    @RequestMapping("portal/obtenerbus.do")
    @ResponseBody
    public String obtenerbus(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        JSONArray jCapasb = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<CapaBusca> capasb = new ArrayList();
        String nombre = request.getParameter("nombre");

        capasb = mapaHelper.getCapab(nombre);

        for (CapaBusca capab : capasb) {
            JSONObject jCapab = new JSONObject();
            jCapab.accumulate("id", capab.getId());
            jCapab.accumulate("capa", capab.getDescripcion());
            jCapab.accumulate("alias", capab.getWms());
            jCapasb.add(jCapab);
        }

        json.accumulate("capasb", jCapasb);

        return json.toString();
    }

    /*CONTROLES DE EVENTOS AVG*/
    @RequestMapping("eventopolygn.do")
    @ResponseBody
    public String insertodelprediodraw(HttpServletRequest request, HttpServletResponse response) {
        String dibupredio = request.getParameter("d");
        JSONArray jsonarray = new JSONArray();
        MapaHelper objtMaPHelp = new MapaHelper();
        List<PredioInserto> insr = new ArrayList();
        insr = objtMaPHelp.getinsertandoa(dibupredio);

        for (PredioInserto vectresul : insr) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("valorresultante", vectresul.getFuncionvalidacioncuatro());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventoclavecat.do")
    @ResponseBody
    public String Eventoclavecat(HttpServletRequest request, HttpServletResponse response) {
        //objeto nuevo
        String nn = request.getParameter("nn");
        JSONArray jsonarray = new JSONArray();
        MapaHelper mapaHelper = new MapaHelper();
        List<Procedimientopredio> camposclave = new ArrayList();

        camposclave = mapaHelper.getclavecatcampos(nn);

        for (Procedimientopredio recorridovec : camposclave) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("clavemuniciopio", recorridovec.getCvemunicipio());
            objetofor.accumulate("zona", recorridovec.getZona());
            objetofor.accumulate("manzanaa", recorridovec.getManzana());
            objetofor.accumulate("nombremun", recorridovec.getNombremunicipio());
            objetofor.accumulate("claveigecem", recorridovec.getCveigecemmmm());

            jsonarray.add(objetofor);
        }

        //jsonsave.accumulate(arreglo);
        return jsonarray.toString();

    }

    @RequestMapping("eventoclavecatlocal.do")
    @ResponseBody
    public String eventoclavecatlocalidesposibles(HttpServletRequest request, HttpServletResponse response) {
        //necesita un id del predio dibujado...
        String opp = request.getParameter("qa");
        JSONArray jsonarray = new JSONArray();
        MapaHelper objetoopciones = new MapaHelper();
        List<Localidadessopcioness> localidposibles = new ArrayList();
        localidposibles = objetoopciones.getopcioneslocalidadesradio(opp);

        for (Localidadessopcioness vectresultante : localidposibles) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("claveigecem", vectresultante.getClaveigecem());
            objetofor.accumulate("numlocaldd", vectresultante.getClaveloc());
            objetofor.accumulate("nombrelocalidad", vectresultante.getNombreloc());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();
    }

    @RequestMapping("regimenpropied.do")
    @ResponseBody
    public String eventoregimenp(HttpServletRequest request, HttpServletResponse response) {

        String numero = request.getParameter("opc");
        JSONArray jsonarray = new JSONArray();
        MapaHelper objetoclashelp = new MapaHelper();
        List<RegimenPropiedad> regmpr = new ArrayList();
        regmpr = objetoclashelp.getcamporp(numero);

        for (RegimenPropiedad vectresul : regmpr) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("descripcion", vectresul.getDescp());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("tiposviadlid.do")
    @ResponseBody
    public String eventovialidad(HttpServletRequest request, HttpServletResponse response) {
        //recuperamos variable
        String tipo_vialidad = request.getParameter("tipv");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Vialidadestip> tipsvial = new ArrayList();
        //mensionas el metodo de la clase help...
        tipsvial = objhelp.gettipoviall(tipo_vialidad);

        for (Vialidadestip vectresul : tipsvial) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("tipvialdd", vectresul.getDescr());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventoclavecatstreet.do")
    @ResponseBody
    public String eventoposiblescalles(HttpServletRequest request, HttpServletResponse response) {
        String identificadorpred = request.getParameter("qw");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Procedimientocalles> tipsvial = new ArrayList();
        //mensionas el metodo de la clase help...
        tipsvial = objhelp.getposiblecallles(identificadorpred);

        for (Procedimientocalles vectresul : tipsvial) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("callles", vectresul.getCallle());
            objetofor.accumulate("bandaa", vectresul.getBanda());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventoentrec.do")
    @ResponseBody
    public String eventoentrecklles(HttpServletRequest request, HttpServletResponse response) {
        String identificadorpred = request.getParameter("ij");
        String nmcalles = request.getParameter("oo");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Procedimientoentrcalless> calles1 = new ArrayList();
        //mensionas el metodo de la clase help...
        calles1 = objhelp.getposibleentrecallles(identificadorpred, nmcalles);

        for (Procedimientoentrcalless vectresul : calles1) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("callls", vectresul.getCallle());
            objetofor.accumulate("badaa", vectresul.getBanda());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventoyclles.do")
    @ResponseBody
    public String eventoycklles(HttpServletRequest request, HttpServletResponse response) {
        String identificadorpred = request.getParameter("ij");
        String nmcalles = request.getParameter("oo");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Procedimientoentrcalless> calles2 = new ArrayList();
        //mensionas el metodo de la clase help...
        calles2 = objhelp.getposibleykcallles(identificadorpred, nmcalles);

        for (Procedimientoentrcalless vectresul : calles2) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("callls", vectresul.getCallle());
            objetofor.accumulate("badaa", vectresul.getBanda());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventocolprd.do")
    @ResponseBody
    public String eventocalculocolonipred(HttpServletRequest request, HttpServletResponse response) {
        String indeticlave = request.getParameter("qr");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Procedimientocol> calles2 = new ArrayList();
        //mensionas el metodo de la clase help...
        calles2 = objhelp.getcalculodecolonia(indeticlave);

        for (Procedimientocol vectresul : calles2) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("clacol", vectresul.getClavecol());
            objetofor.accumulate("nomclo", vectresul.getBanda());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();
    }

    @RequestMapping("guardandodatounos.do")
    @ResponseBody
    public String eventoinsertoformulario1(HttpServletRequest request, HttpServletResponse response) {
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        String d = request.getParameter("d");
        String e = request.getParameter("e");
        String fg = request.getParameter("fg");
        String h = request.getParameter("h");
        String j = request.getParameter("j");
        String k = request.getParameter("k");
        String l = request.getParameter("l");
        String m = request.getParameter("m");
        String n = request.getParameter("n");
        String p = request.getParameter("p");
        String q = request.getParameter("q");
        String r = request.getParameter("r");
        String s = request.getParameter("s");
        String t = request.getParameter("t");
        String u = request.getParameter("u");
        String v = request.getParameter("v");
        String w = request.getParameter("w");
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String z = request.getParameter("z");
        String ax = request.getParameter("ax");
        String bx = request.getParameter("bx");
        String cx = request.getParameter("cx");
        String dx = request.getParameter("dx");
        String ex = request.getParameter("ex");
        String fx = request.getParameter("fx");

        /*variable sustenta a cambiar*/
        String fomrapredopodsa = request.getParameter("forpredio");

        MapaHelper objhelpinserto = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<PredioInsertodos> formlario1 = new ArrayList();
        //mensionas el metodo de la clase help...
        //tipsvial = objhelpinser.(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,ax,bx,cx,dx,ex);
        formlario1 = objhelpinserto.getinsertoa(a, b, c, d, e, fg, h, j, k, l, m, n, p, q, r, s, t, u, v, w, x, y, z, ax, bx, cx, dx, ex, fx, fomrapredopodsa);

        JSONArray jsonarray = new JSONArray();

        for (PredioInsertodos vectorresul : formlario1) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("mun", vectorresul.getMunicipio());
            objetofor.accumulate("zona", vectorresul.getZona());
            objetofor.accumulate("manz", vectorresul.getManzana());
            objetofor.accumulate("pre", vectorresul.getPredio());
            // objetofor.accumulate("domicil", vectorresul.getDomiciliofisc());
            objetofor.accumulate("klabprdo", vectorresul.getId_predio());
            // objetofor.accumulate("usodescripcion", vectorresul.);
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("destodelp.do")
    @ResponseBody
    public String eventodecombodestinopredio(HttpServletRequest request, HttpServletResponse response) {
        //String tipo_uso = request.getParameter("desttinoPre");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<DestinodelPredio> destinodelpredio = new ArrayList();
        //mensionas el metodo de la clase help...
        destinodelpredio = objhelp.getlistacombodesprd();

        for (DestinodelPredio vectorresul : destinodelpredio) {
            JSONObject objetofor = new JSONObject();
            //a.uso, a.descripuso, b.uso as bbuso, b.usoesp, b.descrip
            objetofor.accumulate("auso", vectorresul.getUsso());
            //objetofor.accumulate("descripcionuso", vectorresul.getDescrip());            
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();
    }

    @RequestMapping("destionopredios.do")
    @ResponseBody
    public String eventocombodestinoprediouso(HttpServletRequest request, HttpServletResponse response) {
        String tipo_uso = request.getParameter("tipdes");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Destinopredio> destinopredio = new ArrayList();
        //mensionas el metodo de la clase help...
        destinopredio = objhelp.gettiposusos(tipo_uso);

        for (Destinopredio vectorresul : destinopredio) {
            JSONObject objetofor = new JSONObject();
            //a.uso, a.descripuso, b.uso as bbuso, b.usoesp, b.descrip
            objetofor.accumulate("usot", vectorresul.getUs());
            objetofor.accumulate("descripcionn", vectorresul.getDescripus());
            objetofor.accumulate("usodt", vectorresul.getBbuso());
            objetofor.accumulate("usoespefico", vectorresul.getUsoespecifico());
            objetofor.accumulate("usodescripcion", vectorresul.getDescrippp());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();
    }

    @RequestMapping("combofinusoespfc.do")
    @ResponseBody
    public String eventocombofinusoespefico(HttpServletRequest request, HttpServletResponse response) {
        String usoespfik = request.getParameter("usooepcf");
        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objhelp = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Usoespecifo> usoespefk = new ArrayList();
        //mensionas el metodo de la clase help...
        usoespefk = objhelp.getcombofindescripuso(usoespfik);

        for (Usoespecifo vectorresul : usoespefk) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("claveuso", vectorresul.getUsso());
            objetofor.accumulate("descpusoespefico", vectorresul.getDescripuss());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();
    }

    @RequestMapping("eventosavepropiet.do")
    @ResponseBody
    public String eventoGUardaPropietario(HttpServletRequest request, HttpServletResponse response) {
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        String d = request.getParameter("d");
        String e = request.getParameter("e");
        String f = request.getParameter("f");
        String g = request.getParameter("g");
        String h = request.getParameter("h");
        String i = request.getParameter("i");
        String j = request.getParameter("j");
        String k = request.getParameter("k");
        String l = request.getParameter("l");
        String m = request.getParameter("m");
        String n = request.getParameter("n");
        String o = request.getParameter("o");
        String p = request.getParameter("p");
        String q = request.getParameter("q");
        String r = request.getParameter("r");
        String s = request.getParameter("s");
        String tu = request.getParameter("tu");
        String z = request.getParameter("z");

        //obj de jsonarreglo
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objetoayuda = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<Procedimientoinsertopropietario> insertoexute = new ArrayList();
        //mensionas el metodo de la clase help...
        insertoexute = objetoayuda.getinsertopropietarioo(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, tu, z);

        for (Procedimientoinsertopropietario vectorresul : insertoexute) {
            JSONObject objetofor = new JSONObject();
            /*objetofor.accumulate("mm",vectorresul.getMunicipio());
            objetofor.accumulate("zz", vectorresul.getZona());            
            objetofor.accumulate("maz", vectorresul.getManzana());            
            objetofor.accumulate("lt", vectorresul.getLote());            
            objetofor.accumulate("ef", vectorresul.getEdif());            
            objetofor.accumulate("ef", vectorresul.getEdif());
            objetofor.accumulate("ef", vectorresul.getEdif());
            objetofor.accumulate("ef", vectorresul.getEdif());
            objetofor.accumulate("ef", vectorresul.getEdif());*/
            objetofor.accumulate("clavecataavgstral", vectorresul.getCv_cat());
            objetofor.accumulate("nomp", vectorresul.getPmnpronom());
            objetofor.accumulate("app", vectorresul.getPmnpropat());
            objetofor.accumulate("apm", vectorresul.getPmnpromat());
            objetofor.accumulate("kurp", vectorresul.getCurp());
            objetofor.accumulate("Rfk", vectorresul.getRfc());
            objetofor.accumulate("domicil", vectorresul.getDomfis());
            objetofor.accumulate("capturafecha", vectorresul.getFcapturss());
            objetofor.accumulate("estatus", vectorresul.getEstatus());
            objetofor.accumulate("keypredio", vectorresul.getIdpredio());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();
    }

    @RequestMapping("eventofondd.do")
    @ResponseBody
    public String eventocalculofactorfondo(HttpServletRequest request, HttpServletResponse response) {
        String clavepredio = request.getParameter("rbk");
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objayuuuda = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<ProcedimientoFO> factorfondo = new ArrayList();
        //mensionas el metodo de la clase help...        
        factorfondo = objayuuuda.getresultadofo(clavepredio);

        for (ProcedimientoFO vectorresul : factorfondo) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("resultadodfo", vectorresul.getFfo());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventoarea.do")
    @ResponseBody
    public String eventocalculofactorarea(HttpServletRequest request, HttpServletResponse response) {
        String clavepredio = request.getParameter("rbks");
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objayuuuda = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<ProcedimientoFA> factorareaa = new ArrayList();
        //mensionas el metodo de la clase help...        
        factorareaa = objayuuuda.getresultadofa(clavepredio);

        for (ProcedimientoFA vectorresul : factorareaa) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("resultadodfo", vectorresul.getFa());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventovalorunseul.do")
    @ResponseBody
    public String eventocalculofactorvalunidsuelo(HttpServletRequest request, HttpServletResponse response) {
        String clavepredio = request.getParameter("ravk");
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objayuuuda = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<ProcedimientoVUS> factorvus = new ArrayList();
        //mensionas el metodo de la clase help...        
        factorvus = objayuuuda.getresultadovus(clavepredio);

        for (ProcedimientoVUS vectorresul : factorvus) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("resultadodfo", vectorresul.getVus());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventovalorrestrig.do")
    @ResponseBody
    public String eventocalculofactorirregular(HttpServletRequest request, HttpServletResponse response) {
        String clavepredio = request.getParameter("ravks");
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objayuuuda = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<ProcedimientoFI> factorfi = new ArrayList();
        //mensionas el metodo de la clase help...        
        factorfi = objayuuuda.getresultadofii(clavepredio);

        for (ProcedimientoFI vectorresul : factorfi) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("resultadodfo", vectorresul.getFi());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    @RequestMapping("eventocvtral.do")
    @ResponseBody
    public String eventocalculoCVT(HttpServletRequest request, HttpServletResponse response) {
        String clavepredio = request.getParameter("gcvtpredav");
        JSONArray jsonarray = new JSONArray();
        //objeto de clase help        
        MapaHelper objayuuuda = new MapaHelper();
        // obj de clase de la sql o procedimeinto almacenado...
        List<ProcedimientoCVT> CVVT = new ArrayList();
        //mensionas el metodo de la clase help...        
        CVVT = objayuuuda.getresultadoCCVVTT(clavepredio);

        for (ProcedimientoCVT vectorresul : CVVT) {
            JSONObject objetofor = new JSONObject();
            objetofor.accumulate("resultadoFINAL", vectorresul.getCvt());
            jsonarray.add(objetofor);
        }

        return jsonarray.toString();

    }

    /*CONTROLES DE EVENTOS AVG*/
}
