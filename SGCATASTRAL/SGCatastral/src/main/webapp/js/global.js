//Variables Globales Mapa
var initMap;
var HOST = "192.168.15.44:8080";
var ObjMap;
var opacityNS = 0.9;
/*var iniLatitud = 19.30;
var iniLongitud = -99.65;
var iniZoom = 9;*/

var iniLatitud = 19.17;
var iniLongitud = -99.48;
var iniZoom = 14;
var geocoder=null;
var infowindow=null;
var marker;
var markers = [];
var radBuff = null;
var WMS = "SGCatastral";
var WmsMap = 'http://' + HOST + '/SGCatastral/getMap.do?';
var WmsMapInfo = 'http://' + HOST + '/SGCatastral/getMapInfo.do?';
var eleProductos = [];
var weatherLayer=null;
var cloudLayer=null;
var jsonGraficas;

var usrPunto = null;
var buff_c = null;
var buff_g = null;
var buff_poligono = null;
var rad_buff;
var drawingManager;
var tipo;
var geometria;
var geometrias = [];
var vertices = [];
var verticesBuff = [];
var position;
var radius;
var buffers = [];
var lat1;
var lat2;
var earthRadiusMeters = 6367460.0;
var metersPerDegree = 2.0 * Math.PI * earthRadiusMeters / 360.0;
var radiansPerDegree = Math.PI / 180.0;
var degreesPerRadian = 180.0 / Math.PI;
var metersPerKm = 1000.0;
var meters2PerHectare = 10000.0;